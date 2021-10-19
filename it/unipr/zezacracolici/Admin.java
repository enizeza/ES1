/**
 * 
 */
package it.unipr.zezacracolici;

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
    
    public void addEmployee (String username, String password)
    {
    	Warehouse.addEmployee(username,password);
    }
    
    public void addProduct (Warehouse warehouse, Product newProduct)
    {
        Warehouse.addProduct(this, newProduct);
    }

    public void removeProduct (Warehouse warehouse, Product deleteProduct)
    {
        Warehouse.removeProduct(this, deleteProduct);
    }

}
