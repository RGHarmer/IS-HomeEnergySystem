package behaviours;

import java.io.IOException;
import java.util.Date;

import agents.ApplianceAgent;
import agents.HomeAgent;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import util.HomeApplianceMsg;

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
		try {
			msgAppliance.setContentObject(((HomeAgent) myAgent).ContructApplianceMsgObject());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
