package web.pages;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.base.BasePage;

@Getter
@Setter
public class LoginPage extends BasePage {
    private final String url = "/auth";

    @FindBy(xpath = "//input[@data-cucumber='input-login-email']")
    private WebElement txtUserName;

    @FindBy(xpath = "//input[@data-cucumber='input-login-password']")
    private WebElement txtPassword;

    @FindBy(xpath = "//div[@data-cucumber='button-continue']")
    private WebElement btnContinue;


    public LoginPage(WebDriver driver) {
        super(driver);
        waitForCompletePageLoading();
    }

    public LoginPage(WebDriver driver, String baseUrl){
        super(driver);
        driver.get(defineURL(baseUrl, url));
        waitForCompletePageLoading();
    }

    public AdminViewPage login(String usename, String password){
        enterText(getTxtUserName(), usename);
        clickOnElement(btnContinue);
        waitForElementToAppear(txtPassword);
        enterText(getTxtPassword(), password);
        delay(DELAY_MILLISECONDS);
        return new AdminViewPage(driver);
    }


}
