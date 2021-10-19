/**
 * 
 */
package it.unipr.zezacracolici;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author enize
 * @author leocraco
 *
 */
public class Main {

	public static final String DATAFILE = "users.csv";
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Scanner reader = new Scanner(System.in);
		int menu1=0;
		do
		{
			System.out.println("0:Quit\n1:Register\n2:LogIn");
			menu1 = reader.nextInt();
			switch(menu1) {
				case 0:
					break;
				case 1:
					boolean verification = true;
					System.out.print("Insert username: ");
					String username = reader.next();
					System.out.print("Insert password: ");
					String password = reader.next();
					System.out.print("Confirm password: ");
					String confpassword = reader.next();
					int passlen = password.length();
					do {
						if (verification == false) {
							System.out.print("Reinsert username: ");
							username = reader.next();
							System.out.print("Reinsert password: ");
							password = reader.next();
							System.out.print("Confirm password: ");
							confpassword = reader.next();
						}
						verification = true;
						passlen = password.length();
						if (passlen < 6 || passlen > 12) {
							System.out.println("Error: password length must be between 6 and 12 characters");
							verification = false;
						}
						else if (Objects.equals(password,confpassword) == false) {
							System.out.println("Error: the 2 passwords are different");
							verification = false;
						}
						File file = new File(DATAFILE);
						if (file.exists()) {
						try (DataInputStream fin = new DataInputStream(new BufferedInputStream(new FileInputStream(DATAFILE)))){
							String u;
							String[] usplitted;
							while(true) {
								u = fin.readUTF();
								usplitted = u.split(",");
								if(Objects.equals(username, usplitted[0]) == true) {
									System.out.println("Error: username already in use");
									verification = false;
								}
							}
						}
						catch(EOFException e) {
						}
						catch(IOException e) {
							e.printStackTrace();
						}
						}
					}while (verification==false);
					String user = username+","+password+",client";
					DataOutputStream fOut = null;
					try {
				        fOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(DATAFILE, true)));
				        fOut.writeUTF(user);
					}
					finally {
						fOut.close();
					}
					break;
				case 2:
					//operazioni di login
					boolean logfound = false;
					String role = null;
					System.out.print("Insert username: ");
					String loginUsername = reader.next();
					System.out.print("Insert password: ");
					String loginPassword = reader.next();
					try (DataInputStream flogin = new DataInputStream(new BufferedInputStream(new FileInputStream(DATAFILE)))){
						String person;
						String[] pdata;
						while(logfound == false) {
							person = flogin.readUTF();
							pdata = person.split(",");
							if(Objects.equals(loginUsername, pdata[0]) == true && Objects.equals(loginPassword, pdata[1]) == true) {
								logfound = true;
								role = pdata[2];
							}
						}
					}
					catch(EOFException e) {
					}
					catch(IOException e) {
						e.printStackTrace();
					}
					switch(role) {
					case "user":
						int menuclient = 0;
						System.out.print("0:Quit\n1:Search products");
						menuclient = reader.nextInt();
						switch(menuclient) {
						case 0:
							break;
						case 1:
							System.out.print("Insert name product(0 if empty): ");
							String nameProduct = reader.next();
							System.out.print("Insert name factory(0 if empty): ");
							String nameFactory = reader.next();
							System.out.print("Insert name min price(0 if empty): ");
							float minPrice = reader.nextFloat();
							System.out.print("Insert name max price(0 if empty): ");
							float maxPrice = reader.nextFloat();
							break;
						}
						break;
					case "employee":
						break;
					case "admin":
						break;
					}
					break;
			}
		}
		while(menu1!=0);
		reader.close();
	}
}
