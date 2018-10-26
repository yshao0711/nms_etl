package routines;

import org.apache.log4j.Logger;

public class StringUtils {
    
    static final Logger logger = Logger.getLogger(StringUtils.class);

    /**
     * trimToNull: Null safe trim empty string to null
     * 
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} string("value") input: The string need to be trimmed.
     * 
     * {example} trimToNull("   junk   ") # junk {example} trimToNull("      ")
     * # null
     */
    public static String trimToNull(String value) {
        if (value == null) {
            return null;
        }

        value = value.trim();
        return value.isEmpty() ? null : value;
    }
    
    /**
     * trimToEmpty: trim null string to empty string
     * 
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} string("value") input: The string need to be trimmed.
     * 
     * {example} trimToEmpty("   junk   ") # junk 
     * {example} trimToEmpty("      ") # ""
     * {example} trimToEmpty(null) # ""
     */
    public static String trimToEmpty(String value) {
        if (value == null) {
            return "";
        }

        value = value.trim();
        return value.isEmpty() ? "" : value;
    }
    
    /**
     * cleanNumber: trim any leading and trailing non-digital characters
     * 
     * @param strNumber
     * 
     * {example} cleanNumber("ab 12345 cd") # 12345
     */
    public static String cleanNumber(String strNumber) {
    	if (strNumber == null) {
            return null;
        }
    	strNumber = strNumber.replaceAll("^[^\\d]+|[^\\d]+$", "");
    	return strNumber.isEmpty() ? null : strNumber;
    }
    
    /**
     * cleanText: remove all non-printable characters and trim to null safe
     * 
     * @param strText
     * 
     * {example} cleanText("This \u7279text \u7279is what I need") # This text is what I need
     */
    public static String cleanText(String strText) {
    	if(strText == null) {
    		return null;
    	}
    	strText = strText.replaceAll("\\P{Print}", "").trim();
    	return strText.isEmpty() ? null : strText;
    }
    
    /**
     * getFieldValue: return the field value from a row by its field name
     * 
     * @param input_row
     * @param fieldName
     * @return
     */
    public static String getFieldValue(Object input_row, String fieldName) {
    	try {
    		String returnVal = (String)input_row.getClass().getField(fieldName).get(input_row);
    		return cleanText(returnVal);
    	} catch (Exception ex) {
    		return null;
    	}
    }

    /**
     * properName: Returns the proper case of a string representing a name.
     * 
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} string("value") input: The string need to have proper case.
     * 
     * {example} properCase("this IS a TeSt") # This Is A Test
     */
    public static String properName(String value) {
        if (value == null) {
            return null;
        }

        // Remove contents within parenthesis
        value = value.replaceAll("\\(.*\\)", "");
        
        // Replace multiple whitespace with single space
        value = value.trim().replaceAll("\\s+", " "); 
        
        // these cause the character following to be capitalized
        // Everything after is lower-cased
        final String ACTIONABLE_DELIMITERS = " -'/\""; 

        StringBuilder sb = new StringBuilder();
        boolean capNext = true;

        for (char c : value.toCharArray()) {
            c = (capNext) ? Character.toUpperCase(c) : Character.toLowerCase(c);
            sb.append(c);
            capNext = (ACTIONABLE_DELIMITERS.indexOf((int) c) >= 0); 
        }
        
        return sb.toString();
    }
    
    /**
     * templateString: Returns the passed in string with parameters replaced. Placeholders are defined where the names are wrapped by conventions using 
     * {{placeholdername}}
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} string("select * from ${schema}.name where a=${value}") input: The string with parameters in it
     * {param} string("schema", "test") input: The string pair denoting placeholder name and its value
     * 
     * {example} templateString("select * from {{schema}}.name where a={{value}}", "schema", "test", "value", "123") # select * from test.name where a=123
     */
    public static String templateString(String template, String ... placeholders) {
        if (template == null) {
            return null;
        }
        
        if(placeholders.length % 2 != 0) {
            throw new IllegalArgumentException("Placeholders must have key,value pairs");
        }
        
        // Replace all instances of the placeholders
        for(int i=0; i < placeholders.length; i += 2) {
            String param = "{{" + placeholders[i] + "}}";
            String value = placeholders[i + 1];
            
            template = template.replace(param, value);
        }
        
        logger.trace("Template String: " + template);
        
        return template;
    }
    
    /**
     * removes the file extension, normally after unzip or decryption
     * @param fileName
     * @param extension
     * @return
     */
    public static String stripFileExtension(String fileName, String extension) {
    	if(fileName.indexOf(extension) != -1) {
    		return fileName.replace(extension, "");
    	} else {
    		return fileName;
    	}
    }
    
}
