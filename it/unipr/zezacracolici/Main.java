/**
 * 
 */
package it.unipr.zezacracolici;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

/**
 * @author enize
 * @author leocraco
 *
 */
public class Main {

	public static final String DATAFILE = "users.txt";
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Scanner reader = new Scanner(System.in);
		int n=0;
		do
		{
			System.out.println("0:Quit\n1:Register\n2:LogIn");
			n = reader.nextInt();
			switch(n) {
				case 0:
					break;
				case 1:
					System.out.print("Insert username: ");
					String username = reader.next();
					System.out.print("Insert password: ");
					String password = reader.next();
					String user = username+","+password+",user";
					DataOutputStream fObj = null;
					try {
				        fObj = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(DATAFILE, true)));
				        fObj.writeUTF(user);
					}
					finally {
						fObj.close();
					}
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
