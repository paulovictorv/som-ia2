import java.io.File;
import java.util.Arrays;

import kohonem.KohonemSOM;


public class Main {
	
	public static void main(String[] args) {
		KohonemSOM som = new KohonemSOM(new File("res/wine.csv"), 1, new GeometricLearningRate(), 3);
		double[] input = {14.23,1.71,2.43,15.6,127,2.8,3.06,0.28,2.29,5.64,1.04,3.92,1065}; 
		double[] classify = som.classify(input);
		
		System.out.println(Arrays.toString(classify));
	}
	
}
