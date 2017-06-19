package validator;

import java.util.Map;
public interface IValidator {

    boolean isValid(Map<String, String[]> params);
}
