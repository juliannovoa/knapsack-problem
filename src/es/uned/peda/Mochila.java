package es.uned.peda;

import java.io.PrintStream;

/**
 * The Class Mochila.
 */
public class Mochila {

	/** The input file. */
	private static String inputFile;

	/** The output file. */
	private static String outputFile = null;

	/** Whether to show the trace. */
	private static boolean trace = false;

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		try {

			parseArguments(args);

			final IOData iodata = new IOData();
			final ProblemData data = iodata.readInput(inputFile);
			final PrintStream output = outputFile == null ? System.out : iodata.getOutput(outputFile);

			final double[] values = data.getValues();
			final int[] volumes = data.getVolumes();
			final int knapsackVolume = data.getKnapsackVolume();
			final int numberOfObjects = data.getNumberOfObjects();

			new KnapsackAlgorithm(knapsackVolume, numberOfObjects, values, volumes, trace, output).run();

		} catch (final Exception e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		}

	}

	/**
	 * Parses the arguments.
	 *
	 * @param args
	 *            the args
	 */
	private static void parseArguments(String[] args) {
		// Check if user asks for help
		for (final String arg : args) {
			if (arg.equals("-h")) {
				help();
				System.exit(0);
			}
		}

		// Otherwise, run the algorithm
		switch (args.length) {
		case 1:
			if (args[0].equals("-t")) {
				error();
				System.exit(-1);
			}
			inputFile = args[0];
			break;
		case 2:
			if (args[0].equals("-t")) {
				trace = true;
				inputFile = args[1];
			} else {
				inputFile = args[0];
				outputFile = args[1];
			}
			break;
		case 3:
			if (!args[0].equals("-t")) {
				error();
				System.exit(-1);
			}
			trace = true;
			inputFile = args[1];
			outputFile = args[2];
			break;
		default:
			error();
			System.exit(-1);
		}
	}

	/**
	 * Error.
	 */
	public static void error() {
		System.err.println("Invalid arguments");
		help();
	}

	/**
	 * Help.
	 */
	public static void help() {
		System.out.println("Sintaxis:\n");
		System.out.println("mochila [-t]  [-h]  [fichero_entrada]  [fichero_salida]\n");
		System.out.println("-t                  Traza la aplicacion del algoritmo a los datos\n");
		System.out.println("-h                  Muestra esta ayuda\n");
		System.out.println("fichero_entrada     Nombre del fichero de entrada. Si no existe dara error\n");
		System.out.println("fichero_salida      Nombre del fichero de salida que se creara para almacenar");
		System.out.println("                    la salida. Si falta este argumento se muestra el resultado");
		System.out.println("                    por la salida estandar\n");
	}

}
