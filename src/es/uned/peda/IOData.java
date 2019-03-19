package es.uned.peda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The Class IOData.
 */
public class IOData {

	/** The number of objects. */
	private int numberOfObjects;

	/** The knapsack volume. */
	private int knapsackVolume;

	/** The values. */
	private double[] values;

	/** The volumes. */
	private int[] volumes;

	/**
	 * Read input.
	 *
	 * @param inputFile
	 *            the input file
	 * @return the problem data
	 */
	public ProblemData readInput(String inputFile) {

		final Path inputPath = Paths.get(inputFile);

		if (!Files.exists(inputPath)) {
			throw new IllegalArgumentException("Input file does not exist");
		}

		try (BufferedReader br = Files.newBufferedReader(inputPath)) {

			int lineNumber = 1;

			numberOfObjects = readInteger(br.readLine(), lineNumber++);

			values = new double[numberOfObjects];
			volumes = new int[numberOfObjects];

			for (int i = 0; i < numberOfObjects; i++) {
				readObject(br.readLine(), lineNumber++);
			}

			knapsackVolume = readInteger(br.readLine(), lineNumber++);

			checkEOF(br.readLine());

			return new ProblemData(values, volumes, numberOfObjects, knapsackVolume);

		} catch (final Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Check EOF.
	 *
	 * @param line
	 *            the line
	 */
	private void checkEOF(String line) {
		if (line != null) {
			throw new IllegalArgumentException("Invalid number of lines in file");
		}
	}

	/**
	 * Read and store object volume and value.
	 *
	 * @param line
	 *            the line
	 * @param lineNumber
	 *            the line number
	 */
	private void readObject(String line, int lineNumber) {

		if (line == null) {
			throw new IllegalArgumentException(
					"Error reading arguments. " + (numberOfObjects - lineNumber + 1) + " more elements were expected");
		}

		final String[] arr = line.split(" +");

		if (arr.length != 2) {
			throw new IllegalArgumentException(
					"Error reading argument. Line " + lineNumber + " contains an invalid number of arguments");
		}

		final int idx = lineNumber - 2;

		volumes[idx] = readInteger(arr[0], lineNumber);
		values[idx] = readDouble(arr[1], lineNumber);

	}

	/**
	 * Read double.
	 *
	 * @param line
	 *            the line
	 * @param lineNumber
	 *            the line number
	 * @return the double
	 */
	private double readDouble(String line, int lineNumber) {
		final double n;

		try {
			n = Double.parseDouble(line);
		} catch (final Exception e) {
			throw new IllegalArgumentException("Error reading value (floating point number) in line " + lineNumber);
		}
		if (n <= 0.0) {
			throw new IllegalArgumentException("Value should be greater than zero (line " + lineNumber + ")");
		}
		return n;

	}

	/**
	 * Read integer.
	 *
	 * @param line
	 *            the line
	 * @param lineNumber
	 *            the line number
	 * @return the int
	 */
	private int readInteger(String line, int lineNumber) {
		final int n;
		try {
			n = Integer.parseInt(line.trim());
		} catch (final Exception e) {
			throw new RuntimeException("Error reading volume (integer number) in line " + lineNumber);
		}
		if (n < 1) {
			if (lineNumber == 1) {
				throw new IllegalArgumentException(
						"Number of objects must be greater than zero (line " + lineNumber + ")");
			} else if (lineNumber == (numberOfObjects + 2)) {
				throw new IllegalArgumentException("Total volume must be greater than zero (line " + lineNumber + ")");
			} else {
				throw new IllegalArgumentException(
						"Object's volume must be greater than zero (line " + lineNumber + ")");
			}
		}
		return n;

	}

	/**
	 * Gets the output.
	 *
	 * @param outputFile
	 *            the output file
	 * @return the output
	 */
	public PrintStream getOutput(String outputFile) {
		final Path outputPath = Paths.get(outputFile);

		if (Files.exists(outputPath)) {
			throw new IllegalArgumentException("Output file already exists");
		}

		try {
			return new PrintStream(Files.newOutputStream(outputPath));
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

}
