package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;

public class ToolBarController {
	private PhotoController photo;
	private Init init;
	private CheckBox checkbox;
	private Boolean ignore;

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
		this.checkbox = (CheckBox) init.getRoot().lookup("#checkbox_bordure");

		cocher();

		this.checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
				verif();
				if (!ignore) {
					modifierCadre();
				}
			}

		});
	}

	// Cette fonction permet de mettre à jour la Checkbox en fonction de l'image
	// sélectionnée
	private void cocher() {
		// On ignore l'évènement
		ignore = true;

		if (photo.isBordure()) {
			checkbox.setSelected(true);
		} else {
			checkbox.setSelected(false);
		}
	}

	private void modifierCadre() {
		if (checkbox.isSelected()) {
			photo.setBordure(true);
			photo.ajouterCadre();
		} else {
			photo.setBordure(false);
			photo.supprimerCadre();
			checkbox.setSelected(false);
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
