package Serializable;

import java.time.Instant;

public class EnergyUsageMessageContent implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public float EnergyUsage;
	public Instant Timestamp;
	public float NextUsageEstimate;
}
