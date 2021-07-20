# api-rv

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação.
Está solução gerencia sessões de votação de uma determinada pauta.

## Começando

Para executar o projeto, será necessário instalar os seguintes programas:

- [JDK 8: Necessário para executar o projeto Java](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
- [Gradle 6.8.2: Necessário para realizar o build do projeto Java](https://gradle.org/next-steps/?version=6.8.2&format=bin)
- [Eclipse: Para desenvolvimento do projeto](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/oxygen3a) ou a IDE de sua preferência
- [Banco de dados PostgresSQL Server v12](https://www.enterprisedb.com/postgresql-tutorial-resources-training?cid=48) aqui é o instalador para Windows

## Tecnologias Utilizadas

- [Spring Boot v2.5.2](https://spring.io/projects/spring-boot)
- [Spring Data JPA v2.5.3](https://spring.io/projects/spring-data-jpa)
- [Spring Boot MVC](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html)

## Features

Tratasse de uma API REST com serviços voltados para gerenciamento de Pautas de Assembleias.

## Documentação

Realizei a documentação da API utilizando [Springfox](http://springfox.github.io/springfox/docs/current/) + [OpenAPI Specification](https://swagger.io/resources/open-api/)

Para acessar a documentação basta inicializar o projeto e acessar {url-base}/swagger-ui/index.html#/

## Configuração

Necessário criar um Database no banco chamado *api-rv*.

Para conseguir executar o projeto, será necessário configurar o arquivo *api-vr/src/main/resources/application.properties*, alterando as configurações de acesso ao Banco de Dados.
