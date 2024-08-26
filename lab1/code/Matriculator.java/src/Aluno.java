import java.util.LinkedList;
import java.util.List;

public class Aluno extends Usuario {
    private List<Matricula> matriculas;
    private final int PERMISSAO = 1;
    private Curso curso;

    public Aluno(String nome, String senha, Curso curso) {
        super.Cadastrar(nome, senha);
        this.curso = curso;
        matriculas = new LinkedList<Matricula>();
    }

    public void adicionarMatricula(Disciplina disciplina) {
        // se a disciplina não estiver no curso, verificar se ja tem mais de 2 materias
        // nao obrigatoria abertas
        // instanciar uma nova matricula e adiciona-la
        if (this.matriculas != null) {
            if (matriculas.size() < 6) {
                if (!curso.getDisciplinas().contains(disciplina)) {
                    int cont = 0;

                    for (Matricula matricula : matriculas) {
                        if (!matricula.isObrigatoria()) {
                            cont++;
                        }
                    }
                    if (cont < 2) {
                        throw new RuntimeException(
                                "Você não pode se matricular em mais de 2 disciplinas não obrigatórias");
                    }

                }

            } else
                throw new RuntimeException("Você não pode se matricular em mais de 6 disciplinas");

            Matricula matricula = new Matricula(disciplina);
            matriculas.add(matricula);
        }
    }
    
    public void removerMatricula(Matricula matricula) {
        // a adicionar
    }

    public String calcularMatriculas() {
        // a adicionar
        return "Você está matriculado em:  ";
    }
    
}
