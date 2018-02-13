package S17.comp0;

import java.util.*;

public class PalindromeSubstring {
	
	private static void extend(Set<String> pals, String s, int lo, int hi) {
		while (lo >= 0 && hi < s.length() && s.charAt(lo) == s.charAt(hi)) {
			if (lo != hi) {
				pals.add(s.substring(lo, hi+1));
			}
			lo--;
			hi++;
		}
	}
	
	private static void processWord(String s) {
		Set<String> pals = new TreeSet<>();
		for (int i = 0; i < s.length()-1; i++) {
			extend(pals, s, i,i);
			extend(pals, s, i, i+1);
		}
		for (String pal : pals) {
			System.out.println(pal);
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()) {
			processWord(sc.nextLine());
		}
		sc.close();
	}

}
