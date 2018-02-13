package S17.comp0;

import java.util.*;

public class SecureDoors {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Set<String> names = new HashSet<>();
		int times = Integer.parseInt(sc.nextLine());
		for (int i = 0; i<times; i++) {
			String[] a = sc.nextLine().split(" ");
			boolean entry = a[0].equals("entry");
			String name = a[1];
			if (entry) {
				System.out.println(name + " entered" + (names.contains(name) ? " (ANOMALY)" : ""));
				names.add(name);
			} else {
				System.out.println(name + " exited" + (names.contains(name) ? "" : " (ANOMALY)"));
				names.remove(name);
			}
		}
		sc.close();
	}

}
