# Stock Alert Microservices

Um sistema que monitora ações usando a [API Brapi](https://brapi.dev/) 

## 🔗 Repositório

[https://github.com/kaikinattandossantos/Consumindo_API](https://github.com/kaikinattandossantos/Consumindo_API.git)

## 🏗 Arquitetura e Microsserviços


### `acoes-service`
- **Nome do serviço:** `acoes-service`  
- **Responsável por:**  
  - Consultar preços de ações via API Brapi  
  - Salvar e atualizar ações favoritas no banco de dados  
  - Oferecer endpoints REST para gerenciar ações e alertas  

- **Endpoints principais:**  
  | Método | Endpoint | Descrição |
  |--------|----------|-----------|
  | POST | `/api/acoes-favoritas` | Criar nova ação favorita |
  | GET | `/api/acoes-favoritas` | Listar todas ações favoritas |
  | GET | `/api/acoes-favoritas/{id}` | Buscar ação por ID |
  | PUT | `/api/acoes-favoritas/{id}` | Atualizar ação |
  | DELETE | `/api/acoes-favoritas/{id}` | Deletar ação |
  | GET | `/api/acoes-favoritas/detalhes` | Listar ações favoritas com preços detalhados |





🛠 Tecnologias

Java + Spring Boot

Banco de Dados: MySQL / H2 / PostgreSQL

Cache: Spring Cache (simples)

API de ações: Brapi


⚙ Configurações
acoes-service (application.properties)
```
# Nome único do serviço
spring.application.name=acoes-service

# Conexão com banco de dados
spring.datasource.url=jdbc:mysql://localhost:3306/acoes_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=senha123

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Cache
spring.cache.type=simple

# API externa
brapi.api.token=6trn4ZVWsH8pPCHPt8YvDy
