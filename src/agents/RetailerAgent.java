package agents;

import jade.core.Agent;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.ContractNetResponder;

public abstract class RetailerAgent extends Agent {
	
	protected void setup() {
		MessageTemplate template = MessageTemplate.and(
				MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET),
				MessageTemplate.MatchPerformative(ACLMessage.CFP));
		
		addBehaviour(new ContractNetResponder(this, template) {
			protected ACLMessage prepareResponse(ACLMessage cfp) throws NotUnderstoodException, RefuseException {
				if (new Boolean(false)) { //Something went wrong or agent refuses
					throw new RefuseException("[REFUSE MESSAGE]");
				}
				else {
					ACLMessage propose = cfp.createReply();
					propose.setPerformative(ACLMessage.PROPOSE);
					propose.setContent("[PROPOSAL CONTENT]");
					return propose;
				}
			}
			
			protected ACLMessage prepareResultNotification(ACLMessage cfp, ACLMessage propose, ACLMessage accept) throws FailureException{
				if (new Boolean(false)) { //Something went wrong
					throw new FailureException("[FAIL MESSAGE]");
				}
				else {
					ACLMessage inform = accept.createReply();
					inform.setPerformative(ACLMessage.PROPOSE);
					return inform;
				}
			}
			
			protected void handleRejectProposal(ACLMessage reject) {
				
			}
		});
	}
	
}
