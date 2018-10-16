package agents;

import java.io.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Vector;

import Serializable.TimeEnergyUsage;
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
    
    public int getApplianceValue(int row, int id) {
    	return energyUsageData.get(row).values.get(id);
    }
    
    /**
     * Will read data from the 'csvFile' location into Vector<TimeEnergyUsage> energyUsage
     * @return true if file read correctly
     */
	public boolean readCSV() 
	{ 
		//Create a new Buffered Reader
		 try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			 	//Count keeps track of the line count
			 	int count = 0;
			 	//While reading && count is less than the number of rows to count
	            while ((line = br.readLine()) != null) {
	            	
	                
	                
	                //Skip the first line (The headers)
	                if(count > 0) {
	                	

		                //Separate values by comma
		                String[] row = line.split(comma);
	                	
		                
		                String ts = row[0];
		                Vector<Integer> applianceVals = new Vector<Integer>();
		                for(int i = 1; i< row.length; i++) {
		                	applianceVals.add(Integer.valueOf(row[i]));
		                }
		                
		                TimeEnergyUsage teu = new TimeEnergyUsage(ts, applianceVals);
		                
		                //System.out.println("Adding a new TimeUsage. Timestamp: " + ts + " and " + applianceOne);
		                
		                energyUsageData.add(teu);
		                //System.out.println("TEST");
	                }
	                
	                
	                //Increment count
	                count++;
	                
	            }
	            
	            System.out.println("Number of records: "+count);
	            System.out.println("value at appliance two, timestamp 3:"+getApplianceValue(3,2));
	            return true;

	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	} 

	
	public void setup() {
		
		energyUsageData = new Vector<TimeEnergyUsage>();
		csvFile = "C:\\Users\\Solar\\Documents\\IS-HomeEnergySystem\\bin\\Electricity_P.csv";

		readCSV();
	}
}
