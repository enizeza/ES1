/**
 * 
 */
package it.unipr.zezacracolici;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author enize
 * @author leocraco
 *
 */
public class Main {
	
	public static final String DATAFILE = "users.csv";
	public static final String PRODUCTFILE = "product.csv";
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Scanner reader = new Scanner(System.in);
		int menu1=0;
		boolean admins = false;
		String adminUser="", adminPassword="";
		Admin admin;
		
		File file2 = new File(DATAFILE);
		if (file2.exists()) {
		try (DataInputStream fin = new DataInputStream(new BufferedInputStream(new FileInputStream(DATAFILE)))){
			String u;
			String[] usplitted;
			while(true) {
				u = fin.readUTF();
				usplitted = u.split(",");
				if(Objects.equals("admin", usplitted[2]) == true) {
					admins = true;
					adminUser = usplitted[0];
					adminPassword = usplitted[1];
				}
			}
		}
		catch(EOFException e) {
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		}
		
		if (admins != true) {
			System.out.print("\nInsert username Admin: ");
			String adminUsername2 = reader.next();
			System.out.print("Insert password Admin: ");
			String adminPassword2 = reader.next();
			
			admin = new Admin(adminUsername2,adminPassword2);
			admin.registration("admin");
		}
		else {
			admin = new Admin(adminUser,adminPassword);
		}
		
		do
		{
			System.out.println("\n0:Quit\n1:Register\n2:LogIn");
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
					Client client = new Client(username,password);
					client.registration("client");
					break;
					
				case 2:
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
					
					case "client":
						Client logClient = new Client(loginUsername,loginPassword);
						int menuclient = 0;
						do{
							System.out.println("\n*******CLIENT*******\n");
							System.out.println("\n0:Quit\n1:search products\n2:buy product");
							menuclient = reader.nextInt();
							switch(menuclient) {
								case 0:
									break;
								case 1:
									System.out.print("Insert name product to search(0 if empty): ");
									String nameProduct = reader.next();
									System.out.print("Insert name factory to search(0 if empty): ");
									String nameFactory = reader.next();
									System.out.print("Insert min price to search(0 if empty): ");
									double minPrice = reader.nextDouble();
									System.out.print("Insert max price to search(0 if empty): ");
									double maxPrice = reader.nextDouble(); 
									logClient.searchProduct(nameProduct,nameFactory,minPrice,maxPrice);
									break;
								case 2:
									System.out.print("Insert ID product to buy: ");
									int buyId = reader.nextInt();
									System.out.print("Insert quantity of product to buy: ");
									int buyQuantity = reader.nextInt();
									logClient.buyProduct(buyId, buyQuantity);
									break;
							}
						}
						while(menuclient != 0);
						break;
						
					case "employee":
						Employee logEmployee = new Employee(loginUsername,loginPassword);
						int menuemployee = 0;
						do {
							System.out.println("\n*******EMPLOYEE*******\n");
							logEmployee.routine();
							System.out.println("\n0:Quit\n1:ship product\n2:buy products warehouse\n3:search products");
							menuemployee = reader.nextInt();
							switch(menuemployee) {
								case 0:
									break;
								case 1:
									System.out.print("Insert ID product to ship: ");
									int shipId = reader.nextInt();
									logEmployee.shipProduct(shipId);
									break;
								case 2:
									System.out.print("Insert ID product to buy: ");
									int buyId = reader.nextInt();
									System.out.print("Insert quantity of product to buy: ");
									int buyQuantity = reader.nextInt();
									logEmployee.buyProductEmployee(buyId,buyQuantity);
									break;
								case 3:
									System.out.print("Insert name product to search(0 if empty): ");
									String nameProduct = reader.next();
									System.out.print("Insert name factory to search(0 if empty): ");
									String nameFactory = reader.next();
									System.out.print("Insert min price to search(0 if empty): ");
									double minPrice = reader.nextDouble();
									System.out.print("Insert max price to search(0 if empty): ");
									double maxPrice = reader.nextDouble(); 
									logEmployee.searchProduct(nameProduct,nameFactory,minPrice,maxPrice);
									break;
							}
						}
						while(menuemployee != 0);
						break;
						
					case "admin":
						int menuadmin = 0;
						do {
							System.out.println("\n*******ADMIN*******\n");
							admin.routine();
							System.out.println("\n0:Quit\n1:add employee\n2:add product\n3:remove product\n4:ship product\n5:buy products warehouse\n6:search products");
							menuadmin = reader.nextInt();
							switch(menuadmin) {
							case 0:
								break;
							case 1:
								System.out.print("Insert username: ");
								String employeeUsername = reader.next();
								System.out.print("Insert password: ");
								String employeePassword = reader.next();
								admin.addEmployee(employeeUsername, employeePassword);
								break;
							case 2:
								System.out.print("Insert product name: ");
								String newName = reader.next();
								System.out.print("Insert factory name: ");
								String newFactory = reader.next();
								System.out.print("Insert product price: ");
								Double newPrice = reader.nextDouble();
								System.out.print("Insert product ID: ");
								int newID = reader.nextInt();
								Product newProduct = new Product(newName,newID,newFactory,newPrice,0);
								admin.addProduct(newProduct);
								break;
							case 3:
								System.out.print("Insert Id of product to delete: ");
								int idDelete = reader.nextInt();
								admin.removeProduct(idDelete);
								break;	

							case 4:
								System.out.print("Insert ID product to ship: ");
								int shipId = reader.nextInt();
								admin.shipProduct(shipId);
								break;
							case 5:
								System.out.print("Insert ID product to buy: ");
								int buyId = reader.nextInt();
								System.out.print("Insert quantity of product to buy: ");
								int buyQuantity = reader.nextInt();
								admin.buyProductEmployee(buyId,buyQuantity);
								break;
							case 6:
								System.out.print("Insert name product to search(0 if empty): ");
								String nameProduct = reader.next();
								System.out.print("Insert name factory to search(0 if empty): ");
								String nameFactory = reader.next();
								System.out.print("Insert min price to search(0 if empty): ");
								double minPrice = reader.nextDouble();
								System.out.print("Insert max price to search(0 if empty): ");
								double maxPrice = reader.nextDouble(); 
								admin.searchProduct(nameProduct,nameFactory,minPrice,maxPrice);
								break;

							}
						}
						while(menuadmin != 0);
					break;
					}
			break;
			}
		}
		while(menu1!=0);
		reader.close();
	}
}
