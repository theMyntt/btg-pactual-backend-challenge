package usecases

import (
	"log"

	"github.com/theMyntt/btg-pactual-backend-challenge/internal/core/entities"
	"github.com/theMyntt/btg-pactual-backend-challenge/internal/core/ports"
)

type OrderProcessor interface {
	Execute(order entities.Order)
}

type orderProcessorDependencies struct {
	orderRepository ports.OrderRepositoryPort
}

func NewOrderProcessor(orderRepository ports.OrderRepositoryPort) OrderProcessor {
	return &orderProcessorDependencies{
		orderRepository: orderRepository,
	}
}

func (o *orderProcessorDependencies) Execute(order entities.Order) {
	err := o.orderRepository.Save(order)
	log.Printf("Failed to save order %d: %s", order.ID, err)
}
