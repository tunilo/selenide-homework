package ge.tbc.testautomation.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CustomTestListener implements ITestListener {
    private static String formatTime(long timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        return formatter.format(Instant.ofEpochMilli(timestamp));
    }
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test [" + result.getMethod().getMethodName() + "] started at " + formatTime(System.currentTimeMillis()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test [" + result.getMethod().getMethodName() + "] passed at " + formatTime(System.currentTimeMillis()));
    }
//კი არ ფეილდება ჩემი ტესტები მაგრამ მაინც გაწერეთო
//არა კი არ ფეილდება, მაგრამ მაინც ოპტიმისტი რო იქნები ადამიანი :დდდ
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test [" + result.getMethod().getMethodName() + "] failed at " + formatTime(System.currentTimeMillis()));
        System.out.println("Reason: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test [" + result.getMethod().getMethodName() + "] skipped at " + formatTime(System.currentTimeMillis()));
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test [" + context.getName() + "] finished at " + formatTime(System.currentTimeMillis()));
    }
}
