package com.project.attempt;

import java.util.Collections;
import java.util.LinkedList;

public class LeetCodeAttempt {

    public static void main(String[] args) {

         System.out.println(findThePunishmentNumberOfAnInteger(10));
         System.out.println(findThePunishmentNumberOfAnInteger(37));

    }

    // This method returns the punishment number of an integer.
    public static int findThePunishmentNumberOfAnInteger(int n) {

        // We will store the answer in int punishmentNumber.
        int punishmentNumber = 0;

        // We then loop through all numbers between 1 and n, inclusive.
        for (int currentNumber = 1; currentNumber <= n; currentNumber++) {

            // We then call a helper method to convert the square of int currentNumber
            // into a LinkedList where every digit in the square is an element in
            // the LinkedList, stored in its original digit order.
            LinkedList<Integer> digits = linkedListOfSquare(currentNumber);

            // We then call another recursive helper method that returns int currentNumber
            // if its square can be partitioned into contiguous substrings such that the
            // sum of the integer values of these substrings equals int currentNumber, as
            // per challenge specifications. Otherwise, it returns -1 if this is not possible.
            int partitionSum = partitionSum(currentNumber, digits, -1, 0);

            // If it is a match, we add the square of int currentNumber to int punishmentNumber.
            if (partitionSum == currentNumber) {
                punishmentNumber = punishmentNumber + currentNumber * currentNumber;
            }

        }

        // Finally, we return int punishmentNumber after the loop is completed.
        return punishmentNumber;

    }

    // This method checks whether the square of int n can be partitioned into contiguous
    // substrings such that the sum of the integer values of these substrings equals int n.
    // This is a recursive method to check all possible substring combinations, with LinkedList
    // digits having all the digits of the square of int n in their correct order. Each recursive
    // call of the method will remove an element from LinkedList digits until the list is empty.
    // int currentSum represents the current sum of the integer values of the substrings.
    // int formerDigit will be set to -1 by default, but will be given a positive value when
    // chaining together a substring of digits that we don't want added to int currentSum yet.
    // (For example, in a 5 digit square, we might want to chain together the first 3 digits
    // without adding them up yet, and then add them to the 4th and 5th digits later on)
    private static int partitionSum(int n, LinkedList<Integer> digits, int formerDigit, int currentSum) {

        // If the LinkedList is empty, and int formerDigit is -1, return the value
        // of int currentSum as it is. Otherwise, return the value of int formerDigit
        // plus int currentSum if int formerDigit is not -1.
        if (digits.isEmpty()) {

            if (formerDigit == -1) { return currentSum; }
            else { return formerDigit + currentSum; }

        }

        // We create a new LinkedList to avoid potential issues due to the recursive calls of this method.
        LinkedList<Integer> newDigits = new LinkedList<>(digits);

        // We first get the initial digit at the leftmost position of the LinkedList.
        int currentDigit = newDigits.get(0);

        // If int formerDigit is positive, that means it is a value we want to chain onto int
        // currentDigit without adding them up together normally. So we shift the value of int
        // formerDigit to the left (by multiplying it by 10) and then add int currentDigit to it.
        if (formerDigit != -1) { currentDigit = formerDigit * 10 + currentDigit; }

        // Finally, we remove the current digit we are looking at from the LinkedList.
        newDigits.remove(0);

        // We then want to explore the two possibilities that could be performed with the current digit.

        // One possibility is to simply add the current digit to the current total sum that we have.
        // In this case, we set int formerDigit to -1 (as we don't want to perform any chaining) and
        // int currentSum to the total between the current int currentSum and int currentDigit.
        int path1 = partitionSum(n, newDigits, -1, currentSum + currentDigit);

        // The other possibility is to -not- add the current digit to the current total sum that we
        // have right now. Instead, chaining it to int formerDigit so that the current digit is
        // added to the rightmost position of int formerDigit in the next call of this recursive
        // method. In order to do this, we do a recursive call where the formerDigit argument is
        // set to int currentDigit, and we pass in int currentSum as it is without modification.
        int path2 = partitionSum(n, newDigits, currentDigit, currentSum);

        // If at any point in any of these recursive calls, either int path1 or int path2 ends
        // up being equal to int n, we return the value of n which will propagate back upwards
        // to the initial call of this method and return int n.
        if (path1 == n || path2 == n) { return n; }

        // Otherwise, if no match is ever found, we return -1 to indicate this.
        return -1;

    }

    // A helper method that returns a LinkedList representation of the square of the
    // number passed into it as an argument, where each digit is an element in the LinkedList.
    private static LinkedList<Integer> linkedListOfSquare(int number) {

        // We first find the square of the number.
        int square = number * number;

        // After which, we will store the square in a LinkedList with each digit
        // being its own element within the LinkedList, all stored in the correct order.
        LinkedList<Integer> digits = new LinkedList<>();

        // We add each digit from the square to the LinkedList one by one.
        // Doing it in this manner will result in a reverse order in the LinkedList.
        while (square > 0) {

            digits.add(square % 10);
            square = square / 10;

        }

        // So we perform the .reverse operation to the LinkedList to reflect the correct digit order.
        Collections.reverse(digits);

        // Finally, we return the LinkedList as it is.
        return digits;

    }

}
