package ports

import "github.com/theMyntt/btg-pactual-backend-challenge/internal/core/entities"

type OrderRepositoryPort interface {
	Save(order entities.Order) error
}
