@startuml
abstract class "Usuario"{
     - id: Long
     - senha: String
     - nome : String
     

     +Cadastrar(id : Long , senha : String):void

}

class "Secretaria"{
      {static} -PERMISSAO = 3 : int
      - cursos : List<Curso>
      - professores : List<Professor>
      - alunos : List<Aluno>
      

 
      + gerarCurriculo() : void
      + adicionarCurso(curso : Curso) : void
      + adicionarProfessor(professor : Professor) : void
      + adicionarAluno(aluno : Aluno) : void
      
 
}

class "Aluno"{
     
     - matricula : List<Matricula>
     {static} -PERMISSAO = 1 : int
     - curso: Curso

     +adicionarMatricula(disciplina: Disciplina): void
     +removerMatricula(matricula: Matricula): void
     +calcularMatriculas(): String
     
}

class "Professor"{
     {static} -PERMISSAO = 2 : int
     -disciplinas: List<Disciplinas>

      +listarAlunos(nomeDisciplina: String): void
      +listarDisciplinas(): void
      +removerDisciplina(): void
      +adicionarDisciplina(disciplina: Disciplina): void
}

class "Curso"{
     - nome: String
     - creditos: int
     - disciplinas : List<Disciplina> 
     - {static} dataLimiteMatricula : LocalDate

     +adicionarDisciplina(disciplina: Disciplina): void
     +removerDisciplina(disciplina: Disciplina): void  

}
class "Disciplina"{
     -nome: String
     - estaAtiva : boolean
     - matriculasAbertas : boolean
      {static} + MIN_ALUNOS = 3
      {static} + MAX_ALUNOS = 60 
     - professor : Professor
     - matriculados: List<Matricula>
     
     + verificarDisponibilidade() : boolean
     + adicionarAluno(matricula: Matricula): void
     + removerAluno(matricula: Matricula): void
     + mudarProfessor(professor: Professor): void
     
}

class "Matricula"{
    - obrigatoriedade: boolean
    - disciplina: Disciplina
    - aluno: Aluno

    + matricular(disciplina : Disciplina) : void
    + removerMatricula(disciplina : Disciplina) : void
}

class "GenericDAO"{
    - arquivo : String
    - objestos : List<T>

    + add(objeto : T) : void
    + atualizarDados(novosObjetos : List<T>) : void
    + salvarDados() : void
    + carregarDados() : void
    
}

class "Populador"{
    + popular(dao : GenericDAO<Secretaria>)
}

class "App"{
    - {static} secretaria : Secretaria

    + {static} main(args: String) : void
 
}


 


Usuario <|-- Secretaria
Usuario <|-- Aluno
Usuario <|-- Professor

Aluno "0,*" --> "1" Curso: está em >
Curso "1" o-- "1,*" Disciplina
Disciplina "1" -- "0,*" Matricula
Aluno "1" -- "0,*" Matricula
Professor "1" -- "1,*" Disciplina
GenericDAO "1" --"1" Populador
GenericDAO "1" -- "1" Secretaria
Secretaria"1" -- "1"App



@enduml