package agents;

import util.Negotiation;

@SuppressWarnings("serial")
public class FixedRetailer extends RetailerAgent {
	
	private float setRate = 2;
	private float setMin = 0;
	private float setPenalty = 8;
	
	@Override
	public void ResetRates() {
		initialRate = setRate;
		minRate = setMin;
		penaltyRate = setPenalty;
	}

	@Override
	public void Yield(int step, int steps) {
		currentRate = Negotiation.Calculate(minRate, initialRate, step, steps, 1);
	}
}
