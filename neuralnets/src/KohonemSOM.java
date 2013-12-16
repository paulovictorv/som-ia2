import java.io.File;
import java.util.Set;

import util.DataPoint;
import util.DataPointReader;


public class KohonemSOM {
	
	private Set<DataPoint> dataPoints;
	
	private int radius;
	
	private LearningRate learning;
	
	private int maxClusters;
	
	public KohonemSOM() {
	}
	
	public KohonemSOM(File trainset, int radius, LearningRate learning, int maxClusters) {
		train(trainset, radius, learning, maxClusters);
	}

	
	public void train(File trainset, int radius, LearningRate learning, int maxClusters){
		DataPointReader reader = new DataPointReader();
		dataPoints = reader.readFromFile(trainset);
		doTrain();
	}
	
	private void doTrain() {
		//If the learning rate is different at each epoch, reset it to guarantee it's state before training again
		learning.reset();
		
		//Allocate resulting weightMatrix, determined by the number of clusters and number of dimensions on the dataPoints
		double[][] weightMatrix = new double[dataPoints.iterator().next().countDimensions()][maxClusters];
		
		//Do the actual training
		//TODO: Write the code for training
	}
	
	

}
