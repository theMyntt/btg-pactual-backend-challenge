package usecases

import (
	"github.com/theMyntt/btg-pactual-backend-challenge/internal/core/entities"
	"github.com/theMyntt/btg-pactual-backend-challenge/internal/core/ports"
)

type OrderProcessor interface {
	Execute(order entities.Order) error
}

type orderProcessorDependencies struct {
	orderRepository ports.OrderRepositoryPort
}

func NewOrderProcessor(orderRepository ports.OrderRepositoryPort) OrderProcessor {
	return &orderProcessorDependencies{
		orderRepository: orderRepository,
	}
}

func (o *orderProcessorDependencies) Execute(order entities.Order) error {
	return o.orderRepository.Save(order)
}
