import pl.coherent.domain.Category;
import pl.coherent.domain.Product;

public class StoreHelper {
    RandomStorePopulator populator = new RandomStorePopulator();
    public void fillCategoryByProduct(Category category){
        category.getProductList().add(new Product(
                populator.getName(category.getName()),
                populator.getRate(),
                populator.getPrice())
        );
    }
}
