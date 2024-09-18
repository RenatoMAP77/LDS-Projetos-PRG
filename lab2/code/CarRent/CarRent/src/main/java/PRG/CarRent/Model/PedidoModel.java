
package PRG.CarRent.Model;


import PRG.CarRent.Util.Enums.StatusPedido;
import PRG.CarRent.Util.Enums.TipoPedido;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoModel {
    
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "codigo_pedido", nullable = false, unique = true)
    private String codigoPedido;

    @Column(name = "tipo_pedido", nullable = false)
    private TipoPedido tipoPedido = TipoPedido.NORMAL;

    @Column(name = "status_pedido", nullable = false)
    private StatusPedido statusPedido = StatusPedido.PENDENTE;

}
