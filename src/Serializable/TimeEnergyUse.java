package Serializable;

import java.time.Instant;
import java.util.Vector;
public class TimeEnergyUse implements java.io.Serializable{

	public int absoluteInterval;
	public Instant time;
	public Float value;
	
	public TimeEnergyUse(int abs, Instant t, Float val) {
		absoluteInterval = abs;
		time = t;
		value = val;
	}
}
