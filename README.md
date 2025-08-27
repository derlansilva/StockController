# Sistema de Gestão de Estoque em Java com Spring Boot

Este é um projeto de API RESTful para o gerenciamento de estoque de produtos. Ele foi desenvolvido com Java e o framework Spring Boot, utilizando o JDBC para a persistência de dados em um banco de dados MySQL.

A API permite realizar operações de CRUD (Criar, Ler, Atualizar, Deletar) em produtos e gerenciar as movimentações de estoque, como entrada, saída e transferências entre diferentes tipos de estoque.

## 🚀 Funcionalidades

O sistema oferece os seguintes endpoints e funcionalidades:

### **Produtos**

* `POST /product`: Cria um novo produto no sistema.
* `GET /product/{sku}`: Busca e retorna as informações de um produto pelo seu SKU.
* `GET /product/all`: Lista todos os produtos cadastrados.
* `DELETE /products/{sku}`: Deleta um produto do sistema.

### **Estoque**

* `PUT /products/entry/{sku}/`: Realiza uma movimentação de entrada para um produto específico.
* `PUT /products/exit/{sku}`: Realiza uma movimentação de saida para um produto específico.
* `POST /stocks/transfer`: Realiza uma transferência de estoque entre diferentes tipos (ex: de disponível para reservado). O corpo da requisição deve especificar a origem, destino, SKU e quantidade.
* `PUT /products/{sku}/movement`: Realiza uma movimentação de estoque (entrada, saída, etc.) para um produto específico.


## 🛠️ Tecnologias Utilizadas

* **Java 17+**: Linguagem de programação.
* **Spring Boot**: Framework para o desenvolvimento da API.
* **Spring Data JDBC**: Para a comunicação com o banco de dados.
* **MySQL**: Sistema de gerenciamento de banco de dados.
* **Maven**: Ferramenta de automação de build e gerenciamento de dependências.

## ⚙️ Como Executar o Projeto

Siga estes passos para configurar e rodar a aplicação localmente:

### **Pré-requisitos**

* JDK 17 ou superior.
* MySQL 8.0 ou superior instalado e rodando.
* Maven instalado.

### **Passo 1: Configuração do Banco de Dados**

1.  Crie um banco de dados MySQL para o projeto.
2.  Atualize o arquivo `src/main/resources/application.properties` com as suas credenciais do banco de dados:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_seu_banco?useSSL=false&serverTimezone=UTC
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update
    ```

### **Passo 2: Configuração e Execução**

1.  Clone o repositório para a sua máquina local.
2.  Navegue até a pasta raiz do projeto.
3.  Execute a aplicação a partir da linha de comando usando o Maven:

    ```bash
    mvn spring-boot:run
    ```

    O servidor da API estará disponível em `http://localhost:8080`.

## 📈 Diagrama de Classes

A arquitetura do projeto é organizada em classes com responsabilidades bem definidas, seguindo princípios de Clean Code. O diagrama abaixo ilustra as principais entidades e seus relacionamentos:

```mermaid
classDiagram
    class Product {
      -Long id
      -String sku
      -String description
      -String price
    }

    class Stock {
      -Long id
      -Long productId
      -int availableQuantity
      -int reservedQuantity
      -int lostQuantity
    }

    class StockMovement {
      -Long id
      -Long productId
      -String movementType
      -int quantity
      -LocalDateTime movementDate
    }

    class MovementType {
      <<enum>>
      ENTRADA
      SAIDA
      RESERVA
    }

    Product "1" -- "1" Stock: has >
    Product "1" -- "0..*" StockMovement: has >
    StockMovement "1" -- "1" MovementType: has >
