package it.unipr.zezacracolici;

/**
 * Employee is a subclass of client. It has some privileges more than Client.
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

public class Employee extends Client
{

	private Map<Integer, Product> product = new HashMap<Integer, Product>();
	private Map<Integer, Product> shipProduct = new HashMap<Integer, Product>();
	private Map<Integer, Product> buyProduct = new HashMap<Integer, Product>();	
	
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
			e.printStackTrace();
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
			while(true) {
				strproduct = fproducts.readUTF();
				prodData = strproduct.split(",");
				Product appo = new Product(prodData[1],Integer.parseInt(prodData[2]),prodData[3],Double.parseDouble(prodData[4]),Integer.parseInt(prodData[5]));				
				if (prodData[0].equals("SHIP")) {
					shipProduct.put(Integer.parseInt(prodData[2]),appo);
				}
				else if (prodData[0].equals("BUY")){
					buyProduct.put(Integer.parseInt(prodData[2]),appo);
				}
			}
		}
		catch(EOFException e) {
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void writeOperations(Map<Integer, Product> operations, String what) throws IOException{
		DataOutputStream fProdOut = null;
		try {
	        fProdOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(OPERATIONS, false)));
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

     * @param String username the person username 
     * @param String password the person password
     * 
     * @return void
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
     * @param int idship
     * 
     * @return void
     * 
     * @since 1.0
     */
	public void shipProduct(int idship) throws IOException{
		readOperations();
		readFile();
		
		Product i = shipProduct.get(idship);
		if (i == null) {
			System.out.println("Prodotto non esistente da spedire");
		} else {
			Product p = product.get(i.getId());
			if (p == null) {
				System.out.println("Prodotto non più esistente");
			} else {
				shipProduct.remove(i.getId());
				Product productToModify = product.remove(p.getId());
				int quantity = i.getQuantity();
				
				productToModify.setQuantity(productToModify.getQuantity()-quantity);
				product.put(p.getId(), productToModify);
				
				Product ship = product.get(idship);
				if(ship.getQuantity()==0) {
					System.out.println("Prodotto "+ship.getName_product()+" terminato in magazzino");
					Product appo = new Product(ship.getName_product(),idship,ship.getName_factory(),ship.getPrice(),0);
					buyProduct.put(idship,appo);
				}
				writeOperations(shipProduct,"SHIP");
				writeOperations(buyProduct,"BUY");
				
				writeFile();
			}
		}
	}
	
	
	/**
     * This method buy the products updating the quantity of product available in PRODUCTFILE
     * 
     * @param int idBuy
     * @param int buyQuantity
     * 
     * @return void
     * 
     * @since 1.0
     */
	public void buyProductEmployee(int idBuy, int buyQuantity) throws IOException{
		readOperations();
		readFile();
		
		Product i = buyProduct.get(idBuy);

		Product p = product.get(idBuy);
		if (p == null) {
			System.out.println("Prodotto non esistente");
		} else {
			if (i !=null) {
				buyProduct.remove(i.getId());
			}
			Product productToModify = product.remove(p.getId());
			
			productToModify.setQuantity(productToModify.getQuantity()+buyQuantity);
			product.put(p.getId(), productToModify);
		}	
		writeOperations(shipProduct,"SHIP");
		writeOperations(buyProduct,"BUY");
		
		writeFile();
	}
	
	/**
     * This method prints the operation to do reading them from OPERATIONS
     * 
     * @return void
     * 
     * @since 1.0
     */
	public void routine() {
		System.out.println("*******Operazioni da svolgere*******");
		System.out.println("TYPE,NAME,ID,FACTORY,PRICE,QUANTITY");
		try (DataInputStream fproducts = new DataInputStream(new BufferedInputStream(new FileInputStream(OPERATIONS)))){
			while(true) {
				System.out.println(fproducts.readUTF());
			}
		}
		catch(EOFException e) {
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
