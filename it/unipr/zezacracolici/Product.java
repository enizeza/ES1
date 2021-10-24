/**
 * 
 */
package it.unipr.zezacracolici;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	
	public void setQuantity(int appo) {
		this.quantity = appo;
	}
	
	public String toString() {
        return getName_product() + "," + getId() + "," + getName_factory() + "," + getPrice() + "," + getQuantity();
    }
	
	public String operationsToString(String operation) {
        return operation + "," +getName_product() + "," + getId() + "," + getName_factory() + "," + getPrice() + "," + getQuantity();
    }
	
	public String printProduct() {
		return "\n*************************\nProduct ID = " + getId() +"\nProduct name = " + getName_product() + "\nFactory name = " + getName_factory() + "\nPrice = " + getPrice() + "\n";
	}
}
