import java.util.List;

public class Aluno extends Usuario {
    private List<Matricula> matriculas;
    private final int PERMISSAO = 1;
    private Curso curso;

    public Aluno(String nome, String senha, Curso curso) {
        // super(nome, senha);
        // this.curso = curso;
    }

    public void adicionarMatricula(Disciplina disciplina) {
        // se a disciplina não estiver no curso, verificar se ja tem mais de 2 materias nao obrigatoria abertas
        //instanciar uma nova matricula e adiciona-la
    }
    
    public void removerMatricula(Matricula matricula) {
        // a adicionar
    }

    public String calcularMatriculas() {
        // a adicionar
        return "Você está matriculado em:  ";
    }
    
}
