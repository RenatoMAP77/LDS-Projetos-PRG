package prg.lab.main.Models;


import jakarta.persistence.Column;
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

@Getter
@Setter
@Entity
@Table(name = "Professores")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Professor extends Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cpf;

    private String departamento;

    private Double saldoMoedas;

    @ManyToOne
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;

    public Professor( String cpf, String departamento, Double saldoMoedas, Instituicao instituicao, String email, String nome, String senha) {
        this.cpf = cpf;
        this.departamento = departamento;
        this.saldoMoedas = saldoMoedas;
        this.instituicao = instituicao;
        this.setEmail(email);
        this.setNome(nome);
        this.setSenha(senha);
    }
}
