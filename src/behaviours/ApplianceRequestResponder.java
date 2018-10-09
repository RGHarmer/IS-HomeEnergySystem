package behaviours;

import java.io.IOException;

import agents.ApplianceAgent;
import jade.core.Agent;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.proto.AchieveREResponder;
import util.ApplianceHomeMsg;
import util.HomeApplianceMsg;

public class ApplianceRequestResponder extends AchieveREResponder {

	public ApplianceRequestResponder(Agent a, MessageTemplate mt) {
		super(a, mt);
		// TODO Auto-generated constructor stub
	}
	
	protected ACLMessage prepareResponse(ACLMessage request) throws NotUnderstoodException, RefuseException {
		
		// Actions
		
		if (new Boolean(false)) { // Agent refuses
			throw new RefuseException("[REFUSE MESSAGE]");
		}
		else {
			ACLMessage agree = request.createReply();
			agree.setPerformative(ACLMessage.AGREE);
			return agree;
		}
	}
	
	protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) throws FailureException {
		
		// Actions
		
		if (new Boolean(false)) { //
			throw new FailureException("[FAIL MESSAGE]");
		}
		else {
			ACLMessage inform = request.createReply();
			inform.setPerformative(ACLMessage.INFORM);
			
			try {
				inform.setContentObject(((ApplianceAgent) myAgent).ContructMsgObject(((HomeApplianceMsg)request.getContentObject()).timeStamp));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return inform;
		}
	}
}
