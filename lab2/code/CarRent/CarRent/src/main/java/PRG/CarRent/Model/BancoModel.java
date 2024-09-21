
package PRG.CarRent.Model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import PRG.CarRent.Util.Enums.TipoBanco;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "banco")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BancoModel {
   
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "tipo_banco", nullable = false)
    private TipoBanco tipoBanco = TipoBanco.FISICO ;

    @OneToMany(mappedBy = "banco")
    private List<PedidoModel> pedidos;

    @OneToMany(mappedBy = "banco")
    private List<ContratoCrediario> contratosCrediario;

    @JsonIgnore
    public List<PedidoModel> analisarPedidos(){
        return this.pedidos;
    }

    public void modificarPedido(PedidoModel pedido){
        this.pedidos.add(pedido);
    }

    public boolean avaliarPedido(PedidoModel pedido){
        return true;
    }

   
}
