import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Curso implements Serializable {
    Long serialVersionUID = 5L;
    private String nome;
    private List<Disciplina> disciplinas;
    private static LocalDate dataLimiteMatricula = LocalDate.of(2024, 8, 31);

    public void fecharMatricula() {
        try{
            if (dataLimiteMatricula.isBefore(LocalDate.now())) {
                for (Disciplina disciplina : disciplinas) {
                    disciplina.setMatriculasAbertas(false);
                    if (disciplina.getMatriculados().size() < disciplina.getMIN_ALUNOS()) {
                        disciplina.setEstaAtiva(false);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao fechar matrícula: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Curso(String nome) {
        try{
            if (nome == null) {
                throw new IllegalArgumentException("Nome não pode ser nulo");
            }
            this.nome = nome;
            disciplinas = new LinkedList<Disciplina>();
        } catch (Exception e) {
            System.err.println("Erro ao criar curso: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void adcionarDisciplina(Disciplina disciplina) {
        try{
            if (disciplina == null) {
                throw new IllegalArgumentException("Disciplina não pode ser nula");
            }
            disciplinas.add(disciplina);
        } catch (Exception e) {
            System.err.println("Erro ao adicionar disciplina: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removerDisciplina(Disciplina disciplina) {
        try{
            if (disciplina == null) {
                throw new IllegalArgumentException("Disciplina não pode ser nula");
            }
            disciplinas.remove(disciplina);
        } catch (Exception e) {
            System.err.println("Erro ao remover disciplina: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getNome() {
        return nome;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
