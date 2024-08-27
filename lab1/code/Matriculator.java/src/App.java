import java.util.List;
import java.util.Scanner;
import java.lang.Integer;
import java.util.ArrayList;

public class App {

    static GenericDAO<Secretaria> dao;
    static Secretaria secretaria;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // Adicionar populador caso nao tenha
        dao = new GenericDAO<>("dados");
         List<Secretaria> lista = dao.getAll();
             if (!lista.isEmpty()) {
              secretaria = lista.get(0);
            }else {
             secretaria = new Secretaria("1", "admin");
                dao.add(secretaria);

    }

        int opcao = 0;
        do {
            System.out.println("--------------------");
            System.out.println("|  MENU PRINCIPAL  |");
            System.out.println("--------------------");
    
            System.out.println("1 - Acessar área do Aluno");
            System.out.println("2 - Acessar Secretaria");
            System.out.println("3 - Acessar área do Professor");
            System.out.println("4 - Sair");
            opcao = Integer.parseInt(sc.nextLine());
        
            switch (opcao) {
                case 1:
                
                  menuAluno(sc);
    
                    break;
    
                case 2:
                    menuSecretaria(sc);
                    break;
    
                case 3:
                menuProfessor(sc);
                    break;
                          default:
                          if (opcao!=4) {
                              System.out.println("Opção inválida");
                            
                          }
                    break;
            }
    
        
        } while (opcao!=4);
        List<Secretaria> SAVE = new ArrayList<>();
        SAVE.add(secretaria);
        dao.atualizarDados(SAVE);

        sc.close();
          
    }

    public static void menuAluno(Scanner sc) {
        System.out.println("--------------------");
        System.out.println("|       ALUNO      |");
        System.out.println("--------------------");

        System.out.println("Digite o id:");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Digite a senha:");
        String senha = sc.nextLine();
        Aluno aluno = autenticarAluno(id, senha);
        if (aluno != null) {
            System.out.println("Bem vindo " + aluno.getNome());
            int opcao = 0;
            do {
                 System.out.println("--------------------");
                 System.out.println("|       ALUNO      |");
                 System.out.println("--------------------");
            System.out.println("O que deseja fazer?\n");
            System.out.println("1 - Adicionar matrícula");
            System.out.println("2 - Remover matrícula");
            System.out.println("3 - Calcular matrículas");
            System.out.println("4 - Sair");
                 opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome da disciplina:");
                    String nomeDisciplina = sc.nextLine();
                    Disciplina disciplina = procurarDisciplina(nomeDisciplina); 
                    if (disciplina != null) {
                        aluno.adicionarMatricula(disciplina);
                    } else {
                        System.out.println("Disciplina não encontrada");
                    }
                    break;

                case 2:
                    System.out.println("Digite o nome da disciplina:");
                    nomeDisciplina = sc.nextLine();
                   Disciplina disciplinaX = procurarDisciplina(nomeDisciplina);
                    if (disciplinaX != null) {
                        Matricula matricula = procurMatricula(nomeDisciplina);
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

                default:
                if (opcao!=4) {
                    System.out.println("Opção inválida");
                }
                    break;
            }
            } while (opcao != 4);
            

        } else {
            System.out.println("Usuário não encontrado");
        }
    }

    public static void menuSecretaria(Scanner sc){
                    System.out.println("--------------------");
                    System.out.println("|    SECRETARIA    |");
                    System.out.println("--------------------");
    
                    
    
    
                    System.out.println("Digite o id:");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.println("Digite a senha:");
                    String senha = sc.nextLine();
                    Boolean autenticado = autenticarSecretaria(id, senha);
                    
                
                    if (!autenticado) {
                        System.out.println("Senha ou id incorretos");
                        return;
                    }
                    System.out.println("Bem vindo " );
                    int opcao = 0;
                    do {
                        System.out.println("--------------------");
                        System.out.println("|    SECRETARIA    |");
                        System.out.println("--------------------");
                    System.out.println("O que deseja fazer?\n");
                    System.out.println("1 - Adicionar curso");
                    System.out.println("2 - Gerar currículo");
                    System.out.println("3 - Adicionar disciplina");
                    System.out.println("4 - Remover disciplina");
                    System.out.println("5 - Adcionar Professor");
                    System.out.println("6 - Remover Professor");
                    System.out.println("7 - Adicionar Aluno");
                    System.out.println("8 - Remover Aluno");
                    System.out.println("9 - Mudar professor de disciplina");
                    System.out.println("10 - Sair");
                    opcao = Integer.parseInt(sc.nextLine());
                    
                    switch (opcao) {
                        case 1:
                            System.out.println("Digite o nome do curso:");
                            String nomeCurso = sc.nextLine();
                            Curso curso = new Curso(nomeCurso);
                            secretaria.adicionarCurso(curso);
                            
                            break;
                        case 2:
                            secretaria.gerarCurriculo();
                            
                            break;    
                        case 3:
                            System.out.println("Digite o nome da disciplina:");
                            String nomeDisciplina = sc.nextLine();
                            System.out.println("Digite o nome do professor:");
                            String nomeProfessor = sc.nextLine();
                            System.out.println("Digite o nome do curso que será relacionada a disciplina:");
                            String nomeCursoX = sc.nextLine();

                            Curso cursoAtual = procurarCurso(nomeCursoX);
                            
                            if (cursoAtual == null) {
                                System.out.println("Curso não encontrado");
                                break;
                            }
                            Professor professor = procurarProfessor(nomeProfessor);
                            if (professor != null) {
                                Disciplina disciplina = new Disciplina(nomeDisciplina, professor);
                                cursoAtual.adcionarDisciplina(disciplina);
                                
                                
                            } else {
                                System.out.println("Professor não encontrado");
                            }
                            
                            break;

                        case 4:
                        System.out.println("Digite o nome da disciplina:");
                        nomeDisciplina = sc.nextLine();
                        System.out.println("Digite o nome do curso:");
                        nomeCursoX = sc.nextLine();
                            Curso cursoAtuaX = procurarCurso(nomeCursoX);
                            if (cursoAtuaX == null) {
                                System.out.println("Curso não encontrado");
                                break;
                            }

                            Disciplina disciplina = procurarDisciplina(nomeDisciplina);
                            
                            if (disciplina != null) {
                                cursoAtuaX.removerDisciplina(disciplina);
                            } else {
                                System.out.println("Disciplina não encontrada");
                            }

                            break;

                        case 5:
                            System.out.println("Digite o nome do professor:");
                            String nomeProfessor1 = sc.nextLine();
                            System.out.println("Digite a senha do professor:");
                            String senhaProfessor = sc.nextLine();
                            Professor professorAtual = new Professor(nomeProfessor1,senhaProfessor);
                            secretaria.adicionarProfessor(professorAtual);
                            break; 

                        case 6:
                            System.out.println("Digite o nome do professor:");
                            String nomeProfessor2 = sc.nextLine();

                            Professor professorX = procurarProfessor(nomeProfessor2);

                            if (professorX != null) {
                                secretaria.getProfessores().remove(professorX);
                            } else {
                                System.out.println("Professor não encontrado");
                            }
                            break; 

                        case 7:
                            System.out.println("Digite o nome do aluno:");
                            String nomeAluno = sc.nextLine();
                            System.out.println("Digite a senha do aluno:");
                            String senhaAluno = sc.nextLine();
                            System.out.println("Digite o curso do aluno:");
                            String cursoAluno = sc.nextLine();
                            
                            Aluno alunoX = procurarAluno(nomeAluno);
                            Curso cursoAlunoX = procurarCurso(cursoAluno);
                            
                            if (alunoX != null) {
                                System.out.println("Aluno já cadastrado");
                                break;
                            }
                            if (cursoAlunoX == null) {
                                System.out.println("Curso não encontrado");
                                break;
                            }

                            Aluno alunoAtual = new Aluno(nomeAluno,senhaAluno,cursoAlunoX);
                            secretaria.adicionarAluno(alunoAtual);
                            
                            break; 
                        case 8:
                            System.out.println("Digite o nome do aluno:");
                            String nomeAluno1 = sc.nextLine();

                            Aluno alunoY = procurarAluno(nomeAluno1);
                            if (alunoY != null) {
                                secretaria.getAlunos().remove(alunoY);
                            } else {
                                System.out.println("Aluno não encontrado");
                            }
                            
                            break;

                        case 9:
                            System.out.println("Digite o nome da disciplina:");
                            String nomeDisciplina1 = sc.nextLine();
                            System.out.println("Digite o nome do professor:");
                            String nomeProfessor3 = sc.nextLine();

                            Disciplina disciplina1 = procurarDisciplina(nomeDisciplina1);
                            Professor professor1 = procurarProfessor(nomeProfessor3);


                            if (disciplina1 != null && professor1 != null) {
                                disciplina1.setProfessor(professor1);
                            } else {
                                System.out.println("Disciplina ou professor não encontrado");
                            }
                            break; 


                        default:
                        if(opcao!=10)
                        System.out.println("Opção inválida");
                            break;
                        }
                    }
                    while (opcao != 10);
                
    }

    public static void menuProfessor(Scanner sc){
        
        System.out.println("--------------------");
        System.out.println("|    PROFESSOR    |");
        System.out.println("--------------------");
        
        
        System.out.println("Digite o id:");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Digite a senha:");
        String senha = sc.nextLine();
        Professor prof = autenticarProfessor(id, senha);
        if (prof == null) {
            System.out.println("Usuário não encontrado");
            return;
        }
        System.out.println("Bem vindo " + prof.getNome());
    int opcaoProfessor = 0;
    do {    
        System.out.println("--------------------");
        System.out.println("|    PROFESSOR    |");
        System.out.println("--------------------");
        System.out.println("O que deseja fazer?\n");
        System.out.println("1 - Listar alunos");
        System.out.println("2 - Listar disciplinas");
        System.out.println("3 - Sair");
        
        opcaoProfessor = Integer.parseInt(sc.nextLine());
    
        switch (opcaoProfessor) {
            case 1:
                System.out.println("Digite o nome da disciplina:");
                String nomeDisciplina = sc.nextLine();


                Disciplina disciplina = procurarDisciplina(nomeDisciplina);


                if (disciplina != null) {
                    disciplina.listarAlunos();
                } else {
                    System.out.println("Disciplina não encontrada");
                }
        
                
                break;
             case 2:
                prof.listarDisciplinas();
                break;
        
            default:
                break;
            }
        } while(opcaoProfessor != 3);
        
        


    }

    public static Aluno autenticarAluno(int id, String senha) {

        return secretaria.getAlunos().stream()
                .filter(a -> a.getId() == id && a.getSenha().equals(senha))
                .findFirst()
                .orElse(null);


    }

    public static Professor autenticarProfessor(int id, String senha) {
        return secretaria.getProfessores().stream()
                .filter(p -> p.getId() == id && p.getSenha().equals(senha))
                .findFirst()
                .orElse(null);
    }
    
    public static boolean autenticarSecretaria(int id, String senha) {
        return secretaria.getId() == id && secretaria.getSenha().equals(senha);
    }
    public static Curso procurarCurso(String nomeCurso){
        return secretaria.getCursos().stream()
        .filter(c -> c.getNome().equals(nomeCurso))
        .findFirst()
        .orElse(null);
    }
    public static Disciplina procurarDisciplina(String nomeDisciplina){
        return secretaria.getCursos().stream()
        .flatMap(c -> c.getDisciplinas().stream())
        .filter(d -> d.getNome().equals(nomeDisciplina))
        .findFirst()
        .orElse(null);
    }
    public static Professor procurarProfessor(String nomeProfessor){
        return secretaria.getProfessores().stream()
        .filter(p -> p.getNome().equals(nomeProfessor))
        .findFirst()
        .orElse(null);
    }
    public static Aluno procurarAluno(String nomeAluno){
        return secretaria.getAlunos().stream()
        .filter(a -> a.getNome().equals(nomeAluno))
        .findFirst()
        .orElse(null);
    }
    public static Matricula procurMatricula(String nomeMatricula){
        return secretaria.getAlunos().stream()
        .flatMap(a -> a.getMatriculas().stream())
        .filter(m -> m.getDisciplina().getNome().equals(nomeMatricula))
        .findFirst()
        .orElse(null);
    }

    

    
}