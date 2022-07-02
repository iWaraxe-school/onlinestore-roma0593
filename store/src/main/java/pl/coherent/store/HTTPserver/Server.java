package pl.coherent.store.HTTPserver;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import lombok.SneakyThrows;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import static com.sun.net.httpserver.HttpServer.create;
import static io.restassured.RestAssured.given;

public class Server {
    private static HttpServer httpServer;
    public static final String CREATE_STORE_ENDPOINT = "/create-store";
    public static final String ADD_TO_CART_ENDPOINT = "/add-to-cart";
    public static final String REALM = "online_store";

    public static void start() throws IOException{
        httpServer = create(new InetSocketAddress(8080), 0);
        HttpContext context = httpServer.createContext(CREATE_STORE_ENDPOINT, new CreateStoreHandler());
        context.setAuthenticator(new ApiBasicAuthentication(REALM));

        httpServer.start();
    }

    public static void createContextForAddToCart(HttpHandler addToCartHandler){
        HttpContext context = httpServer.createContext(ADD_TO_CART_ENDPOINT, addToCartHandler);
        context.setAuthenticator(new ApiBasicAuthentication(REALM));
    }

    @SneakyThrows
    public static void sendRequestTo(String endpointURL){
        String responseBody = given().auth()
                .basic("admin", "password")
                .when()
                .get(endpointURL)
                .body()
                .asString();

        TimeUnit.SECONDS.sleep(1);
        if(responseBody.equals("")){
            System.out.println("\nInvalid credentials for basic authentication\n");
        } else {
            System.out.println("\nResponse from API - " + responseBody + "\n");
        }
    }

    public static void stop(){
        httpServer.stop(0);
    }
}