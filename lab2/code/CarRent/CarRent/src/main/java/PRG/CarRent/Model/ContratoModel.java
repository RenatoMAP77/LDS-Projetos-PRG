
package PRG.CarRent.Model;


import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "contrato")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContratoModel{

    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "data_inicio", nullable = false)
    private LocalDateTime dataInicio ;
    
    @Column(name = "data_final", nullable = false)
    private LocalDateTime dataFinal;

    @OneToOne()
    private PedidoModel pedido;




}