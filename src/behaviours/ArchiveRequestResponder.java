package behaviours;

import java.io.IOException;

import agents.ApplianceAgent;
import agents.DataAgent;
import jade.core.Agent;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.proto.AchieveREResponder;
import util.HomeApplianceMsg;

public class ArchiveRequestResponder  extends AchieveREResponder {

	public ArchiveRequestResponder(Agent a, MessageTemplate mt) {
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
			
			//Determine the Appliance Row Number
			String defaultName = "Appliance";
			int id = Integer.parseInt(request.getSender().getName().replace(defaultName, ""));
			
			try {
				//Return the Archive related to the row Number
				inform.setContentObject(((DataAgent)this.myAgent).getApplianceArchive(id,0,((DataAgent)this.myAgent).energyUsageData.size()));
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return inform;
		}
	}
}
