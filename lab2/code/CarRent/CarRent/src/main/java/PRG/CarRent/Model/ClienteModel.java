
package PRG.CarRent.Model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class ClienteModel extends Usuario{

    @Column(name = "cliente_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long cliente_id;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "profissao",nullable = false)
    private String profissao;

    @Column(name = "rg", nullable = true)
    private String rg;

    @Column(name="rua", nullable = false)
    private String rua;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "pais", nullable = false)
    private String pais;

    @Column(name = "cep", nullable = true)
    private String cep;

    @Column(name = "rendimentos", nullable = false)
    private String rendimento;
    @Column(name = "empregadores", nullable = false)
    private ArrayList<String> empregadores = new ArrayList<String>();

    @OneToMany(mappedBy = "cliente")
    private List<PedidoModel> pedidos;

    @OneToMany(mappedBy = "cliente")
    private List<AutomovelModel> automoveis;

    public void introduzirRendimento(String rendimento){
        this.rendimentos.add(rendimento);
    }

    public void introduzirEmpregador(String empregador){
        this.empregadores.add(empregador);
    }

    public void removerRendimento(String rendimento){
        this.rendimentos.remove(rendimento);
    }

    public void removerEmpregador(String empregador){
        this.empregadores.remove(empregador);
    }

    public void introduzirPedido(PedidoModel pedido){
        //todo
    }

    public void cancelarPedido(PedidoModel pedido){
        //todo
    }

    public void atualizarPedido(PedidoModel pedido){
        //todo
    }

    public void atualizarCliente(ClienteModel cliente){
        //todo
    }
    @JsonIgnore
    public List<PedidoModel> getPedidos(){
        return this.pedidos;
    }

    @JsonIgnore
    public List<AutomovelModel> getAutomoveis(){
        return this.automoveis;
    }

}
