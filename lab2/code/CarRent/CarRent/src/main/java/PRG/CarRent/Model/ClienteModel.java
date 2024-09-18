
package PRG.CarRent.Model;

import java.util.ArrayList;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteModel {

    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "nome", nullable = false)
    private String nome; 

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "profissao",nullable = false)
    private String profissao;

    @Column(name = "rg", nullable = true)
    private String rg;

    @Column(name="rua", nullable = false)
    private String rua;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "pais", nullable = false)
    private String pais;

    @Column(name = "cep", nullable = true)
    private String cep;

    @Column(name = "rendimentos", nullable = false)
    private ArrayList<String> rendimentos;
    @Column(name = "empregadores", nullable = false)
    private ArrayList<String> empregadores;

}
