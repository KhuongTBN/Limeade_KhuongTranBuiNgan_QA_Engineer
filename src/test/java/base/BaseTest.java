package base;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import static utils.ConfigUtils.getEnvValue;

public class BaseTest {
    private final int DELAY_TIME = 5000;
    protected static WebDriver driver;
    protected String userName;
    protected String pwd;
    protected static String baseUrl;

    public void setUpSuite() throws IOException {
        userName = getEnvValue("ACCOUNT_USERNAME");
        pwd = getEnvValue("ACCOUNT_PASSWORD");
        baseUrl = getEnvValue("BASE_URL");
    }

    public void pause(){
        try {
            Thread.sleep(DELAY_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
