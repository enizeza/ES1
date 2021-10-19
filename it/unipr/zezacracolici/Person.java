/**
 * 
 */
package it.unipr.zezacracolici;

/**
 * @author enize
 * @author leocraco
 *
 */
public class Person {

	/**
	 * 
	 */
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
}
