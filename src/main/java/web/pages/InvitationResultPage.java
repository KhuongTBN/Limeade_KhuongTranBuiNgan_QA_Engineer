package web.pages;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.base.BasePage;

@Getter
@Setter
public class InvitationResultPage extends BasePage {

    private final String url = "/home/tps/invite/finish";

    @FindBy(xpath = "//div[contains(@class, 'items-center green')]")
    private WebElement successMessage;
    @FindBy(xpath = "//div[contains(@class, 'items-center red')]")
    private WebElement errMessage;

    public InvitationResultPage(WebDriver driver) {
        super(driver);
//        waitForCompletePageLoading();
        waitForTheURLContains(url);
        waitForCompletePageLoading();
    }

    public InvitationResultPage(WebDriver driver, String baseUrl){
        super(driver);
        driver.get(defineURL(baseUrl, url));
        waitForCompletePageLoading();
    }

    public String getUrl() {
        return url;
    }
}
