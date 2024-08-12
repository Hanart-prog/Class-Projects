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
public final class Newton4 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     * 
     * @param x
     *            positive number to compute square root of
     * @param e
     *            compute the error value
     * @return estimate of square root
     * 
     */
    private static double sqrt(double x, double e) {
        double r = x; //Initial guess for square root 
        
        //calculate square root estimate. check if input is zero
        if (r == 0) {
            return 0;
        } else {
        	// estimate for the square root
            double root = 0.5 * (x / r + r);
            
           //construct loop to check if estimate is within 0.01 % error   
            while ((((root * root) - r) / r) > (e * e)) {
                root = 0.5 * (r / root + root);
            }
            return root;
        }
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
        double e; // error margin for square root calculations 
       
      

        // Ask the user they wish to calculate a square root and input answer
        out.print("Enter a ε(error) value: "); 
        e = in.nextDouble();
        
        // Asking for the number to calculate the square root
        out.print("Enter a positive number (to quit enter a negative number): ");
    	input = in.nextDouble();
        
       

    	// loop multiple square root calculations while the user number bigger or equal to zero
        while (input >= 0) {
        	// Calculating the square root with the error
        	if (input == 0) {
        		// print out the error message for 0 and skip loop
        		out.println("Error: 0 is undefined, Please Try again");
        	}else {
        		// calculate and display the square root of the number given
            	root = sqrt(input,e);
            	out.println("The square root of " + input + " is " + root);
        	}
        	
        	
        	// Ask if the user wants to enter again
        	out.print("Enter a positive number (to quit enter a negative number): ");
        	input = in.nextDouble(); // reading the user's decision to continue or stop
        	
        } 
        
        out.println("Goodbye!");
        
        //Close input and output streams
        in.close();
        out.close();
        
    }

}