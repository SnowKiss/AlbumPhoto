package main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modeles.Album;
import modeles.Page;
import vues.Controller;

public class Init {
	private BorderPane root;
	private Scene scene;
	private Album album;
	private Page currentPage;
	private VBox liste;
	private List<ImageView> listeImage;
	private Controller photoController;

	public Init(Stage primaryStage) {
		FXMLLoader loader = new FXMLLoader();
		URL url = getClass().getResource("/vues/FenetreInitiale.fxml");
		loader.setLocation(url);
		try {
			this.setRoot((BorderPane) loader.load());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setPhotoController(loader.getController());
		this.getPhotoController().setInit(this);
		primaryStage.setScene(new Scene(this.getRoot()));
		primaryStage.getScene().getStylesheets().add(getClass().getResource("/vues/application.css").toExternalForm());
		primaryStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
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
		this.setListeImage(new ArrayList<ImageView>());
		this.setListe(new VBox());
		VBox rContainer = new VBox();
		Button bImporter = new Button("Importer");
		ScrollPane sPane = new ScrollPane();
		Group listeGroupe = new Group();
		
		this.getListe().setSpacing(20);
		rContainer.setSpacing(20);
		
		sPane.setContent(this.getListe());
		sPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		sPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		sPane.setFitToWidth(true);
		sPane.setMaxHeight(620);
		
		bImporter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
					photoController.importerImages();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
		listeGroupe.getChildren().addAll(sPane);
		rContainer.getChildren().addAll(bImporter, listeGroupe);
					
		this.getRoot().setRight(rContainer);

		primaryStage.setScene(primaryStage.getScene());
		primaryStage.setResizable(false);
		primaryStage.show();
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

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Page getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Page currentPage) {
		this.currentPage = currentPage;
	}

	public VBox getListe() {
		return liste;
	}

	public void setListe(VBox liste) {
		this.liste = liste;
	}

	public List<ImageView> getListeImage() {
		return listeImage;
	}

	public void setListeImage(List<ImageView> listeImage) {
		this.listeImage = listeImage;
	}

	public Controller getPhotoController() {
		return photoController;
	}

	public void setPhotoController(Controller photoController) {
		this.photoController = photoController;
	}

	public void initAlbum(String title) {
		this.setAlbum(new Album(title));
		this.setCurrentPage(this.getAlbum().getListePages().get(0));
		this.getPhotoController().refreshView();
		
	}
}