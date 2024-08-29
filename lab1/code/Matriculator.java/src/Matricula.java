import java.io.Serializable;

public class Matricula implements Serializable {
    Long serialVersionUID = 7L;
    private boolean obrigatoriedade;
    private Disciplina disciplina;
    private Aluno aluno;

    public Matricula(Disciplina disciplina, Aluno aluno, boolean obrigatoriedade) {
        if (disciplina == null || aluno == null) {
            throw new IllegalArgumentException("Disciplina e aluno não podem ser nulos");
        }
        this.disciplina = disciplina;
        this.aluno = aluno;
        this.obrigatoriedade = obrigatoriedade;
    }

    // metodo para o garbage colector apagar este objeto
    public void removerMatricula() {
        try {
            if (disciplina == null || aluno == null) {
                throw new IllegalArgumentException("Disciplina ou aluno não podem ser nulos");
            }
            disciplina.removerAluno(this);
            this.aluno = null;
            disciplina = null;
        } catch (Exception e) {
            System.err.println("Erro ao remover matricula: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public boolean isObrigatoria() {
        return obrigatoriedade;
    }

    public Aluno getAluno() {
        return aluno;
    }
}