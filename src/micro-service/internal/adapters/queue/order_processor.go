package queue

import (
	"encoding/json"
	"log"

	"github.com/theMyntt/btg-pactual-backend-challenge/internal/core/entities"
)

func OrderProcessorQueueHandler(body []byte) {
	var order entities.Order
	err := json.Unmarshal(body, &order)
	if err != nil {
		log.Print("Cant unmarshal JSON body")
		return
	}

}
