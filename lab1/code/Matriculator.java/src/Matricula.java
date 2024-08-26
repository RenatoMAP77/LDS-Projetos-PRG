public class Matricula {

    private boolean obrigatoriedade;
    private Disciplina disciplina ;
    private Aluno aluno;

    
    public Matricula(Disciplina disciplina, Aluno aluno, boolean obrigatoriedade){
        this.disciplina = disciplina;
        this.aluno = aluno;
        this.obrigatoriedade = obrigatoriedade;
    }

    public void removerMatricula( Disciplina disciplina){
        //...
    }

    public Object getDisciplina() {
        return disciplina;
    }

    public boolean isObrigatoria() {
        return obrigatoriedade;
    }
}