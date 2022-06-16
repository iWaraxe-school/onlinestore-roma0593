import XMLparser.ProductComparator;
import org.reflections.Reflections;
import pl.coherent.domain.Category;
import pl.coherent.domain.Product;

import java.util.*;
import java.util.stream.Collectors;;

public class StoreHelper{
    public static final String CATEGORY_PATH = "pl.coherent.domain.categories";
    Store store;
    RandomStorePopulator populator = new RandomStorePopulator();

    public StoreHelper(Store store) {
        this.store = store;
    }

    public void fillCategoryByProduct(Category category){
        category.getProductList().add(new Product.Builder()
                .setName(populator.getName(category.getName()))
                .setRate(populator.getRate())
                .setPrice(populator.getPrice())
                .build());
    }

    public void initializeCategoriesInStore() throws InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections(CATEGORY_PATH);
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> subType : subTypes) {
            Category category = subType.newInstance();
            store.addCategoryToStore(category);
        }
    }

    public void getStoreInfo() {
        System.out.println("The store consists of the following categories:");
        for (Category category : store.getCategories()) {
            System.out.println(category.getName() + " with the following products:");
            category.getProductsInfoForCategory();
            System.out.println();
        }
    }

    public void fillStoreByProducts() {
        for (Category category : store.getCategories()) {
            for (int i = 0; i < 10; i++) {
                fillCategoryByProduct(category);
            }
        }
    }

    public List<Product> gatherAllStoreProducts(){
        List<Product> allProducts = new ArrayList<>();
        for(Category category : store.getCategories()){
            allProducts.addAll(category.getProductList());
        }
        return allProducts;
    }

    public List<Product> getSortedProducts(List<Product> productList){
        productList.sort(new ProductComparator());
        return productList;
    }

    public List<Product> getTop5ByPrice(List<Product> productList){
        return productList.stream()
                .sorted((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()))
                .collect(Collectors.toList())
                .subList(0, 4);
    }
}