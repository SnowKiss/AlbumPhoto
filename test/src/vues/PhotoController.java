package vues;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import modeles.Main;

public class PhotoController {
	private Main main;

	public PhotoController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	public void ajouterPhoto(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Ajouter une photo");
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		File photo = fileChooser.showOpenDialog(null);
		if(photo != null)
		{
			
			try{
				File destination = new File(this.main.getClass().getResource("").toString());
				Files.copy(photo.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException ex) {
				System.out.println(ex);
	            }
			
			/*
			URL imageURL = getClass().getResource(); 
	        Image image = new Image(imageURL.toExternalForm()); 
	        ImageView imageView = new ImageView(image); 
	        this.main.getRoot().getChildren().setAll(imageView);*/
			System.out.println(photo);
		}
		System.out.println("Image ajoutée !");
	}
	
	public void setMain(Main main) {
        this.main = main;
    }
	
	
	

}
