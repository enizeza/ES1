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
	private int available;
	
	public Product() {
		this.name_product = "";
		this.id = 0;
		this.name_factory = "";
		this.price = 0.00;
		this.available = 0;
	}
	
	public Product(String name1, int i, String name2, double p) {
		this.name_product = name1;
		this.id = i;
		this.name_factory = name2;
		this.price = p;
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

}
