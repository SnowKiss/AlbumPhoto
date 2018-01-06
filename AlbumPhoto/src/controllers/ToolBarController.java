package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ToolBarController {
	private PhotoController photo;
	private Init init;
	private CheckBox checkbox;
	private Boolean ignore;
	private ChoiceBox<String> choiceBoxCouleur;
	private TextField tailleCadre;

	public ToolBarController() {
		super();
		this.ignore = false;

	}

	public void afficherToolBar() {
		this.init.getRoot().lookup("#toolBar").setManaged(true);
		this.init.getRoot().lookup("#toolBar").setVisible(true);
	}

	public void init(PhotoController photoController, Init init) {
		this.init = init;
		this.photo = photoController;
		afficherToolBar();
		this.checkbox = (CheckBox) init.getRoot().lookup("#checkbox_cadre");
		this.checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
				verif();
				if (!ignore) {
					modifierCadre();
				}
			}

		});
		
		this.choiceBoxCouleur = (ChoiceBox) init.getRoot().lookup("#choicebox_couleur_cadre");
		this.choiceBoxCouleur.getSelectionModel()
	    .selectedItemProperty()
	    .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> modifierCouleurCadre(newValue) );

		
		this.tailleCadre = (TextField) init.getRoot().lookup("#textField_taille");
		this.tailleCadre.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    	if (newValue.matches("\\d*")) {
		    		modifierTailleCadre(newValue);
		    	}
		    }
		});
		
		
		cocher();

		
	}

	protected void modifierTailleCadre(String newValue) {
		photo.modifierTailleCadre(newValue);
		
	}

	public void modifierCouleurCadre(String newValue) {
		if(photo.isBordure()) {
			photo.modifierCouleurCadre(newValue);
		}
	}

	// Cette fonction permet de mettre à jour la Checkbox en fonction de l'image
	// sélectionnée
	private void cocher() {
		// On ignore l'évènement
		ignore = true;

		if (photo.isBordure()) {
			checkbox.setSelected(true);
			choiceBoxCouleur.setDisable(false);
			tailleCadre.setDisable(false);
		} else {
			checkbox.setSelected(false);
			choiceBoxCouleur.setDisable(true);
			tailleCadre.setDisable(true);
		}
	}

	private void modifierCadre() {
		if (checkbox.isSelected()) {
			photo.setBordure(true);
			photo.ajouterCadre();
			choiceBoxCouleur.setDisable(false);
			tailleCadre.setDisable(false);
		} else {
			photo.setBordure(false);
			photo.supprimerCadre();
			checkbox.setSelected(false);
			choiceBoxCouleur.setDisable(true);
			tailleCadre.setDisable(true);
		}
	}

	private void verif() {
		ignore = false;
		if (!checkbox.isSelected() && !photo.isBordure()) {
			// on ignore et on décoche
			ignore = true;
			checkbox.setSelected(false);
		}
		if (checkbox.isSelected() && photo.isBordure()) {
			// on ingore et on coche
			ignore = true;
			checkbox.setSelected(true);
		}
	}

	public PhotoController getPhoto() {
		return photo;
	}

	public void setPhoto(PhotoController photo) {
		this.photo = photo;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}

}
