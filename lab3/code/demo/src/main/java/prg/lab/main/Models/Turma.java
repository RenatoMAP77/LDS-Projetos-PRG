// package prg.lab.main.Models;

// import java.util.ArrayList;
// import java.util.List;

// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.OneToOne;
// import jakarta.persistence.Table;

//     /*
//      * Classe que representa uma turma
//      * Ativar caso necessário, e desativar o atributo de Alunos na classe professor
//      */

// @Table(name = "Turma")
// public class Turma {

//     @Id
//     private Long id;

//     private String descricao;

//     @OneToOne
//     @JoinColumn(name = "professor_id")
//     private Professor professor;

//     @OneToMany
//     private List<Aluno> alunos;
    
//     public Turma(String descricao){
//         this.descricao = descricao;
//         this.professor = null;
//         this.alunos = new ArrayList<>();
//     }
// }
