package testdata;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.testng.annotations.DataProvider;
import web.object.Person;

public class DataTestInvite {
    Faker faker = new Faker();

    /*
     * Define test data for invite a person
     */
    @DataProvider(name = "dataProviderInviteAPerson")
    public Object[] dataProviderInviteAPerson() throws Exception {
//        List<Person> persons = new ArrayList<>();
        Name testData = faker.name();
        Person person = new Person(testData.firstName(), testData.lastName(), testData.username()+"@yopmail.com");
//        persons.add(person);
        return new Object[]{person};
    }

    /*
     * Define test data for invite some person
     */
    @DataProvider(name = "dataProviderInviteSomePerson")
    public Object[] dataProviderInviteSomePerson() throws Exception {
        Random random = new Random();
        int rand = 0;
        while (true){
            rand = random.nextInt(4);
            if(rand !=0) break;
        }
        List<Person> persons = new ArrayList<>();
        while (rand > 0){
            Name testData = faker.name();
            Person person = new Person(testData.firstName(), testData.lastName(), testData.username()+"@yopmail.com");
            persons.add(person);
            rand --;
        }
        return new Object[]{persons};
    }
}
