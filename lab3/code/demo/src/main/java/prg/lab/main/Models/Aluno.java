package prg.lab.main.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import prg.lab.main.Util.Enums.TipoUsuario;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Alunos")
public class Aluno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String rg;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "curso")
    private String curso;

    @Column(name = "saldo_moedas")
    private Double saldoMoedas;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email", unique = true) // Garantir unicidade de email
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name ="tipo_usuario")
    private TipoUsuario tipoUsuario = TipoUsuario.ALUNO; ;

    @ManyToOne
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;

    @OneToMany
    @JsonIgnore
    private List<Cupom> cupons;
    public Aluno(String cpf, String rg, String endereco, String curso, String nome, String email, String senha,Double saldo, Instituicao instituicao) {
        this.id = null;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.endereco = endereco;
        this.curso = curso;
        this.saldoMoedas = saldo;
        this.instituicao = instituicao;
        this.tipoUsuario = TipoUsuario.ALUNO;

    }
    
}
