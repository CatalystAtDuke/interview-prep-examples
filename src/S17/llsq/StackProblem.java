package S17.llsq;

import java.util.*;

public class StackProblem {

    public static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        for (int i = 0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                if (stack.pop() != map.get(c)) {
                    return false;
                }
            } else if (map.containsValue(c)) {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static boolean isBalancedSingleType(String s) {
        int balance = 0;
        for (int i = 0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{') {
                balance++;
            } else if (c == '}'){
                balance--;
                if (balance < 0) {
                    return false;
                }
            }
        }
        return balance == 0;
    }

    public static void balanceTest() {
        System.out.println("testing balance of all brackets");
        String[] test = new String[] {"{catalyst}", "()[]{}", "{[()bobby]}",
                "[)", "([{]})", "{"};
        for (String s : test) {
            System.out.println(s + " : " + isBalanced(s));
        }
    }

    public static void singleTypeBalanceTest() {
        System.out.println("testing balance of curly braces");
        String[] test = new String[] {"{catalyst}", "()[]{}", "{[()bobby]}",
                "[)", "([{]})", "{"};
        for (String s : test) {
            System.out.println(s + " : " + isBalancedSingleType(s));
        }
    }

    static int calculator(String s) {
        return 0;
    }

    public static void main(String[] args) {
        balanceTest();
        singleTypeBalanceTest();
    }

}
