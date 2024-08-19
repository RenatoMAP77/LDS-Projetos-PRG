import java.util.List;

public class Disciplina {
    
   private String nome;
   private boolean estaAtiva;
   private Matricula matricula;
   private final int MIN_ALUNOS = 3;
   private final int MAX_ALUNOS = 60;
   private Professor professor ;
   private List<Matricula> matriculados;

    public boolean verificarDisponibilidade() {
        //...
        return true;
    }

    public void adcionarAluno(){
        //...
    }

    public void removerAluno(){
        //...
    }

    public void mudarProfessor(){
        //...
    }
    public String getNome() {
        return nome;
    }
    public Matricula getMatricula() {
        return matricula;
    }
    public List<Matricula> getMatriculados() {
        return matriculados;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEstaAtiva(boolean estaAtiva) {
        this.estaAtiva = estaAtiva;
    }







}
