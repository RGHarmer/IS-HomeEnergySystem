package behaviours;

import java.util.Vector;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetInitiator;

public class RetailContractInitiator extends ContractNetInitiator {

	public RetailContractInitiator(Agent a, ACLMessage cfp) {
		super(a, cfp);
		// TODO Auto-generated constructor stub
	}

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
}
