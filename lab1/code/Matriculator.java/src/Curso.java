import java.util.LinkedList;
import java.util.List;

public class Curso {
    String nome;
    int creditos;
    List<Disciplina> disciplinas;


    public Curso(String nome, int creditos) {
        this.nome = nome;
        this.creditos = creditos;
        disciplinas = new LinkedList<Disciplina>();
    }

    public void adcionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }

    

}
