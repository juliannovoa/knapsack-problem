package es.uned.peda;

/**
 * The Class ProblemData.
 */
public class ProblemData {

	/** The values. */
	private final double[] values;

	/** The volumes. */
	private final int[] volumes;

	/** The number of objects. */
	private final int numberOfObjects;

	/** The knapsack volume. */
	private final int knapsackVolume;

	/**
	 * Instantiates a new problem data.
	 *
	 * @param values
	 *            the values
	 * @param volumes
	 *            the volumes
	 * @param numberOfObjects
	 *            the number of objects
	 * @param knapsackVolume
	 *            the knapsack volume
	 */
	public ProblemData(double[] values, int[] volumes, int numberOfObjects, int knapsackVolume) {

		this.values = values;
		this.volumes = volumes;
		this.numberOfObjects = numberOfObjects;
		this.knapsackVolume = knapsackVolume;

	}

	/**
	 * Gets the values.
	 *
	 * @return the values
	 */
	public double[] getValues() {
		return values;
	}

	/**
	 * Gets the volumes.
	 *
	 * @return the volumes
	 */
	public int[] getVolumes() {
		return volumes;
	}

	/**
	 * Gets the number of objects.
	 *
	 * @return the number of objects
	 */
	public int getNumberOfObjects() {
		return numberOfObjects;
	}

	/**
	 * Gets the knapsack volume.
	 *
	 * @return the knapsack volume
	 */
	public int getKnapsackVolume() {
		return knapsackVolume;
	}

}
