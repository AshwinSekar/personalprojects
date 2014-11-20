package SubjuntivoPerfecto;

import java.util.Scanner;

import Other.Function;

public class Futuro {

	private static boolean reflexive = false;

	public static void main(String[]args){
		Scanner sb = new Scanner(System.in);
		System.out.println("Input a verb");
		String a = sb.nextLine();
		if(a.endsWith("se")){
			a = a.substring(0, a.length() - 2);
			reflexive = true;
		}
		Function.viewArray(future(a));
	}

	public static String[] future(String a) {
		String[] x = new String[6];
		a = Other.Participio.participle(a);
		if(reflexive == true){
			x[0] = "me " + "hubiere " + a;
			x[1] = "te " + "hubieres " + a;
			x[2] = "se " + "hubiere " + a;
			x[3] = "nos " + "hubiéremos " + a;
			x[4] = "os " + "hubiereis " + a;
			x[5] = "se " + "hubieren " + a;
		}else{
			x[0] = "hubiere " + a;
			x[1] = "hubieres " + a;
			x[2] = "hubiere " + a;
			x[3] = "hubiéremos " + a;
			x[4] = "hubiereis " + a;
			x[5] = "hubieren " + a;
		}
		return x;
	}
}
