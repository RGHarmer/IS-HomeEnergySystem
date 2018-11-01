package behaviours;

import java.time.Instant;
import java.util.Map;
import java.util.Vector;

import Serializable.EnergyUsageMessageContent;
import agents.*;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.proto.AchieveREInitiator;
import util.ApplianceHomeMsg;

public class ApplianceRequestInitiator extends AchieveREInitiator {

	public ApplianceRequestInitiator(Agent a, ACLMessage msg) {
		super(a, msg);
		// TODO Auto-generated constructor stub
	}
	
	protected void handleInform(ACLMessage inform) {
		
	}
	
	protected void handleRefuse(ACLMessage refuse) {
		
	}
	
	protected void handleFailure(ACLMessage failure) {
		
	}
	
	protected void handleAllResultNotifications(Vector notifications) {
		Vector<AID> temp = ((HomeAgent)myAgent).appliances;
		for(int i = 0; i<notifications.size(); i++) {
			ACLMessage message = (ACLMessage)notifications.get(i);
			try {
				ApplianceHomeMsg content = (ApplianceHomeMsg)message.getContentObject();
				((HomeAgent)myAgent).energyUsage += content.energyUsed;
				((HomeAgent)myAgent).fallbackUsage.put(message.getSender(), content.energyUsed);
				((HomeAgent)myAgent).energyEstimate += content.energyEstimate;
				((HomeAgent)myAgent).fallbackUsage.put(message.getSender(), content.energyUsed);
				temp.remove(message.getSender());
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}			
		}
		for(int i = 0; i < temp.size(); i++) {
			if (((HomeAgent)myAgent).fallbackUsage.get(temp.get(i)) != null) {
				((HomeAgent)myAgent).energyUsage += ((HomeAgent)myAgent).fallbackUsage.get(temp.get(i));
			}
			if (((HomeAgent)myAgent).fallbackEstimate.get(temp.get(i)) != null) {
				((HomeAgent)myAgent).energyEstimate += ((HomeAgent)myAgent).fallbackEstimate.get(temp.get(i));
			}
		}
		
	}

}
