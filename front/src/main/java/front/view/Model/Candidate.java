package front.view.Model;

/**
 * Created by iilie on 5/16/2017.
 */

public class Candidate {
    private String lastname;
    private String firstname;
    private String cnp;
    private String email;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String useremail) {
        this.email = useremail;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", cnp='" + cnp + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

