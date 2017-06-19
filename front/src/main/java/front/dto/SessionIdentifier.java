package front.dto;

/**
 * Created by rusub on 5/8/2017.
 */
public class SessionIdentifier {

    private boolean success;
    private String token;
    private String failureReason;

    public SessionIdentifier(){

    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
}
