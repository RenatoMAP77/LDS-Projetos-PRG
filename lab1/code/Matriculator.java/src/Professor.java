import java.util.LinkedList;
import java.util.List;

public class Professor extends Usuario {
    private static final long serialVersionUID = 2L;
    private final int PERMISSAO = 2;
    private List<Disciplina> disciplinas;

    public Professor(String nome, String senha) {
        super.Cadastrar(nome, senha);
        disciplinas = new LinkedList<>(); // Inicializando a lista de disciplinas
    }

    public void listarAlunos(String nomeDisciplina) {
        try {
            if (nomeDisciplina == null || nomeDisciplina.isEmpty()) {
                throw new IllegalArgumentException("Nome da disciplina não pode ser nulo ou vazio");
            }

            if (disciplinas == null || disciplinas.isEmpty()) {
                throw new RuntimeException("Não há disciplinas cadastradas");
            }

            boolean encontrouDisciplina = false;
            for (Disciplina disciplina : disciplinas) {
                if (disciplina.getNome().equals(nomeDisciplina)) {
                    disciplina.listarAlunos();
                    encontrouDisciplina = true;
                    break;
                }
            }

            if (!encontrouDisciplina) {
                throw new RuntimeException("Disciplina não encontrada");
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar alunos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void listarDisciplinas() {
        try {
            if (disciplinas == null || disciplinas.isEmpty()) {
                System.out.println("Não há disciplinas cadastradas");
            } else {
                System.out.println("Disciplinas cadastradas:");
                int cont = 1; // Iniciar o contador em 1 para uma numeração mais comum
                for (Disciplina disciplina : disciplinas) {
                    System.out.println(cont + " - " + disciplina.getNome());
                    cont++;
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar disciplinas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public int getPermisssao() {
        return PERMISSAO;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void removerDisciplina(Disciplina disciplina) {
        try {
            if (disciplina == null) {
                throw new IllegalArgumentException("Disciplina não pode ser nula");
            }
            if (!disciplinas.remove(disciplina)) {
                throw new RuntimeException("Disciplina não encontrada na lista");
            }
            disciplinas.remove(disciplina);
        } catch (Exception e) {
            System.err.println("Erro ao remover disciplina: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void adcionarDisciplina(Disciplina disciplina) {
        try {
            if (disciplina == null) {
                throw new IllegalArgumentException("Disciplina não pode ser nula");
            }
            disciplinas.add(disciplina);
        } catch (Exception e) {
            System.err.println("Erro ao adicionar disciplina: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
