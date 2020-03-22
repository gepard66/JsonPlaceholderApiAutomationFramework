import config.JsonPlaceholderApiConfig;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CommentsTests extends JsonPlaceholderApiConfig {

    private String jsonBody;

    @Test
    public void getAllComments() {
        given().
        when().
                get("posts/1/comments").
        then().
            statusCode(200);
    }

    @Test
    public void getSinglePostComments() {
        given().
        when().
                get("/posts/1/comments?id=1").
        then().
                statusCode(200);
    }

    @Test
    public void testSinglePostSchemaJSON() {
        given().
        when().
                get("/posts/1/comments?id=1").
        then().
                body(matchesJsonSchemaInClasspath("SingleCommentJsonSchema.json"));
    }

    @Test
    public void createComment() {
        jsonBody =
                    "{\n" +
                    "  \"postId\": 1,\n" +
                    "  \"id\": 1,\n" +
                    "  \"name\": \"Test name\",\n" +
                    "  \"email\": \"Test email\"" +
                    "  \"body\": \"Test body\"" +
                    "}";

        given()
                .body(jsonBody).
        when()
                .post("/posts/1/comments").
        then()
                .assertThat().statusCode(201).log().all();
    }
}
