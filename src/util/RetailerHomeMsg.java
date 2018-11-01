package util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RetailerHomeMsg implements Serializable {
	public float energyRate;
	public float penaltyRate;
	
	public RetailerHomeMsg(float price, float penalty) {
		energyRate = price;
		penaltyRate = penalty;
	}
}
