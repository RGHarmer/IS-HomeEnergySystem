package behaviours;

import java.util.Iterator;

import agents.HomeAgent;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.proto.SubscriptionInitiator;

@SuppressWarnings("serial")
public class HomeSubscription extends SubscriptionInitiator {
	
	private String type;

	public HomeSubscription(Agent a, ACLMessage msg, String subType) {
		super(a, msg);
		type = subType;
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
						if (sd.getType().equals(type)) {
							if (type.equals("Appliance")) {
								((HomeAgent)myAgent).appliances.add(provider);
							}
							if (type.equals("Retailer")) {
								((HomeAgent)myAgent).retailers.add(provider);
							}
						}
					}
				}
			}
		}
		catch (FIPAException fe) {
			
		}
	}

}
