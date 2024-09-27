package PRG.CarRent.Model;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

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
@Table(name = "contrato_crediario") // Renomeado para refletir o propósito
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContratoCrediario  {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "cpf_cnpj_proprietario")
    private String cpfCnpjProprietario;

    @Column(name = "valor_total")
    private Double valorTotal;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_final" )
    private LocalDateTime dataFinal;
    
    @OneToOne
    private PedidoModel pedido;

    @ManyToOne
    @JoinColumn(name = "banco_id")
    private BancoModel banco;

}
