package kohonem;

public class NeuralNet {

	private ActivationFunction function;

	private double[][] weight;
	
	public NeuralNet(double[][] weights){
		weight = weights;
		this.function = new SigmoidActivationFunction();
	}

	public NeuralNet(double[][] weights, ActivationFunction func){
		weight = weights;
		function = func;
	}
	
	public double[] classify(double... input){
		
		double[] z = new double[input.length];
		
		//Feedforward
		for(int node = 0; node < weight.length; node++){
			double sum = 0.0;
			
			for (int nodeWeigth = 0; nodeWeigth < z.length; nodeWeigth++){
				int dimension = nodeWeigth;
				sum += input[dimension] * weight[node][nodeWeigth];
			}
			
			z[node] = function.activate(sum);
		}
		
		
		return z;
			
	}
	
}
