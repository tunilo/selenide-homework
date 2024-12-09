package ge.tbc.testautomation.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomTestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test [" + result.getMethod().getMethodName() + "] started at " + System.currentTimeMillis());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test [" + result.getMethod().getMethodName() + "] passed at " + System.currentTimeMillis());
    }
//კი არ ფეილდება ჩემი ტესტები მაგრამ მაინც გაწერეთო
//არა კი არ ფეილდება, მაგრამ მაინც ოპტიმისტი რო იქნები ადამიანი :დდდ
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test [" + result.getMethod().getMethodName() + "] failed at " + System.currentTimeMillis());
        System.out.println("Reason: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test [" + result.getMethod().getMethodName() + "] skipped at " + System.currentTimeMillis());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test [" + context.getName() + "] finished at " + System.currentTimeMillis());
    }
}
