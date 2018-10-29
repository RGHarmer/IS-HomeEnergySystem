package agents;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import util.ApplianceHomeMsg;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import Serializable.TimeEnergyUse;
import behaviours.ApplianceRequestResponder;
 
@SuppressWarnings("serial")
public class ApplianceAgent extends Agent {

	//private Map<Instant,Float> archive;
	
	private Vector<TimeEnergyUse> archive;
	private int currentUsageRow;
	private float currentEstimate;
	private float currentUsage;
	
	
	private ApplianceRequestResponder arr;
	
	protected void setup() {
		Object[] args = getArguments();
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Appliance");
		sd.setName(args[0].toString());
		register(sd);
		
		MessageTemplate template = MessageTemplate.and(MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST),
		  		MessageTemplate.MatchPerformative(ACLMessage.REQUEST) );
		
		arr = new ApplianceRequestResponder(this, template);
		
		addBehaviour(arr);
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
	
	public void Reset() {
		
	}
	
	public ApplianceHomeMsg ContructMsgObject(Instant timeStamp) {
		return new ApplianceHomeMsg(getName(), timeStamp, currentUsage, currentEstimate);
	}
	
	public void LoadDataFromAgent() {
		
		//Call 
		//archive = DataAgent.getApplianceArchive(ID, 0, currentUsageRow)
	}
	
	public boolean LinearRegressionSeason() {
		
		int timeAtPreviousSeasonRow;
		boolean appropriateTimeFound = false;
		
		//Find a value one year ago
		for(int i = archive.size(); i>0;i--) {
			
			if(archive.get(i).time.isBefore(archive.get(currentUsageRow).time.minus(1,ChronoUnit.YEARS)) ) {
				//value from one year ago has been found
					//
					if(i+30 < archive.size()) {
						timeAtPreviousSeasonRow = i;
						appropriateTimeFound = true;
					}
				
				//Do Linear Regression for next 30 values
				
				if(appropriateTimeFound) {
					Vector<Float> datasetX = new Vector<Float>();
					Vector<Integer> datasetY = new Vector<Integer>();
					int dataSetCount = 0;
					
					Float datasetTotal = 0f;
					Float datasetMean;
					Float datasetVariance = 0f;
					
					for(int r = i; r<(i+30);r++) {
						datasetX.add(archive.get(r).value);
						datasetY.add(dataSetCount);
						datasetTotal += archive.get(r).value;
						dataSetCount++;
					}
					
					dataSetCount--;
					
					//calculate mean
					datasetMean = (datasetTotal/dataSetCount);
					
					//calculate variance
					for(int j = 0; j<datasetX.size(); j++) {
						datasetVariance += ((datasetX.get(j) - datasetMean)*(datasetX.get(j) - datasetMean));
					}
				}
				
				
			}
			
		}
		return false;
		
	}
	
} 