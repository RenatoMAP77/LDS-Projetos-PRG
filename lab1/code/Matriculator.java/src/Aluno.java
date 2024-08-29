import java.util.LinkedList;
import java.util.List;

public class Aluno extends Usuario {
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
        try {
            if (disciplina == null) {
                throw new IllegalArgumentException("Disciplina não pode ser nula");
            }

            if (this.matriculas != null) {
                if (!disciplina.verificarDisponibilidade()) {
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

                Matricula matricula = new Matricula(disciplina, this, matriculaObrigatoria);
                matriculas.add(matricula);
                disciplina.adcionarAluno(matricula);
            }
        } catch (Exception e) {
            System.err.println("Erro ao adicionar matrícula: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removerMatricula(Matricula matricula) {
        try {
            if (matricula == null) {
                throw new IllegalArgumentException("Matrícula não pode ser nula");
            }

            matricula.removerMatricula();
            matriculas.remove(matricula);
        } catch (Exception e) {
            System.err.println("Erro ao remover matrícula: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String calcularMatriculas() {
        try {
            if (matriculas.isEmpty()) {
                return "Você não está matriculado em nenhuma disciplina";
            }
            StringBuilder matriculasString = new StringBuilder();
            for (Matricula matricula : matriculas) {
                matriculasString.append(matricula.getDisciplina().getNome()).append(" ");
            }
            return "Você está matriculado em: " + matriculas.size() + " disciplinas\n" + matriculasString;
        } catch (Exception e) {
            System.err.println("Erro ao calcular matrículas: " + e.getMessage());
            e.printStackTrace();
            return "Erro ao calcular matrículas";
        }
    }

    public int getPermisssao() {
        return PERMISSAO;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

}
