package util;

import java.util.ArrayList;
import java.util.List;

public class DataPoint {
	
	private List<Double> dimensions;
	
	public DataPoint() {
		dimensions = new ArrayList<Double>();
	}
	
	public void addDimension(String value){
		dimensions.add(Double.parseDouble(value));
	}

	public double getDimension(int i) {
		return dimensions.get(i);
	}
	
	public int countDimensions(){
		return dimensions.size();
	}

	public Double calculateDistance(double[] ds) {
		double distance = 0.0;
		
		for(int j = 0; j < ds.length; j++){
			distance += Math.pow(ds[j] - dimensions.get(j), 2);
		}
		
		return distance;
	}

		
}
