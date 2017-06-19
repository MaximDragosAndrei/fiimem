package front.dto;

/**
 * Created by cosmin on 5/22/2017.
 */
public class AdminStatisticsEntity {
    private String nrChecked;
    private String nrUnchecked;

    public AdminStatisticsEntity() {
    }
    public String getNrChecked() {
        return nrChecked;
    }

    public void setNrChecked(String nrChecked) {
        this.nrChecked = nrChecked;
    }

    public String getNrUnchecked() {
        return nrUnchecked;
    }

    public void setNrUnchecked(String nrUnchecked) {
        this.nrUnchecked = nrUnchecked;
    }

}
