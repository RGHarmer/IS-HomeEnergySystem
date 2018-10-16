package behaviours;

import java.util.Vector;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetInitiator;

@SuppressWarnings("serial")
public class RetailContractInitiator extends ContractNetInitiator {

	public RetailContractInitiator(Agent a, ACLMessage cfp) {
		super(a, cfp);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("rawtypes")
	protected void handlePropose(ACLMessage propose, Vector v) {
		
	}
	
	protected void handleRefuse(ACLMessage refuse) {
		
	}
	
	protected void handleFailure(ACLMessage failure) {
		
	}
	
	@SuppressWarnings("rawtypes")
	protected void handleAllResponses(Vector responses, Vector acceptances) {
		
	}
	
	protected void handleInform(ACLMessage inform) {
		
	}
}
