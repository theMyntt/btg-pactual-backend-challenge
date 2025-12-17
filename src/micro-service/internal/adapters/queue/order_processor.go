package queue

import (
	"database/sql"
	"encoding/json"
	"log"

	"github.com/theMyntt/btg-pactual-backend-challenge/internal/core/entities"
)

type OrderProcessorHandler interface {
	Run(body []byte)
}

type orderProcessorHandlerDependencies struct {
	db *sql.DB
}

func NewOrderProcessorQueueHandler(db *sql.DB) OrderProcessorHandler {
	return &orderProcessorHandlerDependencies{
		db: db,
	}
}

func (o *orderProcessorHandlerDependencies) Run(body []byte) {
	var order entities.Order
	err := json.Unmarshal(body, &order)
	if err != nil {
		log.Print("Cant unmarshal JSON body")
		return
	}

}
