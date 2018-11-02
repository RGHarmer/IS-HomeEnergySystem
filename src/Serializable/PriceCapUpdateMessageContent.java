package Serializable;

import java.time.Instant;

public class PriceCapUpdateMessageContent implements java.io.Serializable {

	Float minBuy;
	Float maxBuy;
	Float minSell;
	Float maxSell;
	
	Instant ts;
	
	public PriceCapUpdateMessageContent(Float minB, Float maxB, Float minS, Float maxS){
		minBuy = minB;
		maxBuy = maxB;
		minSell = minS;
		maxSell = maxS;
		ts = Instant.now();
	}
}
