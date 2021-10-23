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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
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
		
		//Admin admin = new Admin("root","root");
		List<Product> product = new ArrayList<Product>();
		
		//Product product1 = new Product("Mac",1,"Apple",1000.00,2);
		//Product product2 = new Product("360",2,"hp",1000.00,2);
		
		//admin.addProduct(product1);
		//admin.addProduct(product2);
		
		System.out.print("Insert username: ");
		String adminUsername = reader.next();
		System.out.print("Insert password: ");
		String adminPassword = reader.next();
		
		Admin admin = new Admin(adminUsername,adminPassword);
		//admin.registration("admin");
		
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
					Client client = new Client(username,password);
					client.registration("client");
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
					
					case "client":
						Client logClient = new Client(loginUsername,loginPassword);
						int menuclient = 0;
						do {
							System.out.println("0:Quit\n1:Search products\n2:buy product");
							menuclient = reader.nextInt();
							switch(menuclient) {
							case 0:
								break;
							case 1:
								try (DataInputStream fproducts = new DataInputStream(new BufferedInputStream(new FileInputStream(PRODUCTFILE)))){
									String strproduct;
									String[] prodData;
									while(true) {
										strproduct = fproducts.readUTF();
										prodData = strproduct.split(",");
										Product appo = new Product(prodData[0],Integer.parseInt(prodData[1]),prodData[2],Double.parseDouble(prodData[3]),Integer.parseInt(prodData[4]));
										product.add(appo);
									}
								}
								catch(EOFException e) {
								}
								catch(IOException e) {
									e.printStackTrace();
								}
								System.out.print("Insert name product(0 if empty): ");
								String nameProduct = reader.next();
								System.out.print("Insert name factory(0 if empty): ");
								String nameFactory = reader.next();
								System.out.print("Insert min price(0 if empty): ");
								double minPrice = reader.nextDouble();
								System.out.print("Insert max price(0 if empty): ");
								double maxPrice = reader.nextDouble(); 
								
								List<Product> productTest = new ArrayList<Product>();
								for (Product p : product) {
									productTest.add(p);
									if((Objects.equals(nameProduct,"0")==false) && (Objects.equals(nameProduct,p.getName_product())==false)) {
										productTest.remove(p);
									}
									else if(Objects.equals(nameFactory,"0")==false && Objects.equals(nameFactory,p.getName_factory())==false) {
										productTest.remove(p);
									}else if(minPrice != 0 && p.getPrice() < minPrice) {
										productTest.remove(p);
									}else if(maxPrice != 0 && p.getPrice() > maxPrice) {
										productTest.remove(p);
									}
							    }
								for (Product prod : productTest) {
									System.out.println(prod.printProduct());
								}
								
								break;
							case 2:
								//logClient.buyProduct(product1, 1);
								break;
							}
						}while(menuclient != 0);
						break;
						
					case "employee":
						Employee logEmployee = new Employee(loginUsername,loginPassword);
						int menuemployee = 0;
						do {
							System.out.println("0:Quit\n1:add employee\n2:add product\n3:remove product ");
							menuemployee = reader.nextInt();
							switch(menuemployee) {
							case 0:
								break;
							case 1:
								break;
							case 2:
								break;
							}
						}while(menuemployee != 0);
						break;
						
					case "admin":
						int menuadmin = 0;
						do {
							System.out.println("0:Quit\n1:add employee\n2:add product\n3:remove product ");
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
								System.out.print("Insert quantity: ");
								int newQuantity = reader.nextInt();
								//manca id automatizzato
								//Product newProduct = new Product(newName,3,newFactory,newPrice,newQuantity);
								//admin.addProduct(newProduct);
								break;
							case 3:
								System.out.print("Insert Id for delete: ");
								int idDelete = reader.nextInt();
								//Product deleteProduct = new Product(newName,3,newFactory,newPrice,newQuantity);
								admin.removeProduct(idDelete);
								break;
							}
						}while(menuadmin != 0);
						break;
					}
					break;
			}
		}
		while(menu1!=0);
		reader.close();
	}
}
