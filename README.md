# Estoque-SpringBoot

Esse é um sistema simples de gerenciamento de estoque que eu desenvolvi usando **Spring Boot** no back-end e **HTML, CSS e JavaScript** no front-end. Ele serve para cadastrar produtos, controlar o estoque, registrar movimentações e gerar relatórios básicos.

---

## Tecnologias que usei

- **Java 17**  
- **Spring Boot 3**  
- **Spring Data JPA**  
- **HTML / CSS / JavaScript**  
- **H2 / MySQL** (dependendo de como você configurar)  
- **Maven**  
- **Lombok** (opcional, para facilitar em alguns lugares)

---

## O que ele faz

- Cadastrar, atualizar e remover produtos.  
- Controlar entradas e saídas do estoque.  
- Buscar produtos por nome, SKU ou categoria.  
- Gerar relatórios de estoque atual e histórico de movimentações.  
- Interface web simples, só com HTML, CSS e JS.

---

## Como rodar

Clone o repositório e entre na pasta do projeto com:

`git clone https://github.com/SEU_USUARIO/estoque-springboot.git`  
`cd estoque-springboot`

Configure o banco de dados no arquivo `application.properties` e rode o projeto com:

`mvn spring-boot:run`

Abra no navegador em `http://localhost:8080`

---

## Estrutura do projeto

`src/main/java/com/empresa/estoque/`  
- `conf/` → Configurações do Spring Boot  
- `controller/` → Controllers do back-end  
- `model/` → Entidades do sistema  
- `dto/` → Objetos para transferir dados  
- `repository/` → Repositórios JPA  
- `service/` → Regras de negócio  
- `exceptions/` → Classes de exceção personalizadas  

`src/main/resources/`  
- `static/` → Front-end: HTML, CSS, JS  
- `application.properties` → Configurações do Spring Boot

---

## Contribuições

Se você quiser testar, melhorar ou adicionar algo, fique à vontade! 
