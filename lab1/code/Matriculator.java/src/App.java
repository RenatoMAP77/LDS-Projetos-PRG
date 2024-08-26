import java.util.List;
import java.util.Scanner;
import java.lang.Integer;
import java.util.ArrayList;

public class App {

    static GenericDAO<Secretaria> dao = new GenericDAO<>("dados");
    static Secretaria secretaria = dao.getAll().stream().findFirst().orElse(new Secretaria());

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // Adicionar populador caso nao tenha

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
        if (!aluno.equals(null)) {
            System.out.println("Bem vindo " + aluno.getNome());
            int opcao = 0;
            do {
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
                   Disciplina disciplinaX = secretaria.getCursos().stream()
                            .flatMap(c -> c.getDisciplinas().stream())
                            .filter(d -> d.getNome().equals(nomeDisciplina))
                            .findFirst()
                            .orElse(null);
                    if (disciplinaX != null) {
                        Matricula matricula = aluno.getMatriculas().stream()
                                .filter(m -> m.getDisciplina().equals(disciplinaX))
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
                    boolean autenticado = autenticarSecretaria(id, senha);
    
                    if (!autenticado) {
                        throw new RuntimeException("Usuário não encontrado");
                    }
                    System.out.println("Bem vindo " );
                    int opcao = 0;
                    do {
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
                            Curso cursoAtual = secretaria.getCursos().stream()
                                    .filter(c -> c.getNome().equals(nomeCursoX))
                                    .findFirst()
                                    .orElse(null);
                            if (cursoAtual == null) {
                                System.out.println("Curso não encontrado");
                                break;
                            }
                            Professor professor = secretaria.getProfessores().stream()
                                    .filter(p -> p.getNome().equals(nomeProfessor))
                                    .findFirst()
                                    .orElse(null);
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
                            Curso cursoAtuaX = secretaria.getCursos().stream()
                                    .filter(c -> c.getNome().equals(nomeCursoX))
                                    .findFirst()
                                    .orElse(null);
                            if (cursoAtuaX == null) {
                                System.out.println("Curso não encontrado");
                                break;
                            }

                            Disciplina disciplina = secretaria.getCursos().stream()
                                    .flatMap(c -> c.getDisciplinas().stream())
                                    .filter(d -> d.getNome().equals(nomeDisciplina))
                                    .findFirst()
                                    .orElse(null);
                            
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
                            Professor professorX = secretaria.getProfessores().stream()
                                    .filter(p -> p.getNome().equals(nomeProfessor2))
                                    .findFirst()
                                    .orElse(null);
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
                            
                            Aluno alunoX = secretaria.getAlunos().stream()
                                    .filter(a -> a.getNome().equals(nomeAluno))
                                    .findFirst()
                                    .orElse(null);
                            Curso cursoAlunoX = secretaria.getCursos().stream()
                                    .filter(c -> c.getNome().equals(cursoAluno))
                                    .findFirst()
                                    .orElse(null);  
                            
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

                            Aluno alunoY = secretaria.getAlunos().stream()
                                    .filter(a -> a.getNome().equals(nomeAluno1))
                                    .findFirst()
                                    .orElse(null);
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
                            Disciplina disciplina1 = secretaria.getCursos().stream()
                                    .flatMap(c -> c.getDisciplinas().stream())
                                    .filter(d -> d.getNome().equals(nomeDisciplina1))
                                    .findFirst()
                                    .orElse(null);
                            Professor professor1 = secretaria.getProfessores().stream()
                                    .filter(p -> p.getNome().equals(nomeProfessor3))
                                    .findFirst()
                                    .orElse(null);
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
        if (prof.equals(null)) {
            System.out.println("Usuário não encontrado");
            return;
        }
        System.out.println("Bem vindo " + prof.getNome());
    do {
        System.out.println("O que deseja fazer?\n");
        System.out.println("1 - Listar alunos");
        System.out.println("2 - Listar disciplinas");
        System.out.println("3 - Sair");
        
        int opcaoProfessor = Integer.parseInt(sc.nextLine());

        switch (opcaoProfessor) {
            case 1:
                System.out.println("Digite o nome da disciplina:");
                String nomeDisciplina = sc.nextLine();
                Disciplina disciplina = secretaria.getCursos().stream()
                        .flatMap(c -> c.getDisciplinas().stream())
                        .filter(d -> d.getNome().equals(nomeDisciplina))
                        .findFirst()
                        .orElse(null);
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
            }while (opcaoProfessor != 3);
        }
        
        


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
    

    
}