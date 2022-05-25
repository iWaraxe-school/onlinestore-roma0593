import pl.coherent.domain.Category;
import pl.coherent.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private final List<Category> categories = new ArrayList<>();

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategoryToStore(Category category){
        categories.add(category);
    }

}
