/**
 * 
 */
package it.unipr.zezacracolici;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author enize
 * @author leocraco
 */
public class Client extends Person
{

	/**
	 * 
	 */
	public static final String OPERATIONS = "operations.csv";
	public static final String PRODUCTFILE = "product.csv";

	private Map<Integer, Product> product = new HashMap<Integer, Product>();
	
	private void readFile(){
		try (DataInputStream fproducts = new DataInputStream(new BufferedInputStream(new FileInputStream(PRODUCTFILE)))){
			String strproduct;
			String[] prodData;
			while(true) {
				strproduct = fproducts.readUTF();
				prodData = strproduct.split(",");
				Product appo = new Product(prodData[0],Integer.parseInt(prodData[1]),prodData[2],Double.parseDouble(prodData[3]),Integer.parseInt(prodData[4]));
				product.put(Integer.parseInt(prodData[1]),appo);
			}
		}
		catch(EOFException e) {
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
		
	public Client() {
		
	}
	
	public Client(String username, String password) {
		super(username, password);
	}
	
	public void searchProduct(String nameProduct, String nameFactory, double priceMin, double priceMax) {
		readFile();
		for (Product value : product.values()) {
		    
		}
		
		if (nameProduct.equals("0")) {
			if (nameFactory.equals("0")) {
				if (priceMin == 0) {
					if (priceMax == 0) {
						for (Product value : product.values()) {
							System.out.print(value.printProduct());
						}
					}
					else {
						for (Product value : product.values()) {
							if(value.getPrice() <= priceMax) {
								System.out.print(value.printProduct());
							}
						}
					}
				}
				else {
					Map<Integer, Product> test = new HashMap<Integer, Product>();
					
					for (Product value : product.values()) {
						if(value.getPrice() >= priceMin) {
							test.put(value.getId(),value);
						}
					}
					
					if (priceMax == 0) {
						for (Product value : test.values()) {
							System.out.print(value.printProduct());
						}
					}
					else {
						for (Product value : test.values()) {
							if(value.getPrice() <= priceMax) {
								System.out.print(value.printProduct());
							}
						}
					}
				}
			}
			else {
				Map<Integer, Product> test = new HashMap<Integer, Product>();
				
				for (Product value : product.values()) {
					if(value.getName_factory().equals(nameFactory)) {
						test.put(value.getId(),value);
					}
				}
				
				if (priceMin == 0) {
					if (priceMax == 0) {
						for (Product value : test.values()) {
							System.out.print(value.printProduct());
						}
					}
					else {
						for (Product value : test.values()) {
							if(value.getPrice() <= priceMax) {
								System.out.print(value.printProduct());
							}
						}
					}
				}
				else {
					Map<Integer, Product> testPrice = new HashMap<Integer, Product>();
					
					for (Product value : test.values()) {
						if(value.getPrice() >= priceMin) {
							testPrice.put(value.getId(),value);
						}
					}
					
					if (priceMax == 0) {
						for (Product value : testPrice.values()) {
							System.out.print(value.printProduct());
						}
					}
					else {
						for (Product value : testPrice.values()) {
							if(value.getPrice() <= priceMax) {
								System.out.print(value.printProduct());
							}
						}
					}
				}
			}
		}
		else {
			Map<Integer, Product> test = new HashMap<Integer, Product>();
			
			for (Product value : product.values()) {
				if(value.getName_product().equals(nameProduct)) {
					test.put(value.getId(),value);
				}
			}
			
			if (nameFactory.equals("0")) {
				if (priceMin == 0) {
					if (priceMax == 0) {
						for (Product value : test.values()) {
							System.out.print(value.printProduct());
						}
					}
					else {
						for (Product value : test.values()) {
							if(value.getPrice() <= priceMax) {
								System.out.print(value.printProduct());
							}
						}
					}
				}
				else {
					Map<Integer, Product> test2 = new HashMap<Integer, Product>();
					
					for (Product value : test.values()) {
						if(value.getPrice() >= priceMin) {
							test2.put(value.getId(),value);
						}
					}
					
					if (priceMax == 0) {
						for (Product value : test2.values()) {
							System.out.print(value.printProduct());
						}
					}
					else {
						for (Product value : test2.values()) {
							if(value.getPrice() <= priceMax) {
								System.out.print(value.printProduct());
							}
						}
					}
				}
			}
			else {
				Map<Integer, Product> test2 = new HashMap<Integer, Product>();
				
				for (Product value : test.values()) {
					if(value.getName_factory().equals(nameFactory)) {
						test2.put(value.getId(),value);
					}
				}
				
				if (priceMin == 0) {
					if (priceMax == 0) {
						for (Product value : test2.values()) {
							System.out.print(value.printProduct());
						}
					}
					else {
						for (Product value : test2.values()) {
							if(value.getPrice() <= priceMax) {
								System.out.print(value.printProduct());
							}
						}
					}
				}
				else {
					Map<Integer, Product> testPrice = new HashMap<Integer, Product>();
					
					for (Product value : test.values()) {
						if(value.getPrice() >= priceMin) {
							//System.out.print(value.printProduct());
							testPrice.put(value.getId(),value);
						}
					}
					
					if (priceMax == 0) {
						for (Product value : testPrice.values()) {
							System.out.print(value.printProduct());
						}
					}
					else {
						for (Product value : testPrice.values()) {
							if(value.getPrice() <= priceMax) {
								System.out.print(value.printProduct());
							}
						}
					}
				}
			}	
		}
	}
	
	public void buyProduct(int idProduct,int number) throws IOException {
		readFile();
		Product product1 = product.get(idProduct);
		if (number > product1.getQuantity()){
			System.out.println("Troppi prodotti richiesti!! ne abbiamo disponibili: "+ product1.getQuantity());
		}
		else {
			DataOutputStream fProdOut = null;
			try {
		        fProdOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(OPERATIONS, false)));
		        product1.setQuantity(number);
		        fProdOut.writeUTF(product1.operationsToString("SHIP"));
			}
			finally {
				fProdOut.close();
			}
			
		}	
		
	}

}
