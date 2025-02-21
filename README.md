# Job Scraper API com Spring Boot 🚀

## Descrição
Job Scraper é um sistema desenvolvido em **Java** com **Spring Boot** que busca estágios na área de Informática para ensino superior, filtrando vagas na cidade de **Brasília - DF**. O sistema faz scraping em sites de emprego e exibe as vagas encontradas, permitindo acesso rápido por meio do código da vaga.

## Tecnologias Utilizadas
- **Java 21**
- **Spring Boot** (Web, Data JPA, Scheduler)
- **Thymeleaf** (para renderização de páginas)
- **MySQL** (banco de dados)
- **JPA/Hibernate** (para persistência de dados)
- **JUnit 5** (para testes)
- **Mockito** (para mock de serviços e repositórios)
- **Selenium/Jsoup** (para Web Scraping)
- **Git/GitHub** (para controle de versão)

## Funcionalidades
- Busca automática de estágios na área de Informática em Brasília - DF
- Filtragem por tipo de vaga, nível de ensino e localização
- Exibição das vagas encontradas com código para fácil acesso
- Interface minimalista e responsiva
- Testes automatizados para garantir estabilidade

## Como Executar o Projeto
### 1. Clonar o Repositório
```bash
git clone https://github.com/gustavolimaf/job-scraper.git
cd job-scraper
```

### 2. Configurar o Banco de Dados
No arquivo `src/main/resources/application.properties`, configure seu banco de dados MySQL:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/job_scraper
spring.datasource.username=root
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
```

Crie o banco de dados:
```sql
CREATE DATABASE job_scraper;
```

### 3. Executar a Aplicação
```bash
mvn spring-boot:run
```
Acesse a aplicação em: [http://localhost:8080](http://localhost:8080)

### 4. Rodar os Testes
```bash
mvn test
```

## Estrutura do Projeto
```
job-scraper/
├── src/
│   ├── main/
│   │   ├── java/com/example/job_scraper/
│   │   │   ├── controller/
│   │   │   │   ├── VacancyController.java
│   │   │   ├── model/
│   │   │   │   ├── Vacancy.java
│   │   │   ├── repository/
│   │   │   │   ├── VacancyRepository.java
│   │   │   ├── service/
│   │   │   │   ├── ScrapingService.java
│   │   │   │   ├── ScrapingServiceImpl.java
│   │   │   ├── scheduler/
│   │   │   │   ├── JobScheduler.java
│   │   │   ├── JobScraperApplication.java
│   │   ├── resources/
│   │   │   ├── application.properties
│   ├── test/
│   │   ├── java/com/example/job_scraper/
│   │   │   ├── controller/VacancyControllerTest.java
│   │   │   ├── service/ScrapingServiceTest.java
│   │   │   ├── repository/VacancyRepositoryTest.java
├── pom.xml
├── README.md
```

## Contribuição
Sinta-se à vontade para contribuir! Para isso:
1. Fork o repositório
2. Crie uma branch (`git checkout -b feature-nova`)
3. Commit suas alterações (`git commit -m 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature-nova`)
5. Abra um Pull Request

## Licença
Este projeto está sob a [MIT License](LICENSE).

