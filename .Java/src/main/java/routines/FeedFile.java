package routines;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

/*
 * Routines to support working with Feed Files
 */
public class FeedFile {
    static final Logger logger = Logger.getLogger(FeedFile.class);
    
    /**
     * Date Format for Date only
     */
    public static final String DF_DATE = "yyyy-MM-dd";
    
    /**
     * Date Format for Date and Time 
     */
    public static final String DF_DATETIME = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * Date Format for Date and Time with Timezone/Offset
     */    
    public static final String DF_DATETIMETZ = "yyyy-MM-dd HH:mm:ss XXX";
	
    /**
     * Concatenates a folder and a filename, avoiding double slashes etc.
     *
     * {talendTypes} String
     *
     * {Category} FeedFile
     *
     * {param} String(1) folder: The folder path
     * {param} String(2) fileName: The name of the file
     *
     * {example} getFilePath("/s3", "test") # /s3/test
     *
     */	
    public static String getFilePath(String folder, String fileName) {
    	return new File(folder, fileName).getAbsolutePath();
    }
 
    /**
     * Retrieves the Timezone offset portion of a Date
     *
     * {talendTypes} Date
     *
     * {Category} FeedFile
     *
     * {param} Date(1) date: The date to retrieve zone information for
     *
     * {example} getTimeZoneOffset(date) # +07:00
     *
     */ 
    public static String getTimeZoneOffset(String date) {
        if(date == null) {
            return null;
        }
        
        date = date.trim();
        if(date.isEmpty()) {
            return null;
        }
        
        try {
            // Verify we have a DateTimeTz with timezone - This will throw a parse exception
            new SimpleDateFormat(DF_DATETIMETZ).parse(date);
            
            // Just extract it as a string
            String offset = date.substring(date.lastIndexOf(" ")).trim();
            return offset;
        } catch (Exception e) {
            // NOP
        }
        
        throw new IllegalArgumentException("Invalid Offset Value from Feed for DateTime: " + date);
    }
    
    
    /**
     * Retrieves the Date and Time portion of a Date String
     *
     * {talendTypes} String
     *
     * {Category} FeedFile
     *
     * {param} String(1) date: The date string to retrieve date time information for
     *
     * {example} getDateTime("2002-05-30T23:00:00-0500") # 2002-05-30 23:00:00
     *
     */ 
    public static Date getDateTime(String date) {
        if(date == null) {
            return null;
        }
        
        date = date.trim();
        if(date.isEmpty()) {
            return null;
        }
        
        // Date Time Format
        try {
            return new SimpleDateFormat(DF_DATETIME).parse(date);
        } catch (Exception ex) {
            logger.trace("Error parsing Date Time with format(" + DF_DATETIME + "): " + date);
        }
        
        // Date Time Format
        try {
            return new SimpleDateFormat(DF_DATETIMETZ).parse(date);
        } catch (Exception ex) {
            logger.trace("Error parsing Date Time with format(" + DF_DATETIMETZ + "): " + date);
        }
        
        // Just Date Time
        try {
            return new SimpleDateFormat(DF_DATE).parse(date);
        } catch (Exception ex) {
            logger.trace("Error parsing Date Time with format(" + DF_DATE + "): " + date);
        }

        // Non-Parseable date time, Fail the Feed
        throw new IllegalArgumentException("Invalid Date Value from Feed: " + date);
    }
    
    /**
     * Returns the clean version of feed file name without path info and zip extension
     * {talendTypes} String
     *
     * {Category} FeedFile
     *
     * {param} String fileName: The input file name with full path
     *
     * {example} getCleanFeedFileName("/s3/feed_incoming/member_20170907-092803.csv.zip") # member_20170907-092803.csv
     *
     */
    public static String getCleanFeedFileName(String fileName) {
    	fileName = fileName.substring(fileName.lastIndexOf("/")+1);
    	fileName = fileName.replaceAll("[ -]", "_");
    	if(fileName.toLowerCase().endsWith(".zip")) {
    		fileName = fileName.substring(0, fileName.lastIndexOf("."));
    	}
    	return fileName;
    }
    
    /**
     * Types of Feeds that we process. 
     * By convention the Enum Name (Upper Case) = Feed File prefix (Lower Case) = Staging Table name.
     */
    public enum FileType {
    	
		ACCOUNT_ADMINS("account_admins"),
		ACCOUNT_AFFILIATES("account_affiliates"),
		ACCOUNT_MEMBER_MAP("account_member_map"),
		ACCOUNT_RATE_PLAN_MAP("account_rate_plan_map"),
		ACCOUNTS("accounts"),
		ADDRESSES("addresses"),
		AFFILIATES("affiliates"),
		APPLICATIONS_FINAL("applications_final"),
		COMMUNITIES_NP("communities_np"),
		CORPORATE_APPLICATIONS("corporate_applications"),
		COUPON_CLAIMS("coupon_claims"),
		COUPONS("coupons"),
		CREDITS("credits"),
		EXCHANGE_RATES("exchange_rates"),
		LOCATIONS("locations"),
		LOCATIONS_NP("locations_np"),
		MEMBER_ATTRITION_REASONS("member_attrition_reasons"),
		MEMBER_JOIN_INFO("member_join_info"),
		MEMBER_LAST_APPLICATION_INFO("member_last_application_info"),
		MEMBER_LAST_REGISTRATION_SESS("member_last_registration_sess"),
		MEMBER_LEFT_INFO("member_left_info"),
		MEMBER_REJOIN_HISTORY("member_rejoin_history"),
		MEMBER("member"),
		MEMBERS_EXT("members_ext"),
		RATE_PLANS("rate_plans"),
		REGISTRATION_SESSION_HISTORIES("registration_session_histories"),
		REGISTRATION_SESSIONS("registration_sessions"),
		RESERVATIONS_NP("reservations_np"),
		RESERVATIONS("reservations"),
		STRONGVIEW_AGG_DATA("strongview_agg_data"),
		STRONGVIEW_CONV_DATA("strongview_conv_data"),
		STRONGVIEW_DEVICE_DATA("strongview_device_data"),
		STRONGVIEW_DOWNLOAD_FILES("strongview_download_files"),
		STRONGVIEW_MAILING_DATA("strongview_mailing_data"),
		STRONGVIEW_PROG_EXPORT_DATA("strongview_prog_export_data"),
		STRONGVIEW_PROGRAM_FILES("strongview_program_files"),
		STRONGVIEW_SUCCESS_DATA("strongview_success_data"),
		STRONGVIEW_TRACK_DATA("strongview_track_data"),
		SUPERSEDED_RESERVATIONS("superseded_reservations"),
		TRANSACTIONS("transactions"),
		VEHICLES_NP("vehicles_np"),
		VEHICLES("vehicles"),
		WAIVER_PLANS("waiver_plans"),
		WAIVERS("waivers"),
		WEX_FUEL_DATA("wex_fuel_data"),
		ZIPFLEETS("zipfleets"),
		ZONES("zones")
        ;
              
        private final String name;
        private FileType(String name) {
            this.name = name;
        }

        public String filePattern() {
            return this.name + "_[0-9]*.csv.zip";
        }
        
        public String toString() {
            return this.name;
        }
        
        /**
         * Retrieves list of file names to process
         *
         * {talendTypes} Object
         *
         * {Category} FeedFile
         *
         * {example} fileNames() # Set(addresses, locations, ...)
         *
         */ 
        public static java.util.Set<String> fileNames() {
            java.util.Set<String> retVal = new java.util.LinkedHashSet<String>();
            for(FileType type: FileType.values()) {
                retVal.add(type.name);
            }
            return retVal;
        }
    };
    
    /**
     * Mapping between Salesforce import file names to db table names
     */
    public static final HashMap<String, String> SalesforceFileDbMapping = new HashMap<String, String>() {{
        put("Bounces","salesforce_bounces");
        put("Clicks","salesforce_clicks");
        put("Complaints","salesforce_complaints");
        put("Conversions","salesforce_conversions");
        put("Opens","salesforce_opens");
        put("SendJobs","salesforce_sendjobs");
        put("Sent","salesforce_sent");
        put("Surveys","salesforce_surveys");
        put("Unsubs","salesforce_unsubs");
        put("Zipcar_daily_master_send_log_report","salesforce_log_send");
        put("Zipcar_Admin_control_report_log","salesforce_log_control");
        put("Zipcar_daily_unsubscribes_report","salesforce_log_unsubs");
    }};
}
