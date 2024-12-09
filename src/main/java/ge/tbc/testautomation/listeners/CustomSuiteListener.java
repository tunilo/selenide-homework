package ge.tbc.testautomation.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class CustomSuiteListener  implements ISuiteListener {
    private long startTime;

    @Override
    public void onStart(ISuite suite) {
        startTime = System.currentTimeMillis();
        System.out.println("Suite [" + suite.getName() + "] started at " + System.currentTimeMillis());
    }

    @Override
    public void onFinish(ISuite suite) {
        long endTime = System.currentTimeMillis();
        System.out.println("Suite [" + suite.getName() + "] finished at " + endTime);
        System.out.println("Total time taken: " + (endTime - startTime) / 1000 + " seconds");
    }
}