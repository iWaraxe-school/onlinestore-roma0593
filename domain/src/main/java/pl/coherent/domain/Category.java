package pl.coherent.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Category {
    private final List<Product> productList = new ArrayList<>();
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

    public void getProductsInfoForCategory() {
        for (Product product : productList) {
            System.out.println("- " + product.getName());
        }
    }
}
