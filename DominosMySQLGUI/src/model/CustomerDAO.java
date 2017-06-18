package model;

import java.sql.*;
import javafx.collections.*;
import util.DBUtil;

public class CustomerDAO {
	/************************************************
	 * SELECT a particular customer from the database
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 ************************************************/
	public static ObservableList<Customer> searchCustomer(String searchParam) throws SQLException, ClassNotFoundException {
		String query = "SELECT * FROM customers WHERE customerID LIKE '%" + searchParam + "%'"
				+ "OR customerEmail LIKE '%" + searchParam + "%'"
				+ "OR customerPhone LIKE '%" + searchParam + "%'";
		try {
			// run query on the database
			ResultSet rs = DBUtil.executeQuery(query);
			ObservableList<Customer> c = getCustomerListFromRs(rs);
			return c;
		} catch (SQLException e) {
			System.out.println("Error on executing " + query + " on database: " + e);
			e.printStackTrace();
			throw e;
		}

	}

	/*************************************************************************
	 * SELECT Multiple customers from the database and create multiple objects
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *************************************************************************/
	public static ObservableList<Customer> searchCustomers() throws ClassNotFoundException, SQLException{
		String query = "SELECT * FROM customers";
		try{
			//run query on the database
			ResultSet rs = DBUtil.executeQuery(query);
			ObservableList<Customer> customerList = getCustomerListFromRs(rs);
			return customerList;
		}catch(SQLException e){
			System.out.println("Error on executing " + query + " on database " + e);
			e.printStackTrace();
			throw e;
		}
	}
	/*
	 * Used to update customers details
	 */
	public static void updateCustomer(String customerIDParam, String customerEmailParam, String customerPasswordParam, String customerPhoneParam) throws SQLException, ClassNotFoundException{
		String update = "UPDATE customers SET customerEmail = '" + customerEmailParam +
				"', customerPassword = '" + customerPasswordParam +
				"', customerPhone = '" + customerPhoneParam +
				"' WHERE customerID = " + customerIDParam;
		
		try{
			//run update on the database
			DBUtil.executeUpdate(update);
		}catch(SQLException e){
			System.out.println("Error on executing update: " + e);
			e.printStackTrace();
			throw e;
		}
	}
	
	public static void newCustomer(String customerEmailParam, String customerPasswordParam, String customerPhoneParam) throws ClassNotFoundException, SQLException{
		
		String insert = "INSERT INTO customers (customerEmail, customerPassword, customerPhone) VALUES ('"
		+ customerEmailParam + "', '" + customerPasswordParam + "', '" + customerPhoneParam + "')";
		
		try {
			DBUtil.executeUpdate(insert);
		}catch(SQLException e){
			System.out.println("Error on inserting new customer " + e);
			e.printStackTrace();
			throw e;
		}
	}

	private static ObservableList<Customer> getCustomerListFromRs(ResultSet rs) throws SQLException {
		ObservableList<Customer> cList = FXCollections.observableArrayList();
		int customerIDIndex = 1;
		int customerEmailIndex = 2;
		int customerPasswordIndex = 3;
		int customerPhoneIndex = 4;
		
		while(rs.next()){
			Customer c = new Customer();
			c.setCustomerID(rs.getInt(customerIDIndex));
			c.setCustomerEmail(rs.getString(customerEmailIndex));
			c.setCustomerPassword(rs.getString(customerPasswordIndex));
			c.setCustomerPhone(rs.getString(customerPhoneIndex));
			cList.add(c);
		}
		return cList;
	}

	/*
	 * Use result set from Database query to set a customer objects attributes
	 */
	private static Customer getCustomerFromRs(ResultSet rs) throws SQLException {
		Customer c = null;
		if (rs.next()) {
			int customerIDIndex = 1;
			int customerEmailIndex = 2;
			int customerPasswordIndex = 3;
			int customerPhoneIndex = 4;

			c = new Customer();
			c.setCustomerID(rs.getInt(customerIDIndex));
			c.setCustomerEmail(rs.getString(customerEmailIndex));
			c.setCustomerPassword(rs.getString(customerPasswordIndex));
			c.setCustomerPhone(rs.getString(customerPhoneIndex));
		}
		return c;
	}
	
	
}
