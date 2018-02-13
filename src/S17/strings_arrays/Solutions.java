package S17.strings_arrays;

import java.util.*;

/**
 * Solutions and testing code for Lecture 2 from Catalyst Interview Prep Course. MIT License, jk
 *
 * @author Simran
 */
public class Solutions {

    /**
     * Starts from first and last character, increasing left and decreasing right pointer while checking if the
     * characters at those two pointers are equal.
     *
     * @param input
     * @return true for palindrome, false otherwise
     */
    private static boolean isPalindrome(String input) {
        int left = 0;
        int right = input.length() - 1;
        while(right > left) {
            if(input.charAt(left) != input.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * Iterate through every character trying to expand outward as if it was a palindrome. Accounts for even
     * lettered palindrome by starting the high pointer at the last instance of the same character as the low
     * pointer
     *
     * @param input
     * @return First longest palindrome Substring in input
     */
    private static String longestPalindromeSubstring(String input) {
        int length = input.length();
        int low;
        int high;
        int maxLength = 0;
        String ans = "";
        for(int i = 0; i<length; i++) {
            low = i;
            high = i;
//            Search for last instance of character at the last pointer, ex. xdaaaaacd, will
            while((high + 1) < length && input.charAt(high + 1) == input.charAt(low) ) {
                high++;
            }
//            Palindrome expansion outword from the center
            while((low - 1) >= 0 && (high + 1) < length && input.charAt(low - 1) == input.charAt(high + 1)) {
                low--;
                high++;
            }
            int currentLength = high - low + 1;
            if(currentLength > maxLength) {
                maxLength = currentLength;
                ans = input.substring(low, high + 1);
            }
        }
        return ans;
    }

    /**
     * Iterate through input arrays from keeping a pointer at specific positions. Take the lower of the two values,
     * add to the answer array, increment pointers, then repeat until you reach the end of one of the arrays. Add the
     * remaining values to the answer array.
     *
     * @param list1
     * @param list2
     * @return
     */
    private static int[] merge(int[] list1, int[] list2) {
        int size1 = list1.length;
        int size2 = list2.length;
        int[] ans = new int[size1 + size2];
        int point1 = 0;
        int point2 = 0;
        int current = 0;
        while(point1 < size1 && point2 < size2) {
            int first = list1[point1];
            int second = list2[point2];
            if(first < second) {
                point1++;
                ans[current] = first;
            } else {
                point2++;
                ans[current] = second;
            }
            current++;
        }
        while(point1 < size1) {
            ans[current] = list1[point1];
            current++;
            point1++;
        }
        while(point2 < size2) {
            ans[current] = list2[point2];
            current++;
            point2++;
        }
        return ans;
    }

    /**
     * Create a mapping of character to character to determine whether two strings are isomorphic.
     *
     * @param string1
     * @param string2
     * @return
     */
    private static boolean isomorphic(String string1, String string2) {
        if(string1.length() != string2.length()) { return false; }
        Map<Character, Character> mapping = new HashMap<>();
        for(int i = 0; i<string1.length(); i++) {
            Character first = string1.charAt(i);
            Character second = string2.charAt(i);
            if(mapping.containsKey(first)) {
                if(mapping.get(first) == second) { continue; } else {
                    return false;
                }
            } else {
//                Return false if the second character is already being mapped to
                if(mapping.values().contains(second)) { return false; }
                mapping.put(first, second);
            }
        }
        return true;
    }

    /**
     * Find number of pairs in an input array that add up to target sum.
     *
     * @param input
     * @param target
     * @return
     */
    private static int twoSum(int[] input, int target) {
        Map<Integer, Integer> mapping = new HashMap<>();
        int sums = 0;
        for(int i = 0; i<input.length; i++) {
            int current = input[i];
            int complement = target - current;
            if(mapping.containsKey(complement)) {
                sums += mapping.get(complement);
            }
            Integer previous = mapping.getOrDefault(current, 0);
            previous++;
            mapping.put(current, previous);
        }
        return sums;
    }

    /**
     * Same thing as twoSum, except with an index to avoid as parameter
     *
     * @param input
     * @param target
     * @param avoidIndex
     * @return
     */
    private static int twoSumForThreeSum(int[] input, int target, int avoidIndex) {
        Map<Integer, Integer> mapping = new HashMap<>();
        int sums = 0;
        for(int i = 0; i<input.length; i++) {
            if(i == avoidIndex) { continue; }
            int current = input[i];
            int complement = target - current;
            if(mapping.containsKey(complement)) {
                sums += mapping.get(complement);
            }
            Integer previous = mapping.getOrDefault(current, 0);
            previous++;
            mapping.put(current, previous);
        }
        return sums;
    }

    /**
     * Returns true if a+b+c=0 for some a,b,c in the array. Solves the problem a+b = -c for all c using the
     * twoSum helper method.
     *
     * @param input
     * @return
     */
    private static boolean threeSum(int[] input) {
        for(int i = 0; i<input.length; i++) {
            int current = input[i];
            int pairNum = twoSumForThreeSum(input, -current, i);
            if(pairNum != 0) { return true; }
        }
        return false;
    }

    /**
     * Return shortest substring in input containing all characters from the chars string.
     *
     * @param input Total string
     * @param chars Desired characters in a string form
     * @return shortest Substring with all characters in chars from input string.
     */
    private static String shortestSubstring(String input, String chars) {
        Map<Character, Integer> mapping = new HashMap<>();
        int inputLength = input.length();
        int totalChars = chars.length();
        int low = 0;
        int foundChars = 0;
        int minStart = 0;
        int minLength = inputLength;
//        Map character in chars to an integer of the number of times that character appears
        for(int i = 0; i<chars.length(); i++) {
            Character c = chars.charAt(i);
            Integer previous = mapping.getOrDefault(c, 0);
            previous++;
            mapping.put(c, previous);
        }
        for(int i = 0; i<inputLength; i++) {
            Character c = input.charAt(i);
            if(mapping.containsKey(c)){
                if(mapping.get(c) > 0) { // character that you are looking for has been found
                    foundChars++;
                }
                Integer currentCount = mapping.get(c); // reduce value in map by 1
                currentCount--;
                mapping.put(c, currentCount);
            }
            while(foundChars == totalChars) { // found all characters, lets reduce the characters from the left
                Character toBeRemoved = input.charAt(low);
                if(!mapping.containsKey(toBeRemoved)) { low++; continue; }
                Integer countBeforeRemoval = mapping.get(toBeRemoved);
                if(countBeforeRemoval == 0) { // removing character we need, about to start expansion again
                    foundChars--;
                    int currentLength = i - low + 1;
                    if(currentLength < minLength) {
                        minLength = currentLength;
                        minStart = low;
                    }
                }
                low++;
                countBeforeRemoval++;
                mapping.put(toBeRemoved, countBeforeRemoval);
            }
        }
        return input.substring(minStart, minStart + minLength);
    }

    /**
     * @param digits
     * @return next smallest number greater than the given number with the same digits
     */
    private static String nextLargest(char[] digits) {
        int right = digits.length - 1;
        int left = digits.length - 2;
        while(left >= 0 && digits[left] > digits[right]) {
            left--;
            right--;
        }
        if(left < 0) { return "Not Possible!"; }
//        Left is at the first position where the numbers are decreasing (reading right to left)
//        We want to replace this position with the smallest number greater than the value on the right
        Character min = digits[left];
        int nextMinIndex = left+1;
        for(int i = left + 1; i<digits.length; i++) {
            if(digits[i] > min && digits[i] < digits[nextMinIndex]) {
                nextMinIndex = i;
            }
        }
//        Time to swap
        Character nextMin = digits[nextMinIndex];
        digits[left] = nextMin;
        digits[nextMinIndex] = min;
//        Try to sort the right side of the left value we just switched
        ArrayList<Character> sortedRightSide = new ArrayList<>();
        boolean alreadyAdded = false;
        for(int i = digits.length - 1; i>left; i--) {
            char current = digits[i];
            if(min == current) { continue; };
            if(min<current && !alreadyAdded) {
                sortedRightSide.add(min);
                alreadyAdded = true;
            }
            sortedRightSide.add(current);
        }
        if(!alreadyAdded) {
            sortedRightSide.add(min);
        }
//        Add all the characters from start to the left
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<=left; i++) {
            sb.append(digits[i]);
        }
//        Add all sorted characters
        for(Character c: sortedRightSide) {
            sb.append(c);
        }
        return sb.toString();
    }

    private static void testIsPalindrome() {
        String[] inputs = new String[]{"Test", "tacocat", "taco cat", "naan", "not actually"};
        for(String input: inputs) {
            System.out.println(input + " - " + isPalindrome(input));
        }
    }

    private static void testLongestPalindromeSubstring() {
        String[] inputs = new String[]{"iwentskiiksnotreally", "aaaaaa", "tacocat", "randomstring", "racecar"};
        for(String input: inputs) {
            System.out.println(input + " - " + longestPalindromeSubstring(input));
        }
    }

    private static void testMerge() {
        int[] arr1 = {1, 3};
        int[] arr2 = {2, 4, 6, 8};
        System.out.println(Arrays.toString(arr1) + " and " + Arrays.toString(arr2) + " yield: " +
                Arrays.toString(merge(arr1, arr2)));
    }

    private static void testIsomorphic() {
        String[] input1 = new String[]{"aag", "adg", "avd", "vds", "aag"};
        String[] input2 = new String[]{"vvc", "aag", "tad", "vdv", "adg"};
        for(int i = 0; i<input1.length; i++) {
            System.out.println(input1[i] + " and " + input2[i] + ": " + isomorphic(input1[i], input2[i]));
        }
    }

    private static void testTwoSum() {
        int[] arr = new int[]{2, 1, 1, 2};
        int sum = 4;
        System.out.println(Arrays.toString(arr) + " 2sum " + sum + ": " + twoSum(arr, sum));
    }

    private static void testThreeSum() {
        int[] arr = new int[]{1, 2, 5, 3, 9};
        System.out.println(Arrays.toString(arr) + " 3sum " + ": " + threeSum(arr));
    }

    private static void testShortestSubstring() {
        String[] inputs = new String[]{"ADOBECODEBANC", "geeksforgeeks", "tacocat", "randomstring", "racecar"};
        String[] chars = new String[]{"ABC", "ork", "tacocat", "randomstring", "racecar"};
        for(int i = 0; i<inputs.length; i++) {
            System.out.println(inputs[i] + " and " + chars[i] + " - " + shortestSubstring(inputs[i], chars[i]));
        }
    }

    private static void testNextLargest() {
        String[] inputs = new String[]{"1234", "4321", "218765", "534976"};
        for(String input: inputs) {
            System.out.println(input + " - " + nextLargest(input.toCharArray()));
        }
    }

    public static void main(String[] args) {
        testIsPalindrome();
        testLongestPalindromeSubstring();
        testMerge();
        testIsomorphic();
        testTwoSum();
        testThreeSum();
        testShortestSubstring();
        testNextLargest();
    }
}
