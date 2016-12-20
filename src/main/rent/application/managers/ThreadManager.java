package rent.application.managers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class for managing threads of application.
 *
 * @author zagart
 */
public class ThreadManager {
    private ExecutorService mCachedPool = Executors.newCachedThreadPool();

    public void execute(final Runnable pRunnable) {
        new Thread(pRunnable).start();
    }

    public void executeInCachedPool(final Runnable pRunnable) {
        mCachedPool.execute(pRunnable);
    }
}
