package PRG.CarRent.Model;

import PRG.CarRent.Util.Enums.StatusPedido;
import jakarta.persistence.Column;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Agente extends Usuario{   

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    public boolean avaliarPedido(PedidoModel pedido, boolean status){
        if (pedido.getStatusPedido() == StatusPedido.APROVADO || pedido.getStatusPedido() == StatusPedido.REPROVADO){
            return false;
            
        }
        if (status) {
            pedido.setStatusPedido(StatusPedido.APROVADO);
        }
        else{
            pedido.setStatusPedido(StatusPedido.REPROVADO);
        }

        return true;
    }
}
