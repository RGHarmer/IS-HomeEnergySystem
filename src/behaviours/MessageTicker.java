package behaviours;

import java.util.Date;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

public class MessageTicker extends TickerBehaviour {

	private ApplianceRequestInitiator ari;
	private RetailContractInitiator rci;
	private ACLMessage msgAppliance;
	private ACLMessage msgRetail;
	
	public MessageTicker(Agent a, long period) {
		super(a, period);
		
		// APPLIANCE MESSAGING
		msgAppliance = new ACLMessage(ACLMessage.REQUEST);
		//for all Appliances
			//msgAppliance.addReceiver(...);
		msgAppliance.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
		msgAppliance.setReplyByDate(new Date(System.currentTimeMillis() + period));
		msgAppliance.setContent("[APPLIANCE MESSAGE CONTENT]");
		
		// RETAILER MESSAGING
		msgRetail = new ACLMessage(ACLMessage.CFP);
		//for all Retailers
			//msgRetail.addReceiver(...);
		msgRetail.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
		msgRetail.setReplyByDate(new Date(System.currentTimeMillis() + period));
		msgRetail.setContent("[RETAILER MESSAGE CONTENT]");
		
		ari = new ApplianceRequestInitiator(myAgent, msgAppliance);
		rci = new RetailContractInitiator(myAgent, msgRetail);
	}
	
	protected void onTick() {
		myAgent.addBehaviour(ari);
		myAgent.addBehaviour(rci);
	}

}
