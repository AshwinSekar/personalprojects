import java.util.StringTokenizer;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "-1,57,26,0,46,58,46,98,62,97,63,90,82,61,100,89,100,98,136,102,149,69,162,103,163,167,158,167,139,185,138,191,123,190,120,185,107,179,103,181,104,192,93,196,75,196,67,202,59,202,58,210,48,217,28,211,21,216,3,214,2,214,-1,214,-1,57";
		StringTokenizer in = new StringTokenizer(s, ",");
		String a = "";
		String b = "";
		int n = in.countTokens() / 2;
		while(in.hasMoreTokens()) {
			a += in.nextToken() + ",";
			b += in.nextToken() + ",";
		}
		System.out.println("new Polygon(new int[] {" + a.substring(0, a.length()-1) + "}, new int[] {" + b.substring(0,b.length()-1) + "}, " + n + ");");

	}

}
