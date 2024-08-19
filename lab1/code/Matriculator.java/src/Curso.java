import java.util.LinkedList;
import java.util.List;

public class Curso {
    private String nome;
    private int creditos;
    private List<Disciplina> disciplinas;


    public Curso(String nome, int creditos) {
        this.nome = nome;
        this.creditos = creditos;
        disciplinas = new LinkedList<Disciplina>();
    }

    public void adcionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }


    public String getNome() {
        return nome;
    }

    public int getCreditos() {
        return creditos;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }



}
