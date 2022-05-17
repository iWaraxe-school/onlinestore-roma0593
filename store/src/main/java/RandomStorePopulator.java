import com.github.javafaker.Faker;


public class RandomStorePopulator {
    Faker faker = new Faker();

    public String getName(String categoryName){
        String productName = null;
        switch (categoryName){
            case "Bikes": productName = faker.company().logo();
            break;
            case "Phones": productName = faker.starTrek().location();
            break;
            case "Milk": productName = faker.food().ingredient();
            break;
        }
        return productName;
    }

    public double getPrice(){
        return faker.number().randomDouble(2, 0, 100);
    }

    public int getRate(){
        return faker.number().numberBetween(0, 5);
    }


}

