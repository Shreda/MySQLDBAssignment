package controller;

import java.sql.SQLException;

import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import model.Customer;
import model.CustomerDAO;

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
	private void searchEmployees() throws SQLException {
		ObservableList cList = CustomerDAO.searchCustomers();
		populateTable(cList);
	}

	private void populateTable(ObservableList cList) {
		customerTable.setItems(cList);
	}

	// This method is automatically called when the FXML file is loaded
	// we set which field of the Customer objects the TableColumns should use as
	// their data:
	@FXML
	private void initialize() throws SQLException {
		customerIDColumn.setCellValueFactory(cellData -> cellData.getValue().customerIDProperty().asObject());
		customerEmailColumn.setCellValueFactory(cellData -> cellData.getValue().customerEmailProperty());
		customerPasswordColumn.setCellValueFactory(cellData -> cellData.getValue().customerPasswordProperty());
		customerPhoneColumn.setCellValueFactory(cellData -> cellData.getValue().customerPhoneProperty());
		searchEmployees();
	}

}
