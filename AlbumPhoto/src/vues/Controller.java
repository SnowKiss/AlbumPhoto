package vues;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//import java.net.URL;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.util.logging.Logger;
import java.util.Optional;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.StageStyle;
import main.Main;
import modeles.Page;
import modeles.PhotoSimple;

public class Controller {
	private Main main;

	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	public void ajouterPhoto() throws IOException{
		String titre = "";
		
		/* TextInput pour laisser l'utiliateur choisir le titre de la photo */
		TextInputDialog input_titre = new TextInputDialog("");
		input_titre.setTitle("Légende de la photo");
		input_titre.setContentText("Légende de la photo : ");
		input_titre.setHeaderText(null);
		
		
		// Réponse de l'utilisateur
		Optional<String> result = input_titre.showAndWait();
		if (result.isPresent()){
		    titre = result.get();
		}

		
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Ajouter une photo");
		fileChooser.getExtensionFilters().addAll(
		new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		File photo = fileChooser.showOpenDialog(null);
		if(photo != null)
		{
			BufferedImage bufferedImage = ImageIO.read(photo);
	        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
	        
	        //PhotoSimple ps = new PhotoSimple(image,"test");
	        
	        //ajouter l'image à un nouvel objet photo
	        main.getCurrentPage().addPhoto(new PhotoSimple(image,titre));
	        
	        repaint();
	        
		    //    ImageView selectedImage = new ImageView(ps.getImage());  
		    //    main.getRoot().getChildren().addAll(selectedImage);
	         
	 
			System.out.println("file://" + photo.toString());
		}
		System.out.println("Image ajoutée !");
	}
	
	public void repaint() {
		//clean the scene
		Node n = main.getRoot().getChildren().get(0);
		main.getRoot().getChildren().clear();
		main.getRoot().getChildren().addAll(n);
		//draw the current page
		main.getCurrentPage().drawPage(1, 2, main);
		
	}

	public void setMain(Main main) {
        this.main = main;
    }
	
	public void ajouterPage() 
	{
		main.getAlbum().getListePages().add(new Page(main.getAlbum().getListePages().size()));
		main.setCurrentPage(main.getAlbum().getListePages().get(main.getAlbum().getListePages().size()-1));
		repaint();
	}
	
	public void supprimerPage() 
	{
		// on retient l'index de la page à supprimer
		int indexSuppr = main.getCurrentPage().getNumero();
		// mettre à jour les numéros de page des suivants
		for (Page p : main.getAlbum().getListePages())
		{
			if (p.getNumero()>indexSuppr)
			{
				p.setNumero(p.getNumero()-1);
			}
		}
		// changer le focus
		if(indexSuppr==0)
		{
			main.setCurrentPage(main.getAlbum().getListePages().get(indexSuppr+1));
		}
		else
		{
			main.setCurrentPage(main.getAlbum().getListePages().get(indexSuppr-1));
		}
		
		// supprimer la page
		main.getAlbum().getListePages().remove(indexSuppr);
		repaint();
	}
	
	public void pageSuivante() 
	{
		main.setCurrentPage(main.getAlbum().getListePages().get(main.getCurrentPage().getNumero()+1));
		repaint();
	}
	
	public void pagePrecedente() 
	{
		main.setCurrentPage(main.getAlbum().getListePages().get(main.getCurrentPage().getNumero()-1));
		repaint();
	}

}
