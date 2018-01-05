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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import main.Init;
import vues.Controller;

public class PhotoSimple implements Photo {
	
	// Attributs
	Image image;
	String titre;
	ImageView selectedImage;
	Rectangle cadre;
	Text t;
	double x;
	double y;
	double width;
	double height;
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
		
		this.selectedImage = new ImageView(image);
		this.selectedImage.setPreserveRatio(true);
		this.selectedImage.setFitWidth(300);
		this.selectedImage.fitWidthProperty();
		
		this.width = 300;
		this.height = (this.width/image.getWidth())*image.getHeight();
		
		this.cadre = new Rectangle(width, height);
		this.cadre.setFill(Color.TRANSPARENT);
		this.cadre.setStroke(Color.TRANSPARENT);
		this.cadre.setStrokeWidth(0);
		controleur = new Controller();
	}
	
	// Getters & Setters
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void drawPhoto(Init init) {
		// afficher l'image
		
		selectedImage.setX(this.x);
		selectedImage.setY(this.y);
		
		
		
		
		
		// afficher la légende
        t = new Text (selectedImage.getX()+20, selectedImage.getY()+height+20, titre);
        

        
        cadre = new Rectangle(width, height);
        cadre.setFill(Color.TRANSPARENT);
        cadre.setStroke(Color.BLUE);
        cadre.setStrokeWidth(10);
        cadre.relocate(selectedImage.getX()-cadre.getStrokeWidth()/2, selectedImage.getY()-cadre.getStrokeWidth()/2);
        
        DropShadow borderGlow= new DropShadow();
		borderGlow.setOffsetY(0f);
		borderGlow.setOffsetX(0f);
		borderGlow.setColor(Color.RED);
		borderGlow.setWidth(80);
		borderGlow.setHeight(80);
		cadre.setEffect(borderGlow);
		
		init.getRoot().getChildren().addAll(t,cadre,selectedImage);
                
        this.x=selectedImage.getX();
        this.y=selectedImage.getY();
        
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
				selectedImage.setX(event.getX()-(width/2));
				selectedImage.setY(event.getY()-(height/2));
				t.setX(selectedImage.getX()+20);
				t.setY(selectedImage.getY()+height+20);
				cadre.relocate(selectedImage.getX()-cadre.getStrokeWidth()/2, selectedImage.getY()-cadre.getStrokeWidth()/2);
				event.consume();
	    });
        
        /*cadre.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
				selectedImage.setX(event.getX()-(cadre.getWidth()/2));
				selectedImage.setY(event.getY()-(cadre.getHeight()/2));
				t.setX(selectedImage.getX()+20);
				t.setY(selectedImage.getY()+image.getHeight()+20);
				//cadre.relocate(selectedImage.getX(), selectedImage.getY());
				event.consume();
	    });*/
        
		selectedImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event-> {
        	//controleur.afficherToolbar(selectedImage);
        	// on affiche la ToolBar
        	init.getRoot().lookup("#toolBar").setManaged(true);
        	init.getRoot().lookup("#toolBar").setVisible(true);
        	
        	CheckBox check = (CheckBox) init.getRoot().lookup("#checkbox_bordure");
   
        	check.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> ov,
                        Boolean old_val, Boolean new_val) {
                		if(check.isSelected())
                		{
                			init.getRoot().lookup("#choicebox_bordure").getStyleClass().add("border-simple");
                			selectedImage.getStyleClass().add("border-simple");
                		}
                		else
                		{
                			init.getRoot().lookup("#choicebox_bordure").getStyleClass().remove("border-simple");
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

	public Rectangle getCadre() {
		return cadre;
	}

	public void setCadre(Rectangle cadre) {
		this.cadre = cadre;
	}
	
	
	
	
	
}
