package routines;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
 * user specification: the function's comment should contain keys as follows: 1. write about the function's comment.but
 * it must be before the "{talendTypes}" key.
 * 
 * 2. {talendTypes} 's value must be talend Type, it is required . its value should be one of: String, char | Character,
 * long | Long, int | Integer, boolean | Boolean, byte | Byte, Date, double | Double, float | Float, Object, short |
 * Short
 * 
 * 3. {Category} define a category for the Function. it is required. its value is user-defined .
 * 
 * 4. {param} 's format is: {param} <type>[(<default value or closed list values>)] <name>[ : <comment>]
 * 
 * <type> 's value should be one of: string, int, list, double, object, boolean, long, char, date. <name>'s value is the
 * Function's parameter name. the {param} is optional. so if you the Function without the parameters. the {param} don't
 * added. you can have many parameters for the Function.
 * 
 * 5. {example} gives a example for the Function. it is optional.
 */
public class DataUtils {

    /**
     * coalesce: Returns the first non-null value
     * 
     * 
     * {talendTypes} Object
     * 
     * {Category} User Defined
     * 
     * {param} coalesce(null, "world") input: The items to test
     * 
     * {example} coalesce(null, "world") # world
     */
    @SafeVarargs
    public static <T> T coalesce(T ... items) {
        if(items == null) {
            return null;
        }
        
        for(T item: items) {
            if(item != null) {
                return item;
            }
        }
        
        return null;
    }
    
    /**
     * get the next person ID, make sure same member ID to get the same person ID
     * 
     * {currentMemberId} keep track of the current member ID
     * 
     * {newMemberId} the new member ID we are trying to get the person ID for
     * 
     * {seqPersonIdMap} the map object to get the sequence IDs
     * 
     */
    public static Long getNextPersonId(Long currentMemberId, Long newMemberId, java.util.concurrent.atomic.AtomicLong seqPersonIdMap) {
    	if(currentMemberId == null || newMemberId.longValue() != currentMemberId.longValue()) {
    		return seqPersonIdMap.incrementAndGet();
    	} else {
    		return seqPersonIdMap.get();
    	}
    }

    /**
     * null safe object comparison
     * @param object1
     * @param object2
     * @return
     */
    public static boolean isEqual(final Object object1, final Object object2) {
    	if (object1 == object2) {
            return true;
        }
        if (object1 == null || object2 == null) {
            return false;
        }
        return object1.equals(object2);
    }
    
    public static boolean isEqualNumbers(Number a, Number b, double delta)
    {
       if (a == null) {
          return b == null;
       }
       if (b == null) {
          return false;
       }
       return Math.abs(a.doubleValue() - b.doubleValue()) < delta;
    }
    
    /**
     * Try to determine the possible db data type based on input string
     * @param input
     * @return
     */
    public static String guessDBDataType(String input) {
		String dbDataType = "VARCHAR";
		try {
			Long.parseLong(input);
			dbDataType = "BIGINT";
		} catch (Exception ex) {
			try {
				Double.parseDouble(input);
				dbDataType = "DECIMAL";
			} catch (Exception ex2) {
				try {
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(input);
					dbDataType = "TIMESTAMP";
				} catch (Exception ex3) {
					try {
						new SimpleDateFormat("yyyy-MM-dd").parse(input);
						dbDataType = "DATE";
					} catch (Exception ex4) {
					    // default
					}
				}
			}
		}
		
    	return dbDataType;	
    }
    
    /**
     * Get the database table column definition based on inputs
     */
    public static String getDBTableColDef(TreeMap<String, String> fieldMap, Map<String, String> fieldType, Map<String, Integer> fieldSize, Map<String, Map<String, Integer>> fieldTypeCount) {
    	final Map<String, String> columnEncode = new HashMap<String, String>() {{
    	    put("BIGINT","MOSTLY32");
    	    put("DECIMAL","MOSTLY32");
    	    put("VARCHAR","TEXT255");
    	    put("DATE","LZO");
    	    put("TIMESTAMP","LZO");
    	}};
    	
    	for(String key: fieldTypeCount.keySet()) {
    		Map<String, Integer> typeCountMap = (HashMap<String, Integer>)fieldTypeCount.get(key);
    		/*TreeMap<Integer, String> sortedTypeCountMap = new TreeMap<Integer, String>();
    		boolean hasDecimal = false;
    		for(String type: typeCountMap.keySet()) {
    			//System.out.println(key + "***" + type + "***" + typeCountMap.get(type));
    			sortedTypeCountMap.put(typeCountMap.get(type), type);
    			if("DECIMAL".equals(type)) {
    				hasDecimal = true;
    			}
    		}
    		fieldType.put(key, sortedTypeCountMap.lastEntry().getValue());
    		if(hasDecimal && "BIGINT".equals(fieldType.get(key))) {
    			fieldType.put(key, "DECIMAL");
    		}*/
    		if(typeCountMap.containsKey("VARCHAR")) {
    			fieldType.put(key, "VARCHAR");
    		} else if(typeCountMap.size() == 2 && typeCountMap.containsKey("BIGINT") && typeCountMap.containsKey("DECIMAL")) {
    			fieldType.put(key, "DECIMAL");
    		} else if(typeCountMap.size() == 2 && typeCountMap.containsKey("DATE") && typeCountMap.containsKey("TIMESTAMP")) {
    			fieldType.put(key, "TIMESTAMP");
    		} else if(typeCountMap.size() == 1) {
    			fieldType.put(key, (String)typeCountMap.keySet().toArray()[0]);
    		} else {
    			fieldType.put(key, "VARCHAR");
    		}
    	}

    	StringBuffer sb = new StringBuffer();
    	for(Map.Entry<String, String> entry: fieldMap.entrySet()) {
    		String fieldName = entry.getValue();
    		if(fieldType.get(fieldName) == null) {
    			fieldType.put(fieldName, "VARCHAR");
    			if(fieldName.toLowerCase().endsWith("_id")) {
    				fieldType.put(fieldName, "BIGINT");
    			}
    		}
    		if(fieldSize.get(fieldName) == null) {
    			if("VARCHAR".equals(fieldType.get(fieldName))) {
    				fieldSize.put(fieldName, 255);
    			}
    		}
    		//System.out.println(fieldName + ":" + fieldType.get(fieldName) + ":" + fieldSize.get(fieldName));
    		String sizeDef = "";
    		String encoding = "";
    		if("VARCHAR".equals(fieldType.get(fieldName))) {
    			if(fieldSize.get(fieldName) <= 255) {
    				sizeDef = " (255) ";
    				encoding = " TEXT255 ";
    			} else {
    				sizeDef = " (" + ((fieldSize.get(fieldName) > 500) ? fieldSize.get(fieldName) : 500) + ") ";
    				encoding = " TEXT32K ";
    			}
    		} else if("DECIMAL".equals(fieldType.get(fieldName))) {
    			sizeDef = " (38, 5) ";
    			encoding = " MOSTLY32 ";
    		} else {
    			sizeDef = "";
    			encoding = columnEncode.get(fieldType.get(fieldName));
    		}
    		sb.append(fieldName + "\t" + 
    			fieldType.get(fieldName)  + sizeDef + "\t" + 
    			" ENCODE " + encoding);
    		if(!fieldName.equals(fieldMap.lastEntry().getValue())) {
    			sb.append(",\n");
    		}
    	}    	
    	return sb.toString();
    }
}
