package Serializable;

import java.time.Instant;

public class EnergyUsageMessageContent implements java.io.Serializable {

	public float EnergyUsage;
	public Instant Timestamp;
	public float NextUsageEstimate;
}
