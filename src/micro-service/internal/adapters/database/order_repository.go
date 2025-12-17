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
	createUserQuery       = `INSERT INGORE INTO tbl_users (id_user) VALUES (?);`
	createOrderQuery      = `INSERT INTO tbl_orders (id_order, fk_id_user) VALUES (?, ?);`
	createProductQuery    = `INSERT INTO tbl_products (tx_name, tx_price) VALUES (LOWER(?), ?);`
	createOrderItemsQuery = `INSERT INTO tbl_order_items (fk_id_order, fk_id_product) VALUES (?, ?);`

	searchIfExistsProductFromNameQuery = `SELECT id_product FROM tbl_products WHERE tx_name = LOWER(?)`
)

func (o *orderRepositoryDependencies) Save(order entities.Order) error {
	ctx := context.Background()

	tx, err := o.db.BeginTx(ctx, nil)
	if err != nil {
		return err
	}
	defer tx.Rollback()

	_, err = o.db.ExecContext(ctx, createUserQuery, order.ClientID)
	if err != nil {
		return fmt.Errorf("Failed to insert user %d: %w", order.ClientID, err)
	}

	_, err = o.db.ExecContext(ctx, createOrderQuery, order.ID, order.ClientID)
	if err != nil {
		return fmt.Errorf("Failed to insert order %d from the user %d: %w", order.ID, order.ClientID, err)
	}

	for index, product := range order.Items {
		var productId *int
		err := o.db.QueryRowContext(ctx, searchIfExistsProductFromNameQuery, product.Name).Scan(&productId)

		if productId == nil {
			result, err := o.db.ExecContext(ctx, createProductQuery, product.Name, product.Price)
			if err != nil {
				return fmt.Errorf("Failed to insert product %d from order %d: %w", index, order.ID, err)
			}
			productIdBrute, err := result.LastInsertId()
			if err != nil {
				return fmt.Errorf("Failed to get ID from product %d from order %d: %w", index, order.ID, err)
			}
			val := int(productIdBrute)
			productId = &val
		}

		_, err = o.db.ExecContext(ctx, createOrderItemsQuery, order.ID, productId)
		if err != nil {
			return fmt.Errorf("Failed to insert product %d on order %d: %w", index, order.ID, err)
		}

		if err := tx.Commit(); err != nil {
			return fmt.Errorf("Failed to commit transaction: %w", err)
		}
	}

	return nil
}
