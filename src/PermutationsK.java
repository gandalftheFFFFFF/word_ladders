import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;



public class PermutationsK {
	
	public static HashMap<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>>();

    public static void choose(String s, int R) {
    	// Convert String to char array
    	char[] c = getChars(s);
    	
    	enumerate(c, c.length, R, s);
    }
    
    public static char[] getChars(String s) {
    	char[] c = new char[s.length()];
    	for (int i = 0; i < c.length; i++) {
    		c[i] = s.charAt(i);
    	}
    	return c;
    }

    private static void enumerate(char[] a, int n, int r, String word) {
    	String perm = "";
        if (r == 0) {
            for (int i = n; i < a.length; i++) {
            	perm = perm + a[i];
            }
            if (!map.containsKey(perm)) {
            	LinkedList<String> l = new LinkedList<String>();
            	l.add(word);
            	map.put(perm, l);
            } else {
            	if (!map.get(perm).contains(word)) {
            		LinkedList<String> l = map.get(perm);
            		l.add(word);
            		map.put(perm, l);
            	}
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            swap(a, i, n-1);
            enumerate(a, n-1, r-1, word);
            swap(a, i, n-1);
         }
    }  

    // helper function that swaps a[i] and a[j]
    public static void swap(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    public static int factorial(int k) {
    	int result = 1;
    	for (int i = 1; i <= k; i++) {
    		result = result * i;
    	}
    	return result;
    }
}