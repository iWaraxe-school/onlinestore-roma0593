package pl.coherent.store.HTTPserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.http.HttpStatus;
import pl.coherent.store.Store;
import pl.coherent.store.StoreHelper;

import java.io.IOException;
import java.io.OutputStream;

public class CreateStoreHandler implements HttpHandler {
    private static final String RESPONSE = "Categories has been initialize in the store and filled with products";
    StoreHelper storeHelper = new StoreHelper(Store.getStore());

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        storeHelper.initializeCategoriesInStore();
        storeHelper.fillStoreByProducts();

        httpExchange.sendResponseHeaders(200, RESPONSE.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(RESPONSE.getBytes());
        os.close();
    }
}
