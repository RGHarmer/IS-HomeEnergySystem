package agents;

import java.io.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.time.*;
import Serializable.TimeEnergyUsage;
import Serializable.TimeEnergyUse;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;

/**
 * Data Agent is the agent that keeps track of appliance data.
 * It collects the data from the csv.
 * It also can tell appliances their value at row X.
 * @author Solar
 *
 */
public class DataAgent extends Agent {
	Vector<TimeEnergyUsage> energyUsageData;
	static String csvFile;
	static String line = "";
    static String comma = ",";
    
    public Float getApplianceValue(int id, int row) {
    	return energyUsageData.get(row).values.get(id);
    }
    
    public Vector<TimeEnergyUse> getApplianceArchive(int id, int from, int to){
    	Vector<TimeEnergyUse> toReturn = new Vector<TimeEnergyUse>();
    	
    	if(to > energyUsageData.size())
    		to = energyUsageData.size();
    	
    	for(int i = from; i<to;i++) {
    		/**
    		 * Timestamp String to Instant
    		 * 
    		 * Reference: 
    		 * https://stackoverflow.com/questions/35610597/parse-string-timestamp-to-instant-throws-unsupported-field-instantseconds/35611452
    		 */
    		
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    		String ts = energyUsageData.get(i).time;
    		TemporalAccessor temporalAccessor = formatter.parse(ts);
    	    LocalDateTime localDateTime = LocalDateTime.from(temporalAccessor);
    	    ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
    	    Instant tsInstant = Instant.from(zonedDateTime);
    	    
    	    TimeEnergyUse teuse = new TimeEnergyUse(energyUsageData.get(i).absoluteInterval, tsInstant, getApplianceValue(id, i));
    		toReturn.add(teuse);
    	}
    	
    	return toReturn;
    }
    
    
    /**
     * Will read data from the 'csvFile' location into Vector<TimeEnergyUsage> energyUsage
     * @return true if file read correctly
     */
	public boolean readCSV() 
	{ 
		//Create a new Buffered Reader
		 try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			 	//int counter
			 	int count = 0;
			 
			 
			 	//While reading && count is less than the number of rows to count
	            while ((line = br.readLine()) != null) {
	            	if(count > 0) {
	            		
	            		Vector<Float> applianceVals = new Vector<Float>();
		                //Separate values by comma
		                String[] row = line.split(comma);
	                	
		                //1 is abs value
		                int absVal = Integer.parseInt(row[0]);
		                String ts = row[1];
		                
		                //Skip first two rwos
		                for(int i = 2; i< row.length; i++) {
		                	
		                	applianceVals.add(Float.valueOf(row[i]));
		                }
		                
	
		                TimeEnergyUsage teu = new TimeEnergyUsage(absVal, ts, applianceVals);
		                energyUsageData.add(teu);
	            	}
	            	count++;
	                
	            }
	            return true;

	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	} 

	
	public void setup() {
		
		energyUsageData = new Vector<TimeEnergyUsage>();
		csvFile = "C:\\Users\\Solar\\Documents\\IS-HomeEnergySystem\\bin\\CSV_DS.csv";
		
				readCSV();
				System.out.println(getApplianceValue(0,0));
	
	}
}
