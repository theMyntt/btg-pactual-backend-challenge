using Microsoft.EntityFrameworkCore;
using OrderAnalytics.Adapters.DAO.Tables;

namespace OrderAnalytics.Adapters.DAO.Context;

public class DatabaseContext(DbContextOptions options) : DbContext(options)
{
    public DbSet<ClientTable> Clients { get; set; }
    public DbSet<OrderTable> Orders { get; set; }
    public DbSet<ProductTable> Products { get; set; }
}