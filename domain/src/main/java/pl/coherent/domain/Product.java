package pl.coherent.domain;
import java.util.Objects;

public class Product{
    private final String name;
    private int rate;
    private double price;

    private Product(String name, int rate, double price){
        this.name = name;
        this.rate = rate;
        this.price = price;
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

        public Product build(){
            return new Product(name, rate, price);
        }
    }
}


