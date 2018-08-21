package agentBlueprinting;

import java.util.Date;
import java.util.Map;
import java.util.Random;

public class ApplianceAgent {
	
	public float usageRate;
	public float usageMargin;
	private float currentEnergy;
	private float nextPrediction;
	private Map<Date,Float> archive;
	
	private void EstimateEnergy(/*Date period*/) {
		nextPrediction = 0;
		return;
	}
	
	private void UseEnergy() {
		currentEnergy = usageRate + usageMargin * ((new Random().nextFloat() - 0.5f) * 2);
		return;
	}
	
	private void SendData() {
		float lastEnergy = currentEnergy;
		currentEnergy = 0;
		//send lastEnergy
		//send nextPrediction
		archive.put(new Date(), lastEnergy);
		EstimateEnergy();
		return;
	}
}