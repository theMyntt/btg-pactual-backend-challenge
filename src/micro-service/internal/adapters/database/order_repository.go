package database

import (
	"context"
	"database/sql"
	"fmt"

	"github.com/theMyntt/btg-pactual-backend-challenge/internal/core/entities"
	"github.com/theMyntt/btg-pactual-backend-challenge/internal/core/ports"
)

type orderRepositoryDependencies struct {
	db *sql.DB
}

func NewOrderRepository(db *sql.DB) ports.OrderRepositoryPort {
	return &orderRepositoryDependencies{
		db: db,
	}
}

const (
	createUserQuery       = `INSERT IGNORE INTO tbl_users (id_user) VALUES (?);`
	createOrderQuery      = `INSERT INTO tbl_orders (id_order, fk_id_user) VALUES (?, ?);`
	createProductQuery    = `INSERT INTO tbl_products (tx_name, tx_price) VALUES (LOWER(?), ?);`
	createOrderItemsQuery = `INSERT INTO tbl_order_items (fk_id_order, fk_id_product, nu_quantity) VALUES (?, ?, ?);`

	searchIfExistsProductFromNameQuery = `SELECT id_product FROM tbl_products WHERE tx_name = LOWER(?)`
)

func (o *orderRepositoryDependencies) Save(order entities.Order) error {
	ctx := context.Background()

	tx, err := o.db.BeginTx(ctx, nil)
	if err != nil {
		return err
	}
	defer tx.Rollback()

	_, err = tx.ExecContext(ctx, createUserQuery, order.ClientID)
	if err != nil {
		return fmt.Errorf("failed to insert user %d: %w", order.ClientID, err)
	}

	_, err = tx.ExecContext(ctx, createOrderQuery, order.ID, order.ClientID)
	if err != nil {
		return fmt.Errorf("failed to insert order %d from the user %d: %w", order.ID, order.ClientID, err)
	}

	for index, product := range order.Items {
		var productId int64
		err := tx.QueryRowContext(ctx, searchIfExistsProductFromNameQuery, product.Name).Scan(&productId)
		if err != nil {
			if err == sql.ErrNoRows {
				result, err := tx.ExecContext(ctx, createProductQuery, product.Name, product.Price)
				if err != nil {
					return fmt.Errorf("failed to insert product %d from order %d: %w", index, order.ID, err)
				}
				productId, err = result.LastInsertId()
				if err != nil {
					return fmt.Errorf("failed to get ID from product %d from order %d: %w", index, order.ID, err)
				}
			} else {
				return fmt.Errorf("failed to query product %s: %w", product.Name, err)
			}
		}

		_, err = tx.ExecContext(ctx, createOrderItemsQuery, order.ID, productId, product.Quantity)
		if err != nil {
			return fmt.Errorf("failed to insert product %d on order %d: %w", index, order.ID, err)
		}
	}

	if err := tx.Commit(); err != nil {
		return fmt.Errorf("failed to commit transaction: %w", err)
	}

	return nil
}
