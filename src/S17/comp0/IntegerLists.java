package S17.comp0;

import java.util.*;

public class IntegerLists {

	/*
	 * Warning: this fails one of the test cases! Can you figure out where
	 * the bug is?
	 */
    
    private static void process(String input, int elems, String arr) {
        boolean leftToRight = true;
        int left = 0;
        int right = elems;
        for (int i = 0; i<input.length(); i++) {
            char c = input.charAt(i);
            if (c == 'R') {
                leftToRight = !leftToRight;
            } else {
                if (leftToRight) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        if (left > right) {
            System.out.println("error");
            return;
        }
        
        String[] part = arr.substring(1, arr.length()-1).split(",");
        
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i< right-left; i++) {
            sb.append(part[leftToRight ? left + i : right - 1 - i]);
            sb.append(",");
        }

        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        System.out.println(sb.toString());
    }
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int times = Integer.parseInt(sc.nextLine());
        for (int i = 0; i<times; i++) {
            String input = sc.nextLine();
            int elems = Integer.parseInt(sc.nextLine());
            String arr = sc.nextLine();
            process(input, elems, arr);
        }
        sc.close();
    }
    
}
