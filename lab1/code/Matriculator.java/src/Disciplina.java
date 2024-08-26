import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Disciplina {

    private String nome;
    private boolean estaAtiva;
    private boolean matriculasAbertas;
    private final int MIN_ALUNOS = 3;
    private final int MAX_ALUNOS = 60;
    private Professor professor;
    private List<Matricula> matriculados;

    public boolean verificarDisponibilidade() {
        if (!matriculasAbertas) {
            return false;
        } else if (matriculados.size() < MIN_ALUNOS || matriculados.size() > MAX_ALUNOS || !estaAtiva) {
            return false;
        }

        return true;
    }

    private void verificaTamanho() {
        if ( matriculados.size() > MAX_ALUNOS) {
            setMatriculasAbertas(false);
        }
    }

    public void adcionarAluno(Matricula matricula) {
        if (verificarDisponibilidade()) {
            matriculados.add(matricula);
            verificaTamanho();
        } else
            throw new RuntimeException("Não foi possível adicionar o aluno");
    }

    public void removerAluno(Matricula aluno) {
        matriculados.remove(aluno);

    }

    public void mudarProfessor(Professor novoProfessor) {
        this.professor.removerDisciplina(this);
        this.professor = novoProfessor;
    }

    public void listarAlunos() {
        if (matriculados.size() == 0) {
            System.out.println("Não há alunos matriculados");
        }
        System.out.println("Alunos matriculados na disciplina " + this.getNome());
        for (Matricula matricula : this.getMatriculados()) {
            System.out.println(matricula.getAluno().getNome());
        }
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

    public boolean isMatriculasAbertas() {
        return matriculasAbertas;
    }

    public void setMatriculasAbertas(boolean matriculasAbertas) {
        this.matriculasAbertas = matriculasAbertas;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public int getMIN_ALUNOS() {
        return MIN_ALUNOS;
    }

    public int getMAX_ALUNOS() {
        return MAX_ALUNOS;
    }

    public boolean isEstaAtiva() {
        return estaAtiva;
    }

}
