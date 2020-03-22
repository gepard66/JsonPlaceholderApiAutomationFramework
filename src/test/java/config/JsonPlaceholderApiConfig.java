package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

public class JsonPlaceholderApiConfig {

    public static RequestSpecification jsonPlaceholderRequestSpec;

    @BeforeClass
    public static void setup() {
        jsonPlaceholderRequestSpec = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .build();

        RestAssured.requestSpecification = jsonPlaceholderRequestSpec;
    }
}
