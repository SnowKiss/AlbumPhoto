package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class ToolBarController {
	private PhotoController photo;
	private Init init;
	private CheckBox checkbox_cadre;
	private Boolean ignoreCadre;
	private Boolean ignoreOmbre;
	private ChoiceBox<String> choiceBoxCouleurCadre;
	private TextField tailleCadre;
	private CheckBox checkbox_ombre;
	private ChoiceBox<String> choiceBoxCouleurOmbre;
	private TextField tailleOmbre;
	private TextField hauteurImage;
	private TextField largeurImage;
	private CheckBox checkbox_taille;
	private Button bouton_rotation;

	public ToolBarController() {
		super();
		this.ignoreCadre = false;
		this.ignoreOmbre = false;

	}

	public void afficherToolBar() {
		this.init.getRoot().lookup("#toolBar").setManaged(true);
		this.init.getRoot().lookup("#toolBar").setVisible(true);
		//this.checkbox_taille = (CheckBox) init.getRoot().lookup("#checkbox_taille");
		//this.checkbox_taille.setSelected(true);
	}

	public void init(PhotoController photoController, Init init) {
		this.init = init;
		this.photo = photoController;

		afficherToolBar();

		/* Cadre */
		this.checkbox_cadre = (CheckBox) init.getRoot().lookup("#checkbox_cadre");
		this.checkbox_cadre.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
				verifCadre();
				if (!ignoreCadre) {
					modifierCadre();
				}
			}

		});

		this.choiceBoxCouleurCadre = (ChoiceBox) init.getRoot().lookup("#choicebox_couleur_cadre");
		this.choiceBoxCouleurCadre.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue,
						String newValue) -> modifierCouleurCadre(newValue));

		this.tailleCadre = (TextField) init.getRoot().lookup("#textField_taille");
		this.tailleCadre.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.matches("\\d*")) {
					modifierTailleCadre(newValue);
				}
			}
		});

		/* Ombre */
		this.checkbox_ombre = (CheckBox) init.getRoot().lookup("#checkbox_ombre");
		this.checkbox_ombre.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
				verifOmbre();
				if (!ignoreOmbre) {
					modifierOmbre();
				}
			}

		});

		this.choiceBoxCouleurOmbre = (ChoiceBox) init.getRoot().lookup("#choicebox_couleur_ombre");
		this.choiceBoxCouleurOmbre.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue,
						String newValue) -> modifierCouleurOmbre(newValue));

		this.tailleOmbre = (TextField) init.getRoot().lookup("#taille_ombre");
		this.tailleOmbre.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.matches("\\d*")) {
					modifierTailleOmbre(newValue);
				}
			}
		});

		/* Redimension */
		this.hauteurImage = (TextField) init.getRoot().lookup("#textField_hauteur");
		this.hauteurImage.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.matches("\\d*")) {
					photo.modifierHauteurImage(newValue);
				}
			}
		});

		this.largeurImage = (TextField) init.getRoot().lookup("#textField_largeur");
		this.largeurImage.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.matches("\\d*")) {
					photo.modifierLargeurImage(newValue);
				}
			}
		});

		
		/*
		this.checkbox_taille.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
				photo.setRatio(new_val);
			}

		});*/

		/* Rotation */
		this.bouton_rotation = (Button) init.getRoot().lookup("#btn_rotation");
		this.bouton_rotation.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				photo.rotate();
			}
		});

		cocherCadre();
		cocherOmbre();

	}

	// Cette fonction permet de mettre à jour la Checkbox du cadre en fonction
	// de l'image sélectionnée
	public void cocherCadre() {
		// On ignore l'évènement
		ignoreCadre = true;

		if (photo.isCadre()) {
			checkbox_cadre.setSelected(true);
			choiceBoxCouleurCadre.setDisable(false);
			tailleCadre.setDisable(false);
			checkbox_ombre.setDisable(false);
		} else {
			checkbox_cadre.setSelected(false);
			choiceBoxCouleurCadre.setDisable(true);
			tailleCadre.setDisable(true);
			checkbox_ombre.setDisable(true);
		}
	}

	private void modifierCadre() {
		if (checkbox_cadre.isSelected()) {
			photo.setCadre(true);
			photo.ajouterCadre();
			choiceBoxCouleurCadre.setDisable(false);
			tailleCadre.setDisable(false);
			checkbox_ombre.setDisable(false);
		} else {
			photo.setCadre(false);
			photo.supprimerCadre();
			checkbox_cadre.setSelected(false);
			choiceBoxCouleurCadre.setDisable(true);
			tailleCadre.setDisable(true);
			checkbox_ombre.setDisable(true);
		}
	}

	public void modifierTailleCadre(String newValue) {
		photo.modifierTailleCadre(newValue);

	}

	public void modifierCouleurCadre(String newValue) {
		if (photo.isCadre()) {
			photo.modifierCouleurCadre(newValue);
		}
	}

	private void verifCadre() {
		ignoreCadre = false;
		if (!checkbox_cadre.isSelected() && !photo.isCadre()) {
			// on ignore et on décoche
			ignoreCadre = true;
			checkbox_cadre.setSelected(false);
		}
		if (checkbox_cadre.isSelected() && photo.isCadre()) {
			// on ingore et on coche
			ignoreCadre = true;
			checkbox_cadre.setSelected(true);
		}
	}

	// Cette fonction permet de mettre à jour la Checkbox du cadre en fonction
	// de l'image sélectionnée
	private void cocherOmbre() {
		// On ignore l'évènement
		ignoreOmbre = true;

		if (photo.isOmbre()) {
			checkbox_ombre.setSelected(true);
			choiceBoxCouleurOmbre.setDisable(false);
			tailleOmbre.setDisable(false);
		} else {
			checkbox_ombre.setSelected(false);
			choiceBoxCouleurOmbre.setDisable(true);
			tailleOmbre.setDisable(true);
		}
	}

	private void modifierOmbre() {
		if (checkbox_ombre.isSelected()) {
			photo.setOmbre(true);
			photo.ajouterOmbre();
			choiceBoxCouleurOmbre.setDisable(false);
			tailleOmbre.setDisable(false);
		} else {
			photo.setOmbre(false);
			photo.supprimerOmbre();
			checkbox_ombre.setSelected(false);
			choiceBoxCouleurOmbre.setDisable(true);
			tailleOmbre.setDisable(true);
		}
	}

	public void modifierTailleOmbre(String newValue) {
		photo.modifierTailleOmbre(newValue);

	}

	public void modifierCouleurOmbre(String newValue) {
		if (photo.isOmbre()) {
			photo.modifierCouleurOmbre(newValue);
		}
	}

	private void verifOmbre() {
		ignoreOmbre = false;
		if (!checkbox_ombre.isSelected() && !photo.isOmbre()) {
			// on ignore l'évènement et on décoche
			ignoreOmbre = true;
			checkbox_ombre.setSelected(false);
		}
		if (checkbox_ombre.isSelected() && photo.isOmbre()) {
			// on ignore l'évènement et on coche
			ignoreOmbre = true;
			checkbox_ombre.setSelected(true);
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
