package util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RetailerHomeMsg implements Serializable {
	public String agentName;
	public float energyPrice;
	public float penaltyRate;
	
	public RetailerHomeMsg(String name, float price, float penalty) {
		agentName = name;
		energyPrice = price;
		penaltyRate = penalty;
	}
}
