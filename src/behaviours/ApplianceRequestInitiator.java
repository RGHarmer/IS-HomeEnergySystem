package behaviours;

import java.time.Instant;
import java.util.Vector;

import Serializable.EnergyUsageMessageContent;
import agents.*;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.proto.AchieveREInitiator;

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
		//For each agent
		for(int i = 0; i<notifications.size();i++) {
			
			Object Content;
			try {
				Content = (((ACLMessage)notifications.get(i)).getContentObject());
				
				//AID
				AID agentID = ((ACLMessage)notifications.get(i)).getSender();
				
				//Energy Usage
				float eu = ((EnergyUsageMessageContent)Content).EnergyUsage;
				
				//timestamp
				Instant ts = ((EnergyUsageMessageContent)Content).Timestamp;
				

				((HomeAgent)myAgent).AddEnergyUsage(agentID, eu, ts);
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
