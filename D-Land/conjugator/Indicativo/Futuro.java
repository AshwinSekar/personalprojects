package Indicativo;

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
		if(a.endsWith("caber")){
			a = a.substring(0, a.length() - 5) + "cabr";
		}
		if(a.endsWith("saber")){
			a = a.substring(0, a.length() - 5) + "sabr";
		}
		if(a.endsWith("haber")){
			a = a.substring(0, a.length() - 5) + "habr";
		}
		if(a.endsWith("valer")){
			a = a.substring(0, a.length() - 5) + "valdr";
		}
		if(a.endsWith("poner")){
			a = a.substring(0, a.length() - 5) + "pondr";
		}
		if(a.endsWith("poder")){
			a = a.substring(0, a.length() - 5) + "podr";
		}
		if(a.endsWith("salir")){
			a = a.substring(0, a.length() - 5) + "saldr";
		}
		if(a.endsWith("tener")){
			a = a.substring(0, a.length() - 5) + "tendr";
		}
		if(a.endsWith("hacer")){
			a = a.substring(0, a.length() - 5) + "har";
		}
		if(a.endsWith("querer")){
			a = a.substring(0, a.length() - 6) + "querr";
		}
		if(a.endsWith("decir")){
			a = a.substring(0, a.length() - 5) + "dir";
		}
		if(a.endsWith("venir")){
			a = a.substring(0, a.length() - 5) + "vendr";
		}
		if(reflexive == true){
			x[0] = "me " + a + "�";
			x[1] = "te " + a + "�s";
			x[2] = "se " + a + "�";
			x[3] = "nos " + a + "�mos";
			x[4] = "os " + a + "�is";
			x[5] = "se " + a + "�n";
		}else{
			x[0] = a + "�";
			x[1] = a + "�s";
			x[2] = a + "�";
			x[3] = a + "�mos";
			x[4] = a + "�is";
			x[5] = a + "�n";
		}
		return x;
	}
}
