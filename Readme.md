# 🛒 BEX - E-commerce Platform

Uma plataforma de e-commerce moderna desenvolvida em Java com Spring Boot, implementando **CQRS** e **Event Sourcing** para alta escalabilidade e auditabilidade.

---

## 🏗️ Arquitetura

### Padrões Arquiteturais Implementados
- **CQRS (Command Query Responsibility Segregation)**: Separação clara entre operações de escrita (Commands) e leitura (Queries)
- **Event Sourcing**: Todos os estados importantes são persistidos como sequência imutável de eventos
- **Redis Cache**: Cache estratégico para otimização de performance
- **Domain-Driven Design (DDD)**: Modelagem baseada em domínios ricos com Value Objects
- **Clean Architecture**: Separação em camadas bem definidas

---

### Estrutura do Projeto

```text
src/main/java/com/ecommerce/bex/
├── controller/ # APIs REST com separação por interface/implementação
├── service/
│ ├── command/ # Serviços de escrita (Commands)
│ └── query/ # Serviços de leitura (Queries)
├── model/ # Entidades de domínio (Aggregates)
│ └── valueobjects/ # Value Objects imutáveis
├── event/ # Eventos do sistema
├── command/ # Objetos de comando (DTOs de entrada)
├── dto/ # Data Transfer Objects (DTOs de saída)
├── repository/ # Repositórios JPA
├── enums/ # Enumerations do domínio
├── exception/ # Exceções customizadas
├── mapper/ # Mapeamento entre objetos (MapStruct)
├── security/ # Configurações de segurança
├── handler/ # Tratamento global de exceções
└── util/ # Constantes e utilitários
```

---

## 🚀 Funcionalidades

### 🔐 Autenticação & Autorização
- ✅ Registro e login de usuários com validação
- ✅ Geração de tokens JWT
- ✅ Controle de acesso baseado em roles (USER, SELLER, ADMIN)
- ✅ Spring Security integrado com endpoints protegidos

### 🛍️ Gestão de Produtos
- ✅ Cache Redis para produtos individuais e por categoria
- ✅ CRUD completo de produtos
- ✅ Categorização de produtos (15 categorias disponíveis)
- ✅ Controle de estoque com decremento automático
- ✅ Atualização de preços e informações
- ✅ Busca por categoria e ID

### 🛒 Carrinho de Compras
- ✅ Cache Redis para carrinho do usuário
- ✅ Adicionar/remover itens do carrinho
- ✅ Cálculo automático de totais
- ✅ Carrinho persistido por usuário
- ✅ Limpeza automática após finalização do pedido
- ✅ Validação de estoque

### 📦 Sistema de Pedidos
- ✅ Cache Redis para pedidos individuais e listas
- ✅ Criação de pedidos a partir do carrinho
- ✅ Fluxo de status (PENDING → PAID → SENT → RECEIVED)
- ✅ Endereço de entrega automático do usuário
- ✅ Histórico de pedidos por usuário
- ✅ Cancelamento de pedidos com motivo

### 📊 Event Sourcing & Auditoria
- ✅ Auditoria completa de todas as ações do sistema
- ✅ Eventos imutáveis com timestamp preciso
- ✅ Consulta de eventos por usuário, aggregate ou intervalo temporal
- ✅ Replay de eventos para reconstrução de estado
- ✅ Tipos de aggregates: ORDER, CART, PRODUCT

---

## 🛠️ Stack Tecnológica

- **Java 21** - Linguagem de programação
- **Spring Boot 3.5.6** - Framework principal
- **Spring Security 6** - Autenticação e autorização
- **JPA/Hibernate** - ORM e persistência
- **PostgreSQL** - Banco de dados principal
- **Redis** - Cache em memória
- **Docker** - Containerização e orquestração
- **Docker Compose** - Orquestração de múltiplos containers
- **JWT** - Tokens de autenticação
- **MapStruct** - Mapeamento entre objetos
- **Lombok** - Redução de boilerplate
- **SpringDoc OpenAPI 3** - Documentação da API
- **Maven** - Gerenciamento de dependências
- **H2 Database** - Banco em memória para testes

---

## 📌 Módulos Principais (Controllers)

A API é organizada em módulos funcionais, cada um com sua própria `Controller` e `API Interface`:

| Módulo | Descrição | Endereço Base (Exemplo) | Acesso |
| :--- | :--- | :--- | :--- |
| **Auth** | Autenticação e registro de usuários. | `/api/v1/auth` | Público |
| **Product** | Gestão e listagem do catálogo de produtos. | `/api/v1/products` | Geral (Leitura); Vendedor/Admin (Escrita) |
| **Cart** | Gerenciamento do carrinho de compras do usuário autenticado. | `/api/v1/carts` | Autenticado |
| **Order** | Criação e rastreamento de pedidos. | `/api/v1/orders` | Autenticado (Criação/Meus Pedidos); Vendedor/Admin (Status/Todos) |
| **Event** | Consulta do *Event Store* para rastreamento de eventos no sistema. | `/api/v1/events` | Autenticado (Meus Eventos); Admin (Todos os Eventos/Filtros) |

---

## 🛠️ Tratamento de Erros (GlobalHandlerException)

O tratamento de erros é centralizado no `GlobalHandlerException`, que garante respostas padronizadas e informativas, mapeando exceções específicas para seus respectivos códigos HTTP:

| Exceção                           | Código HTTP        | Descrição |
|:----------------------------------|:-------------------| :--- |
| `UserAlreadyExistsException`      | `409 Conflict`     | Credenciais de registro já em uso. |
| `FailedLoginAttemptException`     | `400 Bad Request`  | Falha ao logar (credenciais inválidas). |
| `AccessDeniedException`           | `401 Unauthorized` | Tentativa de acesso sem as permissões necessárias. |
| `ProductNotFoundException`        | `404 Not Found`    | Produto não encontrado. |
| `OrderNotFoundException`          | `404 Not Found`    | Pedido não encontrado. |
| `CartNotFoundException`           | `404 Not Found`    | Carrinho não encontrado. |
| `ProductAlreadyReceivedException` | `400 Bad Request`  | Status do pedido já finalizado. |
| `InvalidCPFNumberException`       | `400 Bad Request`  | CPF com formato ou dígitos inválidos |
| `InvalidEmailException`           | `400 Bad Request`  | Email com formato inválido |
| `InvalidPasswordException`        | `400 Bad Request`  | Senha não atende aos requisitos de complexidade |
| `InvalidZipcodeNumberException`   | `400 Bad Request`  | CEP com formato inválido |
| `ShortPasswordException`          | `400 Bad Request`  | Senha muito curta (< 6 caracteres) |
| `SmallPrivilegesException`        | `400 Bad Request`  | Tentativa de modificar usuário ADMIN |
| `ShortUsernameException`          | `400 Bad Request`  | Nome de usuário muito curto (< 3 caracteres) |
| `UserNotFoundException`           | `404 Not Found`    | Usuário não encontrado no sistema |
| ... e outras                      |                    | |

---

## 📚 API Endpoints

### 🔐 Módulo de Autenticação (`/api/v1/auth`)

| Método | Endpoint    | Descrição | Acesso |
| :--- |:------------| :--- | :--- |
| `POST` | `/register` | Registra um novo usuário no sistema. | Público |
| `POST` | `/login`    | Realiza o login e retorna o token de autenticação (JWT). | Público |

### 🛍️ Módulo de Produtos (`/api/v1/products`)

| Método | Endpoint                      | Descrição | Acesso |
| :--- |:------------------------------| :--- | :--- |
| `GET` | `"` ou `/`                     | Lista todos os produtos (com paginação). | Todos |
| `GET` | `/{id}`                       | Busca um produto específico pelo ID. | Todos |
| `GET` | `/category/{productCategory}` | Lista produtos filtrados por categoria. | Todos |
| `POST` | `/`                           | Cria um novo produto no catálogo. | `SELLER`, `ADMIN` |

### 🛒 Módulo de Carrinho (`/api/v1/carts`)

| Método | Endpoint  | Descrição | Acesso |
| :--- |:----------| :--- | :--- |
| `POST` | `/`       | Adiciona um item ao carrinho do usuário autenticado. | `USER` |
| `GET` | `"` ou `/` | Lista todos os itens do meu carrinho. | `USER` |

### 📦 Módulo de Pedidos (`/api/v1/orders`)

| Método | Endpoint    | Descrição | Acesso |
| :--- |:------------| :--- | :--- |
| `POST` | `/`         | Cria um novo pedido a partir do carrinho do usuário. | `USER` |
| `GET` | `"`  ou `/`  | Lista todos os pedidos do usuário autenticado. | `USER` |
| `PUT` | `/upstatus` | Avança o status de um pedido para a próxima etapa. | `SELLER`, `ADMIN` |

### 📊 Módulo de Eventos (Event Sourcing) (`/api/v1/events`)

| Método | Endpoint                         | Descrição | Acesso |
| :--- |:---------------------------------| :--- | :--- |
| `GET` | `"` ou `/`                        | Busca eventos relacionados ao usuário autenticado. | `USER` |
| `POST` | `/my-events-in-interval`         | Busca meus eventos em um intervalo de datas. | `USER` |

### Módulo de Usuários (`/users`)

| Método | Endpoint           | Descrição                                | Acesso |
| :--- |:-------------------|:-----------------------------------------| :--- |
| `GET` | `/`                | Lista todos os usuários (paginação)      | `ADMIN` |
| `PUT` | `/change-username` | Atualiza username do usuário autenticado | `USER`, `SELLER`, `ADMIN` |
| `PUT` | `/change-email`    | Atualiza email do usuário autenticado    | `USER`, `SELLER`, `ADMIN` |
| `PUT` | `/change-address`  | Atualiza endereço do usuário autenticado | `USER`, `SELLER`, `ADMIN` |


## Administração (`/admin`)

| Módulo | Endpoint | Parâmetros | Descrição |
| :--- | :--- | :--- | :--- |
| **Carts** | `GET /admin/carts` | `?id=123` | Busca carrinhos - ID específico ou todos |
| **Events** | `GET /admin/events` | `?userId=456&aggregateId=789&aggregateType=ORDER` | Busca eventos com múltiplos filtros |
| **Orders** | `GET /admin/orders` | `?id=999` | Busca pedidos - ID específico ou todos |
| **Users** | `GET /admin/users` | `?username=joao&email=joao@email.com` | Busca usuários por username/email |
| **Users** | `PUT /admin/username` | - | Atualiza username de qualquer usuário |

### 🔍 Sistema de Busca Inteligente

Todos os endpoints administrativos seguem um **padrão de prioridade** para filtros:

- **Carts**: `id` → todos
- **Events**: `userId` → `aggregateId` → `aggregateType` → todos
- **Orders**: `id` → todos
- **Users**: `username` → `email` → todos

---

## 🔒 Segurança e Autorização

O projeto utiliza **Spring Security** e anotações `@PreAuthorize` para gerenciar permissões, que se baseiam em *Roles* de usuário.

### Roles de Usuário

| Role | Descrição |
| :--- | :--- |
| `USER_ROLE` | Usuário padrão, pode logar, registrar, gerenciar seu carrinho e pedidos, e visualizar produtos. |
| `SELLER_ROLE` | Vendedor, possui permissão adicional para criar produtos e atualizar o status de pedidos. |
| `ADMIN_ROLE` | Administrador, possui acesso total a todos os endpoints do sistema. |

### Controles de Acesso
- Todas as rotas exigem autenticação (exceto login/registro e Swagger)
- Controle granular por método e role usando @PreAuthorize
- Validação de permissões em tempo de execução
- Proteção contra ataques CSRF

### Sistema de Value Objects com Validação Avançada

#### 📧 **Email Validation**
- **Regex**: `^[a-zA-Z]+[a-zA-Z0-9._]+@[a-zA-Z]+[a-zA-Z0-9._]+\.[a-zA-Z]{2,}$`
- **Validações**: Formato correto, caracteres permitidos, domínio válido

#### 🔢 **CPF Validation**
- **Validação completa**: Dígitos verificadores, formato, sequências inválidas
- **Máscara automática**: Aceita formatos 000.000.000-00 ou 00000000000
- **Algoritmo**: Cálculo dos dois dígitos verificadores

#### 🔐 **Password Validation**
- **Mínimo**: 6 caracteres
- **Regex**: `^(?=.*\d)(?=.*[A-Z])[a-zA-Z\d!@#$%&_+*^~]+$`
- **Requisitos**: Pelo menos 1 número, 1 letra maiúscula, caracteres especiais opcionais

#### 📮 **Zipcode Validation**
- **Formato**: 00000-000 ou 00000000
- **Máscara**: Formatação automática para exibição

#### 👤 **Username Validation**
- **Mínimo**: 3 caracteres
- **Validação**: Não nulo, tamanho adequado

---

## 🎯 Modelagem de Domínio
### Aggregates Principais
- **User**: Gerencia informações de usuário e autenticação
- **Product**: Representa produtos com informações detalhada
- **Cart**: Gerencia itens do carrinho de compras
- **Order**: Controla o fluxo completo de pedidos

### Value Objects
- **UserInformation**: Agrega dados do usuário
- **ProductInformation**: Agrega dados do produto
- **Address**: Endereço completo para entrega
- **Email, CPF, Password, Username**: Objetos de valor com validação

### Eventos Implementados
- Product: Created, Deleted, PriceChanged, StockUpdated
- Cart: ItemAdded, ItemRemoved, CartCleared
- Order: Created, Cancelled, StatusChanged

---

## 🚀 Benefícios da Arquitetura

A arquitetura do `bex-ecommerce-api` foi projetada para garantir performance, escalabilidade e auditabilidade utilizando padrões modernos:

### 🏗️ CQRS (Command Query Responsibility Segregation)

| Benefício | Descrição |
| :--- | :--- |
| ✅ **Otimização Independente** | Permite otimizar os modelos de leitura (Query) e escrita (Command) de forma isolada. |
| ✅ **Escalabilidade Horizontal** | Facilita a escalabilidade das operações de leitura, que geralmente são mais frequentes. |
| ✅ **Modelos de Consulta Especializados** | Modelos de dados de leitura (`QueryService`) são otimizados especificamente para as DTOs de resposta, simplificando consultas complexas. |
| ✅ **Separação de Responsabilidades** | Separação clara do código que altera o estado (Comandos) daquele que apenas consulta o estado (Consultas). |

### 📊 Event Sourcing

| Benefício | Descrição |
| :--- | :--- |
| ✅ **Auditabilidade Completa** | O `EventStore` registra cada mudança como um evento, fornecendo um log imutável e completo de todas as ações no sistema. |
| ✅ **Resiliência e Replay** | Possibilidade de reconstruir o estado atual do agregado a partir de seus eventos, garantindo resiliência. |
| ✅ **Transparência Operacional** | Visão em tempo real de tudo o que acontece na aplicação (módulo `Event`). |
| ✅ **Foundation para Analytics** | Os eventos são uma fonte de dados rica e imediata para sistemas de analytics e relatórios complexos. |

### 🎨 DDD (Domain-Driven Design) & Clean Architecture

| Benefício | Descrição |
| :--- | :--- |
| ✅ **Domínios Ricos** | Entidades e Value Objects (como `UserInformation`, `Address`) bem modelados, encapsulando regras de negócio. |
| ✅ **Código Expressivo e Mantenível** | A estrutura de pacotes e a separação de lógica levam a um código mais limpo e fácil de entender. |
| ✅ **Testabilidade Aprimorada** | Lógica de negócio isolada das camadas de infraestrutura (frameworks, banco de dados). |
| ✅ **Baixo Acoplamento** | As camadas de domínio são desacopladas das camadas externas (controllers, persistência). |

---

## 🔄 Fluxo de Pedidos (`Order` Workflow)

Este é o fluxo de alto nível para a criação e processamento de um pedido, com destaque para a geração dos eventos:

1.  **Usuário** adiciona itens ao carrinho.
2.  **Sistema** valida estoque e calcula totais.
3.  **Usuário** finaliza pedido (`POST /orders`).
    * → Gerado: `OrderCreatedEvent`
4.  **Sistema** limpa carrinho.
    * → Gerado: `CartClearedEvent`
5.  **Sistema** decrementa estoque dos produtos comprados.
    * → Gerado: `ProductStockUpdatedEvent`
6.  **Vendedor** atualiza status (`PUT /orders/upstatus`).
    * → Gerado: `OrderStatusChangedEvent` (Ex: `PENDING` → `PAID` → `SENT` → `RECEIVED`).

---

## 🔧 Configuração e Execução

### Pré-requisitos
- Java 21
- Maven 3.6+
- PostgreSQL 14+
- Redis (Docker ou instalado localmente)

### Configuração da Aplicação
Edite `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bexdb
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# Redis Cache
spring.cache.type=redis
spring.redis.host=localhost
spring.redis.port=6379
spring.cache.redis.time-to-live=3600000

api.security.token.secret=sua-chave-secreta-jwt
```

### Executando a Aplicação
```bash
# Clone o repositório
git clone [url-do-repositorio]

# Navegue até o diretório
cd bex

# Iniciar Redis
docker run -d -p 6379:6379 --name bex-redis redis:7-alpine

# Execute a aplicação
mvn spring-boot:run
```

