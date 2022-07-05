import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import pl.coherent.domain.Product;
import pl.coherent.store.*;
import pl.coherent.store.HTTPserver.Server;

import java.io.OutputStream;
import java.util.*;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class StoreApp {
    @SneakyThrows
    public static void main(String[] args) {

        Store onlineStore = Store.getStore();
        StoreHelper storeHelper = new StoreHelper(onlineStore);
        BlockingQueue<Product> selectedProductOrder = new ArrayBlockingQueue<>(1024);

        Timer timer = new Timer();
        ProductOrderCleaner task = new ProductOrderCleaner(selectedProductOrder);
        timer.schedule(task, 1000,120_000);

        Server.start();
        Server.sendRequestTo("http://localhost:8080/create-store");

        Thread.sleep(1_000);

        storeHelper.getStoreInfo();

        System.out.println("======= Before sorting: ========");
        System.out.format("%-30s \t%s \t%7s", "NAME", "RATE", "PRICE\n");
        List<Product> wholeProductList = storeHelper.gatherAllStoreProducts();
        wholeProductList.forEach(System.out::println);

        TimeUnit.SECONDS.sleep(1);

        AtomicBoolean runIndicator = new AtomicBoolean(true);
        while (runIndicator.get()){
            System.out.println("====== Enter command (sort, top, quit, pick products): =======");

            Chain chain = new Chain(selectedProductOrder);
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if(input.equalsIgnoreCase(Commands.PICK.getValue())){
                Server.createContextForAddToCart(httpExchange -> {
                    String response = "The above products have been added to the cart";

                    runIndicator.set(chain.process(input, wholeProductList));
                    httpExchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = httpExchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                });

                Server.sendRequestTo("http://localhost:8080/add-to-cart");
                continue;
            }

            runIndicator.set(chain.process(input, wholeProductList));

            if(!runIndicator.get()) {
                timer.cancel();
                Server.stop();
            }

            TimeUnit.SECONDS.sleep(5);
        }
    }
}
