using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace OrderAnalytics.Adapters.DAO.Tables;

[Table("tb_orders")]
public class OrderTable
{
    [Key]
    [Column("id_order")]
    public int Id { get; set; }
    
    [Column("tx_final_price", TypeName = "numeric")]
    public decimal FinalPrice { get; set; }
    
    [ForeignKey(nameof(ClientId))]
    [Column("fk_id_client")]
    public ClientTable Client { get; set; }
    public int ClientId { get; set; }

    [InverseProperty("Order")]
    public List<ProductTable> Products { get; set; } = [];
}