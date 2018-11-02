package behaviours;

import agents.RetailerAgent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class RetailerBill extends CyclicBehaviour {

	@Override
	public void action() {
		ACLMessage msg = myAgent.receive();
		if (msg != null) {
			try {
				((RetailerAgent) myAgent).score += (float) msg.getContentObject();
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
