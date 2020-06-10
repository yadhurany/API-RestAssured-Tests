
## Testes de API 

Projeto realizado como Teste para seleção de QA.

#### Objetivo 
- Criar um projeto baseado em Maven na sua IDE de desenvolvimento favorita utilizando a linguagem de programação Java.
- Elaborar seus cenários de testes de acordo com as regras de negócio.
- Realizar a automação dos testes dos cenários.


#### Requisitos para rodar o Projeto 
- `java-jdk 12`;
- `maven 3.6.3`;

#### Como executar o projeto 
```
git clone https://github.com/yadhurany/API-RestAssured-Tests.git
cd API-RestAssured-Tests
mvn dependency:resolve
mvn test
```

#### Cenários de Teste e Descrição de Inconsistências (Bugs)

Disponíveis no arquivo:[Testes](https://github.com/yadhurany/api-restassured/blob/master/Cen%C3%A1rios%20de%20Teste%20e%20Inconsist%C3%AAncias.pdf)

#### Gerar relatório de testes

```
mvn site
```

#### Relatório de testes

Após rodar o comando acima, o relatório estará disponível em api-restassured/target/site/surefire-report.html



