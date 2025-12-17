package main

import (
	"github.com/theMyntt/btg-pactual-backend-challenge/internal/adapters/database"
	internalQueue "github.com/theMyntt/btg-pactual-backend-challenge/internal/adapters/queue"
	"github.com/theMyntt/btg-pactual-backend-challenge/internal/core/usecases"
)

func main() {
	queue, channel := internalQueue.NewConnection()
	db := database.NewConnection()

	messages, err := channel.Consume(queue.Name, "", true, false, false, false, nil)
	if err != nil {
		panic(err)
	}

	orderRepository := database.NewOrderRepository(db)
	orderProcessorUsecase := usecases.NewOrderProcessor(orderRepository)
	orderProcessorQueue := internalQueue.NewOrderProcessorQueueHandler(orderProcessorUsecase)

	go func() {
		for d := range messages {
			orderProcessorQueue.Run(d.Body)
		}
	}()
}
