package agents;

import util.Negotiation;

@SuppressWarnings("serial")
public class SmartRetailer extends RetailerAgent {
	
	protected float aggression;

	@Override
	public void ResetRates() {
		if (sale) {
			aggression += 0.1f;
		}
		else {
			if (aggression > 0.1) {
				aggression -= 0.1f;
			}
		}
		currentRate = initialRate;
	}

	@Override
	public void Yield(int step, int steps) {
		currentRate = Negotiation.Calculate(minRate, initialRate, step, steps, aggression);
	}

}
