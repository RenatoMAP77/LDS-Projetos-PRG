package prg.lab.main.Models;

import java.time.LocalDateTime;


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
import prg.lab.main.Util.Enums.TransactionTypes;

/**
 * Transacao
 */
@Entity
@Table(name = "Transacoes")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    private String descricao;

    private Double quantidade;

    private TransactionTypes tipo;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresaParceira empresa;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;
    
    public Transacao(LocalDateTime data, String descricao, Double quantidade, TransactionTypes tipo, Aluno aluno, EmpresaParceira empresa, Professor professor) {
        this.data = data;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.aluno = aluno;
        this.empresa = empresa;
        this.professor = professor;
    }
}