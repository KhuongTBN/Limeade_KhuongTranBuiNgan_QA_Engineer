package web.pages;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.base.BasePage;
import web.object.Person;

@Getter
@Setter
public class InvitePage extends BasePage {

    private final String url = "/home/tps/invite";


    private String xPathFirstName = "//tr[%d]//input[@field='firstName']";
    private String xPathLastName = "//tr[%d]//input[@field='lastName']";
    private String xPathEmail = "//tr[%d]//input[@field='email']";
    private String xPathStartDate = "//tr[%d]//input[@field='startDate']";


    @FindBy(xpath = "//div[@role='button' and contains(@class, 'cucumber-send-invite-button')]")
    private WebElement btnAddPeople;

    @FindBy(xpath = "//table/tbody[contains(@class, 'invitation-table']")
    private WebElement tbodyInvitation;

    public InvitePage(WebDriver driver) {
        super(driver);
        waitForTheURLContains(url);
        waitForCompletePageLoading();
    }

    public InvitePage(WebDriver driver, String baseUrl){
        super(driver);
        driver.get(defineURL(baseUrl, url));
        waitForCompletePageLoading();
    }

    public InvitationResultPage inviteSomePerson(List<Person> personList){
        for (int i =1; i<= personList.size(); i++){
            Person person = personList.get(i-1);
            enterPersonAt(person, i);
        }
        clickOnElement(btnAddPeople);
        return new InvitationResultPage(driver);
    }

    public void enterPersonAt(Person person, int i) {
        enterText(getElement(By.xpath(String.format(xPathFirstName, i))), person.getFirstName());
        enterText(getElement(By.xpath(String.format(xPathLastName, i))), person.getLastName());
        enterText(getElement(By.xpath(String.format(xPathEmail, i))), person.getEmail());
    }

    public InvitationResultPage inviteAPerson(Person person) {
        enterPersonAt(person, 1);
        clickOnElement(btnAddPeople);
        return new InvitationResultPage(driver);
    }

    @Override
    public void navigate(String baseUrl) {
        super.navigate(defineURL(baseUrl, url));
    }
}
