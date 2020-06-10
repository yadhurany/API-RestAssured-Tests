package test;

import io.restassured.config.JsonConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.config.JsonPathConfig;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class BaseAPI {

    @BeforeAll
    public static void preCondition() {
        baseURI = "http://localhost";
        basePath = "/api/v1";
        port = 8080;

        config = RestAssuredConfig.newConfig().
                jsonConfig(JsonConfig.jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL));

        enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
