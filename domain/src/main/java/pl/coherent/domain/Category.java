package pl.coherent.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private List<Product> productList = new ArrayList<>();
    private final String name;

    public Category(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public String getName() {
        return name;
    }

    public void addProductToCategory(Product product) {
        productList.add(product);
    }

    public String getInfo() {
        return getName() +
                " consists the following products " +
                getProductList();
    }
}
