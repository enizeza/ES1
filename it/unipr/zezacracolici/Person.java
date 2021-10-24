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
import java.util.List;

/**
 * @author enize
 * @author leocraco
 *
 */
public class Person {

	/**
	 * 
	 */
	
	public static final String DATAFILE = "users.csv";
	
	private String username;
	private String password;
	
	public Person() {
		
		this.username = "";
		this.password = "";
	}
	
	public Person(String user, String pass) {
		this.username = user;
		this.password = pass;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void registration(String role) throws IOException {
		String user = getUsername()+","+getPassword()+","+role;
		
		DataOutputStream fOut = null;
		try {
	        fOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(DATAFILE, true)));
	        fOut.writeUTF(user);
		}
		finally {
			fOut.close();
		}
	}
}
