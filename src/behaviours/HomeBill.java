package behaviours;

import java.io.IOException;

import agents.HomeAgent;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class HomeBill extends Behaviour {

	@Override
	public void action() {
		AID reciever = ((HomeAgent) myAgent).retailer;
		float value = ((HomeAgent) myAgent).agreedEnergy * ((HomeAgent) myAgent).agreedRate;
		if (((HomeAgent) myAgent).energyUsage > ((HomeAgent) myAgent).agreedEnergy) {
			value += (((HomeAgent) myAgent).energyUsage - ((HomeAgent) myAgent).agreedEnergy) * ((HomeAgent) myAgent).agreedPenalty;
		}
		try {
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setContentObject(value);
			msg.addReceiver(reciever);
			myAgent.send(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
