
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
		for(int j = 0; j < z.length; j++){
			double sum = 0.0;
			
			for (int i = 0; i < z.length; i++){
				sum += input[i] * weight[i][j];
			}
			
			z[j] = function.activate(sum);
		}
		
		
		return z;
			
	}
	
}
