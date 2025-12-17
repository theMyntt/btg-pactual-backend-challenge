# BTG Pactual Backend Challenge

This is a Go microservice that processes orders from a RabbitMQ topic and saves them to a relational database. The project follows a hexagonal architecture, separating business logic from technology implementations.

## Project Structure

The project is divided into the following layers:

-   **`cmd/service`**: Contains the application's entry point (`main.go`), responsible for initializing and connecting all parts of the system.
-   **`internal/core`**: The heart of the application, containing the business logic.
    -   **`entities`**: Defines the domain data structures, such as `Order` and `OrderItems`.
    -   **`usecases`**: Implements the system's use cases, such as `OrderProcessor`.
    -   **`ports`**: Defines the interfaces (contracts) for external dependencies, such as `OrderRepositoryPort`.
-   **`internal/adapters`**: Implements the ports defined in `core`, connecting to specific technologies.
    -   **`database`**: Implementation of the order repository for a SQL database.
    -   **`queue`**: Handles the connection and consumption of messages from RabbitMQ.
-   **`db/migrations`**: Contains SQL scripts to create and update the database schema.

## Technologies and Libraries

-   **Go**: The programming language used.
-   **RabbitMQ**: The messaging system for receiving orders. The `github.com/rabbitmq/amqp091-go` library is used for integration.
-   **SQL**: The database for persisting orders.
-   **`github.com/shopspring/decimal`**: Library for precise decimal calculations, ideal for monetary values.

## How to Run

1.  **Set Up the Environment**:
    -   Have Go installed.
    -   Have a RabbitMQ instance and a SQL database (like MySQL or PostgreSQL) running.

2.  **Install Dependencies**:
    ```bash
    go mod tidy
    ```

3.  **Run Migrations**:
    Apply the scripts in `db/migrations` to your database to create the necessary tables.

### Database Migrations with `golang-migrate`

This project uses `golang-migrate/migrate` for database migrations.

#### Installation

To install the `migrate` CLI tool for MySQL, use:

```bash
go install -tags 'mysql' github.com/golang-migrate/migrate/v4/cmd/migrate@latest
```

#### Creating a New Migration

To create a new migration file (e.g., `add_products_table`), run:

```bash
migrate create -ext sql -dir db/migrations -seq add_products_table
```

This will create two files in `db/migrations`:
- `XXXXXXXX_add_products_table.up.sql`
- `XXXXXXXX_add_products_table.down.sql`

You should then fill these files with your SQL commands for applying (`up`) and rolling back (`down`) the migration.

#### Applying Migrations

To apply all pending migrations for MySQL, use:

```bash
migrate -path db/migrations -database "mysql://user:password@tcp(localhost:3306)/database_name" up
# Replace the database URL with your actual MySQL connection string.
```

#### Rolling Back Migrations

To rollback the last applied migration for MySQL, use:

```bash
migrate -path db/migrations -database "mysql://user:password@tcp(localhost:3306)/database_name" down 1
# To rollback all migrations, replace `1` with `all`.
# Replace the database URL with your actual MySQL connection string.
```


4.  **Run the Service**:
    ```bash
    go run cmd/service/main.go
    ```

## Business Logic

The service consumes messages from a RabbitMQ queue. Each message represents an order in JSON format, containing client information and a list of items.

Upon receiving an order, the `OrderProcessor` is triggered to:

1.  Start a database transaction.
2.  Insert the client, if they don't already exist.
3.  Insert the order.
4.  Iterate over the order items:
    -   Check if the product already exists in the database.
    -   If not, insert the new product.
    -   Insert the order item, associating the order with the product.
5.  Commit the transaction.

If any error occurs during the process, the transaction is rolled back, and an error log is recorded.
