import com.github.javafaker.Faker;

public class RandomStorePopulator {
    Faker faker = new Faker();

    public String getName(String categoryName){
        String productName = null;
        switch (categoryName){
            case "bike": productName = faker.gameOfThrones().dragon();
            break;
            case "phone": productName = faker.starTrek().location();
            break;
            case "milk": productName = faker.food().spice();
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

