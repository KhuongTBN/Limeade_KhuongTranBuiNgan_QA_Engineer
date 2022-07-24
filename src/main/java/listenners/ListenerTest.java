package listenners;

import java.lang.reflect.Field;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.BaseTestMethod;

public class ListenerTest implements ITestListener {

    public void onTestStart(ITestResult result) {
        setResultTestName(result);
    }

    /*
    * This function to update the method name with its description in the TestNG reports
    */
    private void setResultTestName(ITestResult result) {
        BaseTestMethod baseTestMethod = (BaseTestMethod) result.getMethod();
        try {
            Field mMethodName = baseTestMethod.getClass().getSuperclass().getDeclaredField("m_methodName");
            mMethodName.setAccessible(true);
            mMethodName.set(baseTestMethod, baseTestMethod.getDescription());
        } catch (Exception e) {
            Reporter.log("Failed setResultTestName() with error : " + e.getMessage());
        }
    }
}
