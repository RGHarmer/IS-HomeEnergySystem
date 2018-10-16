package Serializable;

import java.time.Instant;
import java.util.Vector;

public class TimeEnergyUsage implements java.io.Serializable {
	public String time;
	public Vector<Integer> values;
	
	public TimeEnergyUsage(String t, Vector<Integer> vals) {
		time = t;
		values = vals;
	}
}
