import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import pl.coherent.domain.Product;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class ProductOrderExecutor implements Runnable{
    private final BlockingQueue<Product> selectedOrder;
    private final List<Product> productList;

    public ProductOrderExecutor(List<Product> productList, BlockingQueue<Product> selectedOrder) {
        this.productList = productList;
        this.selectedOrder = selectedOrder;
    }

    public BlockingQueue<Product> getSelectedOrder() {
        return selectedOrder;
    }

    @SneakyThrows
    @Override
    public void run() {
        int min = 1;
        int max = 30;
        Random random = new Random();
        int randomInt = random.nextInt(max - min + 1) + min;
        System.out.println(Thread.currentThread().getName() + " has been started");
        System.out.println("I want to buy " + randomInt + " products");
        for(int i = 0; i < randomInt; i++){
            selectedOrder.add(productList.get(new Random().nextInt(max)));
        }

        selectedOrder.forEach(System.out::println);
        System.out.println("\n" + Thread.currentThread().getName() + " has been finished");
    }
}
