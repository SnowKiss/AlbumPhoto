package main;
	
import controllers.Init;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Classe de démarrage de l'application
 *
 */
public class Main extends Application {
	private Init instance;

	/** On commence par initialiser la scène, puis on crée l'album
	 * 
	 */
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
