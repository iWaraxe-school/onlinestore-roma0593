package pl.coherent.store;

public enum Commands {
    SORT("sort"), TOP5("top"), QUIT("quit"), PICK("pick products");

    private final String value;

    Commands(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
