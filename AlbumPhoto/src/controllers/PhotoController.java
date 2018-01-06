package controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.DropShadow;
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
	private Boolean possede_cadre; // Permet de savoir si l'image possède un cadre ou non
	private Boolean possede_ombre; // Permet de savoir si l'image possède un cadre ou non
	private PhotoSimple photo;
	private DropShadow borderGlow;

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

		this.setCadre(false);
		this.setOmbre(false);

		this.setSelectedImage(new ImageView(this.getImage()));
		this.getSelectedImage().setPreserveRatio(true);
		this.getSelectedImage().setFitWidth(300);
		this.getSelectedImage().fitWidthProperty();
		this.getSelectedImage().fitHeightProperty();

		this.setWidth(300);
		this.setHeight((this.getWidth() / this.getImage().getWidth()) * this.getImage().getHeight());

		this.setCadre(new Rectangle(this.getWidth(), this.getHeight()));
		this.getCadre().setFill(Color.TRANSPARENT);
		this.getCadre().setStroke(Color.TRANSPARENT);
		this.getCadre().setStrokeWidth(0);

		this.borderGlow = new DropShadow();
		// this.setCadre(new Rectangle(this.getWidth(), this.getHeight()));
		// this.getCadre().setFill(Color.TRANSPARENT);
		// this.getCadre().setStroke(Color.TRANSPARENT);
		// this.getCadre().setStrokeWidth(0);

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

	public Boolean isCadre() {
		return possede_cadre;
	}

	public void setCadre(Boolean cadre) {
		this.possede_cadre = cadre;
	}

	public PhotoSimple getPhoto() {
		return photo;
	}

	public void setPhoto(PhotoSimple photo) {
		this.photo = photo;
	}

	public Boolean getPossedeCadre() {
		return possede_cadre;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	
	public Boolean getPossede_ombre() {
		return possede_ombre;
	}

	public void setOmbre(Boolean possede_ombre) {
		this.possede_ombre = possede_ombre;
	}
	
	public Boolean isOmbre() {
		return possede_ombre;
	}

	public void ajouterCadre() {
		if (this.isCadre()) {
			this.getCadre().setFill(Color.TRANSPARENT);
			this.getCadre().setStroke(Color.BLUE);
			this.getCadre().setStrokeWidth(10);
			this.getCadre().relocate(this.getSelectedImage().getX() - this.getCadre().getStrokeWidth() / 2,
					this.getSelectedImage().getY() - this.getCadre().getStrokeWidth() / 2);
		}

	}

	public void supprimerCadre() {
		this.getCadre().setFill(Color.TRANSPARENT);
		this.getCadre().setStroke(Color.TRANSPARENT);
		this.getCadre().setStrokeWidth(0);
		this.getCadre().relocate(this.getSelectedImage().getX() - this.getCadre().getStrokeWidth() / 2,
				this.getSelectedImage().getY() - this.getCadre().getStrokeWidth() / 2);
	}

	public void modifierCouleurCadre(String color) {
		Color tmp = null;
		switch (color) {
		case "AQUA":
			tmp = Color.AQUA;
			break;
		case "BLACK":
			tmp = Color.BLACK;
			break;
		case "BLUE":
			tmp = Color.BLUE;
			break;
		case "BLUEVIOLET":
			tmp = Color.BLUEVIOLET;
			break;
		case "BROWN":
			tmp = Color.BROWN;
			break;
		case "CYAN":
			tmp = Color.CYAN;
			break;
		case "DARKBLUE":
			tmp = Color.DARKBLUE;
			break;
		case "GREEN":
			tmp = Color.GREEN;
			break;
		case "RED":
			tmp = Color.RED;
			break;
		case "YELLOW":
			tmp = Color.YELLOW;
			break;
		case "TEAL":
			tmp = Color.TEAL;
			break;
		}
		this.getCadre().setStroke(tmp);
	}
	
	public void modifierTailleCadre(String newValue) {
		try {
			this.getCadre().setStrokeWidth(Integer.parseInt(newValue));
		} catch (Exception e) {

		}

	}
	
	public void modifierTailleOmbre(String newValue) {
		try {
			this.borderGlow.setHeight(Integer.parseInt(newValue));
		} catch (Exception e) {

		}

	}
	
	public void ajouterOmbre() {
		if (this.isOmbre()) {
			borderGlow.setOffsetY(0f);
			borderGlow.setOffsetX(0f); borderGlow.setColor(Color.BLACK);
			borderGlow.setWidth(80); borderGlow.setHeight(80);
			this.getCadre().setEffect(borderGlow);
			this.getCadre().relocate(this.getSelectedImage().getX() - this.getCadre().getStrokeWidth() / 2,
					this.getSelectedImage().getY() - this.getCadre().getStrokeWidth() / 2);
		}

	}

	public void supprimerOmbre() {
		borderGlow.setOffsetY(0f);
		borderGlow.setOffsetX(0f); borderGlow.setColor(Color.TRANSPARENT);
		borderGlow.setWidth(0); borderGlow.setHeight(0);
		this.getCadre().setEffect(borderGlow);
		this.getCadre().relocate(this.getSelectedImage().getX() - this.getCadre().getStrokeWidth() / 2,
				this.getSelectedImage().getY() - this.getCadre().getStrokeWidth() / 2);
	}

	public void modifierCouleurOmbre(String color) {
		Color tmp = null;
		switch (color) {
		case "AQUA":
			tmp = Color.AQUA;
			break;
		case "BLACK":
			tmp = Color.BLACK;
			break;
		case "BLUE":
			tmp = Color.BLUE;
			break;
		case "BLUEVIOLET":
			tmp = Color.BLUEVIOLET;
			break;
		case "BROWN":
			tmp = Color.BROWN;
			break;
		case "CYAN":
			tmp = Color.CYAN;
			break;
		case "DARKBLUE":
			tmp = Color.DARKBLUE;
			break;
		case "GREEN":
			tmp = Color.GREEN;
			break;
		case "RED":
			tmp = Color.RED;
			break;
		case "YELLOW":
			tmp = Color.YELLOW;
			break;
		case "TEAL":
			tmp = Color.TEAL;
			break;
		}
		borderGlow.setColor(tmp);
	}

	public void drawWithJavaFX(Init init) {

		// afficher l'image
		this.getSelectedImage().setX(this.getPhoto().getX());
		this.getSelectedImage().setY(this.getPhoto().getY());

		// afficher la légende
		this.setT(new Text(this.getSelectedImage().getX() + 20, this.getSelectedImage().getY() + this.getHeight() + 20,
				this.getPhoto().getTitre()));

		
		this.getPhoto().setX(this.getSelectedImage().getX());
		this.getPhoto().setY(this.getSelectedImage().getY());

		// Event pour permettre à l'utilisateur de modifier la légende
		this.getT().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			/*
			 * TextInput pour laisser l'utiliateur modifier le titre de la photo
			 */
			TextInputDialog input_titre = new TextInputDialog(this.getT().getText());
			input_titre.setTitle("Légende de la photo");
			input_titre.setContentText("Légende de la photo : ");
			input_titre.setHeaderText(null);

			// Réponse de l'utilisateur
			Optional<String> result = input_titre.showAndWait();
			if (result.isPresent()) {
				this.getT().setText(result.get());
			}
		});

		this.getSelectedImage().addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
			this.getSelectedImage().setX(event.getX() - (this.getWidth() / 2));
			this.getSelectedImage().setY(event.getY() - (this.getHeight() / 2));
			this.getT().setX(this.getSelectedImage().getX() + 20);
			this.getT().setY(this.getSelectedImage().getY() + this.getHeight() + 20);
			this.getCadre().relocate(this.getSelectedImage().getX() - this.getCadre().getStrokeWidth() / 2,
					this.getSelectedImage().getY() - this.getCadre().getStrokeWidth() / 2);
			event.consume();
		});

		this.getSelectedImage().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			init.getToolbar().init(this, init);
			event.consume();
		});

		// this.setCadre(true);
		init.getRoot().getChildren().addAll(this.getT(), this.getCadre(), this.getSelectedImage());
	}

	public void modifierHauteurImage(String hauteur) {
		try {
			this.getSelectedImage().setFitHeight(Integer.parseInt(hauteur));
			this.setHeight(Integer.parseInt(hauteur));
			this.setWidth(this.getSelectedImage().getBoundsInParent().getWidth());
			this.getCadre().setHeight(this.getHeight());
			this.getCadre().setWidth(this.getSelectedImage().getBoundsInParent().getWidth());
		} catch (Exception e) {
		}
		
	}
	
	public void modifierLargeurImage(String largeur) {
		
		try {
			this.getSelectedImage().setFitWidth(Integer.parseInt(largeur));
			this.setWidth(Integer.parseInt(largeur));
			this.setHeight(this.getSelectedImage().getBoundsInParent().getHeight());
			this.getCadre().setWidth(this.getWidth());
			this.getCadre().setHeight(this.getSelectedImage().getBoundsInParent().getHeight());
		} catch (Exception e) {
		}
		
	}
	
	public void setRatio(Boolean ratio)
	{
		if(ratio){
			this.getSelectedImage().setPreserveRatio(true);
		}
		else
		{
			this.getSelectedImage().setPreserveRatio(false);
		}
	}
	
	public void rotate()
	{
		this.getSelectedImage().setRotate(this.getSelectedImage().getRotate() + 90); 
		this.getCadre().setRotate(90);
		this.setHeight(this.getSelectedImage().getBoundsInParent().getHeight());
		this.setWidth(this.getSelectedImage().getBoundsInParent().getWidth());
	}

	

}