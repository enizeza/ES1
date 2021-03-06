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
 * Admin is a subclass of employee. It has some privileges more than Employee.
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 * @version  1.0
 * @since    1.0
 */

public class Admin extends Employee
{	
	private static final String PRODUCTFILE = "product.csv";
	private static final String OPERATIONS = "operations.csv";
	
	private Map<Integer, Product> productMap = new HashMap<Integer, Product>();
	private Map<Integer, Product> shipProduct = new HashMap<Integer, Product>();
	
	
	private void readFile(){
		try (DataInputStream fproducts = new DataInputStream(new BufferedInputStream(new FileInputStream(PRODUCTFILE)))){
			String strproduct;
			String[] prodData;
			while(true) {
				strproduct = fproducts.readUTF();
				prodData = strproduct.split(",");
				Product appo = new Product(prodData[0],Integer.parseInt(prodData[1]),prodData[2],Double.parseDouble(prodData[3]),Integer.parseInt(prodData[4]));
				productMap.put(Integer.parseInt(prodData[1]),appo);
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
	        for(Product element : productMap.values()) {
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
				shipProduct.put(Integer.parseInt(prodData[2]),appo);
			}
		}
		catch(EOFException e) {
		}
		catch(IOException e) {
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
     * This constructor generates an Admin object.
     *

     * @param username the admin username 
     * @param password the admin password
     * 
     * @since 1.0
     */
    public Admin(String username, String password) {
        super(username, password);
    }
    
    /**
     * Empty constructor for the object
     * 
     * @since 1.0
     */
    public Admin() {
    }
    
    
    /**
     * This method is wrapper for Person's registration method.
     * Only subscribe employees
     * 
     * @param username username of the employee to register
     * @param password password of the employee to register
     * 
     * @throws IOException input output
     * 
     * @since 1.0
     */
    public void addEmployee (String username, String password) throws IOException
    {
    	Employee employee = new Employee(username, password);
    	employee.registration("employee");
    }
    
    
    /**
     * This method add a new Product into the file PRODUCTFILE.
     * 
     * @param newProduct the product to add
     * 
     * @throws IOException input output
     * 
     * @since 1.0
     */
    public void addProduct (Product newProduct) throws IOException
    {
    	File fControl = new File(PRODUCTFILE);
    	if(fControl.exists()) {
    		readFile();
    	}
    	Product p = productMap.get(newProduct.getId());
		if (p != null) {
			System.out.println("ID already exists!!!");
		} else {
			DataOutputStream fOut = null;
			try {
		        fOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(PRODUCTFILE, true)));
		        fOut.writeUTF(newProduct.toString());
			}
			finally {
				fOut.close();
			}
		}		
    }
    
    /**
     * This method remove a Product from the file PRODUCTFILE.
     * 
     * @param id the id of the product to remove
     * 
     * @throws IOException input output
     * 
     * @since 1.0
     */
    public void removeProduct (int id) throws IOException
    {
    	File fControl = new File(PRODUCTFILE);
    	if(fControl.exists()) {
    		readFile();
    	}
    	Product p = productMap.get(id);
		if (p == null) {
			System.out.println("ID doesn't exists!!!");
		} else {
			productMap.remove(id);
			writeFile();
			readOperations();
			
			Product test1 = shipProduct.get(id);
			if (test1 != null) {
				shipProduct.remove(id);
			} 
			writeOperations(shipProduct,"SHIP");
		}
    }
}
