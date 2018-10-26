package routines;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;

import org.apache.log4j.*;

/*
 * Desirable for this to be a stand-alone component.
 * There are several of them in the Talend Exchange. But they appear to be lacking at this time/being dated
 * among other things, or not working well between development mode in the IDE and deployment mode, when it 
 * is built.
 */
public class Logging {

    static boolean isInitialized = false;

    // @formatter:off
    private static final Level PRIORITY_TO_LEVEL[] = { 
            Level.TRACE,
            Level.DEBUG,
            Level.INFO,
            Level.WARN,
            Level.ERROR,
            Level.FATAL
    };
    // @formatter:on
    
    // Priority Levels from tWarn
    public static final int PRIORITY_TRACE = 1;
    public static final int PRIORITY_DEBUG = 2;
    public static final int PRIORITY_INFO = 3;
    public static final int PRIORITY_WARN = 4;
    public static final int PRIORITY_ERROR = 5;
    public static final int PRIORITY_FATAL = 6;
    
    
    private static String logFilePath = "";
    

    private static final String LOG_PATTERN = "%d %p [%c] %m%n";

    /**
     * Mapping of Friendly Component Names
     */
    private static final Map<String, String> componentFriendlyNames = new ConcurrentHashMap<String, String>();
    
    /**
     * Log progress every X rows, based on this number. This is so we get periodic progress feedback.
     */
    public static int PROGRESS_ROW_COUNT = 1000;

    /**
     * initializeLogging: Initializes Log4J, based on the Project Preferences.
     * Invoke from a PreJob using a tJava component. Desirable for this to be a
     * stand-alone component.
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} string("jobName") input: The name of the job for the log file.
     * Pass in the global jobName
     * 
     * {example} initializeLogging(jobName)
     */
    public static void initializeLogging(String jobName) {
        if (isInitialized) {
            logMessage(new Date(), jobName, 3, "Job", "Job started");
            return; // Already initialized
        }

        configureAppenders(jobName);
        
        System.out.println("Using log folder: " + getLogFolderPath());
        System.out.println("To change the log folder, set environment variable: TALEND_LOG_FOLDER_PATH to override it.");

        // redirect stderr to Log4J
        System.setErr(new PrintStream(new LoggingOutputStream(Logger.getRootLogger(), Level.ERROR), true));
        
        // Log the Job Start
        logMessage(new Date(), jobName, 3, "Job", "Job started");

        isInitialized = true;
    }

    /**
     * configures all the log4j appenders
     */
    private static void configureAppenders(final String jobName) {
        // Create and configure the Appenders manually here
        Logger.getRootLogger().removeAllAppenders();
        PatternLayout messagePattern = new PatternLayout(LOG_PATTERN);
        
        AsyncAppender asyncAppender = new AsyncAppender();
        asyncAppender.setThreshold(Level.TRACE);
        asyncAppender.setBlocking(true);
        asyncAppender.setBufferSize(256);
        
        asyncAppender.addAppender(configureConsoleLogger(messagePattern, Level.TRACE));
        asyncAppender.addAppender(configureRollingFileLogger(messagePattern, Level.INFO, jobName));
        asyncAppender.addAppender(configureTraceFileLogger(messagePattern, jobName));
        
        
        asyncAppender.activateOptions();
        
        Logger.getRootLogger().addAppender(asyncAppender);
        Logger.getRootLogger().setLevel(Level.TRACE);
        
        // suppress debug messages
        Logger.getLogger("com.amazonaws").setLevel(Level.WARN);
        Logger.getLogger("org.apache").setLevel(Level.WARN);
    }

    /**
     * logMessage: Logs a message. Typically called from a tJavaRow object
     * 
     * {talendTypes} String {talendTypes} Date
     * 
     * {Category} User Defined
     * 
     * {param} moment The date for the message {param} jobName Name of the Job.
     * {param} priority tWarn based priority of the Message {param} component
     * Origin/Component sending the Message {param} message The log message
     * 
     * {example} logMessage(input_row.moment, input_row.job, input_row.priority,
     * input_row.origin, input_row.message);
     */
    public static void logMessage(Date moment, String jobName, int priority, String component, String message) {
        Level level = Level.DEBUG;

        // Map tWarn Priority to Log4J Levels
        priority = priority - 1; // 0 Index it;
        priority = priority < 0 ? 0 : priority;
        level = priority >= PRIORITY_TO_LEVEL.length ? Level.DEBUG : PRIORITY_TO_LEVEL[priority];

        // For now, ignore the 'moment', let the logger stamp its own timestamp.
        String loggerName = friendlyName(jobName, component);
        Logger.getLogger(loggerName).log(level, message);
    }

    /**
     * Configures a Console Appender consistently across Jobs in this project
     * 
     * @param patternLayout
     * @param logLevel
     */
    private static Appender configureConsoleLogger(PatternLayout patternLayout, Level logLevel) {
        ConsoleAppender console = new ConsoleAppender(); // create appender
        // configure the appender
        console.setLayout(patternLayout);
        console.setThreshold(logLevel);
        console.activateOptions();
        
        return console;
    }

    /**
     * Configures a Rolling File Appender consistently across Jobs in this
     * project Outputs to /data/talend/logs/job-{jobName}.log You can redirect
     * the output by setting the context property: job.logging.base.dir
     * 
     * The log file that is created is always the Root/Parent Job that is
     * launched, and not any sub-jobs.
     * 
     * @param patternLayout
     * @param logLevel
     */
    private static Appender configureRollingFileLogger(PatternLayout patternLayout, Level logLevel, String jobName) {
        String logFileFolder = getLogFolderPath();
        File basePath = new File(logFileFolder); 
        File logFile = new File(basePath, "job-" + jobName + ".log");
        logFilePath = logFile.getAbsolutePath();

        RollingFileAppender fa = new RollingFileAppender();
        fa.setName("FileLogger");
        fa.setFile(logFile.getAbsolutePath());
        fa.setLayout(patternLayout);
        fa.setThreshold(logLevel);
        fa.setAppend(false);
        fa.setMaxFileSize(String.valueOf(Integer.MAX_VALUE));
        fa.setMaxBackupIndex(10); // Keep the last 10 runs
        fa.rollOver();
        fa.activateOptions();
        
        return fa;
    }

    /**
     * Configures a File Appender consistently across Jobs in this
     * project Outputs to /data/talend/logs/job-{jobName}.log You can redirect
     * the output by setting the context property: job.logging.base.dir
     * 
     * The log file that is created is always the Root/Parent Job that is
     * launched, and not any sub-jobs. This appender logs TRACE level log info, so its
     * file name is different, and there's only a single file without rolling.
     * 
     * @param patternLayout
     * @param logLevel
     */
    private static Appender configureTraceFileLogger(PatternLayout patternLayout, String jobName) {
        String logFileFolder = getLogFolderPath();
        File basePath = new File(logFileFolder); 
        File logFile = new File(basePath, "job-" + jobName + "-trace.log");

        FileAppender fa = new FileAppender();
        fa.setName("FileLogger");
        fa.setFile(logFile.getAbsolutePath());
        fa.setLayout(patternLayout);
        fa.setThreshold(Level.TRACE);
        fa.setAppend(false);
        fa.activateOptions();
        
        return fa;
    }
    
    /**
     * componentName: Registers a Friendly name for a component within a job.
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} jobName Name of the Job. {param} component Origin/Component
     * sending the Message {param} friendlyName The friendly name to use
     * 
     * {example} logMessage(input_row.moment, input_row.job, input_row.priority,
     * input_row.origin, input_row.message);
     */
    public static void componentName(String jobName, String component, String friendlyName) {
        componentFriendlyNames.put(jobName + ":" + component, friendlyName);
    }

    /**
     * Returns the Friendly Name for a component, or the raw component if no
     * mapping is registered
     * 
     * @param jobName
     * @param component
     * @return
     */
    private static String friendlyName(String jobName, String component) {
        String friendlyComponentName = componentFriendlyNames.get(jobName + ":" + component);
        
        if(friendlyComponentName != null) {
            if(friendlyComponentName.length() > 0) {
                return jobName + " | " + friendlyComponentName;
            }
            
            return jobName;
        }
        
        // Return friendly, or untranslated if no mapping
        return jobName + " | " + component;
    }
    
    /**
     * Get the log file path for the job
     */
    public static String getLogFilePath() {
    	return logFilePath;
    }
    
    /**
     * Get the log folder path from system environment or default to
     * "/date/talend/logs" if not set
     */
    private static String getLogFolderPath() {
    	String logFolderPath = System.getenv("TALEND_LOG_FOLDER_PATH");
    	if(StringUtils.trimToNull(logFolderPath) == null) {
    		logFolderPath = "/data/talend/logs";
    	}
    	return logFolderPath;
    }
    
    

	
	/** 
	 * Collects and Logs Rows. Since this is intended for use in iterators and main loops,
	 * Be mindful on the size of the Rows sets you are working with. This version formats
	 * the rows as a table. This class requires the rows are all of the same type.
	 */	
	public static class TableRowLogger {
		Field[] fields;
		private String[] fieldNames;
		private Integer[] fieldMaxLength;
		
		private ArrayList<String[]> rows = new ArrayList<String[]>(50);			
		
		/**
		 * Bad things happen if you mix the Class types of the row. Don't do that.
		 * @param row
		 */
		public void add(Object row) {	
			// First Row, init the schema and get the header info
			if(rows.size() == 0) {
				// Get the Fields for the row
				fields = row.getClass().getFields(); // Talends Row Structs uses public fields
				fieldNames = new String[fields.length];
				fieldMaxLength = new Integer[fields.length];
				// Default max length to field name
				for(int i=0; i < fields.length; i++) {
					fieldNames[i] = fields[i].getName();
					fieldMaxLength[i] = fieldNames[i].length();
				}
			}
			
			String[] rowValues = new String[fields.length];
			for(int i=0; i < fields.length; i++) {
				Object fldValue  ;
				try {
					fldValue = fields[i].get(row);
					if(fldValue != null) {
						rowValues[i] = StringUtils.trimToEmpty(String.valueOf(fldValue));
						fieldMaxLength[i] = Math.max(fieldMaxLength[i], rowValues[i].length());
					}
				} catch (Exception e) {
					e.printStackTrace(); // Log to error stream, we only expect Java perm errors which we should not get
					// NOP
				} 
			}
			
			// Collect the row values
			rows.add(rowValues);
		}		
		
		public boolean hasRows() {
			return rows.size() > 0;
		}
		
		/**
		 * Logs the rows as a Text Table, if there are text values, and returns the formatted table.
		 * @param jobName
		 * @param priority
		 * @param component
		 * @param message
		 * @return
		 */
		public String logRows(String jobName, int priority, String component, String message) {
			if(rows.isEmpty()) {
				// Nothing to log
				return null;
			}
			StringBuilder newMessage = new StringBuilder(message);
			newMessage.append("\n");
			
			String textTable = formatTextTable();
			newMessage.append(textTable);
			Logging.logMessage(new Date(), jobName, priority, component, newMessage.toString());
			
			return textTable;
		}		
		
		public String formatTextTable() {
			if(rows.isEmpty()) {
				return "No Row Data";
			}
			
			try {
				StringBuilder textTable = new StringBuilder();			
				
				// Add Table Header
				textTable.append(generateLineRow());
				textTable.append(generateValueRow(fieldNames));				
				textTable.append(generateLineRow());
				
				// Add value rows
				for(String[] row: rows) {
					textTable.append(generateValueRow(row));
				}
				
				// Add table footer
				textTable.append(generateLineRow());
				
				return textTable.toString();
			} catch (Exception ex) {
				return "Error generating Text Table: " + ex.toString();
			}
		}
		
		private String generateLineRow() {
			StringBuilder row = new StringBuilder();
			row.append("+");
			for(int i=0; i < fieldNames.length; i++) {
				row.append(StringHandling.RPAD("-", fieldMaxLength[i] + 1, "-"));
				row.append("-+");
			}
			row.append("\n");
			return row.toString();
		}
				
		private String generateValueRow(String[] values) {
			StringBuilder row = new StringBuilder();
			row.append("|");
			
			for(int i=0; i < fieldNames.length; i++) {
				String strValue =  values[i] == null ? " " : values[i];
				row.append(StringHandling.RPAD(" " + strValue, fieldMaxLength[i] + 1, " "));
				row.append(" |");	
			}
		
			row.append("\n");
			return row.toString();
		}		
		
		public String formatHtmlTable() {
			if(rows.isEmpty()) {
				return "No Row Data";
			}
			
			try {
				StringBuilder htmlTable = new StringBuilder();			
				htmlTable.append("<table >");
				
				// Add Table Header
				htmlTable.append("<tr>");
				for(String header: fieldNames) {
					htmlTable.append("<th>");
					htmlTable.append(header);
					htmlTable.append("</th>");
				}		
				htmlTable.append("</tr>");
				
				// Add value rows
				for(String[] row: rows) {
					htmlTable.append("<tr>");					
					for(String value: row) {
						htmlTable.append("<td>");
						htmlTable.append(value == null ? "" : value);
						htmlTable.append("</td>");						
						
					}
					htmlTable.append("</tr>");
				}
				
				// Add table footer
				htmlTable.append("</table>");
				return htmlTable.toString();
			} catch (Exception ex) {
				return "Error generating Text Table: " + ex.toString();
			}
		}		
	}
}

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * An OutputStream that flushes out to a Category.<p>
 * 
 * Note that no data is written out to the Category until the stream is
 *   flushed or closed.<p>
 * 
 * Example:<pre>
 * // make sure everything sent to System.err is logged
 * System.setErr(new PrintStream(new LoggingOutputStream(Category.getRoot(), Priority.WARN), true));
 * 
 * // make sure everything sent to System.out is also logged
 * System.setOut(new PrintStream(new LoggingOutputStream(Category.getRoot(), Priority.INFO), true));
 * </pre>
 * 
 * @author <a href="mailto://Jim.Moore@rocketmail.com">Jim Moore</a>
 * @see Category
 * 
 * URL to the source: http://svn.apache.org/repos/asf/logging/log4j/trunk/contribs/JimMoore/LoggingOutputStream.java
 */

class LoggingOutputStream extends OutputStream {
	protected static final String LINE_SEPERATOR = System.getProperty("line.separator");


	/**
	 * Used to maintain the contract of {@link #close()}.
	 */
	protected boolean hasBeenClosed = false;

	/**
	 * The internal buffer where data is stored. 
	 */
	protected byte[] buf;

	/**
	 * The number of valid bytes in the buffer. This value is always 
	 *   in the range <tt>0</tt> through <tt>buf.length</tt>; elements 
	 *   <tt>buf[0]</tt> through <tt>buf[count-1]</tt> contain valid 
	 *   byte data.
	 */
	protected int count;


	/**
	 * Remembers the size of the buffer for speed.
	 */
	private int bufLength;

	/**
	 * The default number of bytes in the buffer. =2048
	 */
	public static final int DEFAULT_BUFFER_LENGTH = 2048;


	/**
	 * The category to write to.
	 */
	protected Category category;

	/**
	 * The priority to use when writing to the Category.
	 */
	protected Priority priority;


	@SuppressWarnings("unused")
	private LoggingOutputStream() {
		// illegal
	}


	/**
	 * Creates the LoggingOutputStream to flush to the given Category.
	 * 
	 * @param cat        the Category to write to
	 * 
	 * @param priority   the Priority to use when writing to the Category
	 * 
	 * @exception IllegalArgumentException
	 *                   if cat == null or priority == null
	 */
	public LoggingOutputStream(Category cat, Priority priority)
			throws IllegalArgumentException {
		if (cat == null) {
			throw new IllegalArgumentException("cat == null");
		}
		if (priority == null) {
			throw new IllegalArgumentException("priority == null");
		}

		this.priority = priority;
		category = cat;
		bufLength = DEFAULT_BUFFER_LENGTH;
		buf = new byte[DEFAULT_BUFFER_LENGTH];
		count = 0;
	}


	/**
	 * Closes this output stream and releases any system resources
	 *   associated with this stream. The general contract of <code>close</code>
	 *   is that it closes the output stream. A closed stream cannot perform
	 *   output operations and cannot be reopened.
	 */
	public void close() {
		flush();
		hasBeenClosed = true;
	}


	/**
	 * Writes the specified byte to this output stream. The general
	 * contract for <code>write</code> is that one byte is written
	 * to the output stream. The byte to be written is the eight
	 * low-order bits of the argument <code>b</code>. The 24
	 * high-order bits of <code>b</code> are ignored.
	 * 
	 * @param b          the <code>byte</code> to write
	 * 
	 * @exception IOException
	 *                   if an I/O error occurs. In particular,
	 *                   an <code>IOException</code> may be thrown if the
	 *                   output stream has been closed.
	 */
	public void write(final int b) throws IOException {
		if (hasBeenClosed) {
			throw new IOException("The stream has been closed.");
		}

		// don't log nulls
		if (b == 0) {
			return;
		}

		// would this be writing past the buffer?
		if (count == bufLength) {
			// grow the buffer
			final int newBufLength = bufLength+DEFAULT_BUFFER_LENGTH;
			final byte[] newBuf = new byte[newBufLength];

			System.arraycopy(buf, 0, newBuf, 0, bufLength);

			buf = newBuf;
			bufLength = newBufLength;
		}

		buf[count] = (byte)b;
		count++;
	}


	/**
	 * Flushes this output stream and forces any buffered output bytes
	 *   to be written out. The general contract of <code>flush</code> is
	 *   that calling it is an indication that, if any bytes previously
	 *   written have been buffered by the implementation of the output
	 *   stream, such bytes should immediately be written to their
	 *   intended destination.
	 */
	public void flush() {
		if (count == 0) {
			return;
		}

		// don't print out blank lines; flushing from PrintStream puts out these
		if (count == LINE_SEPERATOR.length()) {
			if ( ((char)buf[0]) == LINE_SEPERATOR.charAt(0)  &&
					( ( count == 1 ) ||  // <- Unix & Mac, -> Windows
					( (count == 2) && ((char)buf[1]) == LINE_SEPERATOR.charAt(1) ) ) ) {
				reset();
				return;
			}
		}

		final byte[] theBytes = new byte[count];

		System.arraycopy(buf, 0, theBytes, 0, count);

		category.log(priority, new String(theBytes));

		reset();
	}


	private void reset() {
		// not resetting the buffer -- assuming that if it grew that it
		//   will likely grow similarly again
		count = 0;
	}
}

