package agentBlueprinting;

public abstract class RetailerAgent {
	
	private float currentBuying;
	private float currentSelling;
	private float currentPenalty;
	private float nextBuying;
	private float nextSelling;
	private float nextPenalty;
	
	abstract void CalculateNext();
	
	abstract void Negotiate();
}
