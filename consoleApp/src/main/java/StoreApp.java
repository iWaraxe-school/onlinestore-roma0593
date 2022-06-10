import pl.coherent.domain.Product;
import java.util.List;
import java.util.Scanner;

public class StoreApp {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Store onlineStore = Store.getStore();
        StoreHelper storeHelper = new StoreHelper(onlineStore);

        storeHelper.initializeCategoriesInStore();
        storeHelper.fillStoreByProducts();

        storeHelper.getStoreInfo();

        System.out.println("======= Before sorting: ========");
        System.out.format("%-30s \t%s \t%7s", "NAME", "RATE", "PRICE\n");
        List<Product> wholeProductList = storeHelper.gatherAllStoreProducts();
        wholeProductList.forEach(System.out::println);

        boolean runIndicator = true;
        while (runIndicator){
            System.out.println("\n====== Enter command (sort, top, quit): =======");

            Chain chain = new Chain();
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            runIndicator = chain.process(input, wholeProductList);
        }
    }
}
