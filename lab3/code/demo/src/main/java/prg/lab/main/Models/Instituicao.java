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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "Instituicoes")
@Entity
public class Instituicao {
    
    @Id
    private Long id;

    private String nome;

    @OneToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;
}
