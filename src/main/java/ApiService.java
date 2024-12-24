import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class ApiService {
    private final String BASE_URL = "https://reqres.in";

    public Response POSTregister(String data) {
        return given().baseUri(BASE_URL)
                .body(data)
                .post("/api/register");
    }

    public Response PUTusers(int id, String data) {
        return given().baseUri(BASE_URL)
                .body(data)
                .put("/api/users/" + id);
    }

    public Response DELETEusers(int id) {
        return given().baseUri(BASE_URL)
                .delete("/api/users/" + id);
    }

    public Response GETusers(int id) {
        return given().baseUri(BASE_URL)
                .get("/api/users/" + id);
    }
}