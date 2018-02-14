package S17.llsq2;

import java.util.*;

public class PrefixNotationCalculator {

    public static int calculate(String s) {
        /*
            ) - push a 0 onto count stack
            ( - ignore it
            number - push number to stack
            operator - apply the operator and push it back to the stack, pop the current count, add 1 to the count below it
         */

        Stack<Integer> counts = new Stack<>();
        counts.push(0);
        Stack<Integer> results = new Stack<>();

        Set<String> operators = new HashSet<>();
        for (String t : new String[] {"+", "-", "/", "*"}) {
            operators.add(t);
        }

        String[] tokens = s.split(" ");
        for (int i = tokens.length - 1; i>= 0; i--) {
            String token = tokens[i];
            if (token.equals(")")) {
                counts.push(0);
            } else if (isNumber(token)) {
                results.push(Integer.parseInt(token));
                counts.push(counts.pop() + 1);
            } else if (operators.contains(token)) {
                executeOperator(token, counts, results);
            }
        }

        return results.pop();
    }

    private static void executeOperator(String token, Stack<Integer> counts, Stack<Integer> results) {
        int tmp = results.pop();
        int count = counts.pop() - 1;
        for (int j = 0; j<count; j++) {
            if (token.equals("+")) {
                tmp += results.pop();
            } else if (token.equals("*")) {
                tmp *= results.pop();
            } else if (token.equals("-")) {
                tmp -= results.pop();
            } else {
                tmp /= results.pop();
            }
        }
        results.push(tmp);
        counts.push(counts.pop() + 1);
    }

    private static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static void main(String[] args) {
        String[] tester = new String[] {"( - 1 2 4 )", "( * 5 ( + 3 4 ) )", "( + 3 4 )",  "( * ( - 1 2 4 ) ( * 5 ( + 3 4 ) ) )"};
        for (String s : tester) {
            System.out.println(calculate(s));
        }
    }

}
