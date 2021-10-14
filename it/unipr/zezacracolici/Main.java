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
		Scanner reader = new Scanner(System.in);
		int n=0;
		do
		{
			System.out.println("0:Quit\n1:Register\n2:LogIn");
			n = reader.nextInt();
			//System.out.println();
			switch(n) {
				case 0:
					break;
				case 1:
					System.out.print("Insert username: ");
					String username = reader.next();
					System.out.print("Insert password: ");
					String password = reader.next();
					break;
				case 2:
					//operazioni di login
					break;
			}
		}
		while(n!=0);
		reader.close();
	}

}
