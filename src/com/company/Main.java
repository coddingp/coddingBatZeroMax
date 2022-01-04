package com.company;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums0 = {0, 5, 0, 3};
        int[] nums1 = {0, 4, 0, 3};
        int[] nums2 = {0, 1, 0};
        int[] nums3 = {0, 1, 5};
        int[] nums4 = {0, 2, 0};
        int[] nums5 = {1};
        int[] nums6 = {0};
        int[] nums7 = {};
        int[] nums8 = {7, 0, 4, 3, 0, 2};
        int[] nums9 = {7, 0, 4, 3, 0, 1};
        int[] nums10 = {7, 0, 4, 3, 0, 0};
        int[] nums11 = {7, 0, 1, 0, 0, 7};
        System.out.println(Arrays.toString(nums9));
        zeroMax(nums9);
        System.out.println(Arrays.toString(nums9));
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
            int tempForExchange;
            for (int i = 0; i < odds.length; i++) {
                if (i + 1 < odds.length && odds[i] < odds[i + 1]) {
                    tempForExchange = odds[i];
                    odds[i] = odds[i + 1];
                    odds[i + 1] = tempForExchange;
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
                                nums[j] = nums[oddIndexesInNums[odds.length - oddIndex - 1]];
                            }
                            i++;
                        }
                    }
                }
                return nums;
            } else {
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] == 0) {
                        for (int j = i; j < nums.length; j++) {
                            if (j + 1 < nums.length) {
                                if (nums[j] == maxOdd) {
                                    didProgramWentThroughMaxOdd = true;
                                }
                                if (nums[j] == 0 && oddIndex > 0 && !didProgramWentThroughMaxOdd) {
                                    nums[j] = maxOdd;
                                    i++;
                                    continue;
                                } else if (nums[j] == 0 && oddIndex > 0 && didProgramWentThroughMaxOdd) {
                                    nums[j] = nums[oddIndexesInNums[odds.length - oddIndex - 1]];
                                    i++;
                                    continue;
                                }
                                if (nums[j] % 2 != 0) {
                                    oddIndex--;
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
