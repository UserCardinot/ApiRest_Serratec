# ApiRest_Serratec

* Trabalho individual final de API Restfull
* **[ API CADASTRO ]**

## 📑 Índice

* Sobre o projeto
* Tecnologias utilizadas
* Diagrama do banco de dados

## 📁 Sobre o projeto

- O sistema foi desenvolvido com o intuito de desenvolver os conhecimentos aprendidos na matéria de ApiRest, implementando métodos de CRUD para cadastrar, atualizar, deleter e buscar informações no banco de dados para utilizar em métodos específicos.
  
- O projeto adota uma arquitetura em camadas, com separação clara entre as responsabilidades de controle, serviço e persistência de dados. A comunicação com o banco de dados é feita via Spring Data JPA, e o controle de acesso e autenticação é implementado com JWT. O processo com JWT envolve o Login, onde o usuário envia suas credenciais (ex. email e senha) para um endpoint.  Se autenticado com sucesso, o sistema gera um token JWT e o retorna no corpo da resposta. Todas as requisições subsequentes a endpoints protegidos exigem que o token JWT seja incluído no header.

- A autenticação na aplicação é feita por meio de tokens JWT. O processo envolve:
  - Cadastro: O usuário entrará com suas informações especificando a role (Estudante ou Faculdade)
  - Login: O usuário envia suas credenciais (ex. email e senha) para um endpoint.
  - Geração de Token: Se autenticado com sucesso, o sistema gera um token JWT e o retorna no corpo da resposta.
  - Validação de Token: Todas as requisições subsequentes a endpoints protegidos exigem que o token JWT seja incluído no header Authorization.**(Por enquanto não está solicitando essa solicitação)**

- Foi utilizado o Postman para realizar testes nos endpoints, validando o fluxo de criação e manipulação de dados.

- A documentação da API é gerada automaticamente usando o Swagger (via springdoc-openapi). Acessível por meio do endpoint: [materias-faculdade]([http://localhost:8000/gp1-api/swagger-ui/index.html](http://localhost:8000/materiasfaculdade/swagger-ui/index.html)). A documentação expõe todos os endpoints do CRUD para as classes Materia, Faculdade, Estudante com detalhes sobre as operações permitidas (GET, POST, PUT, DELETE).

  ### Exemplo de Endpoint Documentado:

  Estudante-controller:
  
  - **POST** /estudante/adicionarMateria - Adicionar matéria a um estudante
  - **GET** /estudante/ - Listar Estudantes
  - **PUT** /estudante/{username} - Atualizar Estudante pelo nome
  - **DELETE** /estudante/{id} - Deletar Estudante
 
    
## 🌐 Tecnologias utilizadas

- [**Maven**:](https://maven.apache.org/)  ferramenta de automação e gerenciamento de build, amplamente utilizada para compilar, testar e organizar projetos, especialmente no ecossistema Spring Boot.
  
- [**Maven Repository**:](https://mvnrepository.com/) repositório central de artefatos, onde bibliotecas e dependências são armazenadas e gerenciadas para projetos Maven.
  
- [**Spring Tool Suite 4 (STS)**:](https://spring.io/tools) IDE baseada no Eclipse, otimizada para facilitar o desenvolvimento de projetos Spring Boot, com funcionalidades que agilizam a criação e o gerenciamento de aplicações Spring.
  
- [**Spring initializr**:](https://start.spring.io/) plataforma online que simplifica a criação de projetos Spring Boot, permitindo a configuração rápida de dependências e componentes iniciais.
  
- [**Postman**:](https://www.postman.com/) ferramenta versátil para testar, documentar e colaborar em APIs REST, com funcionalidades que facilitam o envio de requisições e a análise de respostas.
  
- [**Navegador Web (chrome, opera, edge, ...)**:](https://www.google.pt/intl/pt-PT/chrome/?brand=OZZY&ds_kid=43700080663033655&gad_source=1&gclid=Cj0KCQjw4Oe4BhCcARIsADQ0csl8-GMCaOUfHHPMtjSLtPewrsEcGB6gNSERLbPHeIxSPeOPwYR6sWgaAhUPEALw_wcB&gclsrc=aw.ds) utilizado para testar e validar o comportamento da aplicação web em um ambiente real, acessando interfaces e APIs desenvolvidas.

## 🔁 Diagrama do banco de dados

<p align="center">
  <img src="https://github.com/user-attachments/assets/2dcb2f21-fb23-43a0-a1d0-7b2f56fa9e8e" alt="Diagrama"/>
</p>

_____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
