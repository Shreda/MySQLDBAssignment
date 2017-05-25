package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer {
	/*
	 * Instance variables:(What sort of properties does a customer have?)
	 * Because we are working with SQL tables we can find a customers instance
	 * variable from the SQL table.
	 */
	private IntegerProperty customerID;
	private StringProperty customerEmail;
	private StringProperty customerPassword;
	private StringProperty customerPhone;
	
	/*
	 * default constructor.
	 * Initializes instance variables 
	 */
	public Customer(){
		customerID = new SimpleIntegerProperty();
		customerEmail = new SimpleStringProperty();
		customerPassword = new SimpleStringProperty();
		customerPhone = new SimpleStringProperty();	
	}
	
	/*
	 * getters and setters for customerID
	 */
	public int getCustomerID(){
		return customerID.get();
	}
	public String getCustomerIDString(){
		return Integer.toString(customerID.get());
	}
	
	public void setCustomerID(int customerID){
		this.customerID.set(customerID);
	}
	
	public IntegerProperty customerIDProperty(){
		return this.customerID;
	}
	
	/*
	 * getters and setters for customerEmail
	 */
	public String getCustomerEmail(){
		return customerEmail.get();
	}
	
	public void setCustomerEmail(String customerEmail){
		this.customerEmail.set(customerEmail);
	}
	
	public StringProperty customerEmailProperty(){
		return customerEmail;
	}
	
	/*
	 * getters and setters for customerPassword
	 */
	public String getCustomerPassword(){
		return customerPassword.get();
	}
	
	public void setCustomerPassword(String customerPassword){
		this.customerPassword.set(customerPassword);
	}
	
	public StringProperty customerPasswordProperty(){
		return customerPassword;
	}
	
	/*
	 * getters and setters for customerPhone
	 */
	public String getCustomerPhone(){
		return customerPhone.get();
	}
	
	public void setCustomerPhone(String customerPhone){
		this.customerPhone.set(customerPhone);
	}
	
	public StringProperty customerPhoneProperty(){
		return customerPhone;
	}
	
	
}
