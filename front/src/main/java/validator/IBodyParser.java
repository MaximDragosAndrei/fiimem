package validator;

public interface IBodyParser {

    void setBody(String str);
    boolean hasNext();
    String[] next();
}
