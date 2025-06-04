# Sistema de Controle de Equipamentos

Um sistema web para gerenciamento de equipamentos e usuários, desenvolvido com Spring Boot e Thymeleaf.

## 🚀 Funcionalidades

- **Gestão de Equipamentos**
  - Cadastro de equipamentos com nome, descrição e quantidade
  - Edição e exclusão de equipamentos
  - Visualização em tabela com busca integrada

- **Gestão de Usuários**
  - Cadastro de usuários com nome e email
  - Edição e exclusão de usuários
  - Visualização em tabela com busca integrada

- **Interface Moderna**
  - Design responsivo
  - Animações suaves
  - Notificações toast para feedback
  - Ícones intuitivos

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Thymeleaf
- Bootstrap 5
- Bootstrap Icons
- MySQL

## 📋 Pré-requisitos

- Java 17 ou superior
- Maven
- MySQL

## 🔧 Configuração

1. Clone o repositório:
```bash
git clone https://github.com/julialet/cfg-estoque.git
```

2. Configure o banco de dados MySQL:
   - Crie um banco de dados chamado `controle_equipamentos`
   - As configurações de conexão estão em `application.properties`

3. Execute o projeto:
```bash
mvn spring-boot:run
```

4. Acesse a aplicação:
   - Abra seu navegador
   - Acesse `http://localhost:8080`

## 📝 Estrutura do Projeto

```
demo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/controleequipamentos/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       └── service/
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/
│   │       │   └── js/
│   │       └── templates/
└── pom.xml
```

## 👩‍💻 Desenvolvido por

Julia Hille
Breno Werlang
Eduarda Petzinger

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes. 
