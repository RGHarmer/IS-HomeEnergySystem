package util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class HomeRetailerMsg implements Serializable {
	public float energyRequested;
	
	public HomeRetailerMsg(float requested) {
		energyRequested = requested;
	}
}
