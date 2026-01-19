package main

import (
	"net/http"
	"strconv"

	"github.com/gin-gonic/gin"
	"github.com/theMyntt/btg-pactual-backend-challenge/internal/adapters/database"
)

type API struct {
	repo *database.OrderRepository
}

func main() {
	db := database.NewConnection()
	repo := database.NewOrderRepository(db)
	api := &API{repo: repo}

	r := gin.Default()

	r.GET("/orders/total/:orderId", api.getTotalByOrder)
	r.GET("/customers/:customerId/orders/count", api.getOrdersCountByCustomer)
	r.GET("/customers/:customerId/orders", api.listOrdersByCustomer)

	r.Run(":8080")
}

func (api *API) getTotalByOrder(c *gin.Context) {
	orderIdStr := c.Param("orderId")
	orderId, err := strconv.Atoi(orderIdStr)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "invalid order ID"})
		return
	}

	total, err := api.repo.GetTotalByOrder(orderId)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}

	c.JSON(http.StatusOK, gin.H{"total": total})
}

func (api *API) getOrdersCountByCustomer(c *gin.Context) {
	customerIdStr := c.Param("customerId")
	customerId, err := strconv.Atoi(customerIdStr)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "invalid customer ID"})
		return
	}

	count, err := api.repo.GetOrdersCountByCustomer(customerId)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}

	c.JSON(http.StatusOK, gin.H{"count": count})
}

func (api *API) listOrdersByCustomer(c *gin.Context) {
	customerIdStr := c.Param("customerId")
	customerId, err := strconv.Atoi(customerIdStr)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "invalid customer ID"})
		return
	}

	orders, err := api.repo.ListOrdersByCustomer(customerId)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}

	c.JSON(http.StatusOK, gin.H{"orders": orders})
}