package prg.lab.main.Models;

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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "EmpresasParceiras")
public class EmpresaParceira extends Usuario {
    
    public EmpresaParceira(String cnpj, String nome, String email, String senha, String descricao) {
        super(nome, email, senha);
        this.id = null;
        this.cnpj = cnpj;
        this.descricao = descricao;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cnpj;

    private String descricao;
}
