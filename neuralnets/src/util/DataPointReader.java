package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class DataPointReader {
	
	private Set<DataPoint> dataPoints;
	
	public DataPointReader() {
		dataPoints = new HashSet<DataPoint>();
	}
	
	public Set<DataPoint> readFromFile(File file){
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String currLn = null;

			while((currLn = reader.readLine()) != null){
				dataPoints.add( parseLine(currLn) );
			}
			
			reader.close();
			return dataPoints;

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	protected DataPoint parseLine(String currLn){
		StringTokenizer tokenizer = new StringTokenizer(currLn, ",");
		DataPoint dp = new DataPoint();
		while(tokenizer.hasMoreTokens()){
			String currToken = tokenizer.nextToken();
			dp.addDimension(currToken);
		}
		
		return dp;
	}
}
