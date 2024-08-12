import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Hanat Sharif
 *
 */
public class CryptoUtilitiesTest {

	/*
	 * Tests of reduceToGCD
	 */

	/* Verify GCD(0, 0) correctly handles edge case, expecting GCD to be 0*/
	@Test
	public void testReduceToGCD_0_0() {
		NaturalNumber n = new NaturalNumber2(0);
		NaturalNumber nExpected = new NaturalNumber2(0);
		NaturalNumber m = new NaturalNumber2(0);
		NaturalNumber mExpected = new NaturalNumber2(0);
		CryptoUtilites.reduceToGCD(n, m);
		assertEquals(nExpected, n);
		assertEquals(mExpected, m);
	}
	 
	/* Check GCD(30, 21) calculates correct GCD of 3, testing algorithm accuracy */
	@Test
	public void testReduceToGCD_30_21() {
		NaturalNumber n = new NaturalNumber2(30);
		NaturalNumber nExpected = new NaturalNumber2(3);
		NaturalNumber m = new NaturalNumber2(21);
		NaturalNumber mExpected = new NaturalNumber2(0);
		CryptoUtilites.reduceToGCD(n, m);
		assertEquals(nExpected, n);
		assertEquals(mExpected, m);
	}
	
	/* Test GCD(92, 12) for numbers with different magnitudes, expecting GCD of 4 */
	@Test
	public void testReduceToGCD_92_12() {
		NaturalNumber n = new NaturalNumber2(92);
		NaturalNumber nExpected = new NaturalNumber2(4);
		NaturalNumber m = new NaturalNumber2(12);
		NaturalNumber mExpected = new NaturalNumber2(0);
		CryptoUtilites.reduceToGCD(n, m);
		assertEquals(nExpected, n);
		assertEquals(mExpected, m);
	}

	/* Ensure GCD handles large numbers (92366, 123456) efficiently expecting GCD of 2*/
	@Test
	public void testReduceToGCD_92366_123456() {
		NaturalNumber n = new NaturalNumber2(92366);
		NaturalNumber nExpected = new NaturalNumber2(2);
		NaturalNumber m = new NaturalNumber2(123456);
		NaturalNumber mExpected = new NaturalNumber2(0);
		CryptoUtilites.reduceToGCD(n, m);
		assertEquals(nExpected, n);
		assertEquals(mExpected, m);
	}

	/* Test GCD with first number as 0 (0, 92), expecting GCD equals the second number*/
	@Test
	public void testReduceToGCD_0_92() {
		NaturalNumber n = new NaturalNumber2(0);
		NaturalNumber nExpected = new NaturalNumber2(92);
		NaturalNumber m = new NaturalNumber2(92);
		NaturalNumber mExpected = new NaturalNumber2(0);
		CryptoUtilites.reduceToGCD(n, m);
		assertEquals(nExpected, n);
		assertEquals(mExpected, m);
	}
	
	/* Verify GCD(12, 1) equals 1, testing handling of one as input */
	@Test
	public void testReduceToGCD_12_1() {
		NaturalNumber n = new NaturalNumber2(12);
		NaturalNumber nExpected = new NaturalNumber2(1);
		NaturalNumber m = new NaturalNumber2(1);
		NaturalNumber mExpected = new NaturalNumber2(0);
		CryptoUtilites.reduceToGCD(n, m);
		assertEquals(nExpected, n);
		assertEquals(mExpected, m);
	}

	/*
	 * Tests of isEven
	 */
	
	/* Confirm isEven(0) correctly identifies 0 as even*/
	@Test
	public void testIsEven_0() {
		NaturalNumber n = new NaturalNumber2(0);
		NaturalNumber nExpected = new NaturalNumber2(0);
		boolean result = CryptoUtilites.isEven(n);
		assertEquals(nExpected, n);
		assertEquals(true, result);
	}
	
	/* Test isEven(1) recognizes 1 as odd */
	@Test
	public void testIsEven_1() {
		NaturalNumber n = new NaturalNumber2(1);
		NaturalNumber nExpected = new NaturalNumber2(1);
		boolean result = CryptoUtilites.isEven(n);
		assertEquals(nExpected, n);
		assertEquals(false, result);
	}
	
	/* Ensure isEven(2) accurately identifies smallest even number */
	@Test
	public void testIsEven_2() {
		NaturalNumber n = new NaturalNumber2(2);
		NaturalNumber nExpected = new NaturalNumber2(2);
		boolean result = CryptoUtilites.isEven(n);
		assertEquals(nExpected, n);
		assertEquals(true, result);
	}
	
	/* Verify isEven on large even number (3684826844), checking method's scalability*/
	@Test
	public void testIsEven_3684826844() {
		NaturalNumber n = new NaturalNumber2("3684826844");
		NaturalNumber nExpected = new NaturalNumber2("3684826844");
		boolean result = CryptoUtilites.isEven(n);
		assertEquals(nExpected, n);
		assertEquals(true, result);
	}
	
	/* Test isEven with large odd number (3684826833) for accuracy on large inputs */
	@Test
	public void testIsEven_3684826833() {
		NaturalNumber n = new NaturalNumber2("3684826833");
		NaturalNumber nExpected = new NaturalNumber2("3684826833");
		boolean result = CryptoUtilites.isEven(n);
		assertEquals(nExpected, n);
		assertEquals(false, result);
	}

	/*
	 * Tests of powerMod
	 */
	
	/* Check powerMod(0, 0, 2) handles zero base and exponent correctly */
	@Test
	public void testPowerMod_0_0_2() {
		NaturalNumber n = new NaturalNumber2(0);
		NaturalNumber nExpected = new NaturalNumber2(1);
		NaturalNumber p = new NaturalNumber2(0);
		NaturalNumber pExpected = new NaturalNumber2(0);
		NaturalNumber m = new NaturalNumber2(2);
		NaturalNumber mExpected = new NaturalNumber2(2);
		CryptoUtilites.powerMod(n, p, m);
		assertEquals(nExpected, n);
		assertEquals(pExpected, p);
		assertEquals(mExpected, m);
	}
	
	/* Verify powerMod(17, 18, 19) calculates correctly in prime modular space*/
	@Test
	public void testPowerMod_17_18_19() {
		NaturalNumber n = new NaturalNumber2(17);
		NaturalNumber nExpected = new NaturalNumber2(1);
		NaturalNumber p = new NaturalNumber2(18);
		NaturalNumber pExpected = new NaturalNumber2(18);
		NaturalNumber m = new NaturalNumber2(19);
		NaturalNumber mExpected = new NaturalNumber2(19);
		CryptoUtilites.powerMod(n, p, m);
		assertEquals(nExpected, n);
		assertEquals(pExpected, p);
		assertEquals(mExpected, m);
	}
	
	/* Test powerMod(2, 8, 2) for efficiency with small, even modulus */
	@Test
	public void testPowerMod_2_8_2() {
		NaturalNumber n = new NaturalNumber2(2);
		NaturalNumber nExpected = new NaturalNumber2(0);
		NaturalNumber p = new NaturalNumber2(8);
		NaturalNumber pExpected = new NaturalNumber2(8);
		NaturalNumber m = new NaturalNumber2(2);
		NaturalNumber mExpected = new NaturalNumber2(2);
		CryptoUtilites.powerMod(n, p, m);
		assertEquals(nExpected, n);
		assertEquals(pExpected, p);
		assertEquals(mExpected, m);
	}
	
	/* Test powerMod(3, 1, 2) verifies handling of unit exponent, expecting mod result of 1 */
	@Test
	public void testPowerMod_3_1_2() {
		NaturalNumber n = new NaturalNumber2(3);
		NaturalNumber nExpected = new NaturalNumber2(1);
		NaturalNumber p = new NaturalNumber2(1);
		NaturalNumber pExpected = new NaturalNumber2(1);
		NaturalNumber m = new NaturalNumber2(2);
		NaturalNumber mExpected = new NaturalNumber2(2);
		CryptoUtilites.powerMod(n, p, m);
		assertEquals(nExpected, n);
		assertEquals(pExpected, p);
		assertEquals(mExpected, m);
	}

	/*
	 * Tests of isWitnessToCompositeness
	 */

	/* Verify isWitnessToCompositeness with (6, 9), checking detection of compositeness */
	@Test
	public void testWitnessToComp_6_9() {
		NaturalNumber w = new NaturalNumber2(6);
		NaturalNumber wExpected = new NaturalNumber2(6);
		NaturalNumber n = new NaturalNumber2(9);
		NaturalNumber nExpected = new NaturalNumber2(9);
		boolean result = CryptoUtilites.isWitnessToCompositeness(w, n);
		assertEquals(wExpected, w);
		assertEquals(nExpected, n);
		assertEquals(nExpected, n);
		assertEquals(true, result);
	}

	/* Check isWitnessToCompositeness with prime (7, 12) to ensure no false positives */
	@Test
	public void testWitnessToComp_7_12() {
		NaturalNumber w = new NaturalNumber2(7);
		NaturalNumber wExpected = new NaturalNumber2(7);
		NaturalNumber n = new NaturalNumber2(12);
		NaturalNumber nExpected = new NaturalNumber2(12);
		boolean result = CryptoUtilites.isWitnessToCompositeness(w, n);
		assertEquals(wExpected, w);
		assertEquals(nExpected, n);
		assertEquals(nExpected, n);
		assertEquals(true, result);
	}
	
	/* Confirm isWitnessToCompositeness correctly identifies (3, 5) as not a witness, testing prime */
	@Test
	public void testWitnessToComp_3_5() {
		NaturalNumber w = new NaturalNumber2(3);
		NaturalNumber wExpected = new NaturalNumber2(3);
		NaturalNumber n = new NaturalNumber2(5);
		NaturalNumber nExpected = new NaturalNumber2(5);
		boolean result = CryptoUtilites.isWitnessToCompositeness(w, n);
		assertEquals(wExpected, w);
		assertEquals(nExpected, n);
		assertEquals(nExpected, n);
		assertEquals(false, result);
	}
	
	/* Test isWitnessToCompositeness with (2, 13) to verify prime recognition without false witness */
	@Test
	public void testWitnessToComp_2_13() {
		NaturalNumber w = new NaturalNumber2(2);
		NaturalNumber wExpected = new NaturalNumber2(2);
		NaturalNumber n = new NaturalNumber2(13);
		NaturalNumber nExpected = new NaturalNumber2(13);
		boolean result = CryptoUtilites.isWitnessToCompositeness(w, n);
		assertEquals(wExpected, w);
		assertEquals(nExpected, n);
		assertEquals(nExpected, n);
		assertEquals(false, result);
	}
	
	/* Ensure isWitnessToCompositeness detects compositeness in (5, 16), a basic composite case */
	@Test
	public void testWitnessToComp_5_16() {
		NaturalNumber w = new NaturalNumber2(5);
		NaturalNumber wExpected = new NaturalNumber2(5);
		NaturalNumber n = new NaturalNumber2(16);
		NaturalNumber nExpected = new NaturalNumber2(16);
		boolean result = CryptoUtilites.isWitnessToCompositeness(w, n);
		assertEquals(wExpected, w);
		assertEquals(nExpected, n);
		assertEquals(nExpected, n);
		assertEquals(true, result);
	}

	/*
	 * Tests for isPrime1
	 */
	
	/* Verify isPrime1(2) identifies 2 as prime, testing the method's fundamental correctness */
	@Test
	public void isPrime1_2() {
		NaturalNumber n = new NaturalNumber2(2);
		NaturalNumber nExpected = new NaturalNumber2(2);
		boolean result = CryptoUtilites.isPrime1(n);
		assertEquals(nExpected, n);
		assertEquals(true, result);
	}

	/* Confirm isPrime1(3) accurately detects a prime number, essential for validating checks */
	@Test
	public void isPrime1_3() {
		NaturalNumber n = new NaturalNumber2(3);
		NaturalNumber nExpected = new NaturalNumber2(3);
		boolean result = CryptoUtilites.isPrime1(n);
		assertEquals(nExpected, n);
		assertEquals(true, result);
	}
	
	/* Test isPrime1(4) to ensure detection of simple composite, verifying basic algorithm correctness*/
	@Test
	public void isPrime1_4() {
		NaturalNumber n = new NaturalNumber2(4);
		NaturalNumber nExpected = new NaturalNumber2(4);
		boolean result = CryptoUtilites.isPrime1(n);
		assertEquals(nExpected, n);
		assertEquals(false, result);
	}
	
	/* Verify isPrime1(5) correctly classifies 5 as prime, checking accurate */
	@Test
	public void isPrime1_5() {
	NaturalNumber n = new NaturalNumber2(5);
	NaturalNumber nExpected = new NaturalNumber2(5);
	boolean result = CryptoUtilites.isPrime1(n);
	assertEquals(nExpected, n);
	assertEquals(true, result);
	}
	
	/* Confirm isPrime1(9) identifies a composite square number correctly, testing algorithm's case handling */
	@Test
	public void isPrime1_9() {
		NaturalNumber n = new NaturalNumber2(9);
		NaturalNumber nExpected = new NaturalNumber2(9);
		boolean result = CryptoUtilites.isPrime1(n);
		assertEquals(nExpected, n);
		assertEquals(false, result);
	}
	
	/* Test isPrime1(55) for composite detection, ensuring correction */
	@Test
	public void isPrime1_55() {
		NaturalNumber n = new NaturalNumber2(55);
		NaturalNumber nExpected = new NaturalNumber2(55);
		boolean result = CryptoUtilites.isPrime1(n);
		assertEquals(nExpected, n);
		assertEquals(false, result);
	}
	
	/* Verify isPrime1(1234) accurately classifies large composite, testing method's scalability */
	@Test
	public void isPrime1_1234() {
		NaturalNumber n = new NaturalNumber2(1234);
		NaturalNumber nExpected = new NaturalNumber2(1234);
		boolean result = CryptoUtilites.isPrime1(n);
		assertEquals(nExpected, n);
		assertEquals(false, result);
	}
	
	/* Confirm isPrime1(100001) for composite detection, challenging checks with large numbers */
	@Test
	public void isPrime1_100001() {
		NaturalNumber n = new NaturalNumber2(100001);
		NaturalNumber nExpected = new NaturalNumber2(100001);
		boolean result = CryptoUtilites.isPrime1(n);
		assertEquals(nExpected, n);
		assertEquals(false, result);
	}
	
	/* Verify isPrime1(7919) recognizes large prime, testing efficiency and accuracy on larger inputs */
	@Test
	public void isPrime1_7919() {
		NaturalNumber n = new NaturalNumber2(7919);
		NaturalNumber nExpected = new NaturalNumber2(7919);
		boolean result = CryptoUtilites.isPrime1(n);
		assertEquals(nExpected, n);
		assertEquals(true, result);
	}
	
	/* Test isPrime1(6700417) identifies known large prime, validating method's effectiveness on prime numbers */
	@Test
	public void isPrime1_6700417() {
	NaturalNumber n = new NaturalNumber2(6700417);
	NaturalNumber nExpected = new NaturalNumber2(6700417);
	boolean result = CryptoUtilites.isPrime1(n);
	assertEquals(nExpected, n);
	assertEquals(true, result);
	}
	
	/* Confirm isPrime1(6700418) correctly detects large composite, ensuring algorithm's works with large numbers */
	@Test
	public void isPrime1_6700418() {
		NaturalNumber n = new NaturalNumber2(6700418);
		NaturalNumber nExpected = new NaturalNumber2(6700418);
		boolean result = CryptoUtilites.isPrime1(n);
		assertEquals(nExpected, n);
		assertEquals(false, result);
	}

	/*
	 * Tests for isPrime2
	 */
	
	/* Test isPrime2(2) to verify basic prime detection, essential for validating checks */
	@Test
	public void isPrime2_2() {
		NaturalNumber n = new NaturalNumber2(2);
		NaturalNumber nExpected = new NaturalNumber2(2);
		boolean result = CryptoUtilites.isPrime2(n);
		assertEquals(nExpected, n);
		assertEquals(true, result);
	}
	
	/* Confirm isPrime2(3) correctly classifies prime, testing accurate method */
	@Test
	public void isPrime2_3() {
		NaturalNumber n = new NaturalNumber2(3);
		NaturalNumber nExpected = new NaturalNumber2(3);
		boolean result = CryptoUtilites.isPrime2(n);
		assertEquals(nExpected, n);
		assertEquals(true, result);
	}
	
	/* Check isPrime2(4) ensures composite recognition, verifying basic correct approach */
	@Test
		public void isPrime2_4() {
		NaturalNumber n = new NaturalNumber2(4);
		NaturalNumber nExpected = new NaturalNumber2(4);
		boolean result = CryptoUtilites.isPrime2(n);
		assertEquals(nExpected, n);
		assertEquals(false, result);
	}
	
	/* Verify isPrime2(5) for prime detection, assessing accuracy and reliability test */
	@Test
	public void isPrime2_5() {
		NaturalNumber n = new NaturalNumber2(5);
		NaturalNumber nExpected = new NaturalNumber2(5);
		boolean result = CryptoUtilites.isPrime2(n);
		assertEquals(nExpected, n);
		assertEquals(true, result);
	}

	/*
	 * Tests for generateNextLikelyPrime
	 */
	
	/* Test generateNextLikelyPrime(2) next prime, testing algorithm's basic functionality from a small start */
	@Test
	public void generateNextLikelyPrime_2() {
		NaturalNumber n = new NaturalNumber2(2);
		NaturalNumber nExpected = new NaturalNumber2(3);
		CryptoUtilites.generateNextLikelyPrime(n);
		assertEquals(nExpected, n);
	
	}
	
	/* Confirm generateNextLikelyPrime(3) finds next prime correctly, checking proper prime identification */
	@Test
	public void generateNextLikelyPrime_3() {
		NaturalNumber n = new NaturalNumber2(3);
		NaturalNumber nExpected = new NaturalNumber2(5);
		CryptoUtilites.generateNextLikelyPrime(n);
		assertEquals(nExpected, n);
	}

	/* Check generateNextLikelyPrime(4) accurately jumps to next prime, verifying handling composite starting points */
	@Test
	public void generateNextLikelyPrime_4() {
		NaturalNumber n = new NaturalNumber2(4);
		NaturalNumber nExpected = new NaturalNumber2(5);
		CryptoUtilites.generateNextLikelyPrime(n);
		assertEquals(nExpected, n);
	}
	
	/* Verify generateNextLikelyPrime(5) advances to next prime (7), ensuring correct identification past an immediate prime */
	@Test
	public void generateNextLikelyPrime_5() {
		NaturalNumber n = new NaturalNumber2(5);
		NaturalNumber nExpected = new NaturalNumber2(7);
		CryptoUtilites.generateNextLikelyPrime(n);
		assertEquals(nExpected, n);
	}
	
	/* Test generateNextLikelyPrime(11) to find the next prime (13), checking the method's accuracy with small prime numbers */
	@Test
	public void generateNextLikelyPrime_11() {
		NaturalNumber n = new NaturalNumber2(11);
		NaturalNumber nExpected = new NaturalNumber2(13);
		CryptoUtilites.generateNextLikelyPrime(n);
		assertEquals(nExpected, n);
	}
	
	/* Confirm generateNextLikelyPrime(17462) locates the next prime (17467), challenging the function with larger numbers */
	@Test
	public void generateNextLikelyPrime_17462() {
		NaturalNumber n = new NaturalNumber2(17462);
		NaturalNumber nExpected = new NaturalNumber2(17467);
		CryptoUtilites.generateNextLikelyPrime(n);
		assertEquals(nExpected, n);
	}

}