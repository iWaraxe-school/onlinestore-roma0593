import pl.coherent.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class StoreApp {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Store onlineStore = new Store();
        StoreHelper storeHelper = new StoreHelper(onlineStore);

        storeHelper.initializeCategoriesInStore();
        storeHelper.fillStoreByProducts();
        storeHelper.getStoreInfo();
    }
}
