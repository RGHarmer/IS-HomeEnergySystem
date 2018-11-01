package behaviours;

import java.io.IOException;

import agents.RetailerAgent;
import jade.core.Agent;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.proto.ContractNetResponder;
import util.HomeRetailerMsg;

@SuppressWarnings("serial")
public class RetailContractResponder extends ContractNetResponder {

	private int expectedIterations;
	
	public RetailContractResponder(Agent a, MessageTemplate mt) {
		super(a, mt);
		// TODO Auto-generated constructor stub
	}
	
	protected ACLMessage prepareResponse(ACLMessage cfp) throws NotUnderstoodException, RefuseException {
		HomeRetailerMsg message;
		try {
			message = (HomeRetailerMsg)cfp.getContentObject();
			if (message.expectedIterations != 0) {
				expectedIterations = message.expectedIterations;
			}
			((RetailerAgent) myAgent).Yield(message.iteration, expectedIterations);
			
			ACLMessage propose = cfp.createReply();
			propose.setPerformative(ACLMessage.PROPOSE);
			try {
				propose.setContentObject(((RetailerAgent) myAgent).ConstructHomeMsg(message.iteration, expectedIterations));
				return propose;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new NotUnderstoodException("(Home to Retailer) failed to set message content");
			}
		} catch (UnreadableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new NotUnderstoodException("(Home to Retailer) failed to cast message content to HomeRetailMessage");
		}
	}
	
	protected ACLMessage prepareResultNotification(ACLMessage cfp, ACLMessage propose, ACLMessage accept) throws FailureException{
		if (new Boolean(true)) { // All is well
			ACLMessage inform = accept.createReply();
			inform.setPerformative(ACLMessage.PROPOSE);
			return inform;
		}
		else {
			throw new FailureException("[FAIL MESSAGE]");
		}
	}
	
	protected void handleRejectProposal(ACLMessage reject) {
		
	}

}
