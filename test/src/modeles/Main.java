package modeles;
	

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import vues.PhotoController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private BorderPane root;
	private Scene scene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			
			URL url = getClass().getResource("/vues/FenetreInitiale.fxml");
			
			loader.setLocation(url);
			
			root = (BorderPane) loader.load();
			
			// Controleurs
			PhotoController photoController = loader.getController();
			photoController.setMain(this);
			
			
			scene = new Scene(root);
			
			primaryStage.setScene(scene);
			
			primaryStage.show();
			
			

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public BorderPane getRoot() {
		return root;
	}

	public void setRoot(BorderPane root) {
		this.root = root;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	
	
}
