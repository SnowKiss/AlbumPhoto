/*
 * 		Classe Photo : créer une photo, ajouter un titre
 * 
 */

package modeles;

import java.io.File;

import controllers.Init;
import controllers.PhotoController;

public class PhotoSimple implements Photo {
	
	// Attributs
	File imageFile;
	String titre;
	double x;
	double y;
	PhotoController photoController;

	// Constructeur	
	public PhotoSimple(File photo, String titre) {
		super();
		this.titre = titre;
		this.imageFile = photo;
		this.x=300;
		this.y=120;
		this.setPhotoController(new PhotoController(this));
	}
	
	// Getters & Setters
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void drawPhoto(Init init) {
		
		photoController.drawWithJavaFX(init);
	}

	public File getImage() {

		return imageFile;
	}

	public File getImageURL() {
		return imageFile;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public PhotoController getPhotoController() {
		return photoController;
	}

	public void setPhotoController(PhotoController photoController) {
		this.photoController = photoController;
	}
	
}
