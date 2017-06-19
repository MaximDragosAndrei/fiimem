package validator;

/**
 * Created by rusub on 5/7/2017.
 */
public class BodyParser implements IBodyParser{

    private String body;
    private int currentIndex;

    public BodyParser(){
        this.currentIndex = 0;
    }
    public BodyParser(String body){
        this.body = body;
        this.currentIndex = 0;
    }

    /*
        Returns an array of 2 strings
            first: current attribute
            second: current value of attribute
     */
    @Override
    public boolean hasNext(){
        return this.currentIndex < this.body.length();
    }

    @Override
    public String[] next(){
        String[] pair = new String[2];
        if(!hasNext())
            return null;
        pair[0] = this.getWord();
        ++this.currentIndex;
        pair[1] = this.getWord();
        ++this.currentIndex;
        return pair;
    }

    @Override
    public void setBody(String body){
        this.body = body;
        this.currentIndex = 0;
    }

    private int hexaReprezentationToInt(char chr){
        if(chr >= '0' && chr <= '9') {
            return chr - '0';
        }
        int repr = (int)chr | (1 << 5);
        return repr - 87;
    }

    private char getCharacterReprezentation(int first, int second){
        return (char)((first << 4) + second);
    }

    private String getWord(){
        StringBuilder word = new StringBuilder();
        while(this.body.length() > currentIndex &&
                this.body.charAt(currentIndex) != '=' &&
                this.body.charAt(currentIndex) != '&'){
            if(body.charAt(currentIndex) == '%') {
                word.append(
                        this.getCharacterReprezentation(
                                this.hexaReprezentationToInt(body.charAt(currentIndex + 1)),
                                this.hexaReprezentationToInt(body.charAt(currentIndex + 2))));
                currentIndex += 2;
            }
            else
                word.append(this.body.charAt(currentIndex));
            ++currentIndex;
        }
        return word.toString();
    }
}
