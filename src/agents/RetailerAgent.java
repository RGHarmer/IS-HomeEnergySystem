package agents;

import behaviours.RetailContractResponder;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public abstract class RetailerAgent extends Agent {
	
	private RetailContractResponder rcr;
	
	protected void setup() {
		MessageTemplate template = MessageTemplate.and(
				MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET),
				MessageTemplate.MatchPerformative(ACLMessage.CFP));
		
		rcr = new RetailContractResponder(this, template);
		
		addBehaviour(rcr);
	}
	
}
