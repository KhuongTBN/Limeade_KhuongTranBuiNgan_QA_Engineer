package web.base;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import web.constants.HtmlTags;

@Getter
public class BasePage {
    protected static final int TIMEOUT = 15;
    protected static final int POLLING = 500;
    protected static final int DELAY_MILLISECONDS = 1000;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor javascriptExecutor;

    @FindBy(xpath = "//figure[@data-cucumber='user-avatar']")
    private WebElement userAvartar;
    @FindBy(xpath = "//form[@action='/signout']")
    private WebElement logout;

    public BasePage(WebDriver driver) {
        init(driver);
    }

    public BasePage(WebDriver driver, String url){
        init(driver);
        driver.get(url);
    }

    private void init(WebDriver driver){
        if (this.driver == null) {
            this.driver = driver;
            wait = new WebDriverWait(driver, TIMEOUT, POLLING);
            PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
            javascriptExecutor = (JavascriptExecutor) driver;
        }
    }

    public String defineURL(String baseUrl, String url) {
        return baseUrl + url;
    }

    protected void waitForCompletePageLoading() {
        boolean isCompleted = javascriptExecutor.executeScript("return document.readyState").toString().equals("complete");
        for (int i=0; i<5; i++){
            if (isCompleted)
                break;
            delay(DELAY_MILLISECONDS);
            isCompleted = javascriptExecutor.executeScript("return document.readyState").toString().equals("complete");
        }
    }

    protected void waitForElementToAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementToAppear(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitForAllElementsToAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected void waitForAllElementsToAppear(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    protected void waitForElementToBeClickable(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void waitForElementToDisappear(WebElement webElement) {
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    protected void waitForTextToDispear(By locator, String text) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, text)));
    }

    protected void waitForTextToAppear(WebElement webElement, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(webElement, text));
    }

    protected void waitForTheURLContains(String subURL) {
        wait.until(ExpectedConditions.urlContains(subURL));
    }

    public WebElement getLnkText(String lnkText) {
        return driver.findElement(By.linkText(lnkText));
    }

    public void clickOnLinkText(String lnkText) {
        getLnkText(lnkText).click();
    }

    public void enterText(WebElement element, String value){
        if (!value.isBlank())
        {
            element.click();
            element.clear();
            element.sendKeys(value);
            element.sendKeys(Keys.ENTER);
        }
    }

    public void swichToIFrame(WebElement iframe) {
        driver.switchTo().defaultContent();
        driver.switchTo().frame(iframe);
    }

    public void switchToWindowIndex(int windowIndex) {
        ArrayList<String> windowTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windowTabs.get(windowIndex));
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void delay(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getText(WebElement webElement){
        return webElement.getText();
    }

    public String getAttributeValue(WebElement webElement, String attribute){
        waitForElementToAppear(webElement);
        return webElement.getAttribute(attribute);
    }

    public void navigate(String url){
        driver.get(url);
        delay(DELAY_MILLISECONDS);
    }

    public String getHrefLink(WebElement webElement){
        return getAttributeValue(webElement, HtmlTags.HREF);
    }

    public String getHrefOfTextLink(String txtLink){
        WebElement webElement = getLnkText(txtLink);
        return getHrefLink(webElement);
    }

    public void clickOnElement(WebElement we) {
        waitForElementToBeClickable(we);
        scrollToView(we);
        we.click();
    }

    public void clickOnElement(By locale) {
        waitForElementToBeClickable(locale);
        getDriver().findElement(locale).click();
    }

    public WebElement getElement(By locator) {
        waitForElementToAppear(locator);
        return driver.findElement(locator);
    }

    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public void scrollToView(WebElement we){
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", we);
    }

    public void verifyText(String text, boolean isDisplay){
        List<WebElement> lst= driver.findElements(By.xpath("//*[contains(text(),'"+ text +"')]"));
//        int size = lst.size();
        boolean bool = lst.size() > 0;
        Assert.assertEquals(isDisplay, bool);
    }

    public void signOut(){
        clickOnElement(userAvartar);
        clickOnElement(logout);
    }
}
