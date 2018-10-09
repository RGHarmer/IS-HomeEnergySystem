package util;

import java.io.Serializable;
import java.time.Instant;

@SuppressWarnings("serial")
public class ApplianceHomeMsg implements Serializable {
	public String agentName;
	public Instant timeStamp;
	public float energyUsed;
	public float energyEstimate;
	
	public ApplianceHomeMsg(String name, Instant time, float used, float estimate) {
		agentName = name;
		timeStamp = time;
		energyUsed = used;
		energyEstimate = estimate;
	}
}
