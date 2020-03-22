import config.JsonPlaceholderApiConfig;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class PostsTests extends JsonPlaceholderApiConfig {

    private String jsonBody;

    @Test
    public void getAllPosts() {
        given().
        when().
                get("posts").
        then().
                statusCode(200);
    }

    @Test
    public void getSinglePost() {
        given().
        when().
                get("posts/1").
        then().
                statusCode(200);
    }

    @Test
    public void testSinglePostSchemaJSON() {
        given().
        when().
                get("posts/1").
        then().
                body(matchesJsonSchemaInClasspath("SinglePostJsonSchema.json"));
    }

    @Test
    public void createNewPost() {
        jsonBody =
                        "{\n" +
                        "  \"userId\": 99,\n" +
                        "  \"id\": 99,\n" +
                        "  \"title\": \"Test title\",\n" +
                        "  \"body\": \"Test body\"" +
                        "}";
        given()
                .body(jsonBody).
        when()
                .post("posts").
        then()
                .assertThat().statusCode(201).log().all();
    }

    @Test
    public void modifyExistingPost() {
        jsonBody =
                        "{\n" +
                        "  \"userId\": 97,\n" +
                        "  \"id\": 99,\n" +
                        "  \"title\": \"Test title for put request\",\n" +
                        "  \"body\": \"Test body for put request\"" +
                        "}";
        given()
                .body(jsonBody).
        when()
                .put("posts/99").
        then()
                .assertThat().statusCode(200).log().all();
    }

    @Test
    public void validatePutResponse() {
        jsonBody =
                        "{\n" +
                        "  \"userId\": 96,\n" +
                        "  \"id\": 99,\n" +
                        "  \"title\": \"Test title for put request\",\n" +
                        "  \"body\": \"Test body for put request\"" +
                        "}";
        given()
                .body(jsonBody).
        when()
                .put("posts/99").
        then()
                .assertThat().body("id", equalTo(99));
    }

    @Test
    public void modifyPartOfPost() {
        jsonBody =
                        "{\n" +
                        "  \"userId\": 98,\n" +
                        "  \"id\": 99,\n" +
                        "}";
        given()
                .body(jsonBody).
        when()
                .patch("posts/99").
        then()
                .assertThat().statusCode(200).log().all();
    }

    @Test
    public void deletePost() {
        given().
        when()
                .delete("posts/99").
        then()
                .assertThat().statusCode(200);
    }
}
