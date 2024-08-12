@startuml

left to right direction

Actor "User" as US
Actor "Aluno" as AL
Actor "Secretaria" as SC
Actor "Professor" as PR
Actor "Financeiro" as FI

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
}




SC --> UC1
AL --> UC2
AL --> UC7
US --> UC3
PR --> UC5
PR --> UC8

UC2 --> FI
FI --> UC6 
UC6 --> AL

@enduml