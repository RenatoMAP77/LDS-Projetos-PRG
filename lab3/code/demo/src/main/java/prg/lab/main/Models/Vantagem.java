package prg.lab.main.Models;

import java.util.List;

import jakarta.persistence.Entity;
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
    private Long id;

    private String descricao;

    private String foto;

    private int custoEmMoedas;
    

    @ManyToOne
    @JoinColumn(name="empresa_id")
    private EmpresaParceira empresa;

    @OneToMany
    private List<Cupom> cupons;
}