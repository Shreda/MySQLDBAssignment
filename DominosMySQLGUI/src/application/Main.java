package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.fxml.*;
import java.io.IOException;	


public class Main extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Dominos MySQL DB Manmagers");
		
		initRootLayout();
		initCustomerLayout();
	}
	
	private void initCustomerLayout() {
		String location = "/view/CustomerLayout.fxml";
		try{
			//loading the CustomerLayout FXML document
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(location));
			VBox customerLayout = (VBox) loader.load();
			rootLayout.setCenter(customerLayout);
		}catch(IOException e){
			System.out.println("Error on loading " + location + ": " + e );
			e.printStackTrace();
		}
		
	}

	private void initRootLayout() {
		String location = "/view/RootLayout.fxml";
		try{
			//loading the RootLayout FXML document
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(location));
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(IOException e){
			System.out.println("Error on loading " + location + ": " + e);
			e.printStackTrace();	
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
