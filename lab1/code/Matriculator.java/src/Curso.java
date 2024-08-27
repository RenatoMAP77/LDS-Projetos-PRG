import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Curso implements Serializable {
    Long serialVersionUID = 5L;
    private String nome;
    private List<Disciplina> disciplinas;
    private static LocalDate dataLimiteMatricula = LocalDate.of(2024, 8, 31);

    public void fecharMatricula() {

        for (Disciplina disciplina : disciplinas) {
            if (dataLimiteMatricula.isBefore(LocalDate.now())) {
                disciplina.setMatriculasAbertas(false);
                if (disciplina.getMatriculados().size() < disciplina.getMIN_ALUNOS()) {
                    disciplina.setEstaAtiva(false);

                }
            }

        }

    }

    public Curso(String nome) {
        this.nome = nome;
        disciplinas = new LinkedList<Disciplina>();
    }

    public void adcionarDisciplina(Disciplina disciplina) {

        disciplinas.add(disciplina);
    }

    public void removerDisciplina(Disciplina disciplina) {
        disciplinas.remove(disciplina);
    }

    public String getNome() {
        return nome;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
