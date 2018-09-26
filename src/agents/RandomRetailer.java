package agents;

import java.util.Random;

public class RandomRetailer extends RetailerAgent {
	
	private float changeRate;
	
	private float minCurrent;
	private float maxCurrent;
	private float minMin;
	private float maxPenalty;
	private float minChange;
	private float maxChange;

	@Override
	public void ResetRates() {
		currentRate = minCurrent + (new Random().nextFloat() * (maxCurrent - minCurrent));
		minRate = minMin + (new Random().nextFloat() * (currentRate - minMin));
		penaltyRate = currentRate + (new Random().nextFloat() * (maxPenalty - currentRate));
	}

	@Override
	public void Yield() {
		currentRate -= minChange + (new Random().nextFloat() * (maxChange - minChange));
		if (currentRate < minRate) {
			currentRate = minRate;
		}
	}
}
