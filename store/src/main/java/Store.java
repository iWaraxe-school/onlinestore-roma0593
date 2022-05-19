import pl.coherent.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private final List<Category> categories = new ArrayList<>();
    StoreHelper storeHelper = new StoreHelper();

    public Store() {
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategoryToStore(Category category){
        categories.add(category);
    }

    public void addProductToCategory(Category category){
        storeHelper.fillCategoryByProduct(category);
    }

    public void getStoreInfo(){
        System.out.println("The store consists of the following categories:");
        for (Category category : categories) {
            System.out.println(category.getName() + " with the following products:");
            category.getProductsInfoForCategory();
            System.out.println();
        }
    }
}
