package SubjuntivoPerfecto;

import java.util.Scanner;

import Other.Function;

public class Imperfecto1 {

	private static boolean reflexive = false;

	public static void main(String[]args){
		Scanner sb = new Scanner(System.in);
		System.out.println("Input a verb");
		String a = sb.nextLine();
		if(a.endsWith("se")){
			a = a.substring(0, a.length() - 2);
			reflexive = true;
		}
		Function.viewArray(imperfect1(a));
	}

	public static String[] imperfect1(String a) {
		String[] x = new String[6];
		a = Other.Participio.participle(a);
		if(reflexive == true){
			x[0] = "me " + "hubiera " + a;
			x[1] = "te " + "hubieras " + a;
			x[2] = "se " + "hubiera " + a;
			x[3] = "nos " + "hubiéramos " + a;
			x[4] = "os " + "hubierais " + a;
			x[5] = "se " + "hubieran " + a;
		}else{
			x[0] = "hubiera " + a;
			x[1] = "hubieras " + a;
			x[2] = "hubiera " + a;
			x[3] = "hubiéramos " + a;
			x[4] = "hubierais " + a;
			x[5] = "hubieran " + a;
		}
		return x;
	}
}
