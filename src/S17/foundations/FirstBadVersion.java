package S17.foundations;

class FirstBadVersion {

	boolean[] versions;
	
	//testing function
	public void setFirstBadVersion(int firstBad, int totalVersions) {
	    versions = new boolean[totalVersions+1];
	    for (int i = 0; i<firstBad; i++) {
	    	versions[i] = true;
	    }
	}
	
	//testing function
	public boolean isGood(int x) {
		return versions[x];
	}
	/*
	 * q1: how would you improve this to reduce calls to IsGood?
	 * a1: as we will see next week, with hashing. It's also possible
	 * to write it in a way that doesn't query isGood(mid) twice, but
	 * that way can possibly be more confusing to read, and differs
	 * from the format given in the lecture slides.
	 * 
	 * q2: why are we calculating mid = lo + (hi - lo)/2?
	 * a2: to prevent integer overflow from calculating mid
	 * the usual way with (hi + lo)/2 - if hi + lo > Integer.MAX_VAL,
	 * we have a problem.
	 */
	
	public int firstBadVersion(int currentVersion) {
		int lo = 1;
		int hi = currentVersion;
		while (lo < hi) {
			int mid = lo + (hi - lo)/2;
			if (!isGood(mid) && isGood(mid-1)) {
				return mid;
			} else if (isGood(mid)) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		return hi;
	}

	public static void main(String[] args) {
		FirstBadVersion f = new FirstBadVersion();
		int firstBad = 3;
		int totalVersions = 6;
		f.setFirstBadVersion(firstBad, totalVersions);
		System.out.println(f.firstBadVersion(totalVersions));
	}
}

