
package PRG.CarRent.Model;


import PRG.CarRent.Util.Enums.StatusPedido;
import PRG.CarRent.Util.Enums.TipoPedido;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

    @JoinColumn(name = "automovel_id", nullable = false)
    @ManyToOne()
    private AutomovelModel automovel;

    @JoinColumn(name = "cliente_id", nullable = false)
    @ManyToOne()
    private ClienteModel cliente;

    @JoinColumn(name = "empresa_id")
    @ManyToOne()
    private EmpresaModel empresa;

    @JoinColumn(name = "banco_id")
    @ManyToOne()
    private BancoModel banco;

    @JoinColumn(name = "contrato_id")
    @OneToOne(mappedBy = "pedido",cascade = CascadeType.ALL)
    private ContratoModel contrato;

    @JoinColumn(name = "contrato_crediario_id")
    @OneToOne(mappedBy = "pedido",cascade = CascadeType.ALL)
    private ContratoCrediario contratoCrediario;

}
