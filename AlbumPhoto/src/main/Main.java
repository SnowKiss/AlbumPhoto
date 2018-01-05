package main;
	
import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {
	private Init instance;

	@Override
	public void start(Stage primaryStage) {
		try {
			
			// Initialisation de la scène
			instance=new Init(primaryStage);
			
			
			// Création de l'album
			instance.initAlbum("Mon album photo");
			

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}	
	
}
