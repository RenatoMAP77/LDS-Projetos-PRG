import java.util.*;

public class Secretaria extends Usuario {
    private final int PERMISSAO = 3;
    private List<Curso> cursos;
    private List<Professor> professores;
    private List<Aluno> alunos;

    public Secretaria() {
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

    public int getPermisssao() {
        return PERMISSAO;
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

    public void adicionarCurso(Curso curso) {
        cursos.add(curso);
    }

    public void adicionarProfessor(Professor professor) {
        professores.add(professor);
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public int getPERMISSAO() {
        return PERMISSAO;
    }

    public List<Professor> getProfessores() {
        return professores;
    }
}


