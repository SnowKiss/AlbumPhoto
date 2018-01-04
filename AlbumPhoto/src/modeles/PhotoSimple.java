/*
 * 		Classe Photo : créer une photo, ajouter un titre
 * 
 */

package modeles;

import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import main.Main;
import vues.Controller;

public class PhotoSimple implements Photo {
	
	// Attributs
	Image image;
	String titre;
	ImageView selectedImage;
	Text t;
	double x;
	double y;
	Controller controleur;
	Boolean bordure;
	
	// Constructeur	
	public PhotoSimple(Image image, String titre) {
		super();
		this.titre = titre;
		this.image = image;
		this.x=300;
		this.y=120;
		this.bordure = false;
		controleur = new Controller();
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
		selectedImage = new ImageView(image);
		selectedImage.setX(this.x);
		selectedImage.setY(this.y);
		
		// afficher la légende
        t = new Text (selectedImage.getX()+20, selectedImage.getY()+image.getHeight()+20, titre);
        
        // Event pour permettre à l'utilisateur de modifier la légende
        t.addEventHandler(MouseEvent.MOUSE_CLICKED, event-> {
        	/* TextInput pour laisser l'utiliateur modifier le titre de la photo */
    		TextInputDialog input_titre = new TextInputDialog(t.getText());
    		input_titre.setTitle("Légende de la photo");
    		input_titre.setContentText("Légende de la photo : ");
    		input_titre.setHeaderText(null);
    		
    		// Réponse de l'utilisateur
    		Optional<String> result = input_titre.showAndWait();
    		if (result.isPresent()){
    		    t.setText(result.get());
    		}
        });
        
                
        selectedImage.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
				selectedImage.setX(event.getX()-(image.getWidth()/2));
				selectedImage.setY(event.getY()-(image.getHeight()/2));
				t.setX(selectedImage.getX()+20);
				t.setY(selectedImage.getY()+image.getHeight()+20);
				event.consume();
	    });
        
        
		
		main.getRoot().getChildren().addAll(selectedImage);
        main.getRoot().getChildren().addAll(t);
        
        
        
        this.x=selectedImage.getX();
        this.y=selectedImage.getY();
        
		selectedImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event-> {
        	//controleur.afficherToolbar(selectedImage);
        	// on affiche la ToolBar
        	main.getRoot().lookup("#toolBar").setManaged(true);
        	main.getRoot().lookup("#toolBar").setVisible(true);
        	
        	CheckBox check = (CheckBox) main.getRoot().lookup("#checkbox_bordure");
   
        	check.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> ov,
                        Boolean old_val, Boolean new_val) {
                		if(check.isSelected())
                		{
                			main.getRoot().lookup("#choicebox_bordure").getStyleClass().add("border-simple");
                			selectedImage.getStyleClass().add("border-simple");
                		}
                		else
                		{
                			main.getRoot().lookup("#choicebox_bordure").getStyleClass().remove("border-simple");
                			selectedImage.getStyleClass().remove("border-simple");
                		}
                		
                    }
                });
   
        	
        
        });
	}

	public Image getImage() {

		return image;
	}

	public ImageView getSelectedImage() {
		return selectedImage;
	}

	public void setSelectedImage(ImageView selectedImage) {
		this.selectedImage = selectedImage;
	}

	public Text getText() {
		return t;
	}

	public void setText(Text t) {
		this.t = t;
	}
	
	
	
	
	
}
