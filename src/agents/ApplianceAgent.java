package agents;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import util.ApplianceHomeMsg;

import java.time.Instant;
import java.util.Map;

import behaviours.ApplianceRequestResponder;
 
@SuppressWarnings("serial")
public class ApplianceAgent extends Agent {

	private Map<Instant,Float> archive;
	private float currentEstimate;
	private float currentUsage;
	
	
	private ApplianceRequestResponder arr;
	
	protected void setup() {
		Object[] args = getArguments();
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Appliance");
		sd.setName(args[0].toString());
		register(sd);
		
		MessageTemplate template = MessageTemplate.and(MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST),
		  		MessageTemplate.MatchPerformative(ACLMessage.REQUEST) );
		
		arr = new ApplianceRequestResponder(this, template);
		
		addBehaviour(arr);
	}
	
	protected void register(ServiceDescription sd) {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		}
		catch(FIPAException fe) {
			
		}
	}
	
	public void Reset() {
		
	}
	
	public ApplianceHomeMsg ContructMsgObject(Instant timeStamp) {
		return new ApplianceHomeMsg(getName(), timeStamp, currentUsage, currentEstimate);
	}
} 