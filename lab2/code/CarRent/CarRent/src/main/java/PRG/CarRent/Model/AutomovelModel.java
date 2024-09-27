package PRG.CarRent.Model;



import PRG.CarRent.Util.Enums.TipoProprietario;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "automovel")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AutomovelModel {
   
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;


    @Column(name = "marca", nullable = false, unique = false)
    private String marca;

    @Column(name = "modelo", nullable = false, unique = false)
    private String modelo;

    @Column(name = "ano", nullable = false, unique = false)
    private Integer ano;

    @Column(name = "tipo_proprietario", nullable = false, unique = false)
    private TipoProprietario tipoProprietario;

    @JoinColumn(name = "cliente_id", nullable = true)
    @ManyToOne
    private ClienteModel cliente;
    
    @Column(name = "diaria", nullable = false)
    private Double diaria;
    
    @JoinColumn(name = "empresa_id", nullable = true)
    @ManyToOne()
    private EmpresaModel empresa;

    @OneToMany(mappedBy = "automovel")
    private List<PedidoModel> pedidos;

    


}
