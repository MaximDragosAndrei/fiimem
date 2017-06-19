package front.dto;

/**
 * Created by cosmin on 5/14/2017.
 */
public class Login {
    private String email;
    private String pswall;
    public Login(){

    }
    public String getUsername() {
        return email;
    }

    public void setUsername(String username) {
        this.email = username;
    }

    public String getPassword() {
        return pswall;
    }

    public void setPassword(String password) {
        this.pswall = password;
    }
}
