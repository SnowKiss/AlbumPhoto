package main;
	

import java.net.URL;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import modeles.Album;
import modeles.Page;
import vues.Controller;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private BorderPane root;
	private Scene scene;
	private Album album;
	private Page currentPage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			// Initialisation de la scène
			
			FXMLLoader loader = new FXMLLoader();
			URL url = getClass().getResource("/vues/FenetreInitiale.fxml");
			loader.setLocation(url);
			root = (BorderPane) loader.load();
			Controller photoController = loader.getController();
			photoController.setMain(this);
			scene = new Scene(root);
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		        public void handle(KeyEvent event) {
		        	if(event.getCode() == KeyCode.RIGHT) {
	    	            // page suivante
		        		photoController.pageSuivante();
		    	    } 
		        	else if(event.getCode() == KeyCode.LEFT) {
		    	        // page precedente
		        		photoController.pagePrecedente();
		            }
		        }
		    });

			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
			// Création de l'album
			
			album = new Album("Mon album photo");
			currentPage = album.getListePages().get(0);
			
			photoController.refreshView();

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

	public Page getCurrentPage() {
		return currentPage;
	}

	public Album getAlbum() {
		return album;		
	}

	public void setCurrentPage(Page page) {
		this.currentPage=page;
	}
	
}
