package behaviours;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import agents.HomeAgent;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import util.HomeRetailerMsg;

@SuppressWarnings("serial")
public class MessageTicker extends TickerBehaviour {

	private ApplianceRequestInitiator ari;
	private RetailContractInitiator rci;
	private ACLMessage msgAppliance;
	private ACLMessage msgRetail;
	private long period;
	
	public MessageTicker(Agent a, long per) {
		super(a, per);
		period = per;
		UpdateBehaviours();
	}
	
	protected void onTick() {
		myAgent.addBehaviour(ari);
		myAgent.addBehaviour(rci);
		UpdateBehaviours();
	}
	
	protected void UpdateBehaviours() {
		// APPLIANCE MESSAGING
		msgAppliance = new ACLMessage(ACLMessage.REQUEST);
		for (Iterator<AID> i = ((HomeAgent)myAgent).appliances.iterator(); i.hasNext();) {
			AID appliance = i.next();
			msgAppliance.addReceiver(appliance);
		}
		msgAppliance.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
		msgAppliance.setReplyByDate(new Date(System.currentTimeMillis() + period));
		
		// RETAILER MESSAGING
		msgRetail = new ACLMessage(ACLMessage.CFP);
		for (Iterator<AID> i = ((HomeAgent)myAgent).retailers.iterator(); i.hasNext();) {
			AID retailer = i.next();
			msgAppliance.addReceiver(retailer);
		}
		msgRetail.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
		msgRetail.setReplyByDate(new Date(System.currentTimeMillis() + period));
		try {
			msgRetail.setContentObject(new HomeRetailerMsg(0,((HomeAgent) myAgent).iterations));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ari = new ApplianceRequestInitiator(myAgent, msgAppliance);
		rci = new RetailContractInitiator(myAgent, msgRetail);
	}
}
