package agents; 
 
import java.time.Clock;

import behaviours.MessageTicker;
import jade.core.Agent;
 
 
public class HomeAgent extends Agent {
	
	private Clock clock;
	private int cycleInterval = 10000; //in milliseconds
	private MessageTicker ticker;
	
	protected void setup() {
		ticker = new MessageTicker(this, cycleInterval);
		addBehaviour(ticker);
	}
}
