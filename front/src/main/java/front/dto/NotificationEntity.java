package front.dto;

public class NotificationEntity {

    private String email;
    private boolean seen;
    private String message;

    public NotificationEntity() {

    }

    public NotificationEntity(String email, boolean seen, String message) {

        this.email = email;
        this.seen = seen;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


