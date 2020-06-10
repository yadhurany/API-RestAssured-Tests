package simulacoes;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import test.BaseAPI;
import test.Simulacao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.equalTo;
import static test.CPF.createCPF;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SimulacoesAPITests extends BaseAPI {


    private static Integer id;
    private static String TEST_CPF = createCPF();

    @Test
    @Order(0)
    public void criarSimulacaoSucesso() {
        Simulacao simulacao = new Simulacao("Yadhurany", TEST_CPF, "teste@teste.com",
                new BigDecimal(20000.00), 5, false);
        id = given().
                contentType(ContentType.JSON).
                body(simulacao).
                post("/simulacoes").
                then().
                statusCode(201).
                extract().
                path("id");
    }

    @Test
    @Order(1)
    public void criarSimulacaoCPFjaExiste() {
        Simulacao simulacao = new Simulacao("Yadhurany", TEST_CPF, "teste@teste.com",
                new BigDecimal(20000.00), 5, false);
        given().
                contentType(ContentType.JSON).
                body(simulacao).
                post("/simulacoes").
                then().
                statusCode(409). // FIXME: statusCode(400)
                body("mensagem", equalTo("CPF já existente")); // FIXME: equalTo("CPF duplicado"))
    }

    @Test
    @Order(3)
    public void alterarSimulacaoSucesso() {
        Simulacao simulacao = new Simulacao("Yadhurany", TEST_CPF, new Date().getTime() + "_teste@teste.com",
                new BigDecimal(20000.00), 5, false);
        given().
                contentType(ContentType.JSON).
                body(simulacao).
                put("/simulacoes/" + TEST_CPF).
                then().
                statusCode(200);
    }

    @Test
    @Order(4)
    public void alterarSimulacaoCPFsemSimulacao() {
        Simulacao simulacao = new Simulacao("Yadhurany", "17265508009", new Date().getTime() + "_teste@teste.com",
                new BigDecimal(20000.00), 5, false);
        given().
                contentType(ContentType.JSON).
                body(simulacao).
                put("/simulacoes/17265508009").
                then().
                statusCode(404);
    }

    @Test
    @Order(5)
    public void criarSimulacaoErro() {
        Simulacao simulacao = new Simulacao("Yadhurany", null, "teste@teste.com",
                new BigDecimal(20000.00), 5, false);
        given().
                contentType(ContentType.JSON).
                body(simulacao).
                post("/simulacoes").
                then().
                statusCode(400);
    }

    @Test
    @Order(6)
    public void consultarTodasSimulacoes() {
        given().
                when().
                get("/simulacoes").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    @Order(7)
    public void consultarSimulacaoCPF() {
        given().
                when().
                get("/simulacoes/" + TEST_CPF).
                then().
                statusCode(200);
    }

    @Test
    @Order(8)
    public void consultarSimulacaoCPFnaoEncontrada() {
        given().
                when().
                get("/simulacoes/40399016066").
                then().
                statusCode(404);
    }

    @Test
    @Order(9)
    public void removerUmaSimulacaoSucesso() {
        given().
                when().
                delete("/simulacoes/" + id).
                then().
                statusCode(204); // FIXME: statusCode(200)
    }

    @Test
    @Order(10)
    public void removerUmaSimulacaoNaoEncontrada() {
        given().
                when().
                delete("/simulacoes/" + id).
                then().
                statusCode(404). // FIXME: statusCode(400)
                body("mensagem", equalTo("Simulação não encontrada")); // FIXME: remover esta linha
    }

    @Test
    @Order(11)
    public void consultarTodasSimulacoesVazio() {
        List<Integer> ids = given().
                when().
                get("/simulacoes").
                then().
                assertThat().
                statusCode(200).
                extract().
                path("id");

        for (Integer id : ids) {
            given().delete("/simulacoes/" + id);
        }

        given().
                when().
                get("/simulacoes").
                then().
                statusCode(404); // FIXME: statusCode(200)
    }


}
