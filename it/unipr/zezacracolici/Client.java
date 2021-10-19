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
 */
public class Client extends Person
{

	/**
	 * 
	 */
	private List<Product> product = new ArrayList<Product>();
	
	public static final String DATAFILE = "users.csv";
	public static final String PRODUCTFILE = "product.csv";

	
	public void readFile(){
		//List<Product> product = new ArrayList<Product>();
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
		DataOutputStream fProdOut = null;
		try {
	        fProdOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(PRODUCTFILE, false)));
	        for(Product element : product) {
	        	fProdOut.writeUTF(element.toString());
	        }
		}
		finally {
			fProdOut.close();
		}
	}
	
	public Client() {
		
	}
	
	public Client(String username, String password) {
		super(username, password);
	}
	
	/*public Product searchProduct(String nameProduct, String nameFactory, int priceMin, int priceMax) {
		
	}*/
	
	public void buyProduct(Product product1,int number) throws IOException {
		if (number > product1.getQuantity()){
			System.out.println("Troppi prodotti richiesti!!");
		}
		else {
			readFile();	
			//product1.setQuantity(product1.getQuantity() - number);
			Product newobj = new Product(product1.getName_product(), product1.getId(), product1.getName_factory(), product1.getPrice(), product1.getQuantity() - number);
			//product.set(product.indexOf(product1) , newobj);
			product.add(newobj);
			product.remove(product1);
			System.out.println(product);
			writeFile();
		}	
		
	}

}
