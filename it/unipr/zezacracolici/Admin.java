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
    	Employee(username,password);
    }
    
    public void addProduct (Warehouse club, Product newMember)
    {
        Warehouse.addMember(this, newMember);
    }

    public void removeProduct (Warehouse club, Product deleteMember)
    {
        Warehouse.removeMember(this, deleteMember);
    }

}
