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
import java.util.ArrayList;
import java.util.List;

/**
 * @author enize
 * @author leocraco
 *
 */
public class Person {

	/**
	 * 
	 */
	
	public static final String DATAFILE = "users.csv";
	/*public static final String PRODUCTFILE = "product.csv";

	
	public void readFile(){
		List<Product> product = new ArrayList<Product>();
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
	}
	
	public void writeFile() throws IOException{
		DataOutputStream fOut = null;
		try {
	        fOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(PRODUCTFILE, false)));
	        for(Product element : product) {
	        	fOut.writeUTF(element.toString());
	        }
		}
		finally {
			fOut.close();
		}
	}*/
	
	private String username;
	private String password;
	
	public Person() {
		
		this.username = "";
		this.password = "";
	}
	
	public Person(String user, String pass) {
		this.username = user;
		this.password = pass;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void registration(String role) throws IOException {
		String user = getUsername()+","+getPassword()+","+role;
		
		DataOutputStream fOut = null;
		try {
	        fOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(DATAFILE, true)));
	        fOut.writeUTF(user);
		}
		finally {
			fOut.close();
		}
	}
}
