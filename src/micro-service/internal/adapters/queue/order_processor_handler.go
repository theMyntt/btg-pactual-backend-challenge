package queue

import (
	"encoding/json"
	"log"

	"github.com/theMyntt/btg-pactual-backend-challenge/internal/core/entities"
	"github.com/theMyntt/btg-pactual-backend-challenge/internal/core/usecases"
)

type OrderProcessorHandler interface {
	Run(body []byte)
}

type orderProcessorHandlerDependencies struct {
	usecase usecases.OrderProcessor
}

func NewOrderProcessorQueueHandler(usecase usecases.OrderProcessor) OrderProcessorHandler {
	return &orderProcessorHandlerDependencies{
		usecase: usecase,
	}
}

func (o *orderProcessorHandlerDependencies) Run(body []byte) {
	var order entities.Order
	err := json.Unmarshal(body, &order)
	if err != nil {
		log.Print("Cant unmarshal JSON body")
		return
	}

	o.usecase.Execute(order)
}
