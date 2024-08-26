import java.util.*;

public class Secretaria extends Usuario {
    private List<Curso> cursos;
    private List<Professor> professores;
    private List<Aluno> alunos;

    public Secretaria() {
        super.Cadastrar("secretaria","senha123",3);
        this.cursos = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.alunos = new ArrayList<>();
    }

    public void gerarCurriculo() {
        if (cursos == null || cursos.size() == 0) {
            throw new RuntimeException("Não há cursos cadastrados");
        }
        for (Curso curso : cursos) {
            System.out.println("Curso: " + curso.getNome());
            for (Disciplina disciplina : curso.getDisciplinas()) {
                System.out.println(disciplina.getNome());
            }
        }
    }



    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public String getSenha() {
        return super.getSenha();
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    

    public List<Professor> getProfessores() {
        return professores;
    }
}
