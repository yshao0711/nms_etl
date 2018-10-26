package routines;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * Generally used as part of a tJavaFlex component to laod the config in the start code section, then
 * send each config parameters in the main code section.
 * 
 * By default, this looks for a file with the jobname.cfg in the working directory. An Environment
 * variable as CONFIG_FILE_<JOBNAME> can be used to explicitly define the path to the config file.
 * 
 * Properties can be encrypted in the config file by appending ! to their names. These will be decrypted
 * using the Talend Password Utilities, and must have been encrypted using the same utilities.
 */
public class ConfigFile {
    static final Logger logger = Logger.getLogger(ConfigFile.class);
    
    // For any other jobs that want to utilize the same Config file as the Process Daily Feeds Job
    public static final String JOB_PROC_DAILY_FEEDS = "ProcessDailyFeeds";
    public static final String JOB_PROC_DAILY_SALESFORCE = "ProcessDailySalesforce";
    public static final String JOB_PROC_DAILY_MEDALLIA = "ProcessDailyMedallia";
    
    /**
     * loadConfig: Loads the configuration file for the job. The default config file is in the job's folder, with 
     * the same name as the job.
     * 
     * 
     * {Category} User Defined
     * 
     * {param} string("jobName") input: The name of the job
     * {param} properties("context_param") input: The job's context_param instance. This contains cli parameters.
     * 
     * {example} loadConfig(jobName, context_param) # 
     */
    public static Map<String, String> loadConfig(String jobName, Properties context_param ) {
        String envConfigName = "CONFIG_FILE_" + jobName.toUpperCase();
        Map<String, String> retVal = new HashMap<String, String>();
        
        String propertyFilePath = System.getenv(envConfigName);        
        File propertyFile = null;
        
        if(propertyFilePath == null) {
            logger.info("Searching for Default Config File. Use environment property to specify alternate file: '" + envConfigName + "'");
            
            // Check default folder which is the root of the job installations
            propertyFile = new File("/data/talend/jobs/" + jobName + ".cfg");
            if(propertyFile.exists()) {
                propertyFilePath = "/data/talend/jobs/" + jobName + ".cfg";
            } else {
                // Attempt the job working folder
                logger.info("No External Config File: " + propertyFile.getAbsolutePath());
                propertyFilePath = jobName + ".cfg";
            }
        }

        // Use the path that was sent in, or the current search path
        propertyFile = new File(propertyFilePath);
        
        // Check if file exists.
        if(!propertyFile.exists()) {
            logger.info("No External Config File: " + propertyFile.getAbsolutePath());
            return retVal;
        }
        
        logger.info("Using external Config File: " + propertyFile.getAbsolutePath());
        Properties externalProperties = new Properties();
        
        try {
            externalProperties.load(new FileInputStream(propertyFile));
            decryptPasswords(externalProperties);
        } catch (Exception ex) {
            logger.error("Failed to load config file: " + propertyFile.getAbsolutePath(), ex);
            System.exit(1); // Exit process with failure
        }
        
        // Use only those properties that have not been specified in context_param
        // context_param contains those parameters overridden in the command line, and those take precedence
        for(String name: externalProperties.stringPropertyNames()) {
            if(!context_param.containsKey(name)) {
                retVal.put(name, externalProperties.getProperty(name));
            } else {
                logger.info("Specified as --context_param, Skipping File Config for '" + name + "' ");                
            }
        }
                
        return retVal;
    }

    /**
     * Decrypts any properties that have been encrypted. Just uses Talends crypto utilities.
     * 
     * Encrypted properties are identified with names ending with !
     * 
     * @param externalProperties
     */
    private static void decryptPasswords(Properties externalProperties) {
        for(String name: externalProperties.stringPropertyNames()) {
            if(name.endsWith("!")) {
                String value = externalProperties.getProperty(name).trim();
                externalProperties.remove(name);

                // Name for un-encrypted field
                name= name.substring(0, name.length()-1).trim();
                
                // Decrypt value and set
                if(value.length() > 0) {
                    value = routines.system.PasswordEncryptUtil.decryptPassword(value);
                    externalProperties.setProperty(name, value);
                    logger.trace("Decrypting Config: " + name);
                }
            }
        }
    }
}
