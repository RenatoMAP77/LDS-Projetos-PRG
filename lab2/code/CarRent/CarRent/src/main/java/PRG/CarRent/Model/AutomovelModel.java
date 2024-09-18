package PRG.CarRent.Model;



import PRG.CarRent.Util.Enums.TipoProprietario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

    @Column(name = "marca", nullable = false, unique = false)
    private String marca;

    @Column(name = "modelo", nullable = false, unique = false)
    private String modelo;

    @Column(name = "ano", nullable = false, unique = false)
    private int ano;

    @Column(name = "tipo_proprietario", nullable = false, unique = false)
    private TipoProprietario tipoProprietario;



}
