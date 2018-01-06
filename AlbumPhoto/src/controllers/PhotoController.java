package controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import modeles.PhotoSimple;

public class PhotoController {
	private Image image;
	private ImageView selectedImage;
	private Rectangle cadre;
	private Text t;
	private double width;
	private double height;
	private Controller controleur;
	private Boolean bordure;
	private PhotoSimple photo;
	private ToolBarController toolbarcontrol;

	public PhotoController(PhotoSimple photo) {
		
		this.setPhoto(photo);
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(photo.getImage());
			this.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.setBordure(false);
		
		this.setSelectedImage(new ImageView(this.getImage()));
		this.getSelectedImage().setPreserveRatio(true);
		this.getSelectedImage().setFitWidth(300);
		this.getSelectedImage().fitWidthProperty();
		
		this.setWidth(300);
		this.setHeight((this.getWidth()/this.getImage().getWidth())*this.getImage().getHeight());
		
		this.setCadre(new Rectangle(this.getWidth(), this.getHeight()));
		this.getCadre().setFill(Color.TRANSPARENT);
		this.getCadre().setStroke(Color.TRANSPARENT);
		this.getCadre().setStrokeWidth(0);
		
		//this.setCadre(new Rectangle(this.getWidth(), this.getHeight()));
		//this.getCadre().setFill(Color.TRANSPARENT);
		//this.getCadre().setStroke(Color.TRANSPARENT);
		//this.getCadre().setStrokeWidth(0);
		this.setControleur(new Controller());
	}

	public ImageView getSelectedImage() {
		return selectedImage;
	}

	public void setSelectedImage(ImageView selectedImage) {
		this.selectedImage = selectedImage;
	}

	public Rectangle getCadre() {
		return cadre;
	}

	public void setCadre(Rectangle cadre) {
		this.cadre = cadre;
	}

	public Text getT() {
		return t;
	}

	public void setT(Text t) {
		this.t = t;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Controller getControleur() {
		return controleur;
	}

	public void setControleur(Controller controleur) {
		this.controleur = controleur;
	}

	public Boolean isBordure() {
		return bordure;
	}

	public void setBordure(Boolean bordure) {
		this.bordure = bordure;
	}

	public PhotoSimple getPhoto() {
		return photo;
	}

	public void setPhoto(PhotoSimple photo) {
		this.photo = photo;
	}

	public Boolean getBordure() {
		return bordure;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void ajouterCadre()
	{
			this.getCadre().setFill(Color.TRANSPARENT);
			this.getCadre().setStroke(Color.BLUE);
			this.getCadre().setStrokeWidth(10);
			this.getCadre().relocate(this.getSelectedImage().getX()-this.getCadre().getStrokeWidth()/2, this.getSelectedImage().getY()-this.getCadre().getStrokeWidth()/2);

	}
	
	public void supprimerCadre()
	{
		this.getCadre().setFill(Color.TRANSPARENT);
		this.getCadre().setStroke(Color.TRANSPARENT);
		this.getCadre().setStrokeWidth(0);
		this.getCadre().relocate(this.getSelectedImage().getX()-this.getCadre().getStrokeWidth()/2, this.getSelectedImage().getY()-this.getCadre().getStrokeWidth()/2);
	}
	
	
	public void drawWithJavaFX(Init init) {
		this.toolbarcontrol = new ToolBarController(this, init);
		// afficher l'image
		this.getSelectedImage().setX(this.getPhoto().getX());
		this.getSelectedImage().setY(this.getPhoto().getY());
		
		// afficher la légende
		this.setT(new Text (this.getSelectedImage().getX()+20, this.getSelectedImage().getY()+this.getHeight()+20, this.getPhoto().getTitre()));
        
        // définir un cadre
        //this.ajouterCadre(true);
        // ajouter un effet de profondeur
        /*DropShadow borderGlow= new DropShadow();
		borderGlow.setOffsetY(0f);
		borderGlow.setOffsetX(0f);
		borderGlow.setColor(Color.RED);
		borderGlow.setWidth(80);
		borderGlow.setHeight(80);
		this.getCadre().setEffect(borderGlow);
		*/
		
                
        this.getPhoto().setX(this.getSelectedImage().getX());
        this.getPhoto().setY(this.getSelectedImage().getY());
        
        // Event pour permettre à l'utilisateur de modifier la légende
        this.getT().addEventHandler(MouseEvent.MOUSE_CLICKED, event-> {
        	/* TextInput pour laisser l'utiliateur modifier le titre de la photo */
    		TextInputDialog input_titre = new TextInputDialog(this.getT().getText());
    		input_titre.setTitle("Légende de la photo");
    		input_titre.setContentText("Légende de la photo : ");
    		input_titre.setHeaderText(null);
    		
    		// Réponse de l'utilisateur
    		Optional<String> result = input_titre.showAndWait();
    		if (result.isPresent()){
    			this.getT().setText(result.get());
    		}
        });
                
        this.getSelectedImage().addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
        	this.getSelectedImage().setX(event.getX()-(this.getWidth()/2));
        	this.getSelectedImage().setY(event.getY()-(this.getHeight()/2));
        	this.getT().setX(this.getSelectedImage().getX()+20);
        	this.getT().setY(this.getSelectedImage().getY()+this.getHeight()+20);
        	this.getCadre().relocate(this.getSelectedImage().getX()-this.getCadre().getStrokeWidth()/2, this.getSelectedImage().getY()-this.getCadre().getStrokeWidth()/2);
				event.consume();
	    });
                
        this.getSelectedImage().addEventHandler(MouseEvent.MOUSE_CLICKED, event-> {
        	try {
				this.toolbarcontrol.finalize();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	//controleur.afficherToolbar(selectedImage);
        	// on affiche la ToolBar
        	/*init.getRoot().lookup("#toolBar").setManaged(true);
        	init.getRoot().lookup("#toolBar").setVisible(true);
        	
        	
        	init.getRoot().lookup("#checkbox_bordure");        	*/
        	
   /*
        	check.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> ov,
                        Boolean old_val, Boolean new_val) {
                		if(check.isSelected())
                		{
                			getSelectedImage().
                			//init.getRoot().lookup("#choicebox_bordure").getStyleClass().add("border-simple");
                			//getSelectedImage().getStyleClass().add("border-simple");
                		}
                		else
                		{
                			//init.getRoot().lookup("#choicebox_bordure").getStyleClass().remove("border-simple");
                			//getSelectedImage().getStyleClass().remove("border-simple");
                		}
                		
                    }
                });*/
        
        });

        //this.setCadre(true);
        init.getRoot().getChildren().addAll(this.getT(),this.getCadre(),this.getSelectedImage());
	}
	
	
}