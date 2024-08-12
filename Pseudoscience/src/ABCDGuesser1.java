import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Hanat Sharif
 *
 */
public final class ABCDGuesser1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser1() {
    }
    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     * 
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
    	double num = 0; // initialize num to zero
        
    	// Looping until valid number is entered
    	while (num <= 0) {
        	out.print("Please enter the constant μ (mu) to approximate:");
            String input = in.nextLine();
            // Checking if the string can be parsed as a double
            if (FormatChecker.canParseDouble(input)) {
                num = Double.parseDouble(input);
            }
            // if the parsed number is not positive it'll ask the user again
            if (num <= 0) {
                out.println("Invalid input. Please enter a positive real number.");
            }
        }
        return num;
    }

      
    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     * 
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @param variable
     *            the name of the variable ("w", "x", "y", "z")          
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in, SimpleWriter out, String variable) {
    	 double num = 0; // initialize num to zero
    	 
    	// Loop until the correct number is entered, continues as long as the number is non-positive or close to 1.0
         while (num <= 0 || Math.abs(num - 1.0) < 0.0000001) {
             out.print("Enter a positive real number not equal to 1.0 (" + variable + "): ");
             String input = in.nextLine();
             
             // Check if the input string can be parsed as double
             if (FormatChecker.canParseDouble(input)) {
                 num = Double.parseDouble(input);
             }
             
             // if the parsed number is still too close to 1.0 or not positive it'll ask to re-enter again
             if (num <= 0 || Math.abs(num - 1.0) < 0.0000001) {
                 out.println("Invalid input. Please enter a positive real number not equal to 1.0.");
             }
         }
         return num;
     }
    /**
     * Finds the best set of exponents for the given numbers to approximate the
     * constant mu. Returns an array of the best exponents.
     * 
     * @param mu The constant to approximate
     * @param w, x, y, z The four user numbers
     * @return array of four exponents that best approximate the constant mu
     */
    private static double[] findBestExponents(double mu, double w, double x, double y, double z) {
        // Initialization of variables for the best exponents and smallest error
    	double[] bestExponents = new double[4];
        double bestError = Double.MAX_VALUE;

        // Possible values for the exponents
        double[] exponents = {-5, -4, -3, -2, -1, -0.5, -0.333, -0.25, 0, 0.25, 0.333, 0.5, 1, 2, 3, 4, 5};
        
        // Nested loops to try all the combinations of exponents
        int i = 0;
        while (i < exponents.length) {
            double a = exponents[i];
            int j = 0;
            while (j < exponents.length) {
                double b = exponents[j];
                int k = 0;
                while (k < exponents.length) {
                    double c = exponents[k];
                    int l = 0;
                    while (l < exponents.length) {
                        double d = exponents[l];
                        
                        // calculate approximation and error
                        double approximation = Math.pow(w, a) * Math.pow(x, b) * Math.pow(y, c) * Math.pow(z, d);
                        double error = Math.abs(approximation - mu) / mu;

                        // Update the best exponent if the smaller error is found
                        if (error < bestError) {
                            bestError = error;
                            bestExponents[0] = a;
                            bestExponents[1] = b;
                            bestExponents[2] = c;
                            bestExponents[3] = d;
                        }
                        l++;
                    }
                    k++;
                }
                j++;
            }
            i++;
        }

        return bestExponents;
    }


    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        
        // Asking the user for the mu to approximate it and store it
        double mu = getPositiveDouble(in, out);

        out.println("Enter four numbers (each a positive real number not equal to 1)");

        // Asking the user for the four different positive numbers 
        double w = getPositiveDoubleNotOne(in, out, "w");
        double x = getPositiveDoubleNotOne(in, out, "x");
        double y = getPositiveDoubleNotOne(in, out, "y");
        double z = getPositiveDoubleNotOne(in, out, "z");

        // Finding the best exponents for the entered numbers
        double[] bestExponents = findBestExponents(mu, w, x, y, z);

        // Calculating the approximation of mu from the best exponents
        double approximation = Math.pow(w, bestExponents[0]) * Math.pow(x, bestExponents[1]) * Math.pow(y, bestExponents[2]) * Math.pow(z, bestExponents[3]);
        // Calculating the relative error from the approximation
        double relativeError = Math.abs(approximation - mu) / mu * 100;

        // Printing out the results for everything
        out.println("Best exponents: a=" + bestExponents[0] + ", b=" + bestExponents[1] + ", c=" + bestExponents[2] + ", d=" + bestExponents[3]);
        out.println("Approximation of μ: " + approximation);
        out.println("Relative error: " + relativeError + "%");
        
        //Close input and output streams
        
        in.close();
        out.close();
    }

}
