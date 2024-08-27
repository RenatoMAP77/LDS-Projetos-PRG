import java.util.LinkedList;
import java.util.List;

public class Aluno extends Usuario  {
    Long serialVersionUID = 2L;
    private List<Matricula> matriculas;
    private final int PERMISSAO = 1;
    private Curso curso;

    public Aluno(String nome, String senha, Curso curso) {
        super.Cadastrar(nome, senha);
        this.curso = curso;
        this.curso = curso;
        matriculas = new LinkedList<Matricula>();
    }

    // se a disciplina não estiver no curso, verificar se ja tem mais de 2 materias
    // nao obrigatoria abertas
    // instanciar uma nova matricula e adiciona-la
    public void adicionarMatricula(Disciplina disciplina) {
        if (this.matriculas != null) {
            if (disciplina.verificarDisponibilidade() == false) {
                throw new RuntimeException("Disciplina lotada ou não disponível");
                
            }
            boolean matriculaObrigatoria = true;
            if (matriculas.size() < 6) {
                if (!curso.getDisciplinas().contains(disciplina)) {
                    int cont = 0;
                    matriculaObrigatoria = false;
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

            Matricula matricula = new Matricula(disciplina,this, matriculaObrigatoria);
            matriculas.add(matricula);
            disciplina.adcionarAluno(matricula);
        }
    }
    
    public void removerMatricula(Matricula matricula) {
        matricula.removerMatricula();
        matriculas.remove(matricula);
    }

    public String calcularMatriculas() {
        if (matriculas.size() == 0) {
            return "Você não está matriculado em nenhuma disciplina";
            
        }
        String matriculasString = "";
            for (Matricula matricula : matriculas) {
                matriculasString += matricula.getDisciplina().getNome() + " ";
            }
        return "Você está matriculado em:  "+ matriculas.size()+ " disciplinas" +"\n" + matriculasString;
        
    }

    public int getPermisssao() {
        return PERMISSAO;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }
    
}
