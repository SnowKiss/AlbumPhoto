/*
 * 		Classe Photo : créer une photo, ajouter un titre
 * 
 */

package modeles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import main.Main;

public class PhotoSimple implements Photo {
	
	// Attributs
	Image image;
	String titre;
	
	// Constructeur	
	public PhotoSimple(Image image, String titre) {
		super();
		this.titre = titre;
		this.image = image;
	}
	
	// Getters & Setters
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void drawPhoto(Main main) {
		// afficher l'image
		ImageView selectedImage = new ImageView(image); 
		selectedImage.setX(200);
		selectedImage.setY(200);
		
		// afficher la légende
        Text t = new Text (selectedImage.getX()+20, selectedImage.getY()+image.getHeight()+20, titre);
                
        selectedImage.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
				selectedImage.setX(event.getX()-(image.getWidth()/2));
				selectedImage.setY(event.getY()-(image.getHeight()/2));
				t.setX(selectedImage.getX()+20);
				t.setY(selectedImage.getY()+image.getHeight()+20);
				event.consume();
	    });
		
		main.getRoot().getChildren().addAll(selectedImage);
		
        main.getRoot().getChildren().addAll(t);
		
	}

	public Image getImage() {

		return image;
	}
	
}
