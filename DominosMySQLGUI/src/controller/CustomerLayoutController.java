package controller;

import java.sql.SQLException;

import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import model.Customer;
import model.CustomerDAO;
import application.Main;

public class CustomerLayoutController {
	@FXML
	private TableView<Customer> customerTable;
	@FXML
	private TableColumn<Customer, Integer> customerIDColumn;
	@FXML
	private TableColumn<Customer, String> customerEmailColumn;
	@FXML
	private TableColumn<Customer, String> customerPasswordColumn;
	@FXML
	private TableColumn<Customer, String> customerPhoneColumn;
	
	@FXML
	private TextField customerSearchField;
	
	private Main main;

	@FXML
	private void searchCustomers() throws SQLException, ClassNotFoundException {
		ObservableList<Customer> cList = CustomerDAO.searchCustomers();
		populateTable(cList);
	}
	
	@FXML
	private void searchCustomer() throws SQLException, ClassNotFoundException {
		try{
			Customer c = CustomerDAO.searchCustomer(customerSearchField.getText());
			if (c != null){
				ObservableList<Customer> cList = FXCollections.observableArrayList();
				cList.add(c);
				populateTable(cList);
			}
			
			

		}catch(SQLException e){
			System.out.println("Error on searching customer in controller class: " + e);
			e.printStackTrace();
			throw e;
			
		}
	
		
	}
	
	@FXML
	private void handleEdit() throws ClassNotFoundException{
		Customer c = customerTable.getSelectionModel().getSelectedItem();
		try{
			if(c!=null){
				main.initCustomerDialog(c);
				searchCustomers();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}

		
	}

	private void populateTable(ObservableList<Customer> cList) {
		customerTable.setItems(cList);
	}

	// This method is automatically called when the FXML file is loaded
	// we set which field of the Customer objects the TableColumns should use as
	// their data:
	@FXML
	private void initialize() throws SQLException, ClassNotFoundException {
		customerIDColumn.setCellValueFactory(cellData -> cellData.getValue().customerIDProperty().asObject());
		customerEmailColumn.setCellValueFactory(cellData -> cellData.getValue().customerEmailProperty());
		customerPasswordColumn.setCellValueFactory(cellData -> cellData.getValue().customerPasswordProperty());
		customerPhoneColumn.setCellValueFactory(cellData -> cellData.getValue().customerPhoneProperty());
		searchCustomers();
	}
	
	/*
	 * We set a reference back to the main app so we can call methods within it.
	 */
	public void setMain(Main main){
		this.main = main;
	}
}
