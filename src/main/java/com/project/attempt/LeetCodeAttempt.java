package com.project.attempt;

import java.util.Collections;
import java.util.LinkedList;

public class LeetCodeAttempt {

    public static void main(String[] args) {

         System.out.println(findThePunishmentNumberOfAnInteger(10));
         System.out.println(findThePunishmentNumberOfAnInteger(37));

//        LinkedList<Integer> digits = new LinkedList<>();
//        digits.add(8);
//        digits.add(1);
//        System.out.println(partitionSum2(9, digits, -1,0));

    }

    public static int findThePunishmentNumberOfAnInteger(int n) {

        int punishmentNumber = 0;

        for (int i = 1; i <= n; i++) {

            int partitionSum = paritionSum(i);
            if (partitionSum == i) {
                System.out.println("Parition Sum: " + partitionSum);
                punishmentNumber = punishmentNumber + i * i;
            }

//            int currentValue = i * i;
//            int substringSum = 0;

//            while (currentValue > 0) {
//
//                substringSum = substringSum + currentValue % 10;
//                currentValue = currentValue / 10;
//
//                if (substringSum + currentValue == i) {
//                    System.out.println("i value: " + i + ", Current Value: " + currentValue + ", Substring Sum: " + substringSum);
//                    substringSum = i;
//                    break;
//                }
//
//            }

//            if (substringSum == i) {
//
//                punishmentNumber = punishmentNumber + i * i;
//
//            }

        }

        return punishmentNumber;

    }

    private static int paritionSum(int n) {

        int square = n * n;
        LinkedList<Integer> digits = new LinkedList<>();

        while (square > 0) {

            digits.add(square % 10);
            square = square / 10;

        }

        Collections.reverse(digits);
        // System.out.println(digits);

        return partitionSum2(n, digits, -1, 0);

    }

    private static int partitionSum2(int n, LinkedList<Integer> digits, int formerDigit, int currentSum) {

        if (digits.isEmpty()) {

            if (formerDigit == -1) { return currentSum; }
            else { return formerDigit + currentSum; }

        }

        LinkedList<Integer> newDigits = new LinkedList<>(digits);

        int currentDigit = newDigits.get(0);
        if (formerDigit != -1) { currentDigit = formerDigit * 10 + currentDigit; }

        newDigits.remove(0);

        int path1 = partitionSum2(n, newDigits, -1, currentSum + currentDigit);
        int path2 = partitionSum2(n, newDigits, currentDigit, currentSum);

        if (path1 == n || path2 == n) { return n; }

        return -1;

    }

    // 1296, 1 + 29 + 6.
    // 1 + <296>
    // 12 + <96>

}
