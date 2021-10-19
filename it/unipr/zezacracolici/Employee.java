/**
 * 
 */
package it.unipr.zezacracolici;

/**
 * @author enize
 * @author leocraco
 */
public class Employee extends Client
{

	/**
	 * 
	 */	
	public Employee() {
	}
	
	public Employee(String username, String password) {
		super(username, password);
	}
	
	/*public Product buyProduct(int number) {
		Product product = new Product();
		product.buyProduct(number);
		return product;
	}*/
	
	public void addProductQuantity(int number) {
		Product product = new Product();
		product.addProductQuantity(number);
	}

}
