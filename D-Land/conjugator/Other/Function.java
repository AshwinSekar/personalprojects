package Other;

public class Function{

	public static boolean isVowel(String x){
		x = x.toLowerCase();
		x = x.substring(0, 1);
		return isElement(x, Arr.vowel);
	}
	//Arrays & ArrayLists
	public static String[] concat(String[] a, String[] b){
		int l1 = a.length;
		int l2 = b.length;
		int n = 0;
		String[] s = (String[]) new Object[l1 + l2];
		while(n != l1 - 1){
			s[n] = a[n];
			n ++;
		}		
		int c = n;
		while(n != l2 - 1 + n){
			s[n] = b[n - c];
			n ++;
		}
		return s;
	}
	public static <T> boolean isElement(T x, T[] y){
		boolean f = false;
		for(T a : y){
			if(a.equals(x)){
				f = true;
			}
		}
		return f;
	}
	public static <T> void viewArray(T[] x){
		for(T n : x){
			System.out.print(n + ", ");
		}
	}
}
