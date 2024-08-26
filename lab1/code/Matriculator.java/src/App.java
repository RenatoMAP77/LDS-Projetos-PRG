import java.io.File;
import java.util.Scanner;
import java.lang.Integer;

@SuppressWarnings("rawtypes")
public class App {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        GenericDAO meuArquivo = new GenericDAO<String>("dados");
        // meuArquivo.add(new Curso("Engenharia de Software", 10));
        // meuArquivo.add(new Curso("Ciências da Computação", 15));
        // meuArquivo.add(new Disciplina("POO", new Professor("Carlos", "1234")));
        // meuArquivo.add(new Disciplina("Banco de Dados", new Professor("Maria", "4321")));
        // meuArquivo.add(new Disciplina("Estrutura de Dados", new Professor("Joao", "1234")));

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

                // if(){
                //   //  System.out.println("Bem vindo " + meuArquivo.get(id).toString());
                //     // adicionarMatricula(disciplina)
                //     // removerMatricula(matricula)
                //     // calcularMatriculas()
                // } else {
                //     System.out.println("Usuário não encontrado");
                // }



                break;

            case 2:
                System.out.println("--------------------");
                System.out.println("|    SECRETARIA    |");
                System.out.println("--------------------");

                // gerarCurriculo()

                break;


                case 3:
                System.out.println("--------------------");
                System.out.println("|    PROFESSOR    |");
                System.out.println("--------------------");

                // listarAlunos(nomeDisciplina)
                // listarDisciplinas()
                //removerDisciplina()
                //adicionarDisciplina(disciplina)

                }

        }
    }