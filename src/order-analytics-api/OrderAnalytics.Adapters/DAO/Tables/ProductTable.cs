using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Numerics;

namespace OrderAnalytics.Adapters.DAO.Tables;

[Table("tb_products")]
public class ProductTable
{
    [Key]
    [Column("id_product")]
    [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
    public int Id { get; set; }

    [Required]
    [Column("tx_name")]
    public string Name { get; set; } = string.Empty;
    
    [Required]
    [Column("tx_quantity")]
    public BigInteger Quantity { get; set; }
    
    [Required]
    [Column("tx_price")]
    public decimal Price { get; set; }
    
    [ForeignKey(nameof(OrderId))]
    [Column("fk_id_order")]
    public OrderTable Order { get;set; }
    public int OrderId { get; set; }
}