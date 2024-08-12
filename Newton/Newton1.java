import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Hanat Sharif
 *
 */
public final class Newton1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton1() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     * 
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        double r = x; // Initial guess for square root
        
        //calculate square root estimate
         
        double root = 0.5 * (x / r + r);
        
         //construct loop to check if estimate is within 0.01 % error
         
        while ((((root * root) - r) / r) > (0.0001 * 0.0001)) {
            root = 0.5 * (r / root + root);
        }
        return root;
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

        double input; // storing positive number by the user
        double root; // storing the calculated square root of the input
        String str; // users response to continue or stop

       
        // Ask the user they wish to calculate a square root and input answer
        out.println("Do you want to calculate a square root 'y' or 'n'?");
        str = in.nextLine();

        
         // loop to process each new number for square root calculations
        while (str.equals("y")) {
        	out.print("Enter a positive number: ");
        	input = in.nextDouble();
        	// calculate and display the square root of the number given
        	root = sqrt(input);
        	out.println("The square root of " + input + " is " + root);
        	
        	// Ask if the user wants to enter again
        	out.println("Do you wish to continue 'y' or 'n'?");
        	str = in.nextLine(); // reading user's decision to continue or stop
        	
        } 
        
        out.println("Goodbye!");
        
        //Closing input and output streams
        in.close();
        out.close();
        
    }

}
