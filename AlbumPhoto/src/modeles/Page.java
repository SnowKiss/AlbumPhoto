package modeles;

import java.util.ArrayList;
import java.util.List;

import controllers.Init;
import javafx.scene.text.Text;

/**
 * Classe Page : créer, ajout, suppression d'image
 *
 */
public class Page {
	
	// Attributs
	List<Photo> listePhotos;
	int numero;
	
	// Constructeur
	public Page(int numero) {
		super();
		this.listePhotos = new ArrayList<Photo>();
		this.numero = numero;
	}
	
	// Getters & Setters
	public List<Photo> getListePhotos() {
		return listePhotos;
	}

	public void setListePhotos(List<Photo> listePhotos) {
		this.listePhotos = listePhotos;
	}
	
	public void drawPage(int indexPage, int numberOfPages, Init init)
	{
		// Ajoute le numéro de page
		Text t = new Text (550, 620, String.valueOf(numero+1) );
		init.getRoot().getChildren().addAll(t);
	}

	public void addPhoto(Photo photo) {
		listePhotos.add(photo);
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;		
	}
}
