package agents;

import jade.core.Agent;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.Vector;
 
public class ApplianceAgent extends Agent {
	private Map<Instant,Float> archive;
	private float currentEstimate;
	private float currentUsage;
	
	protected void setup() {
		MessageTemplate template = MessageTemplate.and(MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST),
		  		MessageTemplate.MatchPerformative(ACLMessage.REQUEST) );
		
		addBehaviour(new AchieveREResponder(this, template) {
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
					return inform;
				}
			}
		});
	}
	
	
	
} 