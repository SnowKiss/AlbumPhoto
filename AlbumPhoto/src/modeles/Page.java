/*
 * 		Classe Page : créer, ajout, suppression d'image
 * 
 */

package modeles;

import java.util.ArrayList;
import java.util.List;

public class Page {
	
	// Attributs
	List<PhotoSimple> listePhotos;
	
	// Constructeur
	public Page() {
		super();
		this.listePhotos = new ArrayList<PhotoSimple>();
	}
	
	// Getters & Setters
	public List<PhotoSimple> getListePhotos() {
		return listePhotos;
	}

	public void setListePhotos(List<PhotoSimple> listePhotos) {
		this.listePhotos = listePhotos;
	}
}
