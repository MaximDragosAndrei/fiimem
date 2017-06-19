package front.view.Model;

import javax.validation.constraints.*;

/**
 * Created by rusub on 5/6/2017.
 */
public class Person {

    @NotNull @Size(min=2, max=50) @Pattern(regexp = "[a-zA-Z]+")
    private String firstName;
    @NotNull @Size(min=2, max=50) @Pattern(regexp = "[a-zA-Z]+")
    private String lastName;
    @Max(100)
    private int age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
