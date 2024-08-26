import java.util.Scanner;
import java.lang.Integer;

public class App {

    static GenericDAO<Secretaria> dao = new GenericDAO<>("dados");
    static Secretaria secretaria = dao.getAll().stream().findFirst().orElse(new Secretaria());

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // Adicionar populador caso nao tenha

        int opcao = 0;

        System.out.println("--------------------");
        System.out.println("|  MENU PRINCIPAL  |");
        System.out.println("--------------------");

        System.out.println("1 - Acessar área do Aluno");
        System.out.println("2 - Acessar Secretaria");
        System.out.println("3 - Acessar área do Professor");
        opcao = Integer.parseInt(sc.nextLine());

        switch (opcao) {
            case 1:
                System.out.println("--------------------");
                System.out.println("|       ALUNO     |");
                System.out.println("--------------------");

                System.out.println("Digite o id:");
                int id = Integer.parseInt(sc.nextLine());
                System.out.println("Digite a senha:");
                String senha = sc.nextLine();
                Aluno aluno = autenticarAluno(id, senha);
                if (!aluno.equals(null)) {
                    System.out.println("Bem vindo " + aluno.getNome());
                    System.out.println("O que deseja fazer?\n");
                    System.out.println("1 - Adicionar matrícula");
                    System.out.println("2 - Remover matrícula");
                    System.out.println("3 - Calcular matrículas");

                    opcao = Integer.parseInt(sc.nextLine());

                    switch (opcao) {
                        case 1:
                            System.out.println("Digite o nome da disciplina:");
                            String nomeDisciplina = sc.nextLine();
                            Disciplina disciplina = secretaria.getCursos().stream()
                                    .flatMap(c -> c.getDisciplinas().stream())
                                    .filter(d -> d.getNome().equals(nomeDisciplina))
                                    .findFirst()
                                    .orElse(null);
                            if (disciplina != null) {
                                aluno.adicionarMatricula(disciplina);
                            } else {
                                System.out.println("Disciplina não encontrada");
                            }
                            break;

                        case 2:
                            System.out.println("Digite o nome da disciplina:");
                            nomeDisciplina = sc.nextLine();
                            disciplina = secretaria.getCursos().stream()
                                    .flatMap(c -> c.getDisciplinas().stream())
                                    .filter(d -> d.getNome().equals(nomeDisciplina))
                                    .findFirst()
                                    .orElse(null);
                            if (disciplina != null) {
                                Matricula matricula = aluno.getMatriculas().stream()
                                        .filter(m -> m.getDisciplina().equals(disciplina))
                                        .findFirst()
                                        .orElse(null);
                                if (matricula != null) {
                                    aluno.removerMatricula(matricula);
                                } else {
                                    System.out.println("Matrícula não encontrada");
                                }
                            } else {
                                System.out.println("Disciplina não encontrada");
                            }
                            break;

                        case 3:
                            System.out.println(aluno.calcularMatriculas());
                            break;
                    }

                } else {
                    System.out.println("Usuário não encontrado");
                }

                break;

            case 2:
                System.out.println("--------------------");
                System.out.println("|    SECRETARIA    |");
                System.out.println("--------------------");

                // System.out.println("Digite o id:");
                // int id = Integer.parseInt(sc.nextLine());
                // System.out.println("Digite a senha:");
                // String senha = sc.nextLine();
                // Aluno aluno = autenticarAluno(id, senha);

                // System.out.println("1 - Adicionar curso");
                // System.out.println("2 - Gerar currículo");

                // gerarCurriculo()

                break;

            case 3:
                System.out.println("--------------------");
                System.out.println("|    PROFESSOR    |");
                System.out.println("--------------------");

                // listarAlunos(nomeDisciplina)
                // listarDisciplinas()
                // removerDisciplina()
                // adicionarDisciplina(disciplina)

        }

    }

    public static Aluno autenticarAluno(int id, String senha) {
        return secretaria.getAlunos().stream()
                .filter(a -> a.getId() == id && a.getSenha().equals(senha))
                .findFirst()
                .orElse(null);
    }
}