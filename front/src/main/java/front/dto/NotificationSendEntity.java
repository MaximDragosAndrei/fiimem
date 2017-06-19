package front.dto;

public class NotificationSendEntity {
    private AuthEntity auth;
    private String cnp;
    private String message;

    public NotificationSendEntity(AuthEntity auth, String cnp, String message) {
        this.auth = auth;
        this.cnp = cnp;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
