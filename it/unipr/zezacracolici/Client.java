package it.unipr.zezacracolici;

/**
 * Client is a subclass of person. It has some privileges more than Person.
 * 
 * @author   enize
 * @author   leocraco
 * 
 * @version  1.0
 * @since    1.0
 */


/**
 * Libraries for writing and reading file and control Exceptions
 * Libraries for using ArrayList, HasMap
 * 
 * @version     1.0
 * @since       1.0
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Client extends Person
{
	private static final String OPERATIONS = "operations.csv";
	private static final String PRODUCTFILE = "product.csv";

	private Map<Integer, Product> product = new HashMap<Integer, Product>();
	
	private void readFile(){
		try (DataInputStream fproducts = new DataInputStream(new BufferedInputStream(new FileInputStream(PRODUCTFILE)))){
			String strproduct;
			String[] prodData;
			while(true) {
				strproduct = fproducts.readUTF();
				prodData = strproduct.split(",");
				Product appo = new Product(prodData[0],Integer.parseInt(prodData[1]),prodData[2],Double.parseDouble(prodData[3]),Integer.parseInt(prodData[4]));
				product.put(Integer.parseInt(prodData[1]),appo);
			}
		}
		catch(EOFException e) {
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Empty constructor for the object
	 * 
	 * @since 1.0
	 */
	public Client() {	
	}
	
	/** 
     * This constructor generates an Employee object.
     *

     * @param String username the person username 
     * @param String password the person password
     * 
     * @return void
     * 
     * @since 1.0
     */
	public Client(String username, String password) {
		super(username, password);
	}
	
	/**
     * Prints products that respect certain condition of research
     * 
     * @param String nameProduct
     * @param String nameFactory
     * @param double priceMin
     * @param double priceMax
     * 
     * @return void
     * 
     * @since 1.0
     */
	public void searchProduct(String nameProduct, String nameFactory, double priceMin, double priceMax) {
		readFile();
		
		Map<Integer, Product> test = new HashMap<Integer, Product>();
		test = product;
		
		for (Product p : test.values()) {
			if((nameProduct.equals("0") || p.getName_product().equals(nameProduct)) && (nameFactory.equals("0") || p.getName_factory().equals(nameFactory)) && (priceMin == 0 || p.getPrice() >= priceMin) && (priceMax == 0 || p.getPrice() <= priceMax)) {
				System.out.println(p.printProduct());
			}
		}
	}
	
	/**
     * Add to file OPERATIONS, the operation to ship certain quantities of a product
     * 
     * @param int idProduct
     * @param int number
     * 
     * @return void
     * 
     * @since 1.0
     */
	public void buyProduct(int idProduct,int number) throws IOException {
		readFile();
		Product product1 = product.get(idProduct);
		if (number > product1.getQuantity()){
			System.out.println("Troppi prodotti richiesti!! ne abbiamo disponibili: "+ product1.getQuantity());
		}
		else {
			DataOutputStream fProdOut = null;
			try {
		        fProdOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(OPERATIONS, false)));
		        product1.setQuantity(number);
		        fProdOut.writeUTF(product1.operationsToString("SHIP"));
			}
			finally {
				fProdOut.close();
			}
			
		}	
		
	}

}
