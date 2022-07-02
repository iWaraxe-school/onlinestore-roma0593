package pl.coherent.store.DB;

import lombok.SneakyThrows;
import java.sql.Statement;

public class DbTableCreation {
    public static final String CATEGORIES_CREATION_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS categories" +
            " (id INT UNSIGNED NOT NULL AUTO_INCREMENT," +
            " name VARCHAR(45) NOT NULL," +
            " PRIMARY KEY (id)," +
            " UNIQUE INDEX name_UNIQUE (name ASC) VISIBLE)";
    public static final String PRODUCT_CREATION_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS products" +
            " (id INT UNSIGNED NOT NULL AUTO_INCREMENT," +
            " name VARCHAR(45) NOT NULL," +
            " rate INT NOT NULL," +
            " price DOUBLE NOT NULL," +
            " category_id INT UNSIGNED NOT NULL," +
            " PRIMARY KEY (id)," +
            " INDEX fk_category_id (category_id ASC) VISIBLE," +
            " CONSTRAINT fk_category" +
            " FOREIGN KEY (category_id)" +
            " REFERENCES categories (id)" +
            " ON DELETE NO ACTION" +
            " ON UPDATE NO ACTION)";
    public static final String ADDING_CATEGORIES_QUERY = "INSERT IGNORE INTO categories(name)" +
            " VALUES('bike')," +
            " ('phone')," +
            " ('milk')";

    Statement statement;
    MySQLConnect mySQLConnect = new MySQLConnect();

    private DbTableCreation(){}

    public static DbTableCreation getDbTableCreation(){
        return DbTableCreation.InnerDbTableCreation.INSTANCE;
    }

    @SneakyThrows
    public void createAndFillCategoriesTable(){
        statement = mySQLConnect.connect().createStatement();
        statement.execute(CATEGORIES_CREATION_TABLE_QUERY);
        statement.execute(ADDING_CATEGORIES_QUERY);
        mySQLConnect.disconnect();
    }

    @SneakyThrows
    public void createProductsTable(){
        statement = mySQLConnect.connect().createStatement();
        statement.execute(PRODUCT_CREATION_TABLE_QUERY);
        mySQLConnect.disconnect();
    }

    public static class InnerDbTableCreation{
        private static final DbTableCreation INSTANCE = new DbTableCreation();
    }
}


