package controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import modeles.PhotoSimple;

/** 
 * Contr�leur d'une photo
 *
 */
public class PhotoController {
	private Image image;
	private ImageView selectedImage;
	private Rectangle cadre;
	private Text t;
	private double width;
	private double height;
	private Controller controleur;
	private Boolean possede_cadre; // Permet de savoir si l'image poss�de un
									// cadre ou non
	private Boolean possede_ombre; // Permet de savoir si l'image poss�de un
									// cadre ou non
	private PhotoSimple photo;
	private DropShadow borderGlow;
	private Init init;
	
	private Boolean toolBarActive = false;

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
		this.setWidth(300);
		this.setHeight((this.getWidth() / this.getImage().getWidth()) * this.getImage().getHeight());

		// Ajout d'un cadre "transparent"
		this.setCadre(new Rectangle(this.getWidth(), this.getHeight()));
		this.getCadre().setFill(Color.TRANSPARENT);
		this.getCadre().setStroke(Color.TRANSPARENT);
		this.getCadre().setStrokeWidth(0);

		this.borderGlow = new DropShadow();

		this.setControleur(new Controller());
	}

	public void drawWithJavaFX(Init init) {
		this.init = init;

		// afficher l'image
		this.getSelectedImage().setX(this.getPhoto().getX());
		this.getSelectedImage().setY(this.getPhoto().getY());

		// afficher la l�gende
		this.setT(new Text(this.getSelectedImage().getX() + 20, this.getSelectedImage().getY() + this.getHeight() + 20,
				this.getPhoto().getTitre()));

		this.getPhoto().setX(this.getSelectedImage().getX());
		this.getPhoto().setY(this.getSelectedImage().getY());

		// Event pour permettre � l'utilisateur de modifier la l�gende
		this.getT().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			/*
			 * TextInput pour laisser l'utiliateur modifier le titre de la photo
			 */
			TextInputDialog input_titre = new TextInputDialog(this.getT().getText());
			input_titre.setTitle("L�gende de la photo");
			input_titre.setContentText("L�gende de la photo : ");
			input_titre.setHeaderText(null);

			// R�ponse de l'utilisateur
			Optional<String> result = input_titre.showAndWait();
			if (result.isPresent()) {
				this.getT().setText(result.get());
			}
		});

		this.getSelectedImage().addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
			this.getSelectedImage().setX(event.getX() - (this.getWidth() / 2));
			this.getSelectedImage().setY(event.getY() - (this.getHeight() / 2));
			this.majTitre();
			this.getCadre().relocate(this.getSelectedImage().getX() - this.getCadre().getStrokeWidth() / 2,
					this.getSelectedImage().getY() - this.getCadre().getStrokeWidth() / 2);
			event.consume();
		});

		this.getSelectedImage().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if(!toolBarActive)
			{
			init.getToolbar().init(this, init);
			toolBarActive = true;
			}
			event.consume();
		});

		// this.setCadre(true);
		init.getRoot().getChildren().addAll(this.getT(), this.getCadre(), this.getSelectedImage());
		
		// Event pour la suppression d'une image avec touche Suppr du clavier
		this.init.getRoot().getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.DELETE) {
					supprimerImage();
				}
			}
		});
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

	public void ajouterOmbre() {
		if (this.isOmbre()) {
			borderGlow.setOffsetY(0f);
			borderGlow.setOffsetX(0f);
			borderGlow.setColor(Color.BLACK);
			borderGlow.setWidth(80);
			borderGlow.setHeight(80);
			this.getCadre().setEffect(borderGlow);
			this.getCadre().relocate(this.getSelectedImage().getX() - this.getCadre().getStrokeWidth() / 2,
					this.getSelectedImage().getY() - this.getCadre().getStrokeWidth() / 2);
		}

	}

	public void supprimerOmbre() {
		borderGlow.setOffsetY(0f);
		borderGlow.setOffsetX(0f);
		borderGlow.setColor(Color.TRANSPARENT);
		borderGlow.setWidth(0);
		borderGlow.setHeight(0);
		this.getCadre().setEffect(borderGlow);
		this.getCadre().relocate(this.getSelectedImage().getX() - this.getCadre().getStrokeWidth() / 2,
				this.getSelectedImage().getY() - this.getCadre().getStrokeWidth() / 2);
	}

	public void modifierTailleOmbre(String newValue) {
		try {
			this.borderGlow.setHeight(Integer.parseInt(newValue));
			this.borderGlow.setWidth(Integer.parseInt(newValue));
		} catch (Exception e) {

		}

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

	public void modifierHauteurImage(String hauteur) {
		try {
			this.getSelectedImage().setFitWidth(0);
			this.getSelectedImage().setFitHeight(Integer.parseInt(hauteur));
			this.setHeight(Integer.parseInt(hauteur));
			this.setWidth(this.getSelectedImage().getBoundsInParent().getWidth());
			this.getCadre().setHeight(this.getHeight());
			this.getCadre().setWidth(this.getWidth());
			TextField tf_largeur = (TextField) this.init.getRoot().lookup("#textField_largeur");
			tf_largeur.setText(Double.toString(Math.round(this.getSelectedImage().getBoundsInParent().getWidth())));
			majTitre();
		} catch (Exception e) {
		}

	}

	public void modifierLargeurImage(String largeur) {

		try {
			this.getSelectedImage().setFitHeight(0);
			this.getSelectedImage().setFitWidth(Integer.parseInt(largeur));
			this.setWidth(Integer.parseInt(largeur));
			this.setHeight(this.getSelectedImage().getBoundsInParent().getHeight());
			this.getCadre().setWidth(this.getWidth());
			this.getCadre().setHeight(this.getHeight());
			TextField tf_hauteur = (TextField) this.init.getRoot().lookup("#textField_hauteur");
			tf_hauteur.setText(Double.toString(Math.round(this.getSelectedImage().getBoundsInParent().getHeight())));
			majTitre();
		} catch (Exception e) {
			
		}

	}

	public void setRatio(Boolean ratio, String derniere_modif) {
		if (ratio) {
			this.getSelectedImage().setPreserveRatio(true);
			this.setWidth(this.getSelectedImage().getBoundsInParent().getWidth());
			this.setHeight(this.getSelectedImage().getBoundsInParent().getHeight());
			this.getCadre().setWidth(this.getWidth());
			this.getCadre().setHeight(this.getHeight());
		} else {
			this.getSelectedImage().setPreserveRatio(false);
			this.setWidth(this.getSelectedImage().getBoundsInParent().getWidth());
			this.setHeight(this.getSelectedImage().getBoundsInParent().getHeight());
			this.getCadre().setWidth(this.getWidth());
			this.getCadre().setHeight(this.getHeight());
		}
		if(derniere_modif.equals("Largeur"))
		{
			TextField tf_hauteur = (TextField) this.init.getRoot().lookup("#textField_hauteur");
			tf_hauteur.setText(Double.toString(Math.round(this.getSelectedImage().getBoundsInParent().getHeight())));
		}
		else
		{
			TextField tf_largeur = (TextField) this.init.getRoot().lookup("#textField_largeur");
			tf_largeur.setText(Double.toString(Math.round(this.getSelectedImage().getBoundsInParent().getWidth())));
		}
	}
	
	public void rotate() {
		this.getSelectedImage().setRotate(this.getSelectedImage().getRotate() + 90);
		this.getCadre().setRotate(90);
		this.setHeight(this.getSelectedImage().getBoundsInParent().getHeight());
		this.setWidth(this.getSelectedImage().getBoundsInParent().getWidth());
	}

	public void majTitre() {
		this.getT().setX(this.getSelectedImage().getX() + 20);
		this.getT().setY(this.getSelectedImage().getY() + this.getHeight() + 20);
	}

	public void supprimerImage() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Suppression de la photo");
		alert.setHeaderText(null);
		alert.setContentText("Voulez-vous vraiment supprimer cette photo ?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			this.init.getRoot().getChildren().remove(this.getSelectedImage());
			this.supprimerCadre();
			this.supprimerOmbre();
			this.getT().setText(null);
		} else {
		}
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

	

	
}