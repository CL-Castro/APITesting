import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.json.JSONObject;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsIterableContaining.hasItems;

public class restAssuredExamples {

    @Test
    public void ex1(){
        RestAssured.get("http://192.168.1.3/test/test.json").then().assertThat().statusCode(200);
        RestAssured.get("http://192.168.1.1/test/test.json").then().assertThat().statusCode(404);
    }

    @Test
    public void ex2(){
        RestAssured.get("http://192.168.1.3/test/test2.json")
                .then()
                .body("tienda.productos.findAll { it.precio < 10 }.nombre", hasItems("Objeto1", "Objeto3"));
    }

    @Test
    public void ex3(){
        JSONObject request = new JSONObject();
        request.put("id", "4");
        request.put("title", "Post 4");
        Response response = RestAssured.get("https://my-json-server.typicode.com/typicode/demo/posts");
        System.out.println(response.getBody().asString());
        RestAssured.given().body(request.toString()).when()
                .post("https://my-json-server.typicode.com/typicode/demo/posts")
                .then()
                .statusCode(201).log().all();
    }

    @Test
    public void ex4(){
        JSONObject request = new JSONObject();
        request.put("userId", "4");
        request.put("title", "Actualizado");
        Response response = RestAssured.get("http://jsonplaceholder.typicode.com/posts/100");
        System.out.println(response.getBody().asString());
        RestAssured.given()
                .contentType("application/json")
                .body(request)
                .when()
                .put("http://jsonplaceholder.typicode.com/posts/100")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void ex5(){
        Response response = RestAssured.get("http://jsonplaceholder.typicode.com/posts/100");
        System.out.println(response.getBody().asString());
        RestAssured
                .when()
                .delete("http://jsonplaceholder.typicode.com/posts/100")
                .then()
                .statusCode(200)
                .log().all();
    }
}
