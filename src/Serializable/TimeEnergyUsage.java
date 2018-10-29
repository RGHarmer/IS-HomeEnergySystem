package Serializable;

import java.time.Instant;
import java.util.Vector;

public class TimeEnergyUsage implements java.io.Serializable {
	public int absoluteInterval;
	public String time;
	public Vector<Float> values;
	
	public TimeEnergyUsage(int abs, String t, Vector<Float> vals) {
		absoluteInterval = abs;
		time = t;
		values = vals;
	}
}
