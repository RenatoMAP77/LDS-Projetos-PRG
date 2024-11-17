package prg.lab.main.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import prg.lab.main.Util.Enums.TipoUsuario;

@Getter
@Setter
@Entity
@Table(name = "Professores")
@AllArgsConstructor
@NoArgsConstructor
public class Professor  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email", unique = true) // Garantir unicidade de email
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String departamento;

    @Column(nullable = false)
    private Double saldoMoedas;

    @Column(name = "tipo_usuario")
    private TipoUsuario tipoUsuario = TipoUsuario.PROFESSOR;
    @ManyToOne
    @JoinColumn(name = "instituicao_id", nullable = false)
    private Instituicao instituicao;

   
    public Professor( String cpf, String departamento, Double saldoMoedas, Instituicao instituicao, String email, String nome, String senha) {
        this.cpf = cpf;
        this.departamento = departamento;
        this.saldoMoedas = saldoMoedas;
        this.instituicao = instituicao;
        this.setEmail(email);
        this.setNome(nome);
        this.setSenha(senha);
        this.tipoUsuario = TipoUsuario.PROFESSOR;

    }
}
