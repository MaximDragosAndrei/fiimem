package front.dto;

/**
 * Created by rusub on 6/13/2017.
 */
public class MarkPostStructure {

    AuthEntity entity;
    Mark mark;

    public AuthEntity getEntity() {
        return entity;
    }

    public void setEntity(AuthEntity entity) {
        this.entity = entity;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }
}
