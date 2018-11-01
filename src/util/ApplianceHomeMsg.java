package util;

import java.io.Serializable;
import java.time.Instant;

@SuppressWarnings("serial")
public class ApplianceHomeMsg implements Serializable {
	public Instant timeStamp;
	public float energyUsed;
	public float energyEstimate;
	
	public ApplianceHomeMsg(Instant time, float used, float estimate) {
		timeStamp = time;
		energyUsed = used;
		energyEstimate = estimate;
	}
}
