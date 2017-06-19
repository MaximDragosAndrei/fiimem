package front.dto;

public class SuccessReasonEntity {
    private boolean success;
    private String failureReason;

    public SuccessReasonEntity() {
    }

    public SuccessReasonEntity(boolean success, String failureReason) {
        this.success = success;
        this.failureReason = failureReason;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
}
