/**
 * 
 */
package it.unipr.zezacracolici;

/**
 * @author enize
 * @author leocraco
 */
public class Client extends Person
{

	/**
	 * 
	 */
	public Client() {
		
	}
	
	public Client(String username, String password) {
		super(username, password);
	}
	
	/*public Product searchProduct(String nameProduct, String nameFactory, int priceMin, int priceMax) {
		
	}*/
	
	public Product buyProduct(int number) {
		Product product = new Product();
		product.buyProduct(number);
		return product;
	}

}
