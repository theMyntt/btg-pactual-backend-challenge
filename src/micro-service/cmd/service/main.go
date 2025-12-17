package main

import (
	internalQueue "github.com/theMyntt/btg-pactual-backend-challenge/internal/adapters/queue"
)

func main() {
	queue, channel := internalQueue.NewConnection()
	messages, err := channel.Consume(queue.Name, "", true, false, false, false, nil)
	if err != nil {
		panic(err)
	}

	go func() {
		for d := range messages {
			internalQueue.OrderProcessorQueueHandler(d.Body)
		}
	}()
}
