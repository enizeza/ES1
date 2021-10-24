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
import java.util.HashMap;
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
		
		Map<Integer, Product> test = new HashMap<Integer, Product>();
		test = product;
		
		for (Product p : test.values()) {
			if((nameProduct.equals("0") || p.getName_product().equals(nameProduct)) && (nameFactory.equals("0") || p.getName_factory().equals(nameFactory)) && (priceMin == 0 || p.getPrice() >= priceMin) && (priceMax == 0 || p.getPrice() <= priceMax)) {
				System.out.println(p.printProduct());
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
