
public class SigmoidActivationFunction implements ActivationFunction {

	@Override
	public double activate(double value) {
		double dividend = 1 + Math.pow(Math.E, -value);
		return 1 / dividend;
	}

}
