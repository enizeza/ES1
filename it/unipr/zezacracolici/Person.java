package it.unipr.zezacracolici;

/**
 * Libraries for writing into a file and control Exceptions
 * 
 * @version     1.0
 * @since       1.0
 */

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Person is in charge of saving a person object with its properties.
 * Each person has an username and a password.
 * 
 * @author   enize
 * @author   leocraco
 * 
 * @version  1.0
 * @since    1.0
 */

public class Person {
	
	private static final String DATAFILE = "users.csv";
	
	private String username;
	private String password;
	
	
	/**
     * Empty constructor for the object
     * 
     * @since 1.0
     */
	public Person() {
		
		this.username = "";
		this.password = "";
	}
	
	/** 
     * This constructor generates a Person object.
     *

     * @param user the person username 
     * @param pass the person password
     * 
     * 
     * @since 1.0
     */
	public Person(String user, String pass) {
		this.username = user;
		this.password = pass;
	}
	
	/** 
     * This method gets the Person's username.
     *
     * @return String the Person's username.
     * 
     * @since 1.0
     */
	public String getUsername() {
		return this.username;
	}
	
	/** 
     * This method gets the Person's password.
     *
     * @return String the Person's password.
     * 
     * @since 1.0
     */
	public String getPassword() {
		return this.password;
	}
	
	/**
     * This method subscribe a Person with a role.
     * Person subscribed are written in DATAFILE.
     * 
     * @param role the role of the Person
     * 
     * @throws IOException input output
     * 
     * @since 1.0
     */
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
