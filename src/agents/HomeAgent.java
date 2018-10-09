package agents; 
 
import java.io.Serializable;
import java.time.Clock;
import java.time.Instant;

import behaviours.MessageTicker;
import jade.core.AID;
import jade.core.Agent;
import util.AdjustableClock;
import util.HomeApplianceMsg;
 
 
public class HomeAgent extends Agent {

	private AdjustableClock clock;
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
		clock = new AdjustableClock(1);
		ticker = new MessageTicker(this, cycleInterval);
		addBehaviour(ticker);
	}

	public HomeApplianceMsg ContructApplianceMsgObject() {
		return new HomeApplianceMsg(clock.instant());
	}
}
