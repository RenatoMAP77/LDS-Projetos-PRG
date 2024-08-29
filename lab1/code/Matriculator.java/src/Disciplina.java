import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Disciplina implements Serializable {
    Long serialVersionUID = 6L;
    private String nome;
    private boolean estaAtiva;
    private boolean matriculasAbertas;
    private final int MIN_ALUNOS = 3;
    private final int MAX_ALUNOS = 60;
    private Professor professor;
    private List<Matricula> matriculados;

    public Disciplina(String nome, Professor professor, boolean estaAtiva, boolean matriculasAbertas) {
        try {
            if (nome == null || professor == null) {
                throw new IllegalArgumentException("Nome e professor não podem ser nulos");
            }
            this.nome = nome;
            this.professor = professor;
            this.estaAtiva = estaAtiva;
            this.matriculasAbertas = matriculasAbertas;
            matriculados = new LinkedList<>();
            professor.adcionarDisciplina(this);
        } catch (Exception e) {
            System.err.println("Erro ao criar disciplina: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean verificarDisponibilidade() {
        try {
            if (!matriculasAbertas || !estaAtiva || matriculados.size() > MAX_ALUNOS) {
                return false;
            }
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao verificar disponibilidade: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private void verificaTamanho() {
        try {
            if (matriculados.size() > MAX_ALUNOS) {
                setMatriculasAbertas(false);
            }
        } catch (Exception e) {
            System.err.println("Erro ao verificar tamanho: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void adcionarAluno(Matricula matricula) {
        try {
            if (matricula == null) {
                throw new IllegalArgumentException("Matrícula não pode ser nula");
            }
            if (verificarDisponibilidade()) {
                matriculados.add(matricula);
                verificaTamanho();
            } else {
                throw new RuntimeException("Não foi possível adicionar o aluno");
            }
        } catch (Exception e) {
            System.err.println("Erro ao adicionar aluno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removerAluno(Matricula aluno) {
        try {
            if (aluno == null) {
                throw new IllegalArgumentException("Matrícula não pode ser nula");
            }
            matriculados.remove(aluno);
        } catch (Exception e) {
            System.err.println("Erro ao remover aluno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void mudarProfessor(Professor novoProfessor) {
        try {
            if (novoProfessor == null) {
                throw new IllegalArgumentException("Novo professor não pode ser nulo");
            }
            this.professor.removerDisciplina(this);
            this.professor = novoProfessor;
        } catch (Exception e) {
            System.err.println("Erro ao mudar professor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void listarAlunos() {
        try {
            if (matriculados.isEmpty()) {
                System.out.println("Não há alunos matriculados");
            } else {
                System.out.println("Alunos matriculados na disciplina " + this.getNome());
                for (Matricula matricula : this.getMatriculados()) {
                    System.out.println(matricula.getAluno().getNome());
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar alunos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Disciplina(String nome, Professor professor) {
        try {
            if (nome == null || professor == null) {
                throw new IllegalArgumentException("Nome e professor não podem ser nulos");
            }
            this.nome = nome;
            this.professor = professor;
            estaAtiva = true;
            matriculados = new LinkedList<>();
        } catch (Exception e) {
            System.err.println("Erro ao criar disciplina: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public List<Matricula> getMatriculados() {
        return matriculados;
    }

    public void setNome(String nome) {
        try {
            if (nome == null || nome.isEmpty()) {
                throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
            }
            this.nome = nome;
        } catch (Exception e) {
            System.err.println("Erro ao definir nome: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setEstaAtiva(boolean estaAtiva) {
        this.estaAtiva = estaAtiva;
    }

    public boolean isMatriculasAbertas() {
        return matriculasAbertas;
    }

    public void setMatriculasAbertas(boolean matriculasAbertas) {
        this.matriculasAbertas = matriculasAbertas;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        try {
            if (professor == null) {
                throw new IllegalArgumentException("Professor não pode ser nulo");
            }
            this.professor = professor;
        } catch (Exception e) {
            System.err.println("Erro ao definir professor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public int getMIN_ALUNOS() {
        return MIN_ALUNOS;
    }

    public int getMAX_ALUNOS() {
        return MAX_ALUNOS;
    }

    public boolean isEstaAtiva() {
        return estaAtiva;
    }
}
