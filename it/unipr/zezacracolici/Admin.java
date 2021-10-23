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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author enize
 * @author leocraco
 */
public class Admin extends Employee
{	
	private static final String PRODUCTFILE = "product.csv";
	private static final String OPERATIONS = "operations.csv";
	
	private List<Product> product = new ArrayList<Product>();
	public Map<Integer, Product> productMap = new HashMap<Integer, Product>();
	
	
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
			e.printStackTrace();
		}
	}
	

    public Admin(String username, String password) {
        super(username, password);
    }

    public Admin() {
    }
    
    public void addEmployee (String username, String password) throws IOException
    {
    	Employee employee = new Employee(username, password);
    	employee.registration("employee");
    }
    
    public void addProduct (Product newProduct) throws IOException
    {
    	readFile();
    	Product p = productMap.get(newProduct.getId());
		if (p != null) {
			System.out.println("ID già esistente!!!");
		} else {
			DataOutputStream fOut = null;
			try {
		        fOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(PRODUCTFILE, true)));
		        fOut.writeUTF(newProduct.toString());
			}
			finally {
				fOut.close();
			}
			
			DataOutputStream fProdOut = null;
			try {
		        fProdOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(OPERATIONS, false)));
		        fProdOut.writeUTF(newProduct.operationsToString("BUY"));
			}
			finally {
				fProdOut.close();
			}
		}		
    }

    public void removeProduct (int id) throws IOException
    {
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
		for(Product element : product) {
        	if (element.getId() == id) {
        		product.remove(element);
        	}
        }
		
		System.out.println(product);
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

}
