/**
 * 
 */
package it.unipr.zezacracolici;

import java.util.Scanner;

/**
 * @author enize
 * @author leocraco
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n=1;
		do
		{
			Scanner reader = new Scanner(System.in); 
			System.out.println("1:SignIn\n2:LogIn");
			n = reader.nextInt();
			System.out.println(n);
			reader.close();
		}
		while(n!=0);
	}

}
