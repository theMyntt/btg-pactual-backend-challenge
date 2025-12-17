package entities

import "github.com/shopspring/decimal"

type Order struct {
	ID       int          `json:"codigoPedido"`
	ClientID int          `json:"codigoCliente"`
	Items    []OrderItems `json:"itens"`
}

type OrderItems struct {
	Name     string          `json:"produto"`
	Quantity int             `json:"quantidade"`
	Price    decimal.Decimal `json:"preco"`
}
