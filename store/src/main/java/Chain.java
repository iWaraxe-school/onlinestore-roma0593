import pl.coherent.domain.Product;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Chain {
    private final StoreHelper storeHelper = new StoreHelper(Store.getStore());
    private final BlockingQueue<Product> createdOrder;
    CommandProcessor chain;

    public Chain(BlockingQueue<Product> createdOrder) {
        this.createdOrder = createdOrder;
        buildChain();
    }

    private void buildChain(){
        chain = new SortProcessor(new Top5Processor(new QuitProcessor(new PickProductsProcessor(new NoSuchComProcessor(null)))));
    }

    public boolean process(String command, List<Product> productList) {
        return chain.process(command, productList);
    }

    abstract class CommandProcessor {
        private final CommandProcessor nextProcessor;

        public CommandProcessor(CommandProcessor nextProcessor){
            this.nextProcessor = nextProcessor;
        }

        public boolean process(String command, List<Product> productList){
            if(nextProcessor != null)
                return nextProcessor.process(command, productList);
            return false;
        }
    }
    class PickProductsProcessor extends CommandProcessor{
        public PickProductsProcessor(CommandProcessor nextProcessor) {
            super(nextProcessor);
        }

        public boolean process(String command, List<Product> productList){
            if(command.equalsIgnoreCase(Commands.PICK.getValue())) {
                new Thread(new ProductOrderExecutor(productList, createdOrder)).start();
                return true;
            } else {
                return super.process(command, productList);
            }
        }
    }

    class SortProcessor extends CommandProcessor{
        public SortProcessor(CommandProcessor nextProcessor) {
            super(nextProcessor);
        }

        public boolean process(String command, List<Product> productList){
            if(command.equalsIgnoreCase(Commands.SORT.getValue())) {
                System.out.println("\n====== Sorted product list: =======");
                System.out.format("%-30s \t%s \t%7s", "NAME", "RATE", "PRICE\n");
                storeHelper.getSortedProducts(productList).forEach(System.out::println);
                return true;
            } else {
                return super.process(command, productList);
            }
        }
    }

    class Top5Processor extends CommandProcessor{
        public Top5Processor(CommandProcessor nextProcessor) {
            super(nextProcessor);
        }

        public boolean process(String command, List<Product> productList){
            if(command.equalsIgnoreCase(Commands.TOP5.getValue())) {
                System.out.println("\n====== Sorted product list: =======");
                System.out.format("%-30s \t%s \t%7s", "NAME", "RATE", "PRICE\n");
                storeHelper.getTop5ByPrice(productList).forEach(System.out::println);
                return true;
            } else {
                return super.process(command, productList);
            }
        }
    }

    class QuitProcessor extends CommandProcessor{
        public QuitProcessor(CommandProcessor nextProcessor) {
            super(nextProcessor);
        }

        public boolean process(String command, List<Product> productList){
            if(command.equalsIgnoreCase(Commands.QUIT.getValue())) {
                return false;
            } else {
                return super.process(command, productList);
            }
        }
    }

    class NoSuchComProcessor extends CommandProcessor{
        public NoSuchComProcessor(CommandProcessor nextProcessor) {
            super(nextProcessor);
        }

        public boolean process(String command, List<Product> productList){
            if(!command.equalsIgnoreCase(Commands.SORT.getValue()) &&
                    !command.equalsIgnoreCase(Commands.TOP5.getValue()) &&
                    !command.equalsIgnoreCase(Commands.PICK.getValue()) &&
                    !command.equalsIgnoreCase(Commands.QUIT.getValue())) {
                System.out.println("\nSorry, there is no such command. Try something else");
                return true;
            } else {
                return super.process(command, productList);
            }
        }
    }
}
