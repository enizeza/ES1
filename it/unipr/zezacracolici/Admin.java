/**
 * 
 */
package it.unipr.zezacracolici;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author enize
 * @author leocraco
 */
public class Admin extends Employee
{	
	public static final String PRODUCTFILE = "product.csv";

    public Admin(String username, String password) {
        super(username, password);
        String user = username+","+password+",admin";
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
    	DataOutputStream fOut = null;
		try {
	        fOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(PRODUCTFILE, true)));
	        fOut.writeUTF(newProduct.toString());
		}
		finally {
			fOut.close();
		}
    }

    public void removeProduct (Product deleteProduct)
    {
        Warehouse.removeProduct(this, deleteProduct);
    }

}
