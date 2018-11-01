package agents;

import java.util.Random;

@SuppressWarnings("serial")
public class RandomRetailer extends RetailerAgent {
	private float minCurrent;
	private float maxCurrent;
	private float minMin;
	private float maxPenalty;
	private float minChange;
	private float maxChange;

	@Override
	public void ResetRates() {
		initialRate = minCurrent + (new Random().nextFloat() * (maxCurrent - minCurrent));
		minRate = minMin + (new Random().nextFloat() * (initialRate - minMin));
		penaltyRate = initialRate + (new Random().nextFloat() * (maxPenalty - initialRate));
	}

	@Override
	public void Yield(int step, int steps) {
		currentRate -= minChange + (new Random().nextFloat() * (maxChange - minChange));
		if (currentRate < minRate) {
			currentRate = minRate;
		}
	}
}
