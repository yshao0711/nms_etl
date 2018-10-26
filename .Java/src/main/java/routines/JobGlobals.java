package routines;

import org.apache.log4j.Logger;

/**
 * Specify and set Global/Shared variables that can be accessed by all jobs in the process.
 * Fields must be static, with getters/setters
 */
public class JobGlobals {
    
    static final Logger logger = Logger.getLogger(JobGlobals.class);

    private static Long jobId;

    public static Long getJobId() {
        return jobId;
    }

    public static void setJobId(Long jobId) {
        if(JobGlobals.jobId == null) {
            JobGlobals.jobId = jobId;
            logger.info("Job Id: " + jobId);
        } else {
            throw new IllegalStateException("Cannot override Job Id, already set");
        }
    }
}
