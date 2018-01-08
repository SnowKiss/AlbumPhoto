package main;
	
import controllers.Init;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Classe de d�marrage de l'application
 *
 */
public class Main extends Application {
	private Init instance;

	/** On commence par initialiser la sc�ne, puis on cr�e l'album
	 * 
	 */
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
