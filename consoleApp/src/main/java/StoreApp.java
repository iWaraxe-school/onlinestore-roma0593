import org.reflections.Reflections;
import pl.coherent.domain.Category;

import java.util.Iterator;
import java.util.Set;

public class StoreApp {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Store store = new Store();

        Reflections reflections = new Reflections("pl.coherent.domain.categories");
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> subType : subTypes) {
            Category category = subType.newInstance();
            store.addCategoryToStore(category);
        }


        for (Category category : store.getCategories()) {
            for (int i = 0; i < 10; i++) {
                store.addProductToCategory(category);
            }
        }

        store.getStoreInfo();
    }
}
