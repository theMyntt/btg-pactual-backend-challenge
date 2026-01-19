package database

import (
	"database/sql"
	"fmt"
)

type OrderRepository struct {
	db *sql.DB
}

func NewOrderRepository(db *sql.DB) *OrderRepository {
	return &OrderRepository{
		db: db,
	}
}

func (r *OrderRepository) GetTotalByOrder(orderId int) (float64, error) {
	query := `
		SELECT SUM(oi.nu_quantity * p.tx_price) 
		FROM tbl_order_items oi
		JOIN tbl_products p ON oi.fk_id_product = p.id_product
		WHERE oi.fk_id_order = ?
	`
	var total float64
	err := r.db.QueryRow(query, orderId).Scan(&total)
	if err != nil {
		if err == sql.ErrNoRows {
			return 0, fmt.Errorf("order with ID %d not found", orderId)
		}
		return 0, err
	}
	return total, nil
}

func (r *OrderRepository) GetOrdersCountByCustomer(customerId int) (int, error) {
	query := `SELECT COUNT(id_order) FROM tbl_orders WHERE fk_id_user = ?`
	var count int
	err := r.db.QueryRow(query, customerId).Scan(&count)
	if err != nil {
		return 0, err
	}
	return count, nil
}

func (r *OrderRepository) ListOrdersByCustomer(customerId int) ([]int, error) {
	query := `SELECT id_order FROM tbl_orders WHERE fk_id_user = ?`
	rows, err := r.db.Query(query, customerId)
	if err != nil {
		return nil, err
	}
	defer rows.Close()

	var orders []int
	for rows.Next() {
		var orderId int
		if err := rows.Scan(&orderId); err != nil {
			return nil, err
		}
		orders = append(orders, orderId)
	}
	return orders, nil
}
