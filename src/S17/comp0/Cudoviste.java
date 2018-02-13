package S17.comp0;

import java.util.*;

public class Cudoviste {
    
    private static int counter(char[][] spaces, int x, int y) {
        int car = 0;
        for (int i = x; i<x+2; i++) {
            for (int j = y; j<y+2; j++) {
                if (spaces[i][j] == '#') {
                	return -1;
                }
                if (spaces[i][j] == 'X') {
                    car++;
                }
            }
        }
        return car;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String info = sc.nextLine();
        int r = Integer.parseInt(info.split(" ")[0]);
        int c = Integer.parseInt(info.split(" ")[1]);
        char[][] spaces = new char[r][c];
        for (int i = 0; i<r; i++) {
            String row = sc.nextLine();
            for (int j = 0; j<c; j++) {
                spaces[i][j] = row.charAt(j);
            }
        }
        sc.close();
        
        int[] counts = new int[5];
        for (int i = 0; i<r-1; i++) {
            for (int j = 0; j<c-1; j++) {
                int res = counter(spaces, i,j);
                if (res > -1) {
                    counts[res]++;
                }
            }
        }
        for (int i : counts) {
            System.out.println(i);
        }
    }
}