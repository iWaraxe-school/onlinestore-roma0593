public class StoreApp {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Store onlineStore = new Store();
        StoreHelper storeHelper = new StoreHelper(onlineStore);

        storeHelper.initializeCategoriesInStore();
        storeHelper.fillStoreByProducts();
        storeHelper.getStoreInfo();
    }
}
