package util;

import java.io.Serializable;
import java.time.Instant;

@SuppressWarnings("serial")
public class HomeApplianceMsg implements Serializable {
	public Instant timeStamp;
	
	public HomeApplianceMsg(Instant time) {
		timeStamp = time;
	}
}
