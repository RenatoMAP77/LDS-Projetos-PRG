package prg.lab.main.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Cupom")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Cupom {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    
    @ManyToOne
    @JoinColumn(name="empresa_id")
    private EmpresaParceira empresa;

    @ManyToOne
    @JoinColumn(name="aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name="vantagem_id")
    private Vantagem vantagem;

    public Cupom(String codigo, EmpresaParceira empresa, Aluno aluno, Vantagem vantagem){
        this.codigo = codigo;
        this.empresa = empresa;
        this.aluno = aluno;
        this.vantagem = vantagem;
    }

}
