package controller;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;
//import model.CustomerDAO;

public class CustomerDialogController {
	@FXML
	TextField emailField;
	@FXML
	TextField passwordField;
	@FXML
	TextField phoneField;
	
	private Customer customer;
	private Stage dialogStage;
	private boolean okClicked = false;
	
	public void setCustomer(Customer c){
		this.customer = c;
		
		emailField.setText(customer.getCustomerEmail());
		passwordField.setText(customer.getCustomerPassword());
		phoneField.setText(customer.getCustomerPhone());
	}
	
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}
	
	@FXML
	private void handleOK() throws ClassNotFoundException, SQLException{
		/*
		try{
			CustomerDAO.updateCustomer(customer.getCustomerIDString(), emailField.getText(), passwordField.getText(), phoneField.getText());
		}catch(SQLException e){
			System.out.println("Error on updating customer on ok click: " + e);
			e.printStackTrace();
			throw e;
		}
		dialogStage.close();
		*/
		this.customer.setCustomerEmail(emailField.getText());
		this.customer.setCustomerPassword(passwordField.getText());
		this.customer.setCustomerPhone(phoneField.getText());
		okClicked = true;
		dialogStage.close();
	}
	
	@FXML
	private void handleCancel(){
		dialogStage.close();
	}
	
	
	public boolean isOkClicked(){
		return okClicked;
	}



}
