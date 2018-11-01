package util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class HomeRetailerMsg implements Serializable {
	public int iteration;
	public int expectedIterations;
	
	public HomeRetailerMsg(int step, int steps) {
		iteration = step;
		expectedIterations = steps;
	}
	
	public HomeRetailerMsg(int step) {
		iteration = step;
		expectedIterations = 0;
	}
}
