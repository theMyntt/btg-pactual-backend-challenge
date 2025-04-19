using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace OrderAnalytics.Adapters.DAO.Tables;

[Table(("tb_clients"))]
public class ClientTable
{
    [Key]
    [Column("id_client")]
    public int Id { get; set; }
    
    [Column("tx_name")]
    public string? Name { get; set; }
    
    [Column("tx_description")]
    public string? Description { get; set; }
}