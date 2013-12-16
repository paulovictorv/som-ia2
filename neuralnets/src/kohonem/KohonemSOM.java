package kohonem;
import java.io.File;
import java.util.Random;
import java.util.Set;

import util.DataPoint;
import util.DataPointReader;


public class KohonemSOM {
	
	private Set<DataPoint> dataPoints;
	
	private int radius;
	
	private LearningRate learning;
	
	private int maxClusters;
	
	private NeuralNet net;
	
	public KohonemSOM() {
	}
	
	public KohonemSOM(File trainset, int radius, LearningRate learning, int maxClusters) {
		train(trainset, radius, learning, maxClusters);
	}

	
	public void train(File trainset, int radius, LearningRate learning, int maxClusters){
		DataPointReader reader = new DataPointReader();
		dataPoints = reader.readFromFile(trainset);
		double[][] trainedWeights = doTrain();
		net = new NeuralNet(trainedWeights);
	}
	
	public double[] classify(double[] point){
		return net.classify(point);
	}
	
	private double[][] doTrain() {
		//If the learning rate is different at each epoch, reset it to guarantee its state before training again
		learning.reset();
		
		//Allocate resulting weightMatrix, determined by the number of clusters and number of dimensions on the dataPoints
		double[][] weightMatrix = new double[maxClusters][dataPoints.iterator().next().countDimensions()];
		
		//Init weightMatrix
		initMatrix(weightMatrix);

		for (int iterations = 0; iterations < 100; iterations++){
			//Do the actual training
			Double lastDistance = Double.POSITIVE_INFINITY;
			DataPoint minDataPoint = null;
			int minimalCluster = 0;
			
			//For each datapoint in our dataset
			for (DataPoint dp : dataPoints){
				//Iterate through each cluster
				for(int cluster = 0; cluster < weightMatrix.length; cluster++){
					//Calculate this datapoint distance from the clusters
					Double distance = dp.calculateDistance(weightMatrix[cluster]);
					
					//Determine if this distance is the minimum
					if (distance < lastDistance){
						lastDistance = distance;
						//Store the data point which has the minimum distance from a cluster
						minDataPoint = dp;
						//Store the cluster index from which the distance was minimum
						minimalCluster = cluster;
					}
					
				}
			}
			
			double[] clusterToBeUpdated = weightMatrix[minimalCluster];
			//We need to calculate the weight update on each neighbor of the cluster to be updated on a specified neighborhood determined by the radius
			//We then iterate through "radius", incrementing it at each iteration and "giving a nudge" in its neighbors
			for (int theRadius = 0; theRadius == radius; theRadius++){
				//Special case: if the radius == 0, there is no positive or negative neighbors. Calculate it directly then
				if (theRadius == 0){
					updateCluster(minDataPoint, clusterToBeUpdated);
					//Keep going..
					continue;
				}
				
				//Check if we have a positive neighbor within the specified radius
				if (minimalCluster + theRadius > weightMatrix.length){
					double[] positiveNeighbor = weightMatrix[minimalCluster + theRadius];
					//Update the cluster
					updateCluster(minDataPoint, positiveNeighbor);
				}
				
				//Again, check if we have a negative neighbor within the specified radius
				if (minimalCluster - theRadius < weightMatrix.length){
					double[] negativeNeighbor = weightMatrix[minimalCluster - theRadius];
					//Update the cluster
					updateCluster(minDataPoint, negativeNeighbor);
				}
			}
			
			learning.updateLearnRate();
		}
		
		return weightMatrix;
	}
		
	private void updateCluster(DataPoint minDataPoint, double[] clusterToBeUpdated) {
		for (int i = 0; i < clusterToBeUpdated.length; i++){
			clusterToBeUpdated[i] = clusterToBeUpdated[i] + learning.learnRate() * (minDataPoint.getDimension(i) - clusterToBeUpdated[i]);
		}
	}

	private void initMatrix(double[][] weightMatrix) {
		Random random = new Random(System.currentTimeMillis());
		for (int j = 0; j < weightMatrix.length; j++){
			for (int i = 0; i < weightMatrix[j].length; i++){
				weightMatrix[j][i] = random.nextDouble();
			}
		}
	}
	
	

}
