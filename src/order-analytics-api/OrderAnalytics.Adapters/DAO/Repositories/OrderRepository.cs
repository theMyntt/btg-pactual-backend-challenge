using System.Numerics;
using OrderAnalytics.Adapters.DAO.Context;
using OrderAnalytics.Adapters.DAO.Tables;

namespace OrderAnalytics.Adapters.DAO.Repositories;

public abstract class OrderRepository
{
    protected DatabaseContext _context { get; init; }
    
    public abstract Task<List<OrderTable>> GetOrdersFromClientAsync(int clientId);
    public abstract Task<decimal> GetFinalPriceFromOrderAsync(int orderId);
    public abstract Task<BigInteger> CountOrdersFromClientAsync(int clientId);
}