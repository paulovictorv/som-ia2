package util;

import java.util.ArrayList;
import java.util.List;

public class DataPoint {
	
	private List<Double> dataPoints;
	
	public DataPoint() {
		dataPoints = new ArrayList<Double>();
	}
	
	public void addDimension(String value){
		dataPoints.add(Double.parseDouble(value));
	}
	
	public int countDimensions(){
		return dataPoints.size();
	}
		
}
