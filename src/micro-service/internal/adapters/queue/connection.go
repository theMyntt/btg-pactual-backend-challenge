package queue

import (
	"fmt"
	"os"

	"github.com/rabbitmq/amqp091-go"
)

func NewConnection() (amqp091.Queue, *amqp091.Channel) {
	user := os.Getenv("RABBITMQ_USER")
	password := os.Getenv("RABBITMQ_PASSWORD")
	url := os.Getenv("RABBITMQ_URL")

	connectionString := fmt.Sprintf("amqp://%s:%s@%s/", user, password, url)

	conn, err := amqp091.Dial(connectionString)
	if err != nil {
		panic(err)
	}
	defer conn.Close()

	ch, err := conn.Channel()
	if err != nil {
		panic(err)
	}
	defer ch.Close()

	queue, err := ch.QueueDeclare(
		"orders",
		false,
		false,
		false,
		false,
		nil,
	)
	if err != nil {
		panic(err)
	}
	return queue, ch
}
