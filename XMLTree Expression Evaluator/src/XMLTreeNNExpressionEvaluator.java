import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1; 
import components.utilities.Reporter;
import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 * 
 * @author Hanat Sharif
 * 
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     * 
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
    	 // Declare a variable for the result, initializing it to a new NaturalNumber
        NaturalNumber result = new NaturalNumber2();

        // Check if the current node is a number
        if (exp.label().equals("number")) {
            // Set the result to a new NaturalNumber with the value from the node
            result = new NaturalNumber2(exp.attributeValue("value"));
        } else {
            // Evaluate the children of the expression
            NaturalNumber valueOne = evaluate(exp.child(0));
            NaturalNumber valueTwo = evaluate(exp.child(1));

            // Check the type of the operation and apply it to the two values
            if (exp.label().equals("plus")) {
                valueOne.add(valueTwo);
                result = valueOne; // Use result to hold the final value
            } else if (exp.label().equals("minus")) {
                // Check if the first value is less than the second before subtracting
                if (valueOne.compareTo(valueTwo) < 0) {
                    Reporter.fatalErrorToConsole("Subtraction error");
                } else {
                    valueOne.subtract(valueTwo);
                    result = valueOne; // Use result to hold the final value
                }
            } else if (exp.label().equals("times")) {
                valueOne.multiply(valueTwo);
                result = valueOne; // Use result to hold the final value
            } else if (exp.label().equals("divide")) {
                // Check for division by zero before dividing
                if (valueTwo.isZero()) {
                    Reporter.fatalErrorToConsole("Division error");
                } else {
                    valueOne.divide(valueTwo);
                    result = valueOne; // Use result to hold the final value
                }
            }
        }
        return result;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
