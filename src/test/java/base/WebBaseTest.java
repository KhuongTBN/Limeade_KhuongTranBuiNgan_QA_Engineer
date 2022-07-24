package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import web.object.Person;
import web.pages.AdminViewPage;
import web.pages.InvitationResultPage;
import web.pages.InvitePage;
import web.pages.LoginPage;

public class WebBaseTest extends BaseTest {

    @BeforeSuite
    @Override
    public void setUpSuite() throws IOException {
        super.setUpSuite();
        driver = initializeWebDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void quitWebBrowser() {
        if (driver == null) {
            return;
        }
        try {
            driver.close();
            driver.quit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            driver = null;
        }
    }

    public ChromeOptions initChromeOption(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--ignore-certificate-errors");
        return chromeOptions;
    }

    public WebDriver initializeWebDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(initChromeOption());
        }
        return driver;
    }

    @BeforeTest
    public void initMethodTest(){
        Set<String> browserTabs = driver.getWindowHandles();
        if(browserTabs.size() <= 1){
            return;
        }
        String originHandle = driver.getWindowHandle();
        browserTabs.forEach(tab ->{
            if(!tab.equals(originHandle)){
                driver.switchTo().window(tab);
                driver.close();
            }
        });
        driver.switchTo().window(originHandle);
    }

    public InvitationResultPage inviteAPerson(InvitePage invitePage, Person person){
//        adminViewPage.expandMenu("Settings");
//        InvitePage invitePage = adminViewPage.clickOnSettingAddPeople();
        return invitePage.inviteAPerson(person);
    }

    public AdminViewPage login(String userName, String password){
        LoginPage loginPage = new LoginPage(driver, baseUrl);
        return loginPage.login(userName, password);
    }
}
