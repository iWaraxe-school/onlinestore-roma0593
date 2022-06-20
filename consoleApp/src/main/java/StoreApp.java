import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import pl.coherent.domain.Product;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

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

        storeHelper.initializeCategoriesInStore();
        storeHelper.fillStoreByProducts();
        storeHelper.getStoreInfo();

        System.out.println("======= Before sorting: ========");
        System.out.format("%-30s \t%s \t%7s", "NAME", "RATE", "PRICE\n");
        List<Product> wholeProductList = storeHelper.gatherAllStoreProducts();
        wholeProductList.forEach(System.out::println);

        TimeUnit.SECONDS.sleep(1);

        boolean runIndicator = true;
        while (runIndicator){
            System.out.println("\n====== Enter command (sort, top, quit, pick products): =======");

            Chain chain = new Chain(selectedProductOrder);
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            runIndicator = chain.process(input, wholeProductList);
            if(!runIndicator) timer.cancel();

            TimeUnit.SECONDS.sleep(5);
        }
    }
}
