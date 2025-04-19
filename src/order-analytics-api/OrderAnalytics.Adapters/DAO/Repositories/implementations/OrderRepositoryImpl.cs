using System.Numerics;
using Microsoft.EntityFrameworkCore;
using OrderAnalytics.Adapters.DAO.Context;
using OrderAnalytics.Adapters.DAO.Tables;

namespace OrderAnalytics.Adapters.DAO.Repositories.implementations;

public class OrderRepositoryImpl : OrderRepository
{
    public OrderRepositoryImpl(DatabaseContext context) => _context = context; 
    
    public override async Task<List<OrderTable>> GetOrdersFromClientAsync(int clientId)
    {
        return await (
                from order in _context.Orders
                where order.ClientId == clientId
                select order)
            .ToListAsync();
    }

    public override async Task<decimal> GetFinalPriceFromOrderAsync(int orderId)
    {
        var savedOrder = await (
                from order in _context.Orders
                where order.Id == orderId
                select order.FinalPrice)
            .SingleOrDefaultAsync();

        // TODO: Implement Domain Exceptions
        if (savedOrder == 0)
            throw new Exception();

        return savedOrder;
    }

    public override async Task<BigInteger> CountOrdersFromClientAsync(int clientId)
    {
        var count = await (
                from order in _context.Orders
                where order.ClientId == clientId
                select order)
            .LongCountAsync();

        return new BigInteger(count);
    }
}