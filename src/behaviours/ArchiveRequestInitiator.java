package behaviours;

import java.util.Vector;

import agents.ApplianceAgent;
import agents.HomeAgent;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.proto.AchieveREInitiator;
import util.ApplianceHomeMsg;
import util.DataArchiveMsg;

public class ArchiveRequestInitiator extends AchieveREInitiator {
	
	public ArchiveRequestInitiator(Agent a, ACLMessage msg) {
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

		ACLMessage message = (ACLMessage)notifications.get(0);
		try {
			DataArchiveMsg content = (DataArchiveMsg)message.getContentObject();
			((ApplianceAgent)myAgent).archive = content.data;
		} catch (UnreadableException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}			
		
		
	}

}
