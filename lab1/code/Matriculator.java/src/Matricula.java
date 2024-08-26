public class Matricula {

    private boolean obrigatoriedade;
    private Disciplina disciplina ;
    private Aluno aluno;

    
    public Matricula(Disciplina disciplina, Aluno aluno, boolean obrigatoriedade){
        this.disciplina = disciplina;
        this.aluno = aluno;
        this.obrigatoriedade = obrigatoriedade;
    }

    //metodo para o garbage colector apagar este objeto
    public void removerMatricula( ){
        disciplina.removerAluno(this);
        this.aluno = null;
        disciplina = null;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public boolean isObrigatoria() {
        return obrigatoriedade;
    }
    public Aluno getAluno() {
        return aluno;
    }
}