// ============================================================================
//
// Copyright (c) 2006-2015, Talend Inc.
//
// This source code has been automatically generated by_Talend Open Studio for Data Integration
// / Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.


package zipcar_marketingdw.pgpencryptdecrypt_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;
 




	//the import part of tJava_1
	//import java.util.List;


@SuppressWarnings("unused")

/**
 * Job: PGPEncryptDecrypt Purpose: <br>
 * Description:  <br>
 * @author user@talend.com
 * @version 6.4.1.20170623_1246
 * @status 
 */
public class PGPEncryptDecrypt implements TalendJob {



	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}
	
	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	
	private final static String utf8Charset = "UTF-8";
	//contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String,String> propertyTypes = new java.util.HashMap<>();
		
		public PropertiesWithType(java.util.Properties properties){
			super(properties);
		}
		public PropertiesWithType(){
			super();
		}
		
		public void setContextType(String key, String type) {
			propertyTypes.put(key,type);
		}
	
		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}
	
	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();
	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties){
			super(properties);
		}
		public ContextProperties(){
			super();
		}

		public void synchronizeContext(){
			
			if(bashCmd != null){
				
					this.setProperty("bashCmd", bashCmd.toString());
				
			}
			
			if(gpgCmd != null){
				
					this.setProperty("gpgCmd", gpgCmd.toString());
				
			}
			
			if(encrypt != null){
				
					this.setProperty("encrypt", encrypt.toString());
				
			}
			
			if(compress != null){
				
					this.setProperty("compress", compress.toString());
				
			}
			
			if(inputFile != null){
				
					this.setProperty("inputFile", inputFile.toString());
				
			}
			
			if(outputFile != null){
				
					this.setProperty("outputFile", outputFile.toString());
				
			}
			
			if(encryptRecipient != null){
				
					this.setProperty("encryptRecipient", encryptRecipient.toString());
				
			}
			
			if(decryptPassPhrase != null){
				
					this.setProperty("decryptPassPhrase", decryptPassPhrase.toString());
				
			}
			
			if(decryptPinEntryMode != null){
				
					this.setProperty("decryptPinEntryMode", decryptPinEntryMode.toString());
				
			}
			
		}

public String bashCmd;
public String getBashCmd(){
	return this.bashCmd;
}
public String gpgCmd;
public String getGpgCmd(){
	return this.gpgCmd;
}
public Boolean encrypt;
public Boolean getEncrypt(){
	return this.encrypt;
}
public Boolean compress;
public Boolean getCompress(){
	return this.compress;
}
		public String inputFile;
		public String getInputFile(){
			return this.inputFile;
		}
		
public String outputFile;
public String getOutputFile(){
	return this.outputFile;
}
public String encryptRecipient;
public String getEncryptRecipient(){
	return this.encryptRecipient;
}
public java.lang.String decryptPassPhrase;
public java.lang.String getDecryptPassPhrase(){
	return this.decryptPassPhrase;
}
public String decryptPinEntryMode;
public String getDecryptPinEntryMode(){
	return this.decryptPinEntryMode;
}
	}
	private ContextProperties context = new ContextProperties();
	public ContextProperties getContext() {
		return this.context;
	}
	private final String jobVersion = "0.1";
	private final String jobName = "PGPEncryptDecrypt";
	private final String projectName = "ZIPCAR_MARKETINGDW";
	public Integer errorCode = null;
	private String currentComponent = "";
	
		private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
        private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();
	
		private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
		private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
		private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
		public  final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();
	

private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(), new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
	}


private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

public String getExceptionStackTrace() {
	if ("failure".equals(this.getStatus())) {
		errorMessagePS.flush();
		return baos.toString();
	}
	return null;
}

private Exception exception;

public Exception getException() {
	if ("failure".equals(this.getStatus())) {
		return this.exception;
	}
	return null;
}

private class TalendException extends Exception {

	private static final long serialVersionUID = 1L;

	private java.util.Map<String, Object> globalMap = null;
	private Exception e = null;
	private String currentComponent = null;
	private String virtualComponentName = null;
	
	public void setVirtualComponentName (String virtualComponentName){
		this.virtualComponentName = virtualComponentName;
	}

	private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
		this.currentComponent= errorComponent;
		this.globalMap = globalMap;
		this.e = e;
	}

	public Exception getException() {
		return this.e;
	}

	public String getCurrentComponent() {
		return this.currentComponent;
	}

	
    public String getExceptionCauseMessage(Exception e){
        Throwable cause = e;
        String message = null;
        int i = 10;
        while (null != cause && 0 < i--) {
            message = cause.getMessage();
            if (null == message) {
                cause = cause.getCause();
            } else {
                break;          
            }
        }
        if (null == message) {
            message = e.getClass().getName();
        }   
        return message;
    }

	@Override
	public void printStackTrace() {
		if (!(e instanceof TalendException || e instanceof TDieException)) {
			if(virtualComponentName!=null && currentComponent.indexOf(virtualComponentName+"_")==0){
				globalMap.put(virtualComponentName+"_ERROR_MESSAGE",getExceptionCauseMessage(e));
			}
			globalMap.put(currentComponent+"_ERROR_MESSAGE",getExceptionCauseMessage(e));
			System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
		}
		if (!(e instanceof TDieException)) {
			if(e instanceof TalendException){
				e.printStackTrace();
			} else {
				e.printStackTrace();
				e.printStackTrace(errorMessagePS);
				PGPEncryptDecrypt.this.exception = e;
			}
		}
		if (!(e instanceof TalendException)) {
		try {
			for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
				if (m.getName().compareTo(currentComponent + "_error") == 0) {
					m.invoke(PGPEncryptDecrypt.this, new Object[] { e , currentComponent, globalMap});
					break;
				}
			}

			if(!(e instanceof TDieException)){
			}
		} catch (Exception e) {
			this.e.printStackTrace();
		}
		}
	}
}

			public void tJava_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tJava_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tSystem_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tSystem_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tSystem_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tSystem_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tJava_1_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tSystem_1_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tSystem_2_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
		





public void tJava_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tJava_1_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {

			String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if( resumeEntryMethodName == null || resumeIt || globalResumeTicket){//start the resume
				globalResumeTicket = true;



		


	
	/**
	 * [tJava_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tJava_1", false);
		start_Hash.put("tJava_1", System.currentTimeMillis());
		
	
	currentComponent="tJava_1";

	
		int tos_count_tJava_1 = 0;
		
    	class BytesLimit65535_tJava_1{
    		public void limitLog4jByte() throws Exception{
    			
    		}
    	}
    	
        new BytesLimit65535_tJava_1().limitLog4jByte();


String recipientParam = "";
if(context.encryptRecipient.indexOf("|") != -1) {
	String[] arr = context.encryptRecipient.split("\\|");
	for (String s: arr) {
		recipientParam += " --recipient '" + s.trim() + "' ";
	}
} else {
	recipientParam = " --recipient '" + context.encryptRecipient + "' ";
}
//System.out.println(recipientParam);
globalMap.put("recipients", recipientParam);

//set compression flag
if(!context.compress) {
	globalMap.put("compress", " --compress-algo none ");
} else {
	globalMap.put("compress", "");
}

 



/**
 * [tJava_1 begin ] stop
 */
	
	/**
	 * [tJava_1 main ] start
	 */

	

	
	
	currentComponent="tJava_1";

	

 


	tos_count_tJava_1++;

/**
 * [tJava_1 main ] stop
 */
	
	/**
	 * [tJava_1 end ] start
	 */

	

	
	
	currentComponent="tJava_1";

	

 

ok_Hash.put("tJava_1", true);
end_Hash.put("tJava_1", System.currentTimeMillis());

   			if (context.encrypt) {
   				
					if(execStat){   
   	 					runStat.updateStatOnConnection("If1", 0, "true");
					}
				
    			tSystem_1Process(globalMap);
   			}

			   
   				else{
					if(execStat){   
   	 					runStat.updateStatOnConnection("If1", 0, "false");
					}   	 
   				}
   			if (!context.encrypt) {
   				
					if(execStat){   
   	 					runStat.updateStatOnConnection("If2", 0, "true");
					}
				
    			tSystem_2Process(globalMap);
   			}

			   
   				else{
					if(execStat){   
   	 					runStat.updateStatOnConnection("If2", 0, "false");
					}   	 
   				}



/**
 * [tJava_1 end ] stop
 */
				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tJava_1 finally ] start
	 */

	

	
	
	currentComponent="tJava_1";

	

 



/**
 * [tJava_1 finally ] stop
 */
				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tJava_1_SUBPROCESS_STATE", 1);
	}
	

public void tSystem_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tSystem_1_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {

			String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if( resumeEntryMethodName == null || resumeIt || globalResumeTicket){//start the resume
				globalResumeTicket = true;





	
	/**
	 * [tSystem_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tSystem_1", false);
		start_Hash.put("tSystem_1", System.currentTimeMillis());
		
	
	currentComponent="tSystem_1";

	
		int tos_count_tSystem_1 = 0;
		
    	class BytesLimit65535_tSystem_1{
    		public void limitLog4jByte() throws Exception{
    			
    		}
    	}
    	
        new BytesLimit65535_tSystem_1().limitLog4jByte();

		
		String[] command_tSystem_1 = new String[3];
		
			command_tSystem_1[0] = context.bashCmd;
			
			command_tSystem_1[1] = "-c";
			
			command_tSystem_1[2] = context.gpgCmd + globalMap.get("compress") + " --batch --yes --output " + context.outputFile + globalMap.get("recipients") + " --encrypt " + context.inputFile;
			
Runtime runtime_tSystem_1 = Runtime.getRuntime();

String[] env_tSystem_1= null;
java.util.Map<String,String> envMap_tSystem_1= System.getenv();
java.util.Map<String,String> envMapClone_tSystem_1= new java.util.HashMap();
envMapClone_tSystem_1.putAll(envMap_tSystem_1);

final Process ps_tSystem_1 = runtime_tSystem_1.exec(command_tSystem_1 ,env_tSystem_1);

globalMap.remove("tSystem_1_OUTPUT");
globalMap.remove("tSystem_1_ERROROUTPUT");

Thread normal_tSystem_1 = new Thread() {
	public void run() {
		try {
			java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(ps_tSystem_1.getInputStream()));
			String line = "";
			try {
				while((line = reader.readLine()) != null) {
					
					System.out.println(line);
				}
			} finally {
				reader.close();
			}
		} catch(java.io.IOException ioe) {
			
			ioe.printStackTrace();
		}
	}
};
normal_tSystem_1.start();

Thread error_tSystem_1 = new Thread() {
	public void run() {
		try {
			java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(ps_tSystem_1.getErrorStream()));
			String line = "";
			try {
				while((line = reader.readLine()) != null) {
					
					System.err.println(line);
				}
			} finally {
				reader.close();
			}
		} catch(java.io.IOException ioe) {
			
			ioe.printStackTrace();
		}
	}
};
error_tSystem_1.start();
if(ps_tSystem_1.getOutputStream()!=null){
    ps_tSystem_1.getOutputStream().close();
}
ps_tSystem_1.waitFor();
normal_tSystem_1.join(10000);
error_tSystem_1.join(10000);


 



/**
 * [tSystem_1 begin ] stop
 */
	
	/**
	 * [tSystem_1 main ] start
	 */

	

	
	
	currentComponent="tSystem_1";

	


 


	tos_count_tSystem_1++;

/**
 * [tSystem_1 main ] stop
 */
	
	/**
	 * [tSystem_1 end ] start
	 */

	

	
	
	currentComponent="tSystem_1";

	

globalMap.put("tSystem_1_EXIT_VALUE", ps_tSystem_1.exitValue());

 

ok_Hash.put("tSystem_1", true);
end_Hash.put("tSystem_1", System.currentTimeMillis());




/**
 * [tSystem_1 end ] stop
 */
				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tSystem_1 finally ] start
	 */

	

	
	
	currentComponent="tSystem_1";

	

 



/**
 * [tSystem_1 finally ] stop
 */
				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tSystem_1_SUBPROCESS_STATE", 1);
	}
	

public void tSystem_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tSystem_2_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {

			String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if( resumeEntryMethodName == null || resumeIt || globalResumeTicket){//start the resume
				globalResumeTicket = true;





	
	/**
	 * [tSystem_2 begin ] start
	 */

	

	
		
		ok_Hash.put("tSystem_2", false);
		start_Hash.put("tSystem_2", System.currentTimeMillis());
		
	
	currentComponent="tSystem_2";

	
		int tos_count_tSystem_2 = 0;
		
    	class BytesLimit65535_tSystem_2{
    		public void limitLog4jByte() throws Exception{
    			
    		}
    	}
    	
        new BytesLimit65535_tSystem_2().limitLog4jByte();

		
		String[] command_tSystem_2 = new String[3];
		
			command_tSystem_2[0] = context.bashCmd;
			
			command_tSystem_2[1] = "-c";
			
			command_tSystem_2[2] = context.gpgCmd + " --batch --yes " + context.decryptPinEntryMode + " --passphrase " + context.decryptPassPhrase + " --no-mdc-warning --output " + context.outputFile + " --decrypt " + context.inputFile + " 2>/dev/null";
			
Runtime runtime_tSystem_2 = Runtime.getRuntime();

String[] env_tSystem_2= null;
java.util.Map<String,String> envMap_tSystem_2= System.getenv();
java.util.Map<String,String> envMapClone_tSystem_2= new java.util.HashMap();
envMapClone_tSystem_2.putAll(envMap_tSystem_2);

final Process ps_tSystem_2 = runtime_tSystem_2.exec(command_tSystem_2 ,env_tSystem_2);

globalMap.remove("tSystem_2_OUTPUT");
globalMap.remove("tSystem_2_ERROROUTPUT");

Thread normal_tSystem_2 = new Thread() {
	public void run() {
		try {
			java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(ps_tSystem_2.getInputStream()));
			String line = "";
			try {
				while((line = reader.readLine()) != null) {
					
					System.out.println(line);
				}
			} finally {
				reader.close();
			}
		} catch(java.io.IOException ioe) {
			
			ioe.printStackTrace();
		}
	}
};
normal_tSystem_2.start();

Thread error_tSystem_2 = new Thread() {
	public void run() {
		try {
			java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(ps_tSystem_2.getErrorStream()));
			String line = "";
			try {
				while((line = reader.readLine()) != null) {
					
					System.err.println(line);
				}
			} finally {
				reader.close();
			}
		} catch(java.io.IOException ioe) {
			
			ioe.printStackTrace();
		}
	}
};
error_tSystem_2.start();
if(ps_tSystem_2.getOutputStream()!=null){
    ps_tSystem_2.getOutputStream().close();
}
ps_tSystem_2.waitFor();
normal_tSystem_2.join(10000);
error_tSystem_2.join(10000);


 



/**
 * [tSystem_2 begin ] stop
 */
	
	/**
	 * [tSystem_2 main ] start
	 */

	

	
	
	currentComponent="tSystem_2";

	


 


	tos_count_tSystem_2++;

/**
 * [tSystem_2 main ] stop
 */
	
	/**
	 * [tSystem_2 end ] start
	 */

	

	
	
	currentComponent="tSystem_2";

	

globalMap.put("tSystem_2_EXIT_VALUE", ps_tSystem_2.exitValue());

 

ok_Hash.put("tSystem_2", true);
end_Hash.put("tSystem_2", System.currentTimeMillis());




/**
 * [tSystem_2 end ] stop
 */
				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tSystem_2 finally ] start
	 */

	

	
	
	currentComponent="tSystem_2";

	

 



/**
 * [tSystem_2 finally ] stop
 */
				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tSystem_2_SUBPROCESS_STATE", 1);
	}
	
    public String resuming_logs_dir_path = null;
    public String resuming_checkpoint_path = null;
    public String parent_part_launcher = null;
    private String resumeEntryMethodName = null;
    private boolean globalResumeTicket = false;

    public boolean watch = false;
    // portStats is null, it means don't execute the statistics
    public Integer portStats = null;
    public int portTraces = 4334;
    public String clientHost;
    public String defaultClientHost = "localhost";
    public String contextStr = "external";
    public boolean isDefaultContext = true;
    public String pid = "0";
    public String rootPid = null;
    public String fatherPid = null;
    public String fatherNode = null;
    public long startTime = 0;
    public boolean isChildJob = false;
    public String log4jLevel = "";

    private boolean execStat = true;

    private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
        protected java.util.Map<String, String> initialValue() {
            java.util.Map<String,String> threadRunResultMap = new java.util.HashMap<String, String>();
            threadRunResultMap.put("errorCode", null);
            threadRunResultMap.put("status", "");
            return threadRunResultMap;
        };
    };



    private PropertiesWithType context_param = new PropertiesWithType();
    public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

    public String status= "";

    public static void main(String[] args){
        final PGPEncryptDecrypt PGPEncryptDecryptClass = new PGPEncryptDecrypt();

        int exitCode = PGPEncryptDecryptClass.runJobInTOS(args);

        System.exit(exitCode);
    }


    public String[][] runJob(String[] args) {

        int exitCode = runJobInTOS(args);
        String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

        return bufferValue;
    }

    public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;
    	
        return hastBufferOutput;
    }

    public int runJobInTOS(String[] args) {
	   	// reset status
	   	status = "";

        String lastStr = "";
        for (String arg : args) {
            if (arg.equalsIgnoreCase("--context_param")) {
                lastStr = arg;
            } else if (lastStr.equals("")) {
                evalParam(arg);
            } else {
                evalParam(lastStr + " " + arg);
                lastStr = "";
            }
        }


        if(clientHost == null) {
            clientHost = defaultClientHost;
        }

        if(pid == null || "0".equals(pid)) {
            pid = TalendString.getAsciiRandomString(6);
        }

        if (rootPid==null) {
            rootPid = pid;
        }
        if (fatherPid==null) {
            fatherPid = pid;
        }else{
            isChildJob = true;
        }

        if (portStats != null) {
            // portStats = -1; //for testing
            if (portStats < 0 || portStats > 65535) {
                // issue:10869, the portStats is invalid, so this client socket can't open
                System.err.println("The statistics socket port " + portStats + " is invalid.");
                execStat = false;
            }
        } else {
            execStat = false;
        }

        try {
            //call job/subjob with an existing context, like: --context=production. if without this parameter, there will use the default context instead.
            java.io.InputStream inContext = PGPEncryptDecrypt.class.getClassLoader().getResourceAsStream("zipcar_marketingdw/pgpencryptdecrypt_0_1/contexts/"+contextStr+".properties");
            if(isDefaultContext && inContext ==null) {

            } else {
                if (inContext!=null) {
                    //defaultProps is in order to keep the original context value
                    defaultProps.load(inContext);
                    inContext.close();
                    context = new ContextProperties(defaultProps);
                }else{
                    //print info and job continue to run, for case: context_param is not empty.
                    System.err.println("Could not find the context " + contextStr);
                }
            }

            if(!context_param.isEmpty()) {
                context.putAll(context_param);
				//set types for params from parentJobs
				for (Object key: context_param.keySet()){
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
            }
				    context.setContextType("bashCmd", "id_String");
				
                context.bashCmd=(String) context.getProperty("bashCmd");
				    context.setContextType("gpgCmd", "id_String");
				
                context.gpgCmd=(String) context.getProperty("gpgCmd");
				    context.setContextType("encrypt", "id_Boolean");
				
             try{
                 context.encrypt=routines.system.ParserUtils.parseTo_Boolean (context.getProperty("encrypt"));
             }catch(NumberFormatException e){
                 context.encrypt=null;
              }
				    context.setContextType("compress", "id_Boolean");
				
             try{
                 context.compress=routines.system.ParserUtils.parseTo_Boolean (context.getProperty("compress"));
             }catch(NumberFormatException e){
                 context.compress=null;
              }
				    context.setContextType("inputFile", "id_File");
				
                context.inputFile=(String) context.getProperty("inputFile");
				    context.setContextType("outputFile", "id_String");
				
                context.outputFile=(String) context.getProperty("outputFile");
				    context.setContextType("encryptRecipient", "id_String");
				
                context.encryptRecipient=(String) context.getProperty("encryptRecipient");
				    context.setContextType("decryptPassPhrase", "id_Password");
				
            		String pwd_decryptPassPhrase_value = context.getProperty("decryptPassPhrase");
            		context.decryptPassPhrase = null;
            		if(pwd_decryptPassPhrase_value!=null) {
            			if(context_param.containsKey("decryptPassPhrase")) {//no need to decrypt if it come from program argument or parent job runtime
            				context.decryptPassPhrase = pwd_decryptPassPhrase_value;
            			} else if (!pwd_decryptPassPhrase_value.isEmpty()) {
            				try {
            					context.decryptPassPhrase = routines.system.PasswordEncryptUtil.decryptPassword(pwd_decryptPassPhrase_value);
            					context.put("decryptPassPhrase",context.decryptPassPhrase);
            				} catch (java.lang.RuntimeException e) {
            					//do nothing
            				}
            			}
            		}
				    context.setContextType("decryptPinEntryMode", "id_String");
				
                context.decryptPinEntryMode=(String) context.getProperty("decryptPinEntryMode");
        } catch (java.io.IOException ie) {
            System.err.println("Could not load context "+contextStr);
            ie.printStackTrace();
        }


        // get context value from parent directly
        if (parentContextMap != null && !parentContextMap.isEmpty()) {if (parentContextMap.containsKey("bashCmd")) {
                context.bashCmd = (String) parentContextMap.get("bashCmd");
            }if (parentContextMap.containsKey("gpgCmd")) {
                context.gpgCmd = (String) parentContextMap.get("gpgCmd");
            }if (parentContextMap.containsKey("encrypt")) {
                context.encrypt = (Boolean) parentContextMap.get("encrypt");
            }if (parentContextMap.containsKey("compress")) {
                context.compress = (Boolean) parentContextMap.get("compress");
            }if (parentContextMap.containsKey("inputFile")) {
                context.inputFile = (String) parentContextMap.get("inputFile");
            }if (parentContextMap.containsKey("outputFile")) {
                context.outputFile = (String) parentContextMap.get("outputFile");
            }if (parentContextMap.containsKey("encryptRecipient")) {
                context.encryptRecipient = (String) parentContextMap.get("encryptRecipient");
            }if (parentContextMap.containsKey("decryptPassPhrase")) {
                context.decryptPassPhrase = (java.lang.String) parentContextMap.get("decryptPassPhrase");
            }if (parentContextMap.containsKey("decryptPinEntryMode")) {
                context.decryptPinEntryMode = (String) parentContextMap.get("decryptPinEntryMode");
            }
        }

        //Resume: init the resumeUtil
        resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
        resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
        resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
			parametersToEncrypt.add("decryptPassPhrase");
        //Resume: jobStart
        resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "","","","",resumeUtil.convertToJsonText(context,parametersToEncrypt));

if(execStat) {
    try {
        runStat.openSocket(!isChildJob);
        runStat.setAllPID(rootPid, fatherPid, pid, jobName);
        runStat.startThreadStat(clientHost, portStats);
        runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
    } catch (java.io.IOException ioException) {
        ioException.printStackTrace();
    }
}



	
	    java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
	    globalMap.put("concurrentHashMap", concurrentHashMap);
	

    long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    long endUsedMemory = 0;
    long end = 0;

    startTime = System.currentTimeMillis();




this.globalResumeTicket = true;//to run tPreJob




this.globalResumeTicket = false;//to run others jobs

try {
errorCode = null;tJava_1Process(globalMap);
if(!"failure".equals(status)) { status = "end"; }
}catch (TalendException e_tJava_1) {
globalMap.put("tJava_1_SUBPROCESS_STATE", -1);

e_tJava_1.printStackTrace();

}

this.globalResumeTicket = true;//to run tPostJob




        end = System.currentTimeMillis();

        if (watch) {
            System.out.println((end-startTime)+" milliseconds");
        }

        endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        if (false) {
            System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : PGPEncryptDecrypt");
        }





if (execStat) {
    runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
    runStat.stopThreadStat();
}
    int returnCode = 0;
    if(errorCode == null) {
         returnCode = status != null && status.equals("failure") ? 1 : 0;
    } else {
         returnCode = errorCode.intValue();
    }
    resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "","" + returnCode,"","","");

    return returnCode;

  }

    // only for OSGi env
    public void destroy() {


    }














    private java.util.Map<String, Object> getSharedConnections4REST() {
        java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();







        return connections;
    }

    private void evalParam(String arg) {
        if (arg.startsWith("--resuming_logs_dir_path")) {
            resuming_logs_dir_path = arg.substring(25);
        } else if (arg.startsWith("--resuming_checkpoint_path")) {
            resuming_checkpoint_path = arg.substring(27);
        } else if (arg.startsWith("--parent_part_launcher")) {
            parent_part_launcher = arg.substring(23);
        } else if (arg.startsWith("--watch")) {
            watch = true;
        } else if (arg.startsWith("--stat_port=")) {
            String portStatsStr = arg.substring(12);
            if (portStatsStr != null && !portStatsStr.equals("null")) {
                portStats = Integer.parseInt(portStatsStr);
            }
        } else if (arg.startsWith("--trace_port=")) {
            portTraces = Integer.parseInt(arg.substring(13));
        } else if (arg.startsWith("--client_host=")) {
            clientHost = arg.substring(14);
        } else if (arg.startsWith("--context=")) {
            contextStr = arg.substring(10);
            isDefaultContext = false;
        } else if (arg.startsWith("--father_pid=")) {
            fatherPid = arg.substring(13);
        } else if (arg.startsWith("--root_pid=")) {
            rootPid = arg.substring(11);
        } else if (arg.startsWith("--father_node=")) {
            fatherNode = arg.substring(14);
        } else if (arg.startsWith("--pid=")) {
            pid = arg.substring(6);
        } else if (arg.startsWith("--context_type")) {
            String keyValue = arg.substring(15);
			int index = -1;
            if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
                if (fatherPid==null) {
                    context_param.setContextType(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
                } else { // the subjob won't escape the especial chars
                    context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1) );
                }

            }

		} else if (arg.startsWith("--context_param")) {
            String keyValue = arg.substring(16);
            int index = -1;
            if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
                if (fatherPid==null) {
                    context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
                } else { // the subjob won't escape the especial chars
                    context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1) );
                }
            }
        }else if (arg.startsWith("--log4jLevel=")) {
            log4jLevel = arg.substring(13);
		}

    }
    
    private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

    private final String[][] escapeChars = {
        {"\\\\","\\"},{"\\n","\n"},{"\\'","\'"},{"\\r","\r"},
        {"\\f","\f"},{"\\b","\b"},{"\\t","\t"}
        };
    private String replaceEscapeChars (String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0],currIndex);
				if (index>=0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0], strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
    }

    public Integer getErrorCode() {
        return errorCode;
    }


    public String getStatus() {
        return status;
    }

    ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 *     38356 characters generated by Talend Open Studio for Data Integration 
 *     on the October 24, 2018 9:10:02 AM CDT
 ************************************************************************************************/