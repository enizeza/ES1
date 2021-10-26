package it.unipr.zezacracolici;

/**
 * Product is in charge of saving a product object with its properties.
 * Each product has a name_product, id, name_factory, price and quantity.
 * 
 * @author   enize
 * @author   leocraco
 * 
 * @version  1.0
 * @since    1.0
 */

public class Product {
	private String name_product;
	private int id;
	private String name_factory;
	private double price;
	private int quantity;
	
	/**
     * Empty constructor for the object
     * 
     * @since 1.0
     */
	public Product() {
		this.name_product = "";
		this.id = 0;
		this.name_factory = "";
		this.price = 0.00;
		this.quantity = 0;
	}
	
	/** 
     * This constructor generates a Product object.
     *

     * @param name_product the product name_product 
     * @param id the product id
     * @param name_factory the product name_factory
     * @param price the product price
     * @param quantity the product quantity
     * 
     * @since 1.0
     */
	public Product(String name_product, int id, String name_factory, double price, int quantity) {
		this.name_product = name_product;
		this.id = id;
		this.name_factory = name_factory;
		this.price = price;
		this.quantity = quantity;
	}
	
	/** 
     * This method gets the Product's name_product.
     *
     * @return String the Product's name_product.
     * 
     * @since 1.0
     */
	public String getName_product(){
		return this.name_product;
	}
	
	/** 
     * This method gets the Product's id.
     *
     * @return int the Product's id.
     * 
     * @since 1.0
     */
	public int getId(){
		return this.id;
	}
	
	/** 
     * This method gets the Product's name_factory.
     *
     * @return String the Product's name_factory.
     * 
     * @since 1.0
     */
	public String getName_factory(){
		return this.name_factory;
	}
	
	/** 
     * This method gets the Product's price.
     *
     * @return double the Product's price.
     * 
     * @since 1.0
     */
	public double getPrice(){
		return this.price;
	}
	
	/** 
     * This method gets the Product's quantity.
     *
     * @return int the Product's quantity.
     * 
     * @since 1.0
     */
	public int getQuantity() {
		return this.quantity;
	}
	
	/** 
     * This method sets the Product's quantity
     * 
     * @param number quantity to set
     * 
     * @since 1.0
     */
	public void setQuantity(int number) {
		this.quantity = number;
	}
	
	/** 
     * This method compose a string for writing Product object into file CSV.
     *
     * @return String to write into the file
     * 
     * @since 1.0
     */
	public String toString() {
        return getName_product() + "," + getId() + "," + getName_factory() + "," + getPrice() + "," + getQuantity();
    }
	
	/** 
     * This method compose a string for writing Product object into file CSV with the operation.
     *
     * @param operation operation to save on file
     *
     * @return String to write into the file
     * 
     * @since 1.0
     */
	public String operationsToString(String operation) {
        return operation + "," +getName_product() + "," + getId() + "," + getName_factory() + "," + getPrice() + "," + getQuantity();
    }
	
	/** 
     * This method compose a string for printing Product object into the console.
     *
     * @return String to show in console
     * 
     * @since 1.0
     */
	public String printProduct() {
		return "\n*************************\nProduct ID = " + getId() +"\nProduct name = " + getName_product() + "\nFactory name = " + getName_factory() + "\nPrice = " + getPrice() + "\n";
	}
}
