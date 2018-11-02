package agents;

import util.Negotiation;

@SuppressWarnings("serial")
public class FixedRetailer extends RetailerAgent {
	
	@Override
	public void ResetRates() {
		currentRate = initialRate;
	}

	@Override
	public void Yield(int step, int steps) {
		currentRate = Negotiation.Calculate(minRate, initialRate, step, steps, 1);
	}
}
