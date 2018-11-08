import com.google.gson.JsonObject;
import config.HttpResources;
import config.TestConfiguration;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostClientTest extends TestConfiguration {

    @Test
    public void shouldAddClient() {
        given().header(headerToken, tokenValue)
                .body(getPostedJson().toString())
                .contentType(ContentType.JSON)
                .when()
                .post(getEndpointWithResource(HttpResources.CLIENTS))
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .body("data.name", equalTo("Motylem Jestem"))
                .body("data.city", equalTo("Las Deszczowy"));
    }

    private JsonObject getPostedJson() {
        JsonObject obj = new JsonObject();
        obj.addProperty("name", "Motylem Jestem");
        obj.addProperty("city", "Las Deszczowy");
        return obj;
    }
}
