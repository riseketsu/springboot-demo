/**
 * 
 */
package com.rskt.demo.other;

/**
 * @author SHIJIELI
 *
 */
public class PrimeNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 100; i <= 200; i++) {
			if (isPrime(i)) {
				System.out.println(i + "\t");
			}

		}
	}

	private static boolean isPrime(int a) {
		boolean flag = true;
		if (a < 2) {
			return false;
		} else {
			double toval = Math.sqrt(a);
			System.out.println("Math.sqrt(a):" + toval + " a=" + a);
			for (int i = 2; i <= toval; i++) {
				if (a % i == 0) {
					flag = false;
					break;
				}
			}
		}

		return flag;
	}
}
