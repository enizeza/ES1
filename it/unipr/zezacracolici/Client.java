package it.unipr.zezacracolici;

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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Client is a subclass of person. It has some privileges more than Person.
 * 
 * @author   enize
 * @author   leocraco
 * 
 * @version  1.0
 * @since    1.0
 */

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

     * @param username the person username 
     * @param password the person password
     * 
     * @since 1.0
     */
	public Client(String username, String password) {
		super(username, password);
	}
	
	/**
     * Prints products that respect certain condition of research
     * 
     * @param nameProduct product's name
     * @param nameFactory factory's name
     * @param priceMin minimum price
     * @param priceMax maximum price
     * 
     * @since 1.0
     */
	public void searchProduct(String nameProduct, String nameFactory, double priceMin, double priceMax) {
		Map<Integer, Product> test = new HashMap<Integer, Product>();
		
		File fControl = new File(PRODUCTFILE);
		if(fControl.exists()) {
			readFile();
			test = product;
			
			for (Product p : test.values()) {
				if((nameProduct.equals("0") || p.getName_product().equals(nameProduct)) && (nameFactory.equals("0") || p.getName_factory().equals(nameFactory)) && (priceMin == 0 || p.getPrice() >= priceMin) && (priceMax == 0 || p.getPrice() <= priceMax)) {
					System.out.println(p.printProduct());
				}else {
					System.out.println("Products not found");
				}
			}
		}else{
			System.out.println("There currently aren't any products available");
			System.out.println("Try again later");
		}
	}
	
	/**
     * Add to file OPERATIONS, the operation to ship certain quantities of a product
     * 
     * @param idProduct id of the product to order
     * @param number quantity of the products to order
     * 
     * @throws IOException input output
     * 
     * @since 1.0
     */
	public void buyProduct(int idProduct,int number) throws IOException {
		File fControl = new File(PRODUCTFILE);
		if(fControl.exists()) {
			readFile();
			Product product1 = product.get(idProduct);
			if (number > product1.getQuantity()){
				System.out.println("Too many products requested!! Currently available: "+ product1.getQuantity());
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
		}else {
			System.out.println("There currently aren't any products available");
			System.out.println("Try again later");
		}
	}

}
