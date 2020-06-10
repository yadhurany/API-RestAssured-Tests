package restricoes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import test.BaseAPI;

import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.equalTo;
import static test.CPF.createCPF;


public class RestricoesAPITests extends BaseAPI {

    @Test
    public void naoPossuiRestricaoCPF() {
        String cpf = createCPF();
        given().
                when().
                get("/restricoes/" + cpf).
                then().
                assertThat().
                statusCode(204);
    }

    @ParameterizedTest
    @MethodSource("data")
    public void possuiRestricaoCPF(String cpf) {
        given().
                when().
                get("/restricoes/" + cpf).
                then().
                assertThat().
                statusCode(200).
                body("mensagem", equalTo("O CPF " + cpf + " possui restrição")); // FIXME: equalTo("O CPF " + cpf + " tem problema")
    }

    private static String[] data() {
        return new String[] {
            "97093236014",
            "60094146012",
            "84809766080",
            "62648716050",
            "26276298085",
            "01317496094",
            "55856777050",
            "19626829001",
            "24094592008",
            "58063164083",
        };
    }

}
