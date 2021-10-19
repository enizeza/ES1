/**
 * 
 */
package it.unipr.zezacracolici;

import java.util.List;

/**
 * @author enize
 * @author leocraco
 */
public class Admin extends Employee
{

    public Admin(String username, String password) {
        super(username, password);
    }

    public Admin() {
    }
    
    public void addEmployee (Employee [] employee,String username, String password)
    {
    	Employee employee2 = new Employee(username, password);
    	
    }
    
    public void addProduct (List<Product> product, Product newProduct)
    {
    	product.add(newProduct);
    }

    /*public void removeProduct (Warehouse warehouse, Product deleteProduct)
    {
        Warehouse.removeProduct(this, deleteProduct);
    }*/

}
