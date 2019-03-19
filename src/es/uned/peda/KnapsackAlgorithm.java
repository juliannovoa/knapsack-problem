package es.uned.peda;

import java.io.PrintStream;

/**
 * The Class KnapsackAlgorithm.
 */
public class KnapsackAlgorithm implements Runnable {

	/** The output stream. */
	private final PrintStream output;

	/** Whether to show the trace. */
	private final boolean trace;

	/** The object values. */
	private final double[] values;

	/** The object volumes. */
	private final int[] volumes;

	/** The knapsack volume. */
	private final int knapsackVolume;

	/** The number of objects. */
	private final int numberOfObjects;

	/** The tab. */
	private final double[][] tab;

	/**
	 * Instantiates a new knapsack algorithm.
	 *
	 * @param knapsackVolume
	 *            the knapsack volume
	 * @param numberOfObjects
	 *            the number of objects
	 * @param values
	 *            the values
	 * @param volumes
	 *            the volumes
	 * @param trace
	 *            the trace
	 * @param output
	 *            the output
	 */
	public KnapsackAlgorithm(int knapsackVolume, int numberOfObjects, double[] values, int[] volumes, boolean trace,
			PrintStream output) {

		this.output = output;
		this.trace = trace;

		this.values = values;
		this.volumes = volumes;

		this.knapsackVolume = knapsackVolume;
		this.numberOfObjects = numberOfObjects;

		this.tab = new double[numberOfObjects + 1][knapsackVolume + 1];

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		// Initialise tab
		for (int row = 0; row <= numberOfObjects; row++) {
			tab[row][0] = 0;
		}

		for (int weight = 0; weight <= knapsackVolume; weight++) {
			tab[0][weight] = 0;
		}

		for (int item = 0; item < numberOfObjects; item++) {
			for (int weight = 1; weight <= knapsackVolume; weight++) {
				if (volumes[item] > weight) {
					tab[item + 1][weight] = tab[item][weight];
				} else {
					tab[item + 1][weight] = Math.max(tab[item][weight],
							tab[item][weight - volumes[item]] + values[item]);
				}
			}
		}

		printResult();

		if (trace) {
			printTrace();
		}
	}

	/**
	 * Prints the result.
	 */
	private void printResult() {
		int remainingVolume = knapsackVolume;
		double revenue = 0;
		output.println("Final del algoritmo. Los resultados son los siguientes:");
		output.print("  Objetos de volumen: ");

		for (int row = numberOfObjects; row >= 1; row--) {
			if (tab[row][remainingVolume] != tab[row - 1][remainingVolume]) {
				if (remainingVolume != knapsackVolume) {
					output.print(", ");
				}
				output.format("%d", volumes[row - 1]);
				remainingVolume -= volumes[row - 1];
				revenue += values[row - 1];
			}
		}
		output.println();
		output.format("  Beneficio: %.0f", revenue);
		output.println();
	}

	/**
	 * Prints the trace.
	 */
	private void printTrace() {
		output.format("\nTraza del algortimo:");
		output.println();
		output.println();
		printFirstLine();
		for (int row = 0; row <= numberOfObjects; row++) {
			printLine(row);
		}
	}

	/**
	 * Prints the first line.
	 */
	private void printFirstLine() {
		output.print("Limite de volumen         |");
		for (int weight = 0; weight <= knapsackVolume; weight++) {
			output.format("%5d", weight);
		}
		output.println();
		output.format("--------------------------|");
		for (int weight = 0; weight <= knapsackVolume; weight++) {
			output.print("-----");
		}
		output.println();
	}

	/**
	 * Prints the line.
	 *
	 * @param row
	 *            the row
	 */
	private void printLine(int row) {
		if (row == 0) {
			output.print("Posicion 0                |");
		} else {
			output.format("Objeto Nº%2d-> v=%2d, b=%4.1f|", row, volumes[row - 1], values[row - 1]);
		}

		for (int weight = 0; weight <= knapsackVolume; weight++) {
			output.format("%5.1f", tab[row][weight]);
		}

		output.println();
	}

}
