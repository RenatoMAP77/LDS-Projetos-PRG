import java.util.List;

public class Professor {
    private final int permisssao = 2;
    private List<Disciplina> disciplinas;

    public void listarAlunos() {
        //...
    }

    public void listarDisciplinas() {
        //...
    }

    public int getPermisssao() {
        return permisssao;
    }
    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void removerDisciplina(Disciplina disciplina) {
        disciplinas.remove(disciplina);
    }

    public void adcionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }

}
