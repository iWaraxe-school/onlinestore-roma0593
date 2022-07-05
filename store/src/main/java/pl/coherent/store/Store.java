package pl.coherent.store;

import pl.coherent.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private final List<Category> categories = new ArrayList<>();

    public static Store getStore(){
        return InnerStore.INSTANCE;
    }

    private Store(){}

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategoryToStore(Category category){
        categories.add(category);
    }

    public static class InnerStore{
        private static final Store INSTANCE = new Store();
    }

}
