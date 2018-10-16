package agents; 

import java.util.List;

import behaviours.HomeSubscription;
import behaviours.MessageTicker;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import util.AdjustableClock;
import util.HomeApplianceMsg;
import util.HomeRetailerMsg;

@SuppressWarnings("serial")
public class HomeAgent extends Agent {

	private AdjustableClock clock;
	private int cycleInterval = 10000; //in milliseconds
	private MessageTicker ticker;
	public List<AID> appliances;
	public List<AID> retailers;
	
	public float energyEstimate;
	
	protected void setup() {
		Subscribe("Appliance");
		Subscribe("Retailer");
		
		clock = new AdjustableClock(1);
		ticker = new MessageTicker(this, cycleInterval);
		addBehaviour(ticker);
	}
	
	public void Subscribe(String type) {
		DFAgentDescription template = new DFAgentDescription();
		ServiceDescription templateSD = new ServiceDescription();
		templateSD.setType(type);
		template.addServices(templateSD);
		SearchConstraints sc = new SearchConstraints();
		sc.setMaxResults((long)Double.POSITIVE_INFINITY);
		
		HomeSubscription subscriber = new HomeSubscription(this, DFService.createSubscriptionMessage(this,  getDefaultDF(),  template,  sc), type);
		addBehaviour(subscriber);
	}

	public HomeApplianceMsg ContructApplianceMsgObject() {
		return new HomeApplianceMsg(clock.instant());
	}
	public HomeRetailerMsg ContructRetailMsgObject() {
		return new HomeRetailerMsg(energyEstimate);
	}
}
