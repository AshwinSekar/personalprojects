package SubjuntivoPerfecto;

import java.util.Scanner;

import Other.Function;

public class Imperfecto2 {

	private static boolean reflexive = false;

	public static void main(String[]args){
		Scanner sb = new Scanner(System.in);
		System.out.println("Input a verb");
		String a = sb.nextLine();
		if(a.endsWith("se")){
			a = a.substring(0, a.length() - 2);
			reflexive = true;
		}
		Function.viewArray(imperfect2(a));
	}

	public static String[] imperfect2(String a) {
		String[] x = new String[6];
		a = Other.Participio.participle(a);
		if(reflexive == true){
			x[0] = "me " + "hubiese " + a;
			x[1] = "te " + "hubieses " + a;
			x[2] = "se " + "hubiese " + a;
			x[3] = "nos " + "hubiésemos " + a;
			x[4] = "os " + "hubieseis " + a;
			x[5] = "se " + "hubiesen " + a;
		}else{
			x[0] = "hubiese " + a;
			x[1] = "hubieses " + a;
			x[2] = "hubiese " + a;
			x[3] = "hubiésemos " + a;
			x[4] = "hubieseis " + a;
			x[5] = "hubiesen " + a;
		}
		return x;
	}
}
