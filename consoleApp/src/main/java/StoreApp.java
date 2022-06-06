import pl.coherent.domain.Product;
import java.util.List;
import java.util.Scanner;

public class StoreApp {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Store onlineStore = new Store();
        StoreHelper storeHelper = new StoreHelper(onlineStore);

        storeHelper.initializeCategoriesInStore();
        storeHelper.fillStoreByProducts();

        storeHelper.getStoreInfo();

        System.out.println("======= Before sorting: ========");
        System.out.format("%-30s \t%s \t%s", "NAME", "RATE", "PRICE\n");
        List<Product> wholeProductList = storeHelper.gatherAllStoreProducts();
        wholeProductList.forEach(System.out::println);

        boolean runIndicator = true;
        while (runIndicator){
            System.out.println("\n====== Enter command (sort, top, quit): =======");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            switch (input){
                case "sort": System.out.println("\n====== Sorted product list: =======");
                System.out.format("%-30s \t%s \t%10s", "NAME", "RATE", "PRICE\n");
                storeHelper.getSortedProducts(wholeProductList).forEach(System.out::println);
                break;
                case "top": System.out.println("\n====== Top 5 most expensive products: =======");
                System.out.format("%-30s \t%s \t%10s", "NAME", "RATE", "PRICE\n");
                storeHelper.getTop5ByPrice(wholeProductList).forEach(System.out::println);
                break;
                case "quit": runIndicator = false;
            }
        }
    }
}
