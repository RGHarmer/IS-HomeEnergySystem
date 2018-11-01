package util;

public final class Negotiation {
	
	private Negotiation() {}
	
	/**
	 * Negotiation Method
	 * 
	 * @param worst		The worst case value for the negotiator
	 * @param best		The best case value for the negotiator
	 * @param step		The current step of the negotiation
	 * @param steps		The max (expected) number of steps in the negotiation
	 * @param modifier	Negotiator's 'aggression'
	 * @return			Returns NaN if modifier < 0
	 */
	public static Float Calculate(float worst, float best, int step, int steps, float modifier) {
		if (modifier < 0) {
			return Float.NaN;
		}
		return (float) (best + (worst - best) * Math.pow(step / steps, modifier));
	}
}
