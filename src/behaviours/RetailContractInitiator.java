package behaviours;

import java.io.IOException;
import java.util.Date;
import java.util.Vector;

import agents.HomeAgent;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.proto.ContractNetInitiator;
import util.HomeRetailerMsg;
import util.Negotiation;
import util.RetailerHomeMsg;

@SuppressWarnings("serial")
public class RetailContractInitiator extends ContractNetInitiator {
	
	int step = 0;
	Date replyBy;
	

	public RetailContractInitiator(Agent a, ACLMessage cfp) {
		super(a, cfp);
		replyBy = cfp.getReplyByDate();
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
		float homeVal = Negotiation.Calculate(((HomeAgent)myAgent).maxBuyRate, ((HomeAgent)myAgent).minBuyRate, step, ((HomeAgent)myAgent).iterations, ((HomeAgent)myAgent).aggression);
		float bestVal = 0;
		ACLMessage accepted = null;
		for(int i = 0; i < responses.size(); i++) {
			ACLMessage message = (ACLMessage)responses.get(i);
			try {
				RetailerHomeMsg content = (RetailerHomeMsg)message.getContentObject();
				if (content.energyRate < homeVal) {
					if (bestVal == 0 || bestVal > content.energyRate) {
						bestVal = content.energyRate;
						accepted = message;
					}
				}
				
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}			
		}
		if (accepted != null) {
			((HomeAgent)myAgent).retailer = accepted.getSender();
			myAgent.addBehaviour(new HomeBill());
		}
		else {
			step++;
			Vector<ACLMessage> replies = new Vector<ACLMessage>();
			for (int i = 0; i < responses.size(); i++) {
				ACLMessage cfp = ((ACLMessage)responses.get(i)).createReply();
				cfp.setPerformative(ACLMessage.CFP);
				cfp.setReplyByDate(replyBy);
				try {
					cfp.setContentObject(new HomeRetailerMsg(step));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				replies.add(cfp);
			}
			newIteration(replies);
		}
	}
	
	protected void handleInform(ACLMessage inform) {
		
	}
}
