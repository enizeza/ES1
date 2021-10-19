/**
 * 
 */
package it.unipr.zezacracolici;

/**
 * @author enize
 * @author leocraco
 *
 */
public class Product {

	/**
	 * 
	 */
	private String name_product;
	private int id;
	private String name_factory;
	private double price;
	private int quantity;
	
	public Product() {
		this.name_product = "";
		this.id = 0;
		this.name_factory = "";
		this.price = 0.00;
		this.quantity = 0;
	}
	
	public Product(String name_product, int id, String name_factory, double price, int quantity) {
		this.name_product = name_product;
		this.id = id;
		this.name_factory = name_factory;
		this.price = price;
		this.quantity = quantity;
	}
	
	public String getName_product(){
		return this.name_product;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName_factory(){
		return this.name_factory;
	}
	
	public double getPrice(){
		return this.price;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public int buyProduct(int number) {
		if (number > this.quantity){
			System.out.println("Troppi prodotti richiesti!!");
			return -1;
		}
		else
			return (this.quantity = this.quantity - number);
	}
	
	public void addProductQuantity(int number) {
		this.quantity = this.quantity + number;
	}

}
