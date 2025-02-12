package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary {
	private String number = "0"; // string containing the binary value '0' or '1'

	/**
	 * A constructor that generates a binary object.
	 *
	 * @param number a String of the binary values. It should contain only zeros or
	 *               ones
	 *               with any length and order. Otherwise, the value of "0" will be
	 *               stored.
	 *               Leading zeros will be removed. Empty string or invalid input =
	 *               "0".
	 */
	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0";
			return;
		}

		// Validate the binary string (only '0' or '1' allowed)
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0"; // Default to "0" for invalid input
				return;
			}
		}

		// Remove leading zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}
		// If all digits are '0', ensure number is "0"
		this.number = (beg == number.length()) ? "0" : number.substring(beg);

		// Ensure empty strings are replaced with "0"
		if (this.number.isEmpty()) {
			this.number = "0";
		}
	}

	/**
	 * Return the binary value of the variable
	 *
	 * @return the binary value in a string format.
	 */
	public String getValue() {
		return this.number;
	}

	/**
	 * Adding two binary variables.
	 * For more information, visit https://www.wikihow.com/Add-Binary-Numbers
	 *
	 * @param num1 The first addend object
	 * @param num2 The second addend object
	 * @return A binary variable with a value of (num1 + num2).
	 */
	public static Binary add(Binary num1, Binary num2) {
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;
		int carry = 0;
		String num3 = "";
		while (ind1 >= 0 || ind2 >= 0 || carry != 0) {
			int sum = carry;
			if (ind1 >= 0) {
				sum += (num1.number.charAt(ind1) == '1') ? 1 : 0;
				ind1--;
			}
			if (ind2 >= 0) {
				sum += (num2.number.charAt(ind2) == '1') ? 1 : 0;
				ind2--;
			}
			carry = sum / 2;
			sum = sum % 2;
			num3 = ((sum == 0) ? "0" : "1") + num3;
		}
		return new Binary(num3);
	}

	/**
	 * Multiply two binary numbers (repeated addition approach or any correct
	 * approach).
	 * 
	 * @param num1 first factor
	 * @param num2 second factor
	 * @return a Binary object with value (num1 * num2).
	 */
	public static Binary multiply(Binary num1, Binary num2) {
		// Simple approach: repeated add & shift
		Binary product = new Binary("0");
		Binary one = new Binary("1");
		Binary temp = new Binary(num1.getValue()); // copy of num1

		// We'll iterate over the bits of num2 from right to left
		int ind2 = num2.number.length() - 1;
		int shiftCount = 0;

		while (ind2 >= 0) {
			if (num2.number.charAt(ind2) == '1') {
				// we need to add temp (which is num1 shifted) to product
				product = add(product, temp);
			}
			// shift temp (essentially multiply temp by 2)
			temp = add(temp, temp);
			ind2--;
			shiftCount++;
		}
		return product;
	}

	/**
	 * Bitwise AND of two binary numbers.
	 * 
	 * @param num1 first
	 * @param num2 second
	 * @return bitwise AND
	 */
	public static Binary bitwiseAnd(Binary num1, Binary num2) {
		// pad them so they're the same length
		int maxLen = Math.max(num1.number.length(), num2.number.length());
		String s1 = padLeft(num1.number, maxLen, '0');
		String s2 = padLeft(num2.number, maxLen, '0');
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < maxLen; i++) {
			char c1 = s1.charAt(i);
			char c2 = s2.charAt(i);
			if (c1 == '1' && c2 == '1') {
				sb.append('1');
			} else {
				sb.append('0');
			}
		}
		return new Binary(sb.toString());
	}

	/**
	 * Bitwise OR of two binary numbers.
	 * 
	 * @param num1 first
	 * @param num2 second
	 * @return bitwise OR
	 */
	public static Binary bitwiseOr(Binary num1, Binary num2) {
		int maxLen = Math.max(num1.number.length(), num2.number.length());
		String s1 = padLeft(num1.number, maxLen, '0');
		String s2 = padLeft(num2.number, maxLen, '0');
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < maxLen; i++) {
			char c1 = s1.charAt(i);
			char c2 = s2.charAt(i);
			if (c1 == '1' || c2 == '1') {
				sb.append('1');
			} else {
				sb.append('0');
			}
		}
		return new Binary(sb.toString());
	}

	/**
	 * Helper method to pad string with a specified character on the left
	 */
	private static String padLeft(String s, int n, char c) {
		StringBuilder sb = new StringBuilder();
		for (int i = s.length(); i < n; i++) {
			sb.append(c);
		}
		sb.append(s);
		return sb.toString();
	}
}
