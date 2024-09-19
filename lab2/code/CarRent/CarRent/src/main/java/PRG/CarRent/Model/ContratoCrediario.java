
package PRG.CarRent.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "contato_crediario")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContratoCrediario {
    
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "cpf_cnpj_proprietario", nullable = false, unique = true)
    private String cpfCnpjProprietario;

    
    @OneToOne()
    private PedidoModel pedido;

}
