package routines;

public class BooleanUtils {

    public static final String CHAR_TRUE = "t";
    public static final String CHAR_FALSE = "f";

    /**
     * boolChar: Returns t/f based on the passed in String.
     * 
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} boolChar("t") input: The string to sanitize
     * 
     * {example} boolChar("t") # t 
     * {example} boolChar("T") # t 
     * {example} boolChar("f") # f 
     * {example} boolChar("junk") # f
     */
    public static String boolChar(String value) {
        if (value == null) {
            return CHAR_FALSE;
        }
        
        value = value.trim().toLowerCase();
        if(value.equals("t")) {
            return CHAR_TRUE;
        }
        
        return CHAR_FALSE;
    }
    
    /**
     * isTrue: Returns true/false based on the passed in String.
     * 
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} isTrue("t") input: The string to test
     * 
     * {example} isTrue("t") # true
     * {example} isTrue("junk") # false
     */
    public static boolean isTrue(String value) {
    	return boolChar(value).equals("t");
    }
}
