package web.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private String startDate;
    private String manager;
    private String segment;
    private String positionTitle;

    public Person(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Person(String firstName, String lastName, String email, String startDate, String manager, String segment, String positionTitle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.startDate = startDate;
        this.manager = manager;
        this.segment = segment;
        this.positionTitle = positionTitle;
    }
}
