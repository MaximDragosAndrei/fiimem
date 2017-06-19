package front.dto;

/**
 * Created by Syke on 20-May-17.
 */
public class CandidateOutEntity {
    private AuthEntity auth;
    private String cnp;
    private String firstname;
    private String lastname;
    private String phoneNumber;

    public CandidateOutEntity() {
    }

    public CandidateOutEntity(AuthEntity auth, String cnp, String firstname, String lastname, String phoneNumber) {
        this.auth = auth;
        this.cnp = cnp;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
    }

    public AuthEntity getAuth() {
        return auth;
    }

    public void setAuth(AuthEntity auth) {
        this.auth = auth;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
