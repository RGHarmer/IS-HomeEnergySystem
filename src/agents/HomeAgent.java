package agents; 

import java.util.Map;
import java.util.Vector;

import behaviours.HomeSubscription;
import behaviours.MessageTicker;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;

@SuppressWarnings("serial")
public class HomeAgent extends Agent {

	private int cycleInterval = 10000; //in milliseconds
	private MessageTicker ticker;
	public Vector<AID> appliances;
	public Map<AID,Float> fallbackUsage;
	public Map<AID,Float> fallbackEstimate;
	public Vector<AID> retailers;
	public float energyEstimate;
	public float energyUsage;

	public float minBuyRate;
	public float aggression;
	public float maxBuyRate;
	public int iterations = 10;
	
	public AID retailer;
	public float agreedEnergy;
	public float agreedRate;
	public float agreedPenalty;
	
	protected void setup() {
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Home");
		sd.setName("Home");
		register(sd);
		appliances = new Vector<AID>();
		retailers = new Vector<AID>();
		Subscribe("Appliance");
		Subscribe("Retailer");
		
		ticker = new MessageTicker(this, cycleInterval);
		addBehaviour(ticker);
	}
	
	protected void register(ServiceDescription sd) {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		}
		catch (FIPAException fe) {
			
		}
	}
	
	protected void Subscribe(String type) {
		DFAgentDescription template = new DFAgentDescription();
		ServiceDescription templateSD = new ServiceDescription();
		templateSD.setType(type);
		template.addServices(templateSD);
		SearchConstraints sc = new SearchConstraints();
		sc.setMaxResults((long)Double.POSITIVE_INFINITY);
		
		HomeSubscription subscriber = new HomeSubscription(this, DFService.createSubscriptionMessage(this,  getDefaultDF(),  template,  sc), type);
		addBehaviour(subscriber);
	}
}
