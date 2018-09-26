package agents;

import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.time.Instant;
import java.util.Map;

import behaviours.ApplianceRequestResponder;
 
public class ApplianceAgent extends Agent {
	
	public String name;
	private Map<Instant,Float> archive;
	private float currentEstimate;
	private float currentUsage;
	
	
	private ApplianceRequestResponder arr;
	
	protected void setup() {
		MessageTemplate template = MessageTemplate.and(MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST),
		  		MessageTemplate.MatchPerformative(ACLMessage.REQUEST) );
		
		arr = new ApplianceRequestResponder(this, template);
		
		addBehaviour(arr);
	}
	
	public void Reset() {
		
	}
} 