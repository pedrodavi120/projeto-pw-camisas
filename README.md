# Projeto APEX SHIRTS 🚀

![Logo APEX SHIRTS](template-apex-static/images/logo-apex.png)

Bem-vindo ao projeto **APEX SHIRTS**! Esta é uma aplicação web completa desenvolvida como prova prática de Programação Web, utilizando tecnologias modernas e arquitetura robusta.

---

## 📋 Sobre o Projeto

O **APEX SHIRTS** é uma loja virtual (e-commerce) especializada em camisetas premium. A aplicação permite:

- 🛍️ Navegação e visualização de produtos
- 🛒 Adicionar produtos ao carrinho de compras
- 🛒 Finalizar compras (integração futura planejada)
- 👨‍💼 Cadastro e gerenciamento de produtos (Admin)
- 🔐 Validação de dados e persistência no banco de dados

---

## 🚀 Tecnologias Utilizadas

### Backend

- ☕ **Java 21+** - Linguagem de programação
- 🏛️ **Spring Boot** - Framework para desenvolvimento backend
- 💾 **PostgreSQL** - Banco de dados relacional
- 📦 **Thymeleaf** - Motor de templates para renderização de HTML
- 🗄️ **JPA/Hibernate** - Mapeamento objeto-relacional
- 🛠️ **Maven** - Gerenciamento de dependências e build
- 🧪 **JUnit 5** - Testes unitários

### Frontend

- 🎨 **HTML5/CSS3** - Estrutura e estilização semântica
- 🎨 **Bootstrap 5** - Framework CSS para componentes responsivos
- 🎨 **Custom Styles** - Design moderno com gradients e tipografia premium

---

## 🏗️ Arquitetura do Projeto

O projeto segue arquitetura em camadas com separação clara de responsabilidades:

```
ufrn.br.provaPW/
├── src/main/java/ufrn/br/provaPW/
│   ├── controller/        # Controladores web
│   ├── config/            # Configurações do Spring
│   ├── entity/            # Entidades JPA (modelos de domínio)
│   ├── repository/        # Repositórios (interação com banco)
│   ├── service/           # Lógica de negócio
│   ├── dto/               # Data Transfer Objects
│   └── util/              # Utilitários e helpers
├── src/main/resources/
│   ├── application.properties
│   ├── static/            # Arquivos estáticos (CSS, JS, imagens)
│   └── templates/         # Arquivos Thymeleaf
└── docker-compose.yml     # Configuração do Docker
```

---

## 📦 Como Executar

### Pré-requisitos

- ☕ Java Development Kit (JDK) 21 ou superior
- 🗄️ PostgreSQL 14 ou superior (ou Docker)
- 🛠️ Maven 3.6 ou superior

### Opção 1: Com Docker (Recomendado)

O projeto inclui um arquivo `docker-compose.yml` para facilitar a configuração do banco de dados:

```bash
# Iniciar o banco de dados com Docker
docker-compose up -d

# Compilar e rodar a aplicação
mvn spring-boot:run
```

Acesse: [http://localhost:8080](http://localhost:8080)

### Opção 2: Sem Docker

Certifique-se de ter um banco PostgreSQL instalado e rodando:

```bash
# Configurar as credenciais no application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/prova_pw
spring.datasource.username=postgres
spring.datasource.password=sua_senha

# Compilar e rodar
mvn spring-boot:run
```

Acesse: [http://localhost:8080](http://localhost:8080)

---

## 📂 Estrutura do Projeto

### Entidades (src/main/java/.../entity/)

- `Produto.java` - Modelo da camiseta com:
  - `id` (Long) - Chave primária
  - `codigoSku` (String) - Código único (ex: APX-1024)
  - `nome` (String) - Nome do produto
  - `marca` (String) - Marca da camiseta
  - `descricao` (String) - Descrição detalhada
  - `preco` (BigDecimal) - Preço em formato BigDecimal
  - `tamanho` (String) - Tamanho da camiseta
  - `imgUrl` (String) - URL da imagem

### DTOs (src/main/java/.../dto/)

- `camisetaDTO.java` - Objeto de transferência para validação de dados

### Controladores (src/main/java/.../controller/)

- `IndexController.java` - Página inicial e produtos
- `CarrinhoController.java` - Gerenciamento do carrinho de compras
- `AdministradorController.java` - Dashboard administrativo
- `CadastroController.java` - Cadastro de novos produtos

### Templates (src/main/resources/templates/)

- `index.html` - Página de produtos
- `carrinho.html` - Carrinho de compras
- `administrador.html` - Painel admin
- `cadastro.html` - Formulário de cadastro
- `layout.html` - Layout principal com header e footer

---

## 🎨 Design e Estilo

### Paleta de Cores

- **Cor Principal** - `#1A1A1A` (Preto premium)
- **Cor de Destaque** - `#FF5733` (Laranja neon moderno)
- **Textos** - `#E0E0E0` (Cinza claro)
- **Acentos** - Gradientes com tons de roxo e azul

### Tipografia

- **Fontes** - `'Poppins', sans-serif` e `'Montserrat', sans-serif`
- **Hierarquia visual** - Títulos grandes e bem espaçados

### Componentes

- 🏷️ **Badges** - Gradientes vibrantes para categorias
- 💳 **Cards** - Design moderno com bordas arredondadas
- 🛒 **Botões** - Estilo premium com hover effects
- 📊 **Tabelas** - Design limpo e profissional

---

## 📋 Funcionalidades Implementadas

### Funcionalidades da Aplicação

- [x] Listagem de produtos na página inicial
- [x] Detalhes do produto
- [x] Adicionar produtos ao carrinho (via sessão HTTP)
- [x] Visualização do carrinho de compras
- [x] Cálculo automático do total da compra
- [x] Cadastro de novos produtos
- [x] Validação de dados de entrada
- [x] Edição de produtos (Admin)
- [x] Exclusão de produtos (Admin)
- [x] Sistema de login simulado

### Validações Implementadas

- [x] Nome do produto obrigatório
- [x] Preço maior que zero
- [x] Marca obrigatória
- [x] Tamanho válido (P, M, G, GG)
- [x] Código SKU no formato AAA-9999
- [x] Descrição obrigatória
- [x] Validação de formulário robusta com mensagens amigáveis

### Testes

O projeto inclui testes unitários para:

- 🧪 Testes de endpoint de produtos
- 🧪 Testes de integração com banco de dados

---

## 🔧 Configuração de Desenvolvimento

### Banco de Dados

O arquivo `application.properties` já vem configurado para PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/prova_pw
spring.datasource.username=postgres
spring.datasource.password=postgres
```

Para alterar as credenciais, edite este arquivo:

**ou** utilize as variáveis de ambiente do Docker:

```bash
export DB_USERNAME=seu_usuario
export DB_PASSWORD=sua_senha
docker-compose up -d
```

### Scripts SQL
