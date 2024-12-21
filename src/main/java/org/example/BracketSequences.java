package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BracketSequences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Please enter a number: ");
            int number = scanner.nextInt();

            if (number < 0) {
                System.out.println("The number of pairs of brackets cannot be negative.");
            } else {
                System.out.println("You entered: " + number);
                long bracketSequences = countCorrectBracketSequences(number);
                System.out.println("Correct bracket sequences: " + bracketSequences);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a non-negative integer.");
        }
        scanner.close();
    }

    /**
     * The method returns the number of valid bracket sequences for N pairs of brackets.
     * Catalan formula: Cn = C(2n, n) / (n + 1)
     *
     * @param n pairs of brackets
     * @return the number of valid bracket sequences
     */
    public static long countCorrectBracketSequences(int n) {
        if (n < 0) {
            return 0;
        }
        return binomialCoefficient(2 * n, n) / (n + 1);
    }

    /**
     * The method returns the binomial coefficient C(n, k), which represents the
     * number of ways to choose k items from a set of n items.
     *
     * @param n the total number of items
     * @param k the number of items to select
     * @return the binomial coefficient
     */
    private static long binomialCoefficient(int n, int k) {
        long result = 1;
        for (int i = 0; i < k; i++) {
            result *= (n - i);
            result /= (i + 1);
        }
        return result;
    }
}
