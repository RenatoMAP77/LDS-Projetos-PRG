package prg.lab.main.Models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Vantagens")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Vantagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private int custoEmMoedas;

    private String foto;

    // @ManyToOne
    // @JoinColumn(name="empresa_id")
    // private EmpresaParceira empresa;

    // @OneToMany
    // private List<Cupom> cupons;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    @JsonBackReference // Evita serialização recursiva
    private EmpresaParceira empresa;

    @OneToMany
    @JsonIgnore // Ignora este campo na serialização
    private List<Cupom> cupons;

    public Vantagem(String descricao, int custoEmMoedas, EmpresaParceira empresaParceira, String foto) {

        this.descricao = descricao;

        this.custoEmMoedas = custoEmMoedas;

        this.empresa = empresaParceira;

        cupons = new ArrayList<>();
        // se a foto for nula, seta uma foto padrão
        if (foto == null) {
            this.foto = "https://istoe.com.br/wp-content/uploads/2016/01/as_14585846654714.jpg";
        } else
            this.foto = foto;

    }

}
