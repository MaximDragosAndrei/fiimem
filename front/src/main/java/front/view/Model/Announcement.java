package front.view.Model;

/**
 * Created by cosmin on 6/12/2017.
 */
public class Announcement {
    int id;
    private String title;
    private String text;

    public Announcement(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
