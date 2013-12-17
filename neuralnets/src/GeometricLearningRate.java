import kohonem.LearningRate;


public class GeometricLearningRate implements LearningRate {

	
	@Override
	public double learnRate() {
		return 0.6;
	}

	@Override
	public void reset() {
	}

	@Override
	public void updateLearnRate() {

	}

}
