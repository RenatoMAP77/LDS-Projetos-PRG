package prg.lab.main.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private int quantidade;

    private TransactionTypes tipo;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private EmpresaParceira empresa;
    
}