package SubjuntivoPerfecto;

import java.util.Scanner;

import Other.Function;

public class Presente {

	private static boolean reflexive = false;
	
	public static void main(String[]args){
		Scanner sb = new Scanner(System.in);
		System.out.println("Input a verb");
		String a = sb.nextLine();
		if(a.endsWith("se")){
			a = a.substring(0, a.length() - 2);
			reflexive = true;
		}
		Function.viewArray(present(a));
	}

	public static String[] present(String a) {
		String[] x = new String[6];
		a = Other.Participio.participle(a);
			if(reflexive == true){
				x[0] = "me " + "haya " + a;
				x[1] = "te " + "hayas " + a;
				x[2] = "se " + "haya " + a;
				x[3] = "nos " + "hayamos " + a;
				x[4] = "os " + "hayáis " + a;
				x[5] = "se " + "hayan " + a;
			}else{
				x[0] = "haya " + a;
				x[1] = "hayas " + a;
				x[2] = "haya " + a;
				x[3] = "hayamos " + a;
				x[4] = "hayáis " + a;
				x[5] = "hayan " + a;
			}
		return x;
	}
}
