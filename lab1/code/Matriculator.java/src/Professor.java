import java.io.Serializable;
import java.util.List;

public class Professor extends Usuario  {
    Long serialVersionUID = 3L;
    private final int PERMISSAO = 2;
    private List<Disciplina> disciplinas;

    public Professor(String nome, String senha) {
        super.Cadastrar(nome, senha);
        disciplinas = null;
    }

    public void listarAlunos(String nomeDisciplina) {
        if (disciplinas == null || disciplinas.size() == 0) {
            throw new RuntimeException("Não há disciplinas cadastradas");
            
        }
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getNome().equals(nomeDisciplina)) {
               disciplina.listarAlunos();
                
            }
        }
    }

    public void listarDisciplinas() {
        if (disciplinas == null || disciplinas.size() == 0) {
            System.out.println("Não há disciplinas cadastradas");
            
        }
        else{
            System.out.println("Disciplinas cadastradas: ");
            int cont = 0;
            for (Disciplina disciplina : disciplinas) {
             System.out.println(cont+" - " + disciplina.getNome());
        }
    }
    }

    public int getPermisssao() {
        return PERMISSAO;
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
