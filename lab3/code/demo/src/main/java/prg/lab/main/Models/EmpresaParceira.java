package prg.lab.main.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "EmpresaParceiras")
public class EmpresaParceira {
    
    @Column(name = "nome")
    private String nome;

    @Column(name = "email", unique = true) // Garantir unicidade de email
    private String email;

    @Column(name = "senha")
    private String senha;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cnpj")
    private String cnpj;
    @Column(name = "descricao")
    private String descricao;

    @Column(name = "tipo_usuario")
    private TipoUsuario tipoUsuario = TipoUsuario.EMPRESA;
    
    public EmpresaParceira(String cnpj, String nome, String email, String senha, String descricao) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.descricao = descricao;
        this.tipoUsuario = TipoUsuario.EMPRESA;
    }
    
}
