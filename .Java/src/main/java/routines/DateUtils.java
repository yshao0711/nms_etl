package routines;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

/*
 * 
 */
public class DateUtils {
    
    static final Logger logger = Logger.getLogger(DateUtils.class);
    
    static private Date jobRunDate = null;
    
    static final int WEEKS_OFFSET = 26;

    /**
     * runDate: Returns the Run Date for the job. Can only be initialized once. Once a run-date is initialized, this method always returns
     * the same date. If no run date is passed into the function, it defaults to today.
     * 
     * 
     * {talendTypes} Date
     * 
     * {Category} User Defined
     * 
     * {param} date(runDate) input: The desired run date - Can be from the context/passed into the job.
     * 
     * {example} runDate(new Date()) # date
     */
    public static synchronized Date runDate(Date runDate) {
        if (runDate != null && jobRunDate == null) {
            logger.info("Using Run Date: " + runDate);
            jobRunDate = runDate; // Always use this date
            return runDate;
        }
        
        if(jobRunDate != null) {
            return jobRunDate;
        }
        
        jobRunDate = new Date(); // default to now
        
        logger.info("Initializing Run Date to: " + TalendDate.formatDate("yyyy-MM-dd", jobRunDate));
        
        return jobRunDate;
    }
    
    /**
     * dateToSqlStr: Returns the Run Date for the job as a String. 
     * 
     * {talendTypes} Date
     * 
     * {Category} User Defined
     * 
     * {param} date(runDate) input: The desired run date - Can be from the context/passed into the job.
     * 
     * {example} runDate(new Date()) # 2016-01-03 date
     */
    public static synchronized String dateToSqlStr(Date targetDate) {
        return "'" + TalendDate.formatDate("yyyy-MM-dd", targetDate) + "'";
    }

    /**
     * endDate: Computes the Sunday prior to the runDate value
     * 
     * @param runDate The date of the job execution
     * @return end of execution range
     * 
     * {talendTypes} Date
     * 
     * {Category} User Defined
     * 
     * {param} date(execDate) runDate: The desired run date - Can be from the context/passed into the job.
     * 
     * {example} endDate(new Date()) # 2017-01-15
     */
    public static synchronized Date endDate(Date runDate) {
    	Date runningDate = DateUtils.runDate(runDate);
    	
    	int dow = TalendDate.getPartOfDate("DAY_OF_WEEK", runningDate);
    	
    	// DAY_OF_WEEK for Sunday = 1
    	return TalendDate.addDate(runningDate, -dow + 1, "dd");
    }

    /**
     * startDate: Computes the Monday prior to the runDate value offset by N weeks.
     *            Defaults to 26 weeks if offset is not given or zero
     * 
     * @param runDate The date of the job execution
     * @param weeks   The offset in weeks 
     * @return start of the execution range
     * 
     * {talendTypes} Date
     * 
     * {Category} User Defined
     * 
     * {param} date(runDate) run: The desired run date - Can be from the context/passed into the job.
     * {param} int(offset) weeks: The number of weeks to go back in time
     * 
     * {example} endDate(new Date(), 26) # 2016-07-18s
     */
    public static synchronized Date startDate(Date runDate, int weeks) {
    	
    	Date endDate = DateUtils.endDate(runDate);
    	
    	if (weeks == 0) {
    		weeks = DateUtils.WEEKS_OFFSET;
    	}
    	
    	return TalendDate.addDate(endDate, -(weeks * 7 - 1), "dd");
    }
    
    /**
     * dayOfWeek: Computes the Day of the Week from the passed in Date, and returns the java time enum.
     * 
     * {talendTypes} Date
     * 
     * {Category} User Defined
     * 
     * {param} dayOfWeek(date) input: The desired date - Can be from the context/passed into the job.
     * 
     * {example} dayOfWeek(new Date()) # MONDAY
     */    
    public static DayOfWeek dayOfWeek(Date date) {
        String dayText = new SimpleDateFormat("EEEE").format(date).toUpperCase();
        logger.info("Day of Week: " + dayText + " for date: " + TalendDate.formatDate("yyyy-MM-dd", date));
        
        return DayOfWeek.valueOf(dayText);
    }

    /**
     * runDate: Returns the Run Date for the job. This method allows resetting the run-date with the given date value. 
     * If no run date is passed into the function, it defaults to today.
     * 
     * 
     * {talendTypes} Date
     * 
     * {Category} User Defined
     * 
     * {param} date(runDate) input: The desired run date - Can be from the context/passed into the job.
     * 
     * {example} runDate(new Date()) # date
     */
    public static synchronized Date overrideRunDate(Date runDate) {
        if (runDate != null) {
            logger.info("Overridding Run Date with: " + runDate);
            jobRunDate = runDate; // Always use this date
        }
        
        if(jobRunDate == null) {
        	logger.info("Defaulting Run Date to Today");
            jobRunDate = new Date(); // default to now
        }
        
        logger.info("Overridding Run Date to: " + TalendDate.formatDate("yyyy-MM-dd", jobRunDate));
        
        return jobRunDate;
    }
    
    /**
     * runDate: this method overrides the runDate(Date runDate). It verifies the run date passed from command line
     * and prints error messages and exits the job if the date is invalid. 
     * Otherwise it calls the overridden runDate method to return the date object
     * 
     * {talendTypes} Date
     * 
     * {Category} User Defined
     * 
     * {param} date(runDate) input: The desired run date - Can be from the context/passed into the job.
     *
     * {param} Properties context_param: it holds all the context parameters passed from command line.
     * 
     * {example} runDate(new Date(), context_param) # date
     */
    public static synchronized Date runDate(Date runDate, boolean runDateNoCheck, Properties context_param) {
    	String contextRunDate = context_param.getProperty("RunDate");
    	Date today = new Date();
    	
    	//if no RunDate provided at all, just return today
    	if (StringUtils.trimToNull(contextRunDate) != null) {  		
	    	logger.info("You entered the run date: " + contextRunDate);
	    	//check RunDate format
	    	boolean formatError = false;
	    	String strDate = "";
	    	String strPattern = "";
	    	runDate = null;
	    	int pos = contextRunDate.indexOf(";");
	    	if( pos == -1) {
	    		formatError = true;
	    	} else {
	    		strDate = contextRunDate.substring(pos + 1);
	    		strPattern = contextRunDate.substring(0, pos);
	    		try {
		    		SimpleDateFormat sdf = new SimpleDateFormat(strPattern);
		    		runDate = sdf.parse(strDate);
		    		if (runDate == null) {
		    			formatError = true;
		    		}
		    		if(!strDate.equals(sdf.format(runDate))) {
		    			formatError = true;
		    		} 
	    		}catch (ParseException ex) {
		    		formatError = true;
		    	}
	    	}
	    	if(formatError) {
	    		logger.error("Please enter the command switch with your run date like this: "
	    				+ "\n-context_param:RunDate=\"yyyy-MM-dd;2017-02-01\""
	    				+ "\nAlso please make sure it's a valid date and follow the specified format.");
	    		System.exit(-1);
	    	}
    	}
    	if(runDate != null) {
	    	//make sure the RunDate is NOT a future date beyond today
	    	if(TalendDate.compareDate(runDate, today, "yyyy-MM-dd") == 1) {
	    		logger.error("Future dates beyond today are not allowed.");
	    		System.exit(-1);
	    	}
	    	//make sure if you running a date more than one day back, you need to have a override switch
	    	if(!runDateNoCheck && TalendDate.diffDate(today, runDate, "dd") >= 2) {
	    		logger.warn("Run date is more than one day back.");
	    		logger.warn("Please confirm your run date by providing another context parameter like this: " 
	    				+ "--context_param:RunDate_NoCheck=true");
	    		System.exit(-1);    		
	    	}
    	}
    	return DateUtils.runDate(runDate);
    }
    
    /**
     * isFirstDayOfMonth: check if runDate is on the first day of a month
     * @param runDate
     * @return true if runDate is first day of a month, false otherwise
     */
    public static boolean isFirstDayOfMonth(Date runDate) {
    	if(runDate == null) runDate = new Date();
    	Calendar c = Calendar.getInstance();
    	c.setTime(runDate);
    	return c.get(Calendar.DAY_OF_MONTH) == 1;
    }
    
    /**
     * getFirstDayOfMonth: get the first day of the month of the runDate
     * @param runDate
     * @return first day of the month of the runDate
     * {example} 2018-05-01
     */
    public static Date getFirstDayOfMonth(Date runDate) {
    	if(runDate == null) runDate = new Date();
    	Calendar c = Calendar.getInstance();
    	c.setTime(runDate);
    	c.set(Calendar.DAY_OF_MONTH, 1);
    	c.set(Calendar.HOUR_OF_DAY, 0);
    	c.set(Calendar.MINUTE, 0);
    	c.set(Calendar.SECOND, 0);
    	c.set(Calendar.MILLISECOND, 0);
    	return c.getTime();
    }
    
    /**
     * getMonthlyEndDate: return the last day of previous month of the runDate
     * @param runDate
     * @return endDate for the monthly aggregation calculation
     * {example} 2018-04-30
     */
    public static Date getMonthlyEndDate(Date runDate) {
    	if(runDate == null) runDate = new Date();
    	Date d = getFirstDayOfMonth(runDate);
    	return TalendDate.addDate(d, -1, "dd");
    }
    
    /**
     * getMonthlyStartDate: return the date that weeks before the monthly end date
     * @param runDate
     * @param weeks
     * @return startDate for the monthly aggregation calculation
     * {example} 2017-10-31
     */
    public static Date getMonthlyStartDate(Date runDate, int weeks) {
    	if(runDate == null) runDate = new Date();
    	Date d = getMonthlyEndDate(runDate);
    	if (weeks == 0) {
    		weeks = DateUtils.WEEKS_OFFSET;
    	}	
    	return TalendDate.addDate(d, -(weeks * 7 - 1), "dd");
    }
}
