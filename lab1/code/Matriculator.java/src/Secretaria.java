import java.util.List;

public class Secretaria extends Usuario {
    private final int PERMISSAO = 3;
    private List<Curso> cursos;
    private List<Professor> professores;
    private List<Aluno> alunos;


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
    
}
