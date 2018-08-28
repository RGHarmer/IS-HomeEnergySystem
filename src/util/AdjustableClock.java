package util;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AdjustableClock extends Clock {

	private ZonedDateTime begin;
	private float mult;
	
	public AdjustableClock(float multiplier) {
		begin = ZonedDateTime.now();
		mult = multiplier;
	}
	
	@Override
	public ZoneId getZone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Instant instant() {
		ZonedDateTime now = ZonedDateTime.now();
		long beginSeconds = begin.toInstant().getEpochSecond();
		long nowSeconds = now.toInstant().getEpochSecond();
		long difference = nowSeconds - beginSeconds;
		return begin.plusSeconds(difference * (long)mult).toInstant();
	}

	@Override
	public Clock withZone(ZoneId arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
