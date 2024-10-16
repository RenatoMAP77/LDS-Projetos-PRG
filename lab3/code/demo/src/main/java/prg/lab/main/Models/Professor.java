package prg.lab.main.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Professores")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Professor extends Usuario {
    
    @Id
    private Long id;

    private String cpf;

    private String departamento;

    private int saldoMoedas;

    @OneToOne
    @JoinColumn(name = "instituicao_id")
    private Instituição instituicao;
}
