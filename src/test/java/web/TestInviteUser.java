package web;

import base.WebBaseTest;
import com.github.javafaker.Faker;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import testdata.DataTestInvite;
import web.object.Person;
import web.pages.AdminViewPage;
import web.pages.InvitationResultPage;
import web.pages.InvitePage;

public class TestInviteUser extends WebBaseTest {
    Faker faker = new Faker();

    @Test (description = "Successfully invite an user", dataProvider = "dataProviderInviteAPerson", dataProviderClass = DataTestInvite.class)
    public void testInviteAPerson(Person person){
        // Step1: Login to system with your username & password
        AdminViewPage admin = login(userName, pwd);
        // Step2: Expand the Settings menu
        admin.expandMenu("Settings");
        //Step3: Click on Add People menu
        InvitePage invitePage = admin.clickOnSettingAddPeople();
        //Step4: Add a person
        InvitationResultPage successPage =  invitePage.inviteAPerson(person);
        //Step5: Verify Congratulations page displays
        Assert.assertTrue(driver.getCurrentUrl().endsWith(successPage.getUrl()));
        Assert.assertTrue(successPage.getSuccessMessage().getText().endsWith("Congratulations"));
        successPage.signOut();
    }

    @Test (description = "Successfully invite some user", dataProvider = "dataProviderInviteSomePerson", dataProviderClass = DataTestInvite.class)
    public void testInviteSomePerson(List<Person> persons){
        // Step1: Login to system with your username & password
        AdminViewPage admin = login(userName, pwd);
        // Step2: Expand the Settings menu
        admin.expandMenu("Settings");
        //Step3: Click on Add People menu
        InvitePage invitePage = admin.clickOnSettingAddPeople();
        //Step4: Add a person
//        InvitationResultPage successPage =  invitePage.inviteSomePerson(persons);
        InvitationResultPage resultPage =  invitePage.inviteSomePerson(persons);
        //Step5: Verify Congratulations page displays
        Assert.assertTrue(driver.getCurrentUrl().endsWith(resultPage.getUrl()));
        Assert.assertTrue(resultPage.getSuccessMessage().getText().endsWith("Congratulations"));
        resultPage.signOut();
    }

    @Test (description = "Failed invite an invited user", dataProvider = "dataProviderInviteAPerson", dataProviderClass = DataTestInvite.class)
    public void testInvitePersonInvited(Person person){
        // Step1: Make sure existing an invited person
        AdminViewPage adminViewPage = login(userName, pwd);
        InvitePage invitePage = new InvitePage(driver, baseUrl);
        InvitationResultPage invitedPerson = inviteAPerson(invitePage, person);
        // Step2: Go back the invitation page
        invitePage.navigate(baseUrl);
        InvitationResultPage resultPage = inviteAPerson(invitePage, person);
        //Step5: Verify error message displays
        Assert.assertTrue(driver.getCurrentUrl().endsWith(resultPage.getUrl()));
        Assert.assertTrue(resultPage.getErrMessage().getText().endsWith("Uh oh! Unable to add user because email already exists"));
        resultPage.signOut();
    }
}
