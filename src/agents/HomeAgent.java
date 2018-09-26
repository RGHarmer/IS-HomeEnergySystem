package agents; 
 
import java.time.Clock;
import java.time.Instant;

import behaviours.MessageTicker;
import jade.core.AID;
import jade.core.Agent;
 
 
public class HomeAgent extends Agent {
	
	public String name;
	private Clock clock;
	private int cycleInterval = 10000; //in milliseconds
	private MessageTicker ticker;
	/**
	 * This method adds an energy usage block to the map, using the associated Appliance Agent ID
	 * 
	 * @param id This is the Agent ID
	 * @param usage This is the energy usage for the current Cycle
	 * @param timestamp this is the associated Instant/Timestamp
	 */
	public void AddEnergyUsage(AID id, float usage, Instant timestamp) {
		//Log stuff
		
	}
	
	protected void setup() {
		ticker = new MessageTicker(this, cycleInterval);
		addBehaviour(ticker);
	}
}
