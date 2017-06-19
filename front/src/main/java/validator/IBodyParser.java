package validator;

/**
 * Created by rusub on 5/7/2017.
 */
public interface IBodyParser {

    void setBody(String str);
    boolean hasNext();
    String[] next();
}
