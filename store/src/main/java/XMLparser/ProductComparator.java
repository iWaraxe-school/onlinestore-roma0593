package XMLparser;
import pl.coherent.domain.Product;
import java.util.*;

public class ProductComparator implements Comparator<Product> {

    XMLParser parser = new XMLParser();
    final Set<Map.Entry<String, String>> entries = parser.getFieldSortOrderMap().entrySet();

    @Override
    public int compare(Product o1, Product o2) {
        int result = 0;
        for(Map.Entry<String, String> entry : entries){

            if(entry.getValue().equals("asc")){
                switch (entry.getKey()){
                    case "name": result = o1.getName().compareTo(o2.getName());
                    break;
                    case "rate": result = o1.getRate() - o2.getRate();
                    break;
                    case "price": result = Double.compare(o1.getPrice(), o2.getPrice());
                    break;
                }
            } else if(entry.getValue().equals("desc")){
                switch (entry.getKey()){
                    case "name": result = o2.getName().compareTo(o1.getName());
                        break;
                    case "rate": result = o2.getRate() - o1.getRate();
                        break;
                    case "price": result = Double.compare(o2.getPrice(), o1.getPrice());
                        break;
                }
            }

            if(result != 0) break;
        }
        return result;
    }
}

