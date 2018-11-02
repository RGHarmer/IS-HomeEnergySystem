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
import java.util.Random;
import java.util.Vector;
import java.time.*;
import Serializable.TimeEnergyUsage;
import Serializable.TimeEnergyUse;
import behaviours.ApplianceRequestResponder;
import behaviours.ArchiveRequestResponder;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * Data Agent is the agent that keeps track of appliance data.
 * It collects the data from the csv.
 * It also can tell appliances their value at row X.
 * @author Solar
 *
 */
public class DataAgent extends Agent {
	public Vector<TimeEnergyUsage> energyUsageData;
	static String csvFile;
	static String line = "";
    static String comma = ",";
	private ArchiveRequestResponder archiveReqR;
	
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

	
	protected void register(ServiceDescription sd) {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		}
		catch(FIPAException fe) {
			
		}
	}
	
	public void setup() {
		Object[] args = getArguments();
		ServiceDescription sd = new ServiceDescription();
		sd.setType("DataAgent");
		sd.setName("DataAgent");
		register(sd);
		
		energyUsageData = new Vector<TimeEnergyUsage>();
		csvFile = args[0].toString();
		
		readCSV();
	
		
		MessageTemplate template = MessageTemplate.and(MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST),
		  		MessageTemplate.MatchPerformative(ACLMessage.REQUEST) );
		
		archiveReqR = new ArchiveRequestResponder(this, template);
		
		addBehaviour(archiveReqR);
	}
}
