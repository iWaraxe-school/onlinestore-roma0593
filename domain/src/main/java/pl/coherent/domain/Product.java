package pl.coherent.domain;
import java.util.Objects;

public class Product{
    private final String name;
    private int rate;
    private double price;
    private final int categoryId;

    private Product(String name, int rate, double price, int categoryId){
        this.name = name;
        this.rate = rate;
        this.price = price;
        this.categoryId = categoryId;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }

    public double getPrice() {
        return price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    @Override
    public String toString() {
        return String.format("%-30s \t%d \t%10s", name, rate, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public static class Builder{
        private String name;
        private int rate;
        private double price;
        private int categoryId;

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setRate(int rate){
            this.rate = rate;
            return this;
        }

        public Builder setPrice(double price){
            this.price = price;
            return this;
        }

        public Builder setCategoryId(int categoryId){
            this.categoryId = categoryId;
            return this;
        }

        public Product build(){
            return new Product(name, rate, price, categoryId);
        }
    }
}


