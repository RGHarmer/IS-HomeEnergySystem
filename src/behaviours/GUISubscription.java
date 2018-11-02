package behaviours;

import java.util.Iterator;

import agents.GUIAgent;
import agents.HomeAgent;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.proto.SubscriptionInitiator;

public class GUISubscription extends SubscriptionInitiator {

	public GUISubscription(Agent a, ACLMessage msg, String subType) {
		super(a, msg);
	}
	
	protected void handleInform(ACLMessage inform) {
		try {
			DFAgentDescription[] results = DFService.decodeNotification(inform.getContent());
			if (results.length > 0) {
				for (int i = 0; i < results.length; ++i) {
					DFAgentDescription dfd = results[i];
					AID provider = dfd.getName();
					@SuppressWarnings("rawtypes")
					Iterator services = dfd.getAllServices();
					while (services.hasNext()) {
						ServiceDescription sd = (ServiceDescription)services.next();
						if (sd.getType().equals("Home")) {
							((GUIAgent)this.myAgent).myHomeAgent = provider;
						}
					}
				}
			}
		}
		catch (FIPAException fe) {
			
		}
	}
}
