package main;
	
import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {
	private Init instance;

	@Override
	public void start(Stage primaryStage) {
		try {
			
			// Initialisation de la sc�ne
			instance=new Init(primaryStage);
			
			
			// Cr�ation de l'album
			instance.initAlbum("Mon album photo");
			

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}	
	
}
