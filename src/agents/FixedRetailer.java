package agents;

@SuppressWarnings("serial")
public class FixedRetailer extends RetailerAgent {
	
	private float setRate = 2;
	private float setMin = 0;
	private float setPenalty = 8;
	private float changeRate = 0.1f;
	
	@Override
	public void ResetRates() {
		currentRate = setRate;
		minRate = setMin;
		penaltyRate = setPenalty;
	}

	@Override
	public void Yield() {
		currentRate -= changeRate;
		if (currentRate < minRate) {
			currentRate = minRate;
		}
	}
}
