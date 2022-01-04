package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int[] nums = {7, 0, 4, 3, 0, 1};
        zeroMax(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static int[] zeroMax(int[] nums) {
        boolean thereAreNoOdds = true;
        boolean thereAreNoEven = true;
        boolean didProgramWentThroughMaxOdd = false;
        int countOdd = 0;
        int maxOdd = 0;
        for (int i = 0; i < nums.length; i++) { //I am counting how many odd there are in the array
            if (nums[i] == 0) {
                i++;
                for (int j = i; j < nums.length; j++) {
                    if (nums[j] % 2 != 0) {
                        countOdd++;
                        thereAreNoOdds = false;
                    } else {
                        if (nums[j] != 0) {
                            thereAreNoEven = false;
                        }
                    }
                }
                i++;
            }
        }

        int[] oddIndexesInNums = new int[countOdd];

        int[] odds = new int[countOdd];//creating void array of odds to check later which of odds values bigger
        int oddIndex = 0; // making it equal to zero again
        for (int i = 0; i < nums.length; i++) { //assigning values to each of the odds array cells
            if (nums[i] == 0) {
                for (int j = i; j < nums.length; j++) {
                    if (nums[j] % 2 != 0) {
                        odds[oddIndex] = nums[i];
                        oddIndexesInNums[oddIndex] = j;
                        oddIndex++;
                    }
                    i++;
                }
            }
        }


        if (thereAreNoOdds) {//if there are no odds then there MUST be no changes in given array
            return nums;
        } else { //if there are odds we will check which of the odds value is bigger than others
            // if there are no evens, we will assign the biggest odd value to all the zeros
            for (int odd : odds) {
                if (odd > maxOdd) {
                    maxOdd = odd;
                }
            }
            int tempForExchange = 0;
            for (int i = 0; i < odds.length; i++) {
                if (i + 1 < odds.length && odds[i] < odds[i + 1]) {
                    tempForExchange = odds[i];
                    odds[i] = odds[i + 1];
                    odds[i+1] = tempForExchange;
                }
            }
            System.out.println(Arrays.toString(odds));
//            System.out.println("Проверяем чему равен булиан thereAreNoEven: " + thereAreNoEven);

            if (thereAreNoEven) {
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] == 0) {
                        for (int j = i; j < nums.length; j++) {
                            if (nums[j] == maxOdd) {
                                didProgramWentThroughMaxOdd = true;
                            }
                            if (nums[j] % 2 != 0) {
                                oddIndex--;
                            }
                            if (nums[j] == 0 && oddIndex > 0 && !didProgramWentThroughMaxOdd) {
                                nums[j] = maxOdd;
                            } else if (nums[j] == 0 && oddIndex > 0 && didProgramWentThroughMaxOdd) {
                                nums[j] = nums[oddIndexesInNums[odds.length-oddIndex]];
                            }

                            i++;
                        }
                    }
                }
                return nums;
            } else {
                System.out.println("Нужно изменить код после этой полосы...  Что делать если в массиве\"nums\" есть и odds и evens и ноли :)");
                Scanner scanAnswer = new Scanner(System.in);
                String froScanAnswer = scanAnswer.nextLine();
                System.out.println("Program will do next: " + froScanAnswer);
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] == 0) {
                        for (int j = i; j < nums.length; j++) {
                            if (j + 1 < nums.length) {
                                if (nums[j + 1] > 0) {
                                    nums[j] = maxOdd;
                                    j++;
                                    i++;
                                }
                            }
                            i++;
                        }
                    }
                }
                return nums;
            }
        }
    }
}
