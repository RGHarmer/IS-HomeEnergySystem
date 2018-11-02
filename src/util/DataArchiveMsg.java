package util;

import java.io.Serializable;
import java.time.Instant;
import java.util.Vector;

import Serializable.TimeEnergyUse;


@SuppressWarnings("serial")
public class DataArchiveMsg  implements Serializable {
	public Vector<TimeEnergyUse> data;
	
	public DataArchiveMsg(Vector<TimeEnergyUse> dataDump) {
		data = dataDump;
	}
}
