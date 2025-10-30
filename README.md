# ** 📚 Library System **

Sistema de gerenciamento de biblioteca desenvolvido em Java com Spring Boot, focado em boas práticas e uma arquitetura limpa e escalável.
O projeto está em progresso e tem como objetivo fornecer uma base sólida para o gerenciamento de livros, autores, usuários e empréstimos.

## 🚀 Tecnologias Utilizadas

- ☕ Java JDK 21+
- 🧩 Spring Boot 3.5.7 (Web, JPA, Validation) 
- 🗃️ Hibernate (implementação JPA)
- 🧠 Lombok (para reduzir boilerplate)
- ⚙️ Maven (gerenciamento de dependências e build)
- 💾 SQL Server 

## 🏗️ Arquitetura

O projeto segue o padrão em camadas, separando responsabilidades e facilitando a manutenção:

```plaintext
src
├── model        → Entidades e modelos de domínio
├── repository   → Camada de persistência (JPA/Hibernate)
├── service      → Regras de negócio e lógica da aplicação
└── controller   → Endpoints REST (interface com o mundo externo)
```

Essa estrutura ajuda a manter o código limpo, testável e organizado.

## 🔧 Em Desenvolvimento

O projeto ainda está em construção.
Próximas etapas planejadas:

- Containerização com Docker
- Implementação de Spring Security (autenticação e controle de acesso)
- Implementação de testes automatizados


## 💡 Objetivo

Criar um sistema modular e simples, com foco no aprendizado e boas práticas com Spring Boot, JPA e arquitetura em camadas.
A ideia é evoluir o projeto gradualmente, implementando novos recursos e tecnologias, ao mesmo tempo em que evoluo junto ao projeto.

## 🧑‍💻 Autor

Desenvolvido por Guilherme A. Fortes

Em constante evolução 🚧
