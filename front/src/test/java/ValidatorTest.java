import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validator.IValidator;
import validator.Validator;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by rusub on 5/7/2017.
 */
class ValidatorTest {

    IValidator validator;
    Map<String, String[]> map;
    @BeforeEach
    void setUp() {
        validator = new Validator();
        map = new HashMap<String, String[]>();
    }

    @AfterEach
    void tearDown() {
        validator = null;
        map = null;
    }

    @Test
    void alphaNumericWordsShouldContainsOnlyLettersAndDigits(){
        map.put("res-alfn", new String[]{"wordȚ21"});
        boolean res = validator.isValid(map);
        assertTrue(res);
    }

    @Test
    void alphaNumericWordsShouldOtherCharacters(){
        map.put("res-alfn", new String[]{"12as@#"});
        System.out.println(map.size());
        boolean res = validator.isValid(map);
        assertFalse(res);
    }

    @Test
    void numbersContainsOnlyDigits() {
        map.put("res-num", new String[]{"123"});
        boolean res = validator.isValid(map);
        assertTrue(res);
    }

    @Test
    void numbersDoNotContainsLetters(){
        map.put("res-num", new String[]{"123a"});
        boolean res = validator.isValid(map);
        assertFalse(res);
    }

    @Test
    void wordsContainsOnlyLetters(){
        map.put("res-alfa", new String[]{"wordăîĂ"});
        boolean res = validator.isValid(map);
        assertTrue(res);
    }

    @Test
    void wordsDoNotContainsCharactersOtherThanLetters(){
        map.put("res-alfa", new String[]{"word2@#1"});
        boolean res = validator.isValid(map);
        assertFalse(res);
    }


    @Test
    void emailsShouldNotContainsAtSymbolInLocalPart(){
        map.put("res-email", new String[]{"popescu@ion@gmail.com"});
        boolean res = validator.isValid(map);
        assertFalse(res);
    }

    @Test
    void emailsShouldContainsOnlyOneAtSymbol(){
        map.put("res-email", new String[]{"popescu_ion@gmail.com"});
        boolean res = validator.isValid(map);
        assertTrue(res);
    }

    @Test
    void allShouldAcceptAnyWord(){
        map.put("res-all", new String[]{"o4r01012!@@@^%25"});
        boolean res = validator.isValid(map);
        assertTrue(res);
    }

    @Test
    void dateShouldContainsThreeNumbersSeparateByDots(){
        map.put("res-date", new String[]{"01.01.2017"});
        boolean res = validator.isValid(map);
        assertTrue(true);
    }

    @Test
    void dateShouldNotContainYearFormatedWithLessThenFourDigites(){
        map.put("res-date", new String[]{"01.01.20"});
        boolean res = validator.isValid(map);
        assertFalse(res);
    }

    @Test
    void namesThatDoNotCorrespondToConvetionShouldNotPass(){
        map.put("res-word", new String[]{"word"});
        map.put("res", new String[]{"1234"});
        boolean res = validator.isValid(map);
        assertFalse(res);
    }

    @Test
    void namesThatCorrespondToConvetionShouldBeAccepted(){
        map.put("res-alfa", new String[]{"word"});
        map.put("res-num", new String[]{"1234"});
        boolean res = validator.isValid(map);
        assertTrue(res);
    }

}