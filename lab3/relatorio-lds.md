# I - Arquitetura

A arquitetura do projeto desenvolvido pelo grupo foi estruturada de forma clara e eficiente, com dois diretórios principais: demo, que abriga o backend, e money-system-frontend, onde está implementado o front-end. Essa divisão reflete uma separação bem definida entre as camadas de backend e frontend, promovendo uma manutenção mais simplificada e maior modularidade.
Backend
O backend foi implementado em Java, utilizando o Spring Boot como framework principal e gerenciador de dependências, o que trouxe uma série de benefícios, como configuração simplificada, alta escalabilidade e integração eficiente com outras tecnologias. Entre os módulos e dependências utilizadas, destacam-se:

Lombok: Redução de verbosidade no código, automatizando a geração de métodos como getters, setters, equals, hashCode e construtores.
SpringDoc OpenAPI: Para documentação automatizada da API, com interface interativa baseada no Swagger UI, facilitando testes e comunicação com o frontend.
MySQL e MariaDB: Bancos de dados relacionais utilizados para persistência em ambientes de produção.
H2 Database: Banco de dados em memória, utilizado em ambientes de desenvolvimento e testes rápidos.
Spring Boot Starter Mail: Suporte ao envio de e-mails, essencial para funcionalidades como notificações ou recuperação de credenciais.
A aplicação segue uma Arquitetura MVC (Model-View-Controller), organizada em pacotes específicos para cada responsabilidade:

Repositories: Camada responsável pela interação direta com o banco de dados, utilizando o Spring Data JPA para simplificar consultas e persistência.

Controllers: Pontos de entrada da aplicação, gerenciando as requisições HTTP e conectando-as às camadas de serviço.
Services: Camada intermediária que concentra as regras de negócio e lógica da aplicação, promovendo uma separação clara entre o controlador e o repositório.
Model: Representação das entidades do sistema, com mapeamento objeto-relacional (ORM) usando anotações do JPA.
Util: Classes utilitárias e DTOs (Data Transfer Objects), usadas para facilitar a transferência de dados entre camadas e ENUMS.
Front-end:

O front-end da aplicação foi desenvolvido utilizando React, uma biblioteca JavaScript amplamente reconhecida por sua eficiência e flexibilidade na criação de interfaces dinâmicas. Para a estilização, foi adotado o Tailwind CSS, que oferece uma abordagem utilitária, permitindo uma customização rápida e consistente do design.
 Além disso, foi integrado o Axios, uma biblioteca popular para realizar requisições HTTP, garantindo uma comunicação eficiente com o backend. Essa combinação de tecnologias proporcionou um front-end moderno, responsivo e altamente performático, facilitando o desenvolvimento de uma interface amigável e funcional para os usuários.





# II – Organização do GitHub
Melhorias na Organização do Repositório GitHub
URL do repositório: https://github.com/FernandoIbrahim/LDS-Projetos-PRG
Problemas identificados:
1.	Ausência de um README informativo:
o	Não havia uma descrição clara do propósito do projeto.
o	Não existiam instruções sobre como instalar e configurar o ambiente, dificultando o onboarding de novos desenvolvedores.
2.	Falta de orientação sobre a configuração do banco de dados:
o	O projeto apresentava conflitos na inicialização devido à ausência de um banco de dados configurado.
o	Era necessário criar o banco manualmente, o que não foi documentado.

# III - Dificuldades para Configurações do ambiente:
A principal dificuldade enfrentada durante a configuração da aplicação foi a falta de informações claras sobre qual banco de dados deveria ser utilizado, considerando que o projeto incluía dependências de diferentes bancos de dados, como MySQL, MariaDB e H2. Além disso, houve incertezas quanto à porta em que o backend estava configurado para escutar, o que dificultou a inicialização e integração dos serviços.
Esses problemas poderiam ter sido facilmente resolvidos com a implementação de um Docker previamente configurado, que automatizaria o processo de configuração do ambiente, garantindo uniformidade entre as máquinas de desenvolvimento. Outra solução viável seria a utilização de um banco de dados leve e autossuficiente, como o SQLite, que eliminaria a necessidade de configurações externas e facilitaria o desenvolvimento e testes locais.


# IV – Sugestão de melhorias

##### Fernando
Uma série de alterações foram realizadas no metodo login, com objetivo principal melhorar a organização, a reutilização e a clareza do código. Primeiramente, substituímos o uso de HashMap para estruturar a resposta por um DTO (Data Transfer Object), denominado LoginResponseDTO. Esse DTO encapsula os campos necessários para a resposta do login, como id e tipoUsuario, proporcionando uma estrutura mais limpa e padronizada. Essa abordagem elimina a manipulação manual de mapas de dados, facilitando a leitura e manutenção do código.

Além disso, o LoginResponseDTO foi projetado para ser genérico, permitindo sua reutilização em diferentes métodos de login, como para professores, alunos ou empresas. Isso garante consistência nas respostas da API, evitando duplicação de código e promovendo uma arquitetura mais escalável.

Outra melhoria significativa foi a introdução de um tratamento mais claro para situações de login falho. Agora, quando as credenciais são inválidas, o método retorna um status HTTP 401 Unauthorized, tornando o comportamento da API mais transparente para os consumidores.

Por fim, os métodos de login nos controladores foram completamente refatorados para adotar o uso do LoginResponseDTO, consolidando uma estrutura de resposta padronizada e consistente para as operações de autenticação. Essa mudança foi implementada no AlunoController, no qual o login de alunos agora retorna um objeto que encapsula as informações essenciais do usuário autenticado, como id e tipoUsuario. Da mesma forma, no ProfessorController e Empresa Controller, o método de login foi reformulado para utilizar o mesmo DTO, garantindo uniformidade na resposta e simplificando o código.




##### Jhonata

Descrição
Refatoração do EmailService
Descrição
A refatoração do EmailService foi realizada para corrigir violações dos princípios SOLID, melhorar a organização e garantir maior flexibilidade e clareza no código.
________________________________________
Princípios SOLID violados antes da refatoração
1.	Responsabilidade Única (SRP)
O EmailService acumulava responsabilidades que iam além de seu propósito principal, como a construção do conteúdo de e-mails e a lógica para enviar os e-mails propriamente ditos. Isso dificultava a manutenção e aumentava o acoplamento.
2.	Aberto/Fechado (OCP)
A classe não era extensível sem modificar o código existente. Cada novo tipo de e-mail demandava alterações diretas, comprometendo a escalabilidade.
3.	Substituição de Liskov (LSP)
Não havia abstração clara que permitisse intercambiar implementações de construção de conteúdo de e-mail sem impacto no comportamento esperado.
________________________________________
Importância da refatoração
•	Manutenção e Testabilidade: A separação das responsabilidades facilita a escrita de testes unitários e a adição de novos tipos de e-mails.
•	Extensibilidade: Adicionar novos tipos de e-mails agora pode ser feito com a criação de novas implementações da interface IEmailContentProvider, sem alterar a lógica central.
•	Reutilização: As classes responsáveis por gerar o conteúdo do e-mail podem ser reutilizadas em diferentes contextos, promovendo a consistência.
________________________________________
Design Patterns adotados
1.	Factory Method
Utilizado para encapsular a lógica de criação dos provedores de conteúdo de e-mail, garantindo que o EmailService permaneça desacoplado de implementações específicas.
2.	Strategy
Implementado por meio da interface IEmailContentProvider, permitindo que o comportamento de geração de conteúdo seja alterado dinamicamente, dependendo do tipo de e-mail.
3.	Dependency Injection
O EmailService recebe dependências externas (provedores de conteúdo de e-mail) por meio de injeção, reduzindo o acoplamento e facilitando a testabilidade.
________________________________________
Alterações realizadas
•	Extração da lógica de geração de conteúdo para classes específicas (BonusEmailProvider, VantagemEmailProvider, VantagemEmailEmpresaProvider), todas implementando a interface IEmailContentProvider.
•	Uso da interface para garantir flexibilidade e encapsular a responsabilidade de gerar o assunto e o corpo do e-mail.
•	O EmailService agora foca exclusivamente no envio de e-mails, delegando a criação de conteúdo às classes adequadas.
Essa refatoração segue os princípios SOLID, tornando o sistema mais robusto, flexível e fácil de manter, além de alinhar-se a boas práticas de engenharia de software.
