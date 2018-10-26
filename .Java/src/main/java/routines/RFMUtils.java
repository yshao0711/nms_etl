package routines;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

/*
 * Routines to compute RFM values
 */
public class RFMUtils {
	static final Logger logger = Logger.getLogger(RFMUtils.class);
	
	static private double recency_weight = 4.0;
	static private double frequency_weight = 0.5;
	static private double monetary_weight = 5.5;
	
	/**
	 * Initialize the weight factors for the calculation of the RFM weighted value
	 * 
	 * @param rw The recency weight factor
	 * @param fw The frequency weight factor
	 * @param mw The monetary weight factor
	 * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} double(rweight) recencyWeight: The reservation's recency value
     * {param} double(fweight) frequencyWeight: The reservation's frequency value
     * {param} double(mweiht) monetaryWeight: The reservation's monetary value
     * 
     * {example} initRFMWeights(1.0, 1.0, 1.0)
	 */
	public static void initRFMWeights(double recencyWeight, double frequencyWeight, double monetaryWeight) {
		recency_weight = recencyWeight;
		frequency_weight = frequencyWeight;
		monetary_weight = monetaryWeight;
	}
	
	/**
	 * Prints the RFM weight factors
	 * 
	 * @return A string with RFM factor weight values
	 * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {example} printRFMWeights(): "Rw = 4.0, Fw = 0.5, Mw = 5.5"
	 */
	public static String printRFMWeights() {
		return "Rw = " + recency_weight + ", Fw = " + frequency_weight + ", Mw = " + monetary_weight;
	}

    /**
     * recency: Computes the recency value for the reservation's number of days
     * 
     * @param numDays The number of recency days for reservation
     * 
     * {talendTypes} Double
     * 
     * {Category} User Defined
     * 
     * {param} double(days) numDays: The number of days to compute recency.
     * 
     * {example} recency(190): recency = 1
     */
    public static double recency(double numDays) {
    	
    	double recency;
    	
    	if (numDays <= 14) {
    		recency = 5;
    	} else if (numDays > 182 ) {
    		recency = 1; 
    	} else {
    		recency = RFMUtils.round((182 - numDays) * (1.0 / (182 - 14) * 4.0) + 1.0, 1).doubleValue();
    	}
    	
    	return recency;
    }
    
    
    /**
     * frequency: Computes the frequency value for the reservation's median time
     * 
     * @param numDays The number of frequency days for reservation
     * 
     * {talendTypes} Double
     * 
     * {Category} User Defined
     * 
     * {param} double(hours) medianTime: The number of hours to compute recency.
     * 
     * {example} frequency(19): frequency = 1
     */
    public static double frequency(double medianTime) {
    
    	double frequency;
    	
    	if (medianTime <= 17) {
    		frequency = 5;
    	} else if (medianTime >= 56 ) {
    		frequency = 1; 
    	} else {
    		frequency = RFMUtils.round((56 - medianTime) * (1.0 / (56 - 17) * 4.0) + 1.0, 1).doubleValue();
    	}
    	
    	return frequency;
    }
    
    /**
     * monetary: Computes the monetary value for the reservation's hours
     * 
     * @param reservationHours The number of monetary hourss for reservation
     * 
     * {talendTypes} Double
     * 
     * {Category} User Defined
     * 
     * {param} double(hours) reservationHours: The number of hours to compute monetary value.
     * 
     * {example} monetary(0): monetary = 1
     */
    public static double monetary(double reservationHours) {
    	
    	double monetary;
    	
    	if (reservationHours >= 75) {
    		monetary = 5;
    	} else if (reservationHours == 0 ) {
    		monetary = 1; 
    	} else {
    		monetary = RFMUtils.round(reservationHours * (1.0 / 75 * 4) + 1.0, 1).doubleValue();
    	}
    	
    	return monetary;    	
    }
    
    /**
     * weightedRFM: Computes the weighted RFM value
     * 
     * @param recency The reservation's recency value
     * @param frequency The reservation's frequency value
     * @param monetary The reservation's monetary value
     * 
     * {talendTypes} Int
     * 
     * {Category} User Defined
     * 
     * {param} double(recent) recency: The reservation's recency value
     * {param} double(frequent) frequency: The reservation's frequency value
     * {param} double(money) monetary: The reservation's monetary value
     * 
     * {example} weightedRFM(1.0, 1.0, 1.0): weightedRFM = 10
     */
    public static int weightedRFM(double recency, double frequency, double monetary) {
    	double x = recency_weight * recency + frequency_weight * frequency + monetary_weight * monetary;
    	
    	return RFMUtils.round(x, 0).intValue();
    }
    
    /**
     * calculate member profile based on its weighted RFM
     * 
     * @param weightedRFM the weighted RFM value
     * 
     * @return String profile
     */
    public static String profile(int weightedRFM) {
    	if(weightedRFM == 50) {
    		return "Power User";
    	} else if(weightedRFM >= 40) {
    		return "Strong";
    	} else if(weightedRFM >= 30) {
    		return "Standard+";
    	} else if(weightedRFM >= 20) {
    		return "Standard-";
    	} else if(weightedRFM > 10) {
    		return "Vulnerable";
    	} else {
    		return "Low Activity";
    	}
    }
    
    /**
     * round:  Round value to given scale using half-even rounding mode. 
     *         Note that for rounding off a 5, the IEC 60559 standard is 
     *         expected to be used, ‘go to the even digit’.
     * 
     * @param val	The value to round
     * @param scale The scale value
     * 
     * @return  BigDecimal
     */
    private static BigDecimal round(double val, int scale) {
    	return BigDecimal.valueOf(val).setScale(scale, BigDecimal.ROUND_HALF_EVEN);
    }
}
