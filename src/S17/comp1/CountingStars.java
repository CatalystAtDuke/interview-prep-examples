package S17.comp1;

import java.util.*;
import java.io.*;

public class CountingStars {
    
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

    private static void dfs(int i, int j, boolean[][] visited, boolean[][] bright) {
        if (i < 0 || j < 0 || i >= visited.length || j >= visited[0].length || visited[i][j] || !bright[i][j]) {
            return;
        }
        visited[i][j] = true;
        dfs(i+1, j, visited, bright);
        dfs(i-1, j, visited, bright);
        dfs(i, j+1, visited, bright);
        dfs(i, j-1, visited, bright);
    }
    
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("in.txt"));
        int caseNum = 1;
        while (sc.hasNextLine()) {
            String[] info = sc.nextLine().split(" ");
            int r = Integer.parseInt(info[0]);
            int c = Integer.parseInt(info[1]);
            boolean[][] bright = new boolean[r][c];
            boolean[][] visited = new boolean[r][c];
            for (int i = 0; i<r; i++) {
                String row = sc.nextLine();
                for (int j = 0; j<c; j++) {
                    bright[i][j] = row.charAt(j) == '-';
                }
            }

            int count = 0;
            for (int i = 0; i<r; i++) {
                for (int j = 0; j<c; j++) {
                    if (!visited[i][j] && bright[i][j]) {
                        dfs(i, j, visited, bright);
                        count++;
                    }
                }
            }

            System.out.println("Case " + caseNum + ": " + count);
            caseNum++;
        }

        sc.close();
    }
}