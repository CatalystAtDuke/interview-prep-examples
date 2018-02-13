package S17.comp0;

import java.util.*;

public class Queens {
	
	   
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int queens = 0;
        boolean[] rows = new boolean[8];
        boolean[] cols = new boolean[8];
        boolean[] downRightDiag = new boolean[15];
        boolean[] upRightDiag = new boolean[15];
        
        for (int i = 0; i<8; i++) {
        	String s = sc.nextLine();
        	for (int j = 0; j<8; j++) {
        		char c = s.charAt(j);
        		if (c == '*') {
        			if (rows[i] || cols[j] || downRightDiag[i - j + 7] || upRightDiag[i + j]) {
        				System.out.println("invalid");
        				sc.close();
        				return;
        			}
        			queens++;
        			rows[i] = true;
        			cols[j] = true;
        			downRightDiag[i - j + 7] = true;
        			upRightDiag[i + j] = true;
        		}
        	}
        }
        System.out.println((queens == 8 ? "" : "in") + "valid");
        sc.close();
    }
}
