import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Utilities that could be used with RSA cryptosystems.
 * 
 * @author Hanat Sharif
 * 
 */
public final class CryptoUtilites {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CryptoUtilites() {
    }

    /**
     * Useful constant, not a magic number: 3.
     */
    private static final int THREE = 3;

    /**
     * Pseudo-random number generator.
     */
    private static final Random GENERATOR = new Random1L();

    /**
     * Returns a random number uniformly distributed in the interval [0, n].
     * 
     * @param n
     *            top end of interval
     * @return random number in interval
     * @requires n > 0
     * @ensures <pre>
     * randomNumber = [a random number uniformly distributed in [0, n]]
     * </pre>
     */
    public static NaturalNumber randomNumber(NaturalNumber n) {
        assert !n.isZero() : "Violation of: n > 0";
        final int base = 10;
        NaturalNumber result;
        int d = n.divideBy10();
        if (n.isZero()) {
            /*
             * Incoming n has only one digit and it is d, so generate a random
             * number uniformly distributed in [0, d]
             */
            int x = (int) ((d + 1) * GENERATOR.nextDouble());
            result = new NaturalNumber2(x);
            n.multiplyBy10(d);
        } else {
            /*
             * Incoming n has more than one digit, so generate a random number
             * (NaturalNumber) uniformly distributed in [0, n], and another
             * (int) uniformly distributed in [0, 9] (i.e., a random digit)
             */
            result = randomNumber(n);
            int lastDigit = (int) (base * GENERATOR.nextDouble());
            result.multiplyBy10(lastDigit);
            n.multiplyBy10(d);
            if (result.compareTo(n) > 0) {
                /*
                 * In this case, we need to try again because generated number
                 * is greater than n; the recursive call's argument is not
                 * "smaller" than the incoming value of n, but this recursive
                 * call has no more than a 90% chance of being made (and for
                 * large n, far less than that), so the probability of
                 * termination is 1
                 */
                result = randomNumber(n);
            }
        }
        return result;
    }

    /**
     * Finds the greatest common divisor of n and m.
     * 
     * @param n
     *            one number
     * @param m
     *            the other number
     * @updates n
     * @clears m
     * @ensures n = [greatest common divisor of #n and #m]
     */
    public static void reduceToGCD(NaturalNumber n, NaturalNumber m) {

        /*
         * Use Euclid's algorithm; in pseudocode: if m = 0 then GCD(n, m) = n
         * else GCD(n, m) = GCD(m, n mod m)
         */

    	if (!m.isZero()) {
    		//'n' becomes quotient, remainder is n mod m
            NaturalNumber remainder = n.divide(m);
            n.copyFrom(remainder);
            //Recursive: with 'n' and 'm' being swapped
            reduceToGCD(m, remainder);
            n.transferFrom(m);
        }
 
    }

    /**
     * Reports whether n is even.
     * 
     * @param n
     *            the number to be checked
     * @return true iff n is even
     * @ensures isEven = (n mod 2 = 0)
     */
    public static boolean isEven(NaturalNumber n) {
    	//Creating a temp copy for n to avoid altering its value 
        NaturalNumber temp = n.newInstance();
        temp.copyFrom(n);
        //initializing NaturalNumber to represent '2' for division
        NaturalNumber two = n.newInstance();
        two.setFromInt(2);
        NaturalNumber remainder = temp.divide(two);
        boolean isEven = remainder.isZero();
        return isEven;
    }

    /**
     * Updates n to its p-th power modulo m.
     * 
     * @param n
     *            number to be raised to a power
     * @param p
     *            the power
     * @param m
     *            the modulus
     * @updates n
     * @requires m > 1
     * @ensures n = #n ^ (p) mod m
     */
    public static void powerMod(NaturalNumber n, NaturalNumber p,
            NaturalNumber m) {
        assert m.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: m > 1";

        /*
         * Use the fast-powering algorithm as previously discussed in class,
         * with the additional feature that every multiplication is followed
         * immediately by "reducing the result modulo m"
         */
        NaturalNumber num = new NaturalNumber2(n) ;
        NaturalNumber pow = new NaturalNumber2(p);
        NaturalNumber one = new NaturalNumber2(1);
        NaturalNumber two = new NaturalNumber2(2);
        
        if (pow.isZero ()) {
        	// any number to the power of 0 is 1
        	n. copyFrom(one);
        } else {
        	if (isEven (pow)) {
        		//halve the power and recursively call powerMod
        		pow.divide(two); 
        		powerMod(n,pow,m); 
        		num.copyFrom(n) ;
        } else {
        	//decrement the power by 1 to make it even and continue
        	pow.decrement();
        	num.copyFrom(n);
        	powerMod(n,pow,m);
        }
        n. multiply (num);
        NaturalNumber result = n. divide(m);
        n. copyFrom (result);
        }
    }

    /**
     * Reports whether w is a "witness" that n is composite, in the sense that
     * either it is a square root of 1 (mod n), or it fails to satisfy the
     * criterion for primality from Fermat's theorem.
     * 
     * @param w
     *            witness candidate
     * @param n
     *            number being checked
     * @return true iff w is a "witness" that n is composite
     * @requires n > 2  and  1 < w < n - 1
     * @ensures <pre>
     * isWitnessToCompositeness =
     *     (w ^ 2 mod n = 1)  or  (w ^ (n-1) mod n /= 1)
     * </pre>
     */
    public static boolean isWitnessToCompositeness(NaturalNumber w,
            NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(2)) > 0 : "Violation of: n > 2";
        assert (new NaturalNumber2(1)).compareTo(w) < 0 : "Violation of: 1 < w";
        n.decrement();
        assert w.compareTo(n) < 0 : "Violation of: w < n - 1";
        n.increment();
        
        NaturalNumber nMinusOne = n.newInstance();
        nMinusOne.copyFrom(n);
        nMinusOne.decrement();
        NaturalNumber wCopy = w.newInstance();
        wCopy.copyFrom(w);

        //Check if 'w' squared modulo 'n' = 1, indicating a potential witness to compositeness
        NaturalNumber two = new NaturalNumber2(2);
        powerMod(wCopy, two, n);
        boolean isSquareRootOfOne = wCopy.compareTo(new NaturalNumber2(1)) == 0;

        
        wCopy.copyFrom(w); // Reseting for next test
        
        //Applying fermat's theorem to test if 'w' raised to n-1 modulo 'n' is not 1
        powerMod(wCopy, nMinusOne, n);
        boolean doesNotSatisfyFermatsTheorem = wCopy.compareTo(new NaturalNumber2(1)) != 0;

        
        return isSquareRootOfOne || doesNotSatisfyFermatsTheorem;
    }

    /**
     * Reports whether n is a prime; may be wrong with "low" probability.
     * 
     * @param n
     *            number to be checked
     * @return true means n is very likely prime; false means n is definitely
     *         composite
     * @requires n > 1
     * @ensures <pre>
     * isPrime1 = [n is a prime number, with small probability of error
     *         if it is reported to be prime, and no chance of error if it is
     *         reported to be composite]
     * </pre>
     */
    public static boolean isPrime1(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";
        boolean isPrime;
        if (n.compareTo(new NaturalNumber2(THREE)) <= 0) {
            /*
             * 2 and 3 are primes
             */
            isPrime = true;
        } else if (isEven(n)) {
            /*
             * evens are composite
             */
            isPrime = false;
        } else {
            /*
             * odd n >= 5: simply check whether 2 is a witness that n is
             * composite (which works surprisingly well :-)
             */
            isPrime = !isWitnessToCompositeness(new NaturalNumber2(2), n);
        }
        return isPrime;
    }

    /**
     * Reports whether n is a prime; may be wrong with "low" probability.
     * 
     * @param n
     *            number to be checked
     * @return true means n is very likely prime; false means n is definitely
     *         composite
     * @requires n > 1
     * @ensures <pre>
     * isPrime2 = [n is a prime number, with small probability of error
     *         if it is reported to be prime, and no chance of error if it is
     *         reported to be composite]
     * </pre>
     */
    public static boolean isPrime2(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";

        /*
         * Use the ability to generate random numbers (provided by the
         * randomNumber method above) to generate several witness candidates --
         * say, 10 to 50 candidates -- guessing that n is prime only if none of
         * these candidates is a witness to n being composite (based on fact #3
         * as described in the project description); use the code for isPrime1
         * as a guide for how to do this, and pay attention to the requires
         * clause of isWitnessToCompositeness
         */

        boolean isPrime = true; 
        //Directly treat numbers less than or equal to 3 as prime and even as compsitie
        if (n.compareTo(new NaturalNumber2(THREE)) <= 0) {
            isPrime = true; 
        } else if (isEven(n)) {
            isPrime = false; 
        } else {
        	//Prepare for witness testing on odd numbers greater than 3
            NaturalNumber nMinusTwo = n.newInstance();
            nMinusTwo.copyFrom(n);
            nMinusTwo.subtract(new NaturalNumber2(2)); 

            int iterations = 10; 
            NaturalNumber w = new NaturalNumber2(1); 
            for (int i = 0; i < iterations && isPrime; i++) {
                w = randomNumber(nMinusTwo);
                //Making sure w is within the valid range
                w.add(new NaturalNumber2(2)); 
                
                //If 'w' is witness to the compositeness of 'n'
                if (!(w.compareTo(new NaturalNumber2(2)) < 0 || w.compareTo(nMinusTwo) > 0)) {
                    if (isWitnessToCompositeness(w, n)) {
                        isPrime = false; 
                    }
                }
            }
        }

        return isPrime; 
    }


    /**
     * Generates a likely prime number at least as large as some given number.
     * 
     * @param n
     *            minimum value of likely prime
     * @updates n
     * @requires n > 1
     * @ensures n >= #n  and  [n is very likely a prime number]
     */
    public static void generateNextLikelyPrime(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";

        /*
         * Use isPrime2 to check numbers, starting at n and increasing through
         * the odd numbers only (why?), until n is likely prime
         */

        NaturalNumber two = new NaturalNumber2(2);
        NaturalNumber three = new NaturalNumber2(3);

        //Update 'n' to 2 or 3 if it less than 2, handling the smallest primes
        if (n.compareTo(two) < 0) {
            n.copyFrom(two); 
        } else if (n.compareTo(two) == 0) {
            n.copyFrom(three); 
        } else {
            // Making sure 'n' is odd by adding one for even and two to start checking for the next odd number
            if (isEven(n)) {
                n.increment(); 
            } else {
                n.add(two); 
            }

            //Looping through odd numbers, starting from 'n' until a prime is found
            while (!isPrime2(n)) {
                n.add(two); 
            }
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

        /*
         * Sanity check of randomNumber method -- just so everyone can see how
         * it might be "tested"
         */
        final int testValue = 17;
        final int testSamples = 100000;
        NaturalNumber test = new NaturalNumber2(testValue);
        int[] count = new int[testValue + 1];
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < testSamples; i++) {
            NaturalNumber rn = randomNumber(test);
            assert rn.compareTo(test) <= 0 : "Help!";
            count[rn.toInt()]++;
        }
        for (int i = 0; i < count.length; i++) {
            out.println("count[" + i + "] = " + count[i]);
        }
        out.println("  expected value = " + (double) testSamples
                / (double) (testValue + 1));

        /*
         * Check user-supplied numbers for primality, and if a number is not
         * prime, find the next likely prime after it
         */
        while (true) {
            out.print("n = ");
            NaturalNumber n = new NaturalNumber2(in.nextLine());
            if (n.compareTo(new NaturalNumber2(2)) < 0) {
                out.println("Bye!");
                break;
            } else {
                if (isPrime1(n)) {
                    out.println(n + " is probably a prime number"
                            + " according to isPrime1.");
                } else {
                    out.println(n + " is a composite number"
                            + " according to isPrime1.");
                }
                if (isPrime2(n)) {
                    out.println(n + " is probably a prime number"
                            + " according to isPrime2.");
                } else {
                    out.println(n + " is a composite number"
                            + " according to isPrime2.");
                    generateNextLikelyPrime(n);
                    out.println("  next likely prime is " + n);
                }
            }
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}