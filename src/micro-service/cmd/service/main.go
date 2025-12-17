package main

import (
	"github.com/theMyntt/btg-pactual-backend-challenge/internal/adapters/database"
	internalQueue "github.com/theMyntt/btg-pactual-backend-challenge/internal/adapters/queue"
)

func main() {
	queue, channel := internalQueue.NewConnection()
	db := database.NewConnection()

	messages, err := channel.Consume(queue.Name, "", true, false, false, false, nil)
	if err != nil {
		panic(err)
	}

	orderProcessorQueue := internalQueue.NewOrderProcessorQueueHandler(db)

	go func() {
		for d := range messages {
			orderProcessorQueue.Run(d.Body)
		}
	}()
}
