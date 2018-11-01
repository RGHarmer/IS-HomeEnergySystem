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
import java.util.Random;
import java.util.Vector;

import Serializable.TimeEnergyUse;
import behaviours.ApplianceRequestResponder;
 
@SuppressWarnings("serial")
public class ApplianceAgent extends Agent {

	
	//Data Archive (Goes into the future for testing purposes)
	public Vector<TimeEnergyUse> archive;
	//A pointer to select current row.
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
		
		for(int l = 0; l<60; l++) {
			Random rand = new Random( );
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
	
	public void Reset() {
		currentEstimate = 0;
		currentUsage = 0;
	}
	
	public ApplianceHomeMsg ConstructMsgObject(Instant timeStamp) {
		return new ApplianceHomeMsg(timeStamp, currentUsage, currentEstimate);
	}
	
	public void LoadDataFromAgent() {
		
		//Call 
		//archive = DataAgent.getApplianceArchive(ID, 0, currentUsageRow)
	}
	
	
	/**
	 * REFRENCES:
	 * https://www.geeksforgeeks.org/linear-regression-python-implementation/
	 * https://introcs.cs.princeton.edu/java/97data/LinearRegression.java.html
	 * 
	 * From these references I was able to determine both how Linear Regression 
	 * worked (For the most part) and how to implement the algorithm. 
	 */
	public void LinearRegressionSeason() {
		
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
					Float countMean;
					Float countTotal = 0f;
					Float datasetVariance = 0f;
					Float datasetCovariance = 0f;
					
					for(int r = i; r<(i+30);r++) {
						datasetX.add(archive.get(r).value);
						datasetY.add(dataSetCount);
						datasetTotal += archive.get(r).value;
						countTotal += dataSetCount;
						dataSetCount++;
						
					}
					
					dataSetCount--;
					
					//calculate mean
					datasetMean = (datasetTotal/dataSetCount);
					countMean = (countTotal/dataSetCount);
					
					
					//calculate variance and covariance
					for(int j = 0; j<datasetX.size(); j++) {
						datasetVariance += ((datasetX.get(j) - datasetMean)*(datasetX.get(j) - datasetMean));
						datasetCovariance += (j - countMean)*(datasetX.get(j) - datasetMean);
					}
					
					Float beta1 = datasetCovariance/datasetVariance;
					Float beta0 = countMean - beta1*datasetMean;
					
					Float rss = 0f;
					Float ssr = 0f;
					
					for (int k = 0; k<dataSetCount; k++) {
						Float fit = beta1*datasetX.get(k)+beta0;
						rss += (fit-datasetY.get(k))*(fit-datasetY.get(k));
						ssr += (fit-countMean)*(fit-countMean);
					}
					
					System.out.println("REGRESSION:");
					System.out.println(rss);
					System.out.println(ssr);
					
					currentEstimate = rss;
				}
				
				
			}
			
		}
		
	}
	
} 