import lombok.SneakyThrows;
import pl.coherent.domain.Product;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;

public class ProductOrderCleaner extends TimerTask {
    private final BlockingQueue<Product> selectedOrder;

    public ProductOrderCleaner(BlockingQueue<Product> selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("Cleaner is started in " + Thread.currentThread().getName());
        for (Product product : selectedOrder){
            selectedOrder.take();
        }

        System.out.println("Cleared product order: " + selectedOrder);
        System.out.println("Cleaner is finished in " + Thread.currentThread().getName());
    }
}
