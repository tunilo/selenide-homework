package ge.tbc.testautomation.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CustomSuiteListener implements ISuiteListener {

    private long startTime;

    private static String formatTime(long timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        return formatter.format(Instant.ofEpochMilli(timestamp));
    }

    @Override
    public void onStart(ISuite suite) {
        startTime = System.currentTimeMillis();
        System.out.println("Suite [" + suite.getName() + "] started at " + formatTime(startTime));
    }

    @Override
    public void onFinish(ISuite suite) {
        long endTime = System.currentTimeMillis();
        System.out.println("Suite [" + suite.getName() + "] finished at " + formatTime(endTime));
        System.out.println("Total time taken: " + (endTime - startTime) / 1000 + " seconds");
    }
}