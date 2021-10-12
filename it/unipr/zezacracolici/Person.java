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
	private String role;
	
	public Person() {
		
		this.username = "";
		this.password = "";
		this.role = "";
	}
	
	public Person(final String user, final String pass, final String ro) {
		this.username = user;
		this.password = pass;
		this.role = ro;
	}
}
