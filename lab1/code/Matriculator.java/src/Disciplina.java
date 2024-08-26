import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Disciplina {
    
   private String nome;
   private boolean estaAtiva;
   private final int MIN_ALUNOS = 3;
   private final int MAX_ALUNOS = 60;
   private Professor professor ;
   private List<Matricula> matriculados;
   private static LocalDate dataLimiteMatricula = LocalDate.of(2024, 8, 31);
   

    public boolean verificarDisponibilidade() {
        if (matriculados.size() < MIN_ALUNOS || matriculados.size() > MAX_ALUNOS || !estaAtiva || LocalDate.now().isAfter(dataLimiteMatricula)) { 
            return false;
        }
        
        return true;
    }

    public void adcionarAluno(Matricula matricula){
        if (verificarDisponibilidade()){
            matriculados.add(matricula);
        }
        else throw new RuntimeException("Não foi possível adicionar o aluno");
    }

    public void removerAluno(Matricula aluno){
        matriculados.remove(aluno);
        
    }

    public void mudarProfessor(Professor novoProfessor){
        this.professor.removerDisciplina(this);
        this.professor = novoProfessor;
    }

    public Disciplina(String nome, Professor professor) {
        this.nome = nome;
        this.professor = professor;
        estaAtiva = true;
        matriculados = new LinkedList<Matricula>();
    }
    public String getNome() {
        return nome;
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
    public LocalDate getDataMatricula() {
        return dataLimiteMatricula;
    }







}
