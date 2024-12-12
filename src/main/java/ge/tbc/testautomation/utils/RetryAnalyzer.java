package ge.tbc.testautomation.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    int counter = 0;
    @Override
    public boolean retry(ITestResult iTestResult) {
        RetryCount annotation = iTestResult.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(RetryCount.class);

        if (counter < annotation.count()){
            counter++;
            return true;
        }//რავი ცოტნეს სე ეწერა და აღარ დავწერე ხელახლა :დ
        return false;
    }
}