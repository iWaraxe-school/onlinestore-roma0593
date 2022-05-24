import org.reflections.Reflections;
import pl.coherent.domain.Category;
import pl.coherent.domain.Product;

import java.util.Set;

public class StoreHelper {
    public static final String CATEGORY_PATH = "pl.coherent.domain.categories";
    Store store;

    RandomStorePopulator populator = new RandomStorePopulator();

    public StoreHelper(Store store) {
        this.store = store;
    }

    public void fillCategoryByProduct(Category category){
            category.getProductList().add(new Product(
                    populator.getName(category.getName()),
                    populator.getRate(),
                    populator.getPrice())
            );
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

}
