package prg.lab.main.Models;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "Alunos")
public class Aluno extends Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    private String rg;

    private String endereco;

    private String curso;

    private int saldoMoedas;

    @OneToOne
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;

    @OneToMany
    private List<Cupom> cupons;

    public Aluno(String cpf, String rg, String endereco, String curso, String nome, String email, String senha, Instituicao instituicao) {
        super(nome, email, senha);
        this.id = null;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.curso = curso;
        this.saldoMoedas = 0;
        this.instituicao = instituicao;

    }
}
