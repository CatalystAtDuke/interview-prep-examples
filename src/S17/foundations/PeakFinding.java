package S17.foundations;

public class PeakFinding {
	
	/*
	 * Additional rules not mentioned in class:
	 * 1. endpoints of array are peaks if they're
	 * higher than their sole neighbor.
	 * 
	 * 2. the terrain does not have "plateaus" - 
	 * each element is guaranteed to be different
	 * in height from its neighbor. After all,
	 * in nature there are no perfectly flat surfaces.
	 */
    
    //tester method
    public static double[] generatePeak(int index, int length) {
		assert index < length;
		double[] ret = new double[length];
		for (int i = 1; i<=index; i++) {
		    ret[i] = (ret[i-1] + 1) * 1.5;
		}
		for (int i = index + 1; i<length; i++) {
		    ret[i] = ret[i-1] * 0.9;
		}
		return ret;
    }
    
    private static boolean leftSlope(double[] heights, int i) {
    	return heights[i] > heights[i+1];
    }
    
    private static boolean isPeak(double[] heights, int i) {
        return heights[i] > heights[i-1] && heights[i] > heights[i+1];
    }
    
    /* 
     * compare this code to first bad version.
     * How are the indices different? Compare 
     * it to the generic binary search code on the slides,
     * as well.
     */
    
    public static int findPeak(double[] heights) {
        int lo = 0;
        int hi = heights.length - 1;
        // special endpoint checks
        if (heights[lo] > heights[lo+1]) return lo;
        if (heights[hi] > heights[hi-1]) return hi;
        
        while (lo < hi) {
            int mid = lo + (hi - lo)/2;
            if (isPeak(heights, mid)) {
                return mid;
            } else if (leftSlope(heights, mid)) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return hi;
	}
    
    public static void main(String[] args) {
		int peak = 68;
		int length = 180;
		System.out.println(findPeak(new double[] {0,2,0.1,0,0}));
    }
   
}
