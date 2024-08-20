@startuml

left to right direction

Actor "Usuario" as US
Actor "Aluno" as AL
Actor "Secretaria" as SC
Actor "Professor" as PR
Actor "Sistema Financeiro" as FI

US <|-- AL
US <|-- SC
US <|-- PR

rectangle SistemaDeMatriculas{
usecase "Gerar Curriculo" as UC1
usecase "Matricular-se" as UC2
usecase "Fazer login" as UC3
usecase "Visualizar Alunos" as UC5
usecase "Cobrar" as UC6
usecase "Cancelar Matricula" as UC7
usecase "Visualizar diciplinas" as UC8
usecase "Matricular-se em obrigatória" as UC9
usecase "Matricular-se em alternativa" as UC10
usecase "Gerenciar Alunos" as UC11
usecase "Gerenciar Professores" as UC12
usecase "Gerenciar Disciplinas" as UC13
}




SC --> UC1
SC --> UC11
SC --> UC12
SC --> UC13
AL --> UC2
(UC2).> (UC7):Extends
(UC9).> (UC2):Include
(UC2).> (UC10):Extends
US --> UC3
PR --> UC5
PR --> UC8

UC2 --> FI
FI --> UC6 
UC6 --> AL

@enduml