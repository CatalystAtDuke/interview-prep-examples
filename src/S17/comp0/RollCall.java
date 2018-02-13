package S17.comp0;
import java.util.*;

public class RollCall {
	
	public static class Name {
		String first;
		String last;
		public Name(String first, String last) {
			this.first = first;
			this.last = last;
		}
	}
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        List<Name> names = new ArrayList<>();
        Map<String, Integer> firstNameCount = new HashMap<>();
        while (sc.hasNextLine()) {
        	String[] s = sc.nextLine().split(" ");
        	names.add(new Name(s[0], s[1]));
        	firstNameCount.put(s[0], firstNameCount.getOrDefault(s[0], 0) + 1);
        }
        sc.close();
        
        Collections.sort(names, new Comparator<Name>() {

			@Override
			public int compare(Name o1, Name o2) {
				int res = o1.last.compareTo(o2.last);
				if (res != 0) return res;
				return o1.first.compareTo(o2.first);
			}
        	
        });
        
        for (Name name : names) {
        	if (firstNameCount.get(name.first) > 1) {
        		System.out.println(name.first + " " + name.last);
        	} else {
        		System.out.println(name.first);
        	}
        }
        
    }
    
    
    
}