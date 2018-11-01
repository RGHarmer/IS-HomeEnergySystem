package agents;

import behaviours.RetailContractResponder;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import util.RetailerHomeMsg;
import jade.domain.DFService;
import jade.domain.FIPAException;

@SuppressWarnings("serial")
public abstract class RetailerAgent extends Agent {

	public float score;
	protected RetailContractResponder rcr;
	protected float initialRate;
	protected float currentRate;
	protected float minRate;
	protected float aggression;
	protected float penaltyRate;
	
	protected void setup() {
		Object[] args = getArguments();
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Retailer");
		sd.setName(args[1].toString());
		register(sd);
		
		MessageTemplate template = MessageTemplate.and(
				MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET),
				MessageTemplate.MatchPerformative(ACLMessage.CFP));
		
		rcr = new RetailContractResponder(this, template);
		
		addBehaviour(rcr);
	}
	
	protected void register(ServiceDescription sd) {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		}
		catch (FIPAException fe) {
			
		}
	}
	
	public RetailerHomeMsg ConstructHomeMsg(int step, int steps) {
		return new RetailerHomeMsg(currentRate, penaltyRate);
	}
	
	public abstract void ResetRates();
	
	public abstract void Yield(int step, int steps);
}
