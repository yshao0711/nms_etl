package routines;

public class NotificationUtils {

    
    /**
     * jobStatusSubject: Generates the notification subject line.
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} jobStatusSubject("Test", true) input: The string need to be printed.
     * 
     * {example} jobStatusSubject(context.environment, true, "Test")  # [COMPLETED] Zipcar MarketingDB - PROD - Test
     * {example} jobStatusSubject(context.environment, false, "Test") # [FAILED] Zipcar MarketingDB - PROD - Test 
     */
    public static String jobStatusSubject(String environment, boolean isSuccess, String message) {
        return NotificationUtils.formatSubject(isSuccess ? "[COMPLETED]" : "[FAILED]", environment, message);
    }
    
    
    /**
     * jobStatusSubject: Generates the notification subject line.
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} alertSubject(context.environment, "Test") input: The string need to be printed.
     * 
     * {example} alertSubject(context.environment, "Test")  # [ALERT] Zipcar MarketingDB - PROD - Test
     */
    public static String alertSubject(String environment, String message) {
        return NotificationUtils.formatSubject("[ALERT]", environment, message);
    }
    
    /**
     * validationSubject: Generates the notification subject line.
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} validationSubject("PROD", true, "Test") input: The string need to be printed.
     * 
     * {example} validationSubject(context.environment, true, "Test")  # [VALIDATION PASSED] Zipcar MarketingDB - PROD - Test
     * {example} validationSubject(context.environment, false, "Test") # [VALIDATION PROBLEM] Zipcar MarketingDB - PROD - Test 
     */
    public static String validationSubject(String environment, boolean isSuccess, String message) {
        return NotificationUtils.formatSubject(isSuccess ? "[VALIDATION PASSED]" : "[VALIDATION PROBLEM]", environment, message);
    }
    
    
    /**
     * Formats the notification/alert subject line
     * @param prefix
     * @param environment
     * @param message
     * @return
     */
    private static final String formatSubject(String prefix, String environment, String message) {
        String subject = prefix + " Zipcar MarketingDB";
        subject += " - " + environment.toUpperCase() + " - " + message;
        
        return subject;
    }
}
