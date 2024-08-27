import java.util.ArrayList;
import java.util.List;

public class Populador {
    public void popular() {
        GenericDAO<Secretaria> DAO = new GenericDAO<>("dados");
        //gerar 5 cursos
        //gerar 5 disciplinas, uma para cada curso
        //25 alunos, 5 para cada curso
        //gerar 5 professores, uma para cada disciplina
        //gerar uma secretaria para armazenar tudo

        Secretaria secretaria = new Secretaria("1", "123");

        for (int i = 0; i < 5; i++) {
            Curso curso = new Curso("Curso" + i);
            secretaria.adicionarCurso(curso);
            for (int j = 0; j < 5; j++) {
                Professor professor = new Professor("Professor" + i+j, "senha123");
                Disciplina disciplina = new Disciplina("Disciplina" + j, professor, true, true);
                curso.adcionarDisciplina(disciplina);
                for (int k = 0; k < 5; k++) {
                    Aluno aluno = new Aluno("Aluno" + i+j+k, "123", curso);
                    aluno.adicionarMatricula(disciplina);
                    
                }
            }
        }
        List<Secretaria> secretarias = new ArrayList<>();
        secretarias.add(secretaria);
        DAO.atualizarDados(secretarias);
}
}