/**
 * 
 * @author Shelby Jorgensen
 * For CS302
 * Person class, used for phone book app
 * Person class will be used as the element in a binary search tree
 *
 */

package Lab3;

import java.io.*;


public class Person implements Comparable<Person>, Serializable  {
	
	private String name;
	private String address;
	private String phoneNumber;
	
	// Default constructor
	public Person() {
		
	}
	
	public Person(String newName, String newAddress, String newPhoneNumber) {
		this.name = newName;
		this.address = newAddress;
		this.phoneNumber = newPhoneNumber;
	}
	
	// Name setter
	void setName(String newName) {
		this.name = newName;
	}
	
	// Address setter
	void setAddress(String newAddress) {
		this.address = newAddress;
	}
	
	// Phone Number setter
	void setPhoneNumber(String newPhoneNumber) {
		this.phoneNumber = newPhoneNumber;
	}
	
	// Name getter
	public String getName() {
		return this.name;
	}
	
	// Address getter
	public String getAddress() {
		return this.address;
	}
	
	// Phone number getter
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	// Over ride of the to string method
	@Override
	public String toString() {
		return this.name + "\r\n"  + this.phoneNumber + "\r\n"  + this.address + "\r\n";
	}
	
	// Over ride of the compare to method
	@Override
	public int compareTo(Person p2) {
		return this.name.compareTo(p2.name);
	}
}
