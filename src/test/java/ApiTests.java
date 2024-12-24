import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ApiTests {
    private final ApiService apiService = new ApiService();

    @Test
    @Step("Positive Test: User Register")
    public void testPostRegisterPositive() {
        String data = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}";
        Response response = apiService.POSTregister(data);
        assertEquals(200, response.statusCode());
        assertFalse(response.jsonPath().getString("token").isEmpty());
    }

    @Test
    @Step("Negative Test: User Register")
    public void testPostRegisterNegative() {
        String data = "{\"email\": \"ann@gmail.com\"}";
        Response response = apiService.POSTregister(data);
        assertEquals(400, response.statusCode());
        assertEquals("Missing password", response.jsonPath().getString("error"));
    }

    @Test
    @Step("Positive Test: Update User")
    public void testPutUserPositive() {
        String data = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";
        Response response = apiService.PUTusers(2, data);
        assertEquals(200, response.statusCode());
        assertEquals("morpheus", response.jsonPath().getString("name"));
    }

    @Test
    @Step("Negative Test: Update User")
    public void testPutUserNegative() {
        String data = "{\"job\": \"zion resident\"}";
        Response response = apiService.PUTusers(-12, data);
        assertEquals(404, response.statusCode());
    }

    @Test
    @Step("Positive Test: Delete User")
    public void testDeleteUserPositive() {
        Response response = apiService.DELETEusers(2);
        assertEquals(204, response.statusCode());
    }

    @Test
    @Step("Negative Test: Delete User")
    public void testDeleteUserNegative() {
        Response response = apiService.DELETEusers(-12);
        assertEquals(404, response.statusCode());
    }

    @Test
    @Step("Positive Test: Get User")
    public void testGetUserPositive() {
        Response response = apiService.GETusers(2);
        assertEquals(200, response.statusCode());
        assertNotNull(response.jsonPath().getString("data.email"));
    }

    @Test
    @Step("Negative Test: Get User")
    public void testGetUserNegative() {
        Response response = apiService.GETusers(-12);
        assertEquals(404, response.statusCode());
    }
}
