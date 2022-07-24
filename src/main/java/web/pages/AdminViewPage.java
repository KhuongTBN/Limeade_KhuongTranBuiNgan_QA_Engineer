package web.pages;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.base.BasePage;

@Getter
@Setter
public class AdminViewPage extends BasePage {
    private final String url = "/home/engage/dashboard";

    private String xpathMenu = "//span[@data-cucumber='menu-item-label' and text()='%s']/ancestor::li[@data-cucumber='sitebar-item']";
    private String xpathSubMenu = "//span[@data-cucumber='menu-item-label' and text()='%s']/ancestor::li/ul/li";

    @FindBy(xpath = "//div[@data-cucumber='sitebar-menu-items']")
    private WebElement divSitebarMenu;

    @FindBy(xpath = "//li[@data-name='settings_users_invite']")
    private WebElement liSettingAddPeople;

    @FindBy(id = "page-wrapper")
    private WebElement divMainLayout;

    public AdminViewPage(WebDriver driver) {
        super(driver);
        waitForTheURLContains(url);
        waitForCompletePageLoading();
        waitForElementToAppear(divMainLayout);
    }

    public AdminViewPage(WebDriver driver, String baseUrl){
        super(driver);
        driver.get(defineURL(baseUrl, url));
        waitForCompletePageLoading();
    }

    public void expandMenu(String label){
        waitForElementToAppear(By.xpath(String.format(xpathMenu, label)));
        List<WebElement> lstSubMenu = getListSubMenu(label);
        if (lstSubMenu.size() > 0) {
            WebElement subMenu = lstSubMenu.get(0);
            if(subMenu.isDisplayed()){
                return;
            }
        }
        clickOnMenu(label);
        waitForAllElementsToAppear(getListSubMenu(label));
    }

    public WebElement getMenu(String label){
        String xpath = String.format(xpathMenu, label);
        return getElement(By.xpath(xpath));
    }

    public List<WebElement> getListSubMenu(String label){
        String xpath = String.format(xpathSubMenu, label);
        return getElements(By.xpath(xpath));
    }

    public void clickOnMenu(String label){
        WebElement menu = getMenu(label);
        clickOnElement(menu);
    }

    public InvitePage clickOnSettingAddPeople(){
        clickOnElement(liSettingAddPeople);
        return new InvitePage(driver);
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void navigate(String baseUrl) {
        super.navigate(defineURL(baseUrl, url));
    }
}
