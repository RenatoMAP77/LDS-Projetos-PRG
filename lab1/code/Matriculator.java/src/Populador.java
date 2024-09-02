import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Populador {
    public void popular(GenericDAO<Secretaria> dao) throws IOException {
        Secretaria secretaria = new Secretaria("1", "123");

        for (int i = 0; i < 5; i++) {
            Curso curso = new Curso("Curso" + i);
            secretaria.adicionarCurso(curso);
            for (int j = 0; j < 5; j++) {
                Professor professor = new Professor("Professor" + i+j, "123");
                Disciplina disciplina = new Disciplina("Disciplina" + i+j, professor, true, true);
                secretaria.adicionarProfessor(professor);
                curso.adcionarDisciplina(disciplina);
                for (int k = 0; k < 5; k++) {
                    Aluno aluno = new Aluno("Aluno" + i+j+k, "123", curso);
                    aluno.adicionarMatricula(disciplina);
                    
                    secretaria.adicionarAluno(aluno); 
                }
            }
        }
        List<Secretaria> secretarias = new ArrayList<>();
        secretarias.add(secretaria);
        
        dao.atualizarDados(secretarias);
    }
}