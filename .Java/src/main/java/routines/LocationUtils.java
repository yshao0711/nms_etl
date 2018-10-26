package routines;


public class LocationUtils {

    /**
     * basePostalCode: Trim a postal code to its base code. For US, this only retains the 5 digits, and strips out the +4.
     * 
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} string("postalCode") input: The original postal code
     * {param} string("isoCountry") input: The country containig the postal code
     * 
     * {example} basePostalCode(" 12345-6789") # 12345
     * {example} basePostalCode("12345") # 12345
     * {example} basePostalCode("123456789") # 12345
     * {example} basePostalCode(null) # null
     * {example} basePostalCode("   ") # null
     */
    public static String basePostalCode(String postalCode, String isoCountry) {
        if (postalCode == null) {
           return null;
        }
        
        postalCode = postalCode.trim().toUpperCase(); // Standardize case
        isoCountry = isoCountry == null ? "": isoCountry.trim().toUpperCase();
        
        // If we are a US code, we need to trim to first 5 digits of the postal code
        if(isoCountry.equals("US")   // US
           || isoCountry.equals("VI")   // Virgin Islands (U.S.)
           || isoCountry.equals("UM")   // United States Minor Outlying Islands
        ) {
            // Cleanup non-digits -- such as Location Id: 1269292273, Address Id: 1269292270 =  "OR 97401"
            postalCode = postalCode.replaceAll( "[^\\d]", "" );
            
            
            if(postalCode.length() > 5) {
                postalCode = postalCode.substring(0, 5);
            }
        }
        
        return postalCode.isEmpty() ? null: postalCode;
    }
}
