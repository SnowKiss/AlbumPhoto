package controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import modeles.Page;
import modeles.Photo;
import modeles.PhotoSimple;

public class Controller {
	private Init init;

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
	        
	        //ajouter l'image à un nouvel objet photo
	        init.getCurrentPage().addPhoto(new PhotoSimple(photo,titre));
	        
	        init.getCurrentPage().getListePhotos().get(init.getCurrentPage().getListePhotos().size()-1).drawPhoto(init);
	        
		    //    ImageView selectedImage = new ImageView(ps.getImage());  
		    //    init.getRoot().getChildren().addAll(selectedImage);
	         
	 
			System.out.println("file://" + photo.toString());
		}
		System.out.println("Image ajoutée !");
	}
	
	public void ajouterImage(File image) throws IOException{
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

        //ajouter l'image à un nouvel objet photo
        init.getCurrentPage().addPhoto(new PhotoSimple(image,titre));
        
        init.getCurrentPage().getListePhotos().get(init.getCurrentPage().getListePhotos().size()-1).drawPhoto(init);
        
	    //    ImageView selectedImage = new ImageView(ps.getImage());  
	    //    init.getRoot().getChildren().addAll(selectedImage);

		System.out.println("Image ajoutée !");
	}
	
	public void repaint() {
		//clean the scene
		//Node n = init.getRoot().getChildren().get(0);
		//init.getRoot().getChildren().clear();
		//init.getRoot().getChildren().addAll(n);
		//draw the current page
		//init.getCurrentPage().drawPage(1, 2, init);
		//init.getCurrentPage().getListePhotos().get(init.getCurrentPage().getListePhotos().size()-1).drawPhoto(init);
		
	}

	public void setInit(Init init) {
        this.init = init;
    }
	
	public void ajouterPage() 
	{
		init.getAlbum().getListePages().add(new Page(init.getAlbum().getListePages().size()));
		init.setCurrentPage(init.getAlbum().getListePages().get(init.getAlbum().getListePages().size()-1));
		refreshView();
	}
	
	public void refreshView() {
		// on desactive les autres nodes de la vue
		for (Node n: init.getRoot().getChildren())
		{
			n.setManaged(false);
			n.setVisible(false);
		}
		// on réactive les éléments de la page active
		for (Photo p: init.getCurrentPage().getListePhotos())
		{
			p.getPhotoController().getT().setManaged(true);
			p.getPhotoController().getT().setVisible(true);
			p.getPhotoController().getSelectedImage().setManaged(true);
			p.getPhotoController().getSelectedImage().setVisible(true);
			p.getPhotoController().getCadre().setManaged(true);
			p.getPhotoController().getCadre().setVisible(true);
		}

		// on réactive le menu
		init.getRoot().getChildren().get(0).setManaged(true);
		init.getRoot().getChildren().get(0).setVisible(true);
		init.getRoot().getRight().setManaged(true);
    	init.getRoot().getRight().setVisible(true);
		
		
		// on réactive la toolbar
		//init.getRoot().getChildren().get(1).setManaged(true);
		//init.getRoot().getChildren().get(1).setVisible(true);
		
		// on affiche le numero de la page
		Text t = new Text (624, 635, String.valueOf(init.getCurrentPage().getNumero()+1)+"/"+String.valueOf(init.getAlbum().getListePages().size()) );
		init.getRoot().getChildren().addAll(t);
	}

	public void supprimerPage() 
	{
		// on retient l'index de la page à supprimer
		int indexSuppr = init.getCurrentPage().getNumero();
		// mettre à jour les numéros de page des suivants
		for (Page p : init.getAlbum().getListePages())
		{
			if (p.getNumero()>indexSuppr)
			{
				p.setNumero(p.getNumero()-1);
			}
		}
		// changer le focus
		if(indexSuppr==0)
		{
			init.setCurrentPage(init.getAlbum().getListePages().get(indexSuppr+1));
		}
		else
		{
			init.setCurrentPage(init.getAlbum().getListePages().get(indexSuppr-1));
		}
		
		// supprimer la page
		init.getAlbum().getListePages().remove(indexSuppr);
		refreshView();
	}
	
	public void pageSuivante() 
	{
		if(init.getAlbum().getListePages().size() > (init.getCurrentPage().getNumero()+1))
		{
			init.setCurrentPage(init.getAlbum().getListePages().get(init.getCurrentPage().getNumero()+1));
			refreshView();
		}
	}
	
	public void pagePrecedente() 
	{
		if(init.getCurrentPage().getNumero() != 0)
		{
			init.setCurrentPage(init.getAlbum().getListePages().get(init.getCurrentPage().getNumero()-1));
			refreshView();
		}
	}
	
	public void afficherToolbar(ImageView selectedImage)
	{
		
		// On affiche la toolbar

		//init.getRoot().getChildren().get(1).setManaged(true);
		//init.getRoot().getChildren().get(1).setVisible(true);
	}
	
	public void importerImages() throws IOException{
		//String titre = "";
				
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Importer des images");
		fileChooser.getExtensionFilters().addAll(
		new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		List<File> photos = fileChooser.showOpenMultipleDialog(null);
		for(File photo : photos)
		{
			BufferedImage bufferedImage = ImageIO.read(photo);
	        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
	        
	        //ajouter l'image à la liste affichée
	        ImageView sample = new ImageView(image);
	        sample.setPreserveRatio(true);
	        sample.setFitWidth(170);
	        sample.fitWidthProperty();
	        sample.setOnMouseClicked(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent mouseEvent) {
	                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
	                    if(mouseEvent.getClickCount() == 2){
	                        try {
								ajouterImage(photo);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                    }
	                }
	            }
	        });
	        init.getListe().getChildren().add(sample);
	        init.getListeImage().add(sample);
	        
	        //init.getCurrentPage().getListePhotos().get(init.getCurrentPage().getListePhotos().size()-1).drawPhoto(init);
	        
		    //    ImageView selectedImage = new ImageView(ps.getImage());  
		    //    init.getRoot().getChildren().addAll(selectedImage);
	         
	 
			System.out.println("file://" + photo.toString());
		}
		System.out.println("Image ajoutée !");
	}
	
	
}
