# Stock Alert Microservices

Um sistema de **microsserviços** que monitora ações usando a [API Brapi](https://brapi.dev/) e envia notificações por e-mail quando o preço de uma ação atinge um valor específico definido pelo usuário.

## 🔗 Repositório

[https://github.com/kaikinattandossantos/Consumindo_API](https://github.com/kaikinattandossantos/Consumindo_API.git)

## 🏗 Arquitetura e Microsserviços

O projeto é composto por **dois microsserviços** principais:

### 1. `acoes-service`
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

### 2. `email-service`
- **Nome do serviço:** `email-service`  
- **Porta:** `8090`  
- **Responsável por:**  
  - Receber requisições do `acoes-service`  
  - Disparar e-mails quando o preço da ação atingir o valor definido  

- **Configurações importantes:**  
  - AWS SES ou outro serviço SMTP  
  - Credenciais devem ser configuradas como **variáveis de ambiente** ou via **IAM Role**  

### 🔄 Comunicação entre microsserviços
O `acoes-service` chama o `email-service` via HTTP usando a URL configurada:  

```properties
email.service.url=http://localhost:8090
```
Fluxo completo:

Usuário cadastra uma ação favorita com valor-alvo no acoes-service.

O acoes-service verifica os preços via Brapi.

Quando o preço atinge o valor-alvo, o acoes-service chama o email-service.

O email-service envia o e-mail para o usuário.

🛠 Tecnologias

Java + Spring Boot

Banco de Dados: MySQL / H2 / PostgreSQL

Cache: Spring Cache (simples)

API de ações: Brapi

Envio de e-mails: AWS SES / SMTP

Arquitetura: Microsserviços + Feign Client

Monitoramento: Logs via Logback ou CloudWatch

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

# Serviço de e-mail
email.service.url=http://localhost:8090

# Função Lambda
spring.cloud.function.definition=verificarPrecos


```
email-service (application.properties)
```
# Nome do serviço
spring.application.name=email-service

# Porta do serviço
server.port=8090

# AWS SES (não colocar chaves diretamente)
aws.region=us-east-1
aws.ses.source-email=seu-email@exemplo.com
Importante: Chaves da AWS devem ser configuradas como variáveis de ambiente ou via IAM Role.
```
