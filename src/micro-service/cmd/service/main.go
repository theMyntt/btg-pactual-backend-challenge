package main

import (
	"log"

	"github.com/theMyntt/btg-pactual-backend-challenge/internal/adapters/queue"
)

func main() {
	queue, channel := queue.NewConnection()
	messages, err := channel.Consume(queue.Name, "", true, false, false, false, nil)
	if err != nil {
		panic(err)
	}

	go func() {
		for d := range messages {
			log.Printf("Mensagem recebida: %s", d.Body)
		}
	}()
}
