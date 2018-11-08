import config.HttpResources;
import config.TestConfiguration;
import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class GetProductTest extends TestConfiguration {


    @Test
    public void getAllProducts() {

        given()
                .header(headerToken, tokenValue)
                .when()
                .get(getEndpointWithResource(HttpResources.PRODUCTS))
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
    }

    @Test(dataProvider = "products")
    public void getProductsById(int id, String prodKey, String cost) {
        given()
                .header(headerToken, tokenValue)
                .when().get(getEndpointWithResource(HttpResources.PRODUCTS) + "/" + id)
                .then().assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .body("data.id", equalTo(id))
                .body("data.cost", equalTo(cost))
                .body("data.product_key", equalTo(prodKey));
    }

    @DataProvider(name = "products")
    protected Object[][] productsDataProvider() {
        return new Object[][]{
                {43, "Filet z Kurczaka", "22.00"},
                {45, "Ananas", "18.00"},
                {44, "Pralka", "800.00"},
        };
    }

}
