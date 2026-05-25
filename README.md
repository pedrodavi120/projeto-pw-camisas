# APEX SHIRTS 🚀

![Logo APEX SHIRTS](template-apex-static/images/logo-apex-branco.png)

Bem-vindo ao **APEX SHIRTS**, uma loja virtual (e-commerce) de camisetas premium desenvolvida como prova prática de Programação Web.

---

## 📋 Funcionalidades Principais

- 🛍️ **Navegação e Compra:** Vitrine de camisetas com visualização de detalhes e sistema de carrinho de compras baseado em sessão HTTP.
- 👨‍💼 **Painel Administrativo:** CRUD completo de produtos (cadastro, edição, listagem e exclusão).
- 🔐 **Validações:** Validação rigorosa de campos (como SKU, preço e tamanho) com mensagens de feedback amigáveis.
- 🧪 **Testes:** Cobertura com testes unitários de endpoints e integração com banco de dados.

---

## 🚀 Tecnologias Utilizadas

- **Backend:** Java 21+, Spring Boot, JPA/Hibernate, Maven, JUnit 5
- **Frontend:** HTML5, CSS3, Bootstrap 5, Custom premium styles (gradients & modern typography)
- **Banco de Dados:** PostgreSQL 14+

---

## 🏗️ Estrutura e Arquitetura

A aplicação segue uma arquitetura em camadas padrão (`Controller`, `Service`, `Repository`, `Entity`, `DTO`):

```text
ufrn.br.provaPW/
├── src/main/java/ufrn/br/provaPW/
│   ├── controller/        # Controladores web (Index, Carrinho, Admin, Cadastro)
│   ├── entity/            # Entidades JPA (Produto)
│   ├── repository/        # Interfaces de persistência
│   ├── service/           # Lógica de negócios
│   └── dto/               # CamisetaDTO para validações
└── src/main/resources/
    ├── application.properties
    ├── static/            # CSS, JS, imagens (logo, cards)
    └── templates/         # HTMLs Thymeleaf (carrinho, cadastro, etc.)
```

---

## 📦 Como Executar

### Pré-requisitos
- JDK 21+
- PostgreSQL ou Docker
- Maven 3.6+

### Opção 1: Com Docker (Recomendado)
```bash
# Iniciar o banco de dados PostgreSQL
docker-compose up -d

# Compilar e executar o projeto
mvn spring-boot:run
```

### Opção 2: Sem Docker
1. Certifique-se de que o PostgreSQL está rodando localmente.
2. Configure as credenciais no arquivo `src/main/resources/application.properties`.
3. Execute o comando:
```bash
mvn spring-boot:run
```

Acesse a aplicação em: **[apex-shirts.onrender.com](https://apex-shirts.onrender.com)**
