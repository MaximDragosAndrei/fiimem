package validator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by rusub on 5/6/2017.
 */
public class Validator implements IValidator {

    private Map<String, Pattern> fieldRegex;
    private IBodyParser parser = new BodyParser();
    public Validator(){
        this.fieldRegex = new HashMap<String, Pattern>();
        this.fieldRegex.put("num", Pattern.compile("[0-9]+"));
        this.fieldRegex.put("alfn", Pattern.compile("([0-9]+|\\p{L}+)+"));
        this.fieldRegex.put("alfa", Pattern.compile("\\p{L}+"));
        this.fieldRegex.put("email", Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"));
        this.fieldRegex.put("all", Pattern.compile(".+"));
        this.fieldRegex.put("date", Pattern.compile("[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}"));
    }

    @Override
    public boolean isValid(Map<String, String[]> params) {
        boolean validParameterName = true;
        for(Map.Entry<String, String[]> entry : params.entrySet()){
            if(!validParameterName)
                break;
            validParameterName = false;
            for(Map.Entry<String, Pattern> validator : this.fieldRegex.entrySet()){
                if((entry.getKey()).contains(validator.getKey())){
                    validParameterName = true;
                    for(String str : entry.getValue()) {
                        if(!validator.getValue().matcher(str).matches()){
                            return false;
                        }
                    }
                }
            }
            if(!validParameterName)
                return false;
        }
        return true;
    }


}
