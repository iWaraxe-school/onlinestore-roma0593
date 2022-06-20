import XMLparser.ProductComparator;
import lombok.SneakyThrows;
import pl.coherent.domain.Category;
import pl.coherent.domain.Product;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;;

public class StoreHelper{
    public static final String MYSQL_CON_URL = "jdbc:mysql://localhost:3306/onlinestore?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    public static final String MYSQL_USER = "root";
    public static final String MYSQL_PASSWORD= "Touchnet#1";
    public static final String CATEGORY_NAME_SELECTOR_QUERY = "SELECT name FROM categories";
    public static final String CATEGORY_ID_SELECTOR_QUERY = "SELECT id FROM categories WHERE name=?";
    public static final String PRODUCT_INSERTION_QUERY = "INSERT INTO products (name, rate, price, category_id) VALUES (?,?,?,?)";

    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;

    Store store;
    RandomStorePopulator populator = new RandomStorePopulator();

    public StoreHelper(Store store) {
        this.store = store;
    }

    @SneakyThrows
    public void fillCategoryByProduct(Category category){
        connection = DriverManager.getConnection(MYSQL_CON_URL, MYSQL_USER, MYSQL_PASSWORD);
        statement = connection.prepareStatement(CATEGORY_ID_SELECTOR_QUERY);
        statement.setString(1, category.getName());
        resultSet = statement.executeQuery();
        resultSet.next();

        Product product = new Product.Builder()
                .setName(populator.getName(category.getName()))
                .setRate(populator.getRate())
                .setPrice(populator.getPrice())
                .setCategoryId(resultSet.getInt("id"))
                .build();

        category.getProductList().add(product);
        insertProductIntoDB(product);
    }

    @SneakyThrows
    private void insertProductIntoDB(Product product){
        connection = DriverManager.getConnection(MYSQL_CON_URL, MYSQL_USER, MYSQL_PASSWORD);
        statement = connection.prepareStatement(PRODUCT_INSERTION_QUERY);

        statement.setString(1, product.getName());
        statement.setInt(2, product.getRate());
        statement.setDouble(3, product.getPrice());
        statement.setDouble(4, product.getCategoryId());

        statement.executeUpdate();
    }

    @SneakyThrows
    public void initializeCategoriesInStore() {
        connection = DriverManager.getConnection(MYSQL_CON_URL, MYSQL_USER, MYSQL_PASSWORD);

        statement = connection.prepareStatement(CATEGORY_NAME_SELECTOR_QUERY);
        resultSet = statement.executeQuery();
        while (resultSet.next()){
            Category category = new Category(resultSet.getString("name"));
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