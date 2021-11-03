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
 * Employee is a subclass of client. It has some privileges more than Client.
 * 
 * @author   enize
 * @author   leocraco
 * 
 * @version  1.0
 * @since    1.0
 */

public class Employee extends Client
{

	private Map<Integer, Product> product = new HashMap<Integer, Product>();
	private Map<Integer, Product> shipProduct = new HashMap<Integer, Product>();
	private Map<Integer, Product> buyProduct = new HashMap<Integer, Product>();
	//private Map<Integer, Product> operations = new HashMap<Integer, Product>();
	
	private static final String PRODUCTFILE = "product.csv";
	private static final String OPERATIONS = "operations.csv";

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
		}
	}
	
	private void writeFile() throws IOException{
		DataOutputStream fProdOut = null;
		try {
	        fProdOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(PRODUCTFILE, false)));
	        for(Product element : product.values()) {
	        	fProdOut.writeUTF(element.toString());
	        }
		}
		finally {
			fProdOut.close();
		}
	}
	
	private void readOperations() {
		try (DataInputStream fproducts = new DataInputStream(new BufferedInputStream(new FileInputStream(OPERATIONS)))){
			String strproduct;
			String[] prodData;
			int n = 1;
			while(true) {
				strproduct = fproducts.readUTF();
				prodData = strproduct.split(",");
				Product appo = new Product(prodData[1],Integer.parseInt(prodData[2]),prodData[3],Double.parseDouble(prodData[4]),Integer.parseInt(prodData[5]));				
				if (prodData[0].equals("SHIP")) {
					shipProduct.put(n,appo);
				}
				else if (prodData[0].equals("BUY")){
					buyProduct.put(n,appo);
				}
				n = n+1;
			}
		}
		catch(EOFException e) {
		}
		catch(IOException e) {
		}
		System.out.println(buyProduct);
	}
	
	private void writeOperations(Map<Integer, Product> operations, String what, boolean mod) throws IOException{
		DataOutputStream fProdOut = null;
		try {
	        fProdOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(OPERATIONS, mod)));
	        for (Product value : operations.values()) {
	        	fProdOut.writeUTF(value.operationsToString(what));
	        }
	        
		}
		finally {
			fProdOut.close();
		}
	}
	
	/**
	 * Empty constructor for the object
	 * 
	 * @since 1.0
	 */
	public Employee() {
	}
	
	/** 
     * This constructor generates an Employee object.
     *
     * @param username the person username 
     * @param password the person password
     * 
     * @since 1.0
     */
	public Employee(String username, String password) {
		super(username, password);
	}
	
	/**
     * This method ship the products that the client bought.
     * Updating the quantity of product available in PRODUCTFILE
     * Removing from OPERATIONS the operation of shipping
     * 
     * @param idship id of the product to ship
     * 
     * @throws IOException input output
     * 
     * @since 1.0
     */
	public void shipProduct(int idship) throws IOException{
		readOperations();
		readFile();
		
		Product i = shipProduct.get(idship);
		if (i == null) {
			System.out.println("No products to ship");
		} else {
			Product p = product.get(i.getId());
			if (p == null) {
				System.out.println("Product doesn't exist anymore");
			} else {
				shipProduct.remove(idship);
				Product productToModify = product.remove(p.getId());
				int quantity = i.getQuantity();
				
				productToModify.setQuantity(productToModify.getQuantity()-quantity);
				product.put(p.getId(), productToModify);
				
				Product ship = product.get(p.getId());
				if(ship.getQuantity()==0) {
					System.out.println("Product "+ship.getName_product()+" run out in warehouse");
					Product appo = new Product(ship.getName_product(),ship.getId(),ship.getName_factory(),ship.getPrice(),0);
					buyProduct.put(0,appo);
				}
				writeOperations(shipProduct,"SHIP",false);
				writeOperations(buyProduct,"BUY",true);
				
				writeFile();
			}
		}
	}
	
	/**
     * This method buy the products updating the quantity of product available in PRODUCTFILE
     * 
     * @param idBuy id of the product to buy
     * @param buyQuantity how many products to buy
     * 
     * @throws IOException input output
     * 
     * @since 1.0
     */
	public void buyProductEmployee(int idBuy, int buyQuantity) throws IOException{
		readOperations();
		readFile();
		
		Product i = buyProduct.get(idBuy);
		if (i == null) {
			System.out.println("No products to buy");
		} else {
			Product p = product.get(i.getId());
			if (p == null) {
				System.out.println("Nonexistent product");
			} else {
				buyProduct.remove(idBuy);
				p.setQuantity(p.getQuantity()+buyQuantity);
				product.replace(p.getId(), p);
			}
		}
		writeOperations(shipProduct,"SHIP",false);
		writeOperations(buyProduct,"BUY",true);
		
		writeFile();
	}
	
	/**
     * This method prints the operation to do reading them from OPERATIONS
     * 
     * @since 1.0
     */
	
	public void routine() {
		File fControl = new File(OPERATIONS);
		int n = 1;
		if (fControl.exists()) {
			System.out.println("*******Operations to carry out*******");
			System.out.println("TYPE,NAME,ID,FACTORY,PRICE,QUANTITY");
			try (DataInputStream fproducts = new DataInputStream(new BufferedInputStream(new FileInputStream(OPERATIONS)))){
				while(true) {
					System.out.println(n + " " + fproducts.readUTF());
					n = n+1;
				}
			}
			catch(EOFException e) {
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("*******No operations to be performed*******");
		}
	}

}
