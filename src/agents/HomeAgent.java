package agents; 
 
import java.time.Clock;
import java.util.Date;
import java.util.Vector;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
import jade.proto.ContractNetInitiator;
import util.AdjustableClock;
 
 
public class HomeAgent extends Agent {
	
	private Clock clock;
	private int cycleInterval = 10000; //in milliseconds
 
	protected void setup() {
		//clock = new AdjustableClock(1); // change 1 to whatever multiplier
		
		// APPLIANCE MESSAGING
		ACLMessage msgAppliance = new ACLMessage(ACLMessage.REQUEST);
		//for all Appliances
		//msgAppliance.addReceiver(...);
		msgAppliance.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
		msgAppliance.setReplyByDate(new Date(System.currentTimeMillis() + cycleInterval));
		msgAppliance.setContent("[APPLIANCE MESSAGE CONTENT]");
		
		addBehaviour(new AchieveREInitiator(this, msgAppliance) {
			protected void handleInform(ACLMessage inform) {
				
			}
			
			protected void handleRefuse(ACLMessage refuse) {
				
			}
			
			protected void handleFailure(ACLMessage failure) {
				
			}
			
			protected void handleAllResultNotifications(Vector notifications) {
				
			}
		});
		
		// RETAILER MESSAGING
		ACLMessage msgRetailer = new ACLMessage(ACLMessage.CFP);
		//for all Retailers
			//msgRetailer.addReceiver(...);
		msgRetailer.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
		msgRetailer.setReplyByDate(new Date(System.currentTimeMillis() + cycleInterval));
		msgRetailer.setContent("[RETAILER MESSAGE CONTENT]");
		
		addBehaviour(new ContractNetInitiator(this, msgRetailer) {
			protected void handlePropose(ACLMessage propose, Vector v) {
				
			}
			
			protected void handleRefuse(ACLMessage refuse) {
				
			}
			
			protected void handleFailure(ACLMessage failure) {
				
			}
			
			protected void handleAllResponses(Vector responses, Vector acceptances) {
				
			}
			
			protected void handleInform(ACLMessage inform) {
				
			}
		});
	} 
} 
