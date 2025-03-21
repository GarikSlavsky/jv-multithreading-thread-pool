package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);

        List<Callable<String>> callables = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            callables.add(new MyThread());
        }

        List<Future<String>> futures = service.invokeAll(callables);
        for (Future<String> future : futures) {
            try {
                logger.info(future.get());
            } catch (Exception e) {
                logger.error(e);
            }
        }
        service.shutdown();
    }
}
