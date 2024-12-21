package org.example;

import java.math.BigInteger;

public class FactorialDigitSum {
    public static void main(String[] args) {
        int number = 100;
        System.out.println("The sum of the digits in " + number + "! is: " + sumOfDigitsInFactorial(number));
    }

    /**
     * Calculates the sum of the digits in the factorial of a given number.
     *
     * @param number the number whose factorial digit sum needs to be calculated
     * @return the sum of the digits in the factorial of the number
     */
    private static int sumOfDigitsInFactorial(int number) {
        // Calculate factorial using BigInteger to handle large numbers
        BigInteger sum = getFactorial(number);

        // Convert factorial to string and calculate the sum of its digits
        char[] charArray = sum.toString().toCharArray();
        int sumOfDigits = 0;
        for (char digit : charArray) {
            sumOfDigits += Character.getNumericValue(digit);
        }
        return sumOfDigits;
    }

    private static BigInteger getFactorial(int number) {
        BigInteger sum = BigInteger.ONE;

        for (int i = 2; i <= number; i++) {
            sum = sum.multiply(BigInteger.valueOf(i));
        }
        return sum;
    }
}
