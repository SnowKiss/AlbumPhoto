/*
 * 		Classe Page : créer, ajout, suppression d'image
 * 
 */

package application;

import java.util.ArrayList;
import java.util.List;

public class Page {
	
	// Attributs
	List<Photo> listePhotos;
	
	// Constructeur
	public Page() {
		super();
		this.listePhotos = new ArrayList<Photo>();
	}
	
	// Getters & Setters
	public List<Photo> getListePhotos() {
		return listePhotos;
	}

	public void setListePhotos(List<Photo> listePhotos) {
		this.listePhotos = listePhotos;
	}
}
