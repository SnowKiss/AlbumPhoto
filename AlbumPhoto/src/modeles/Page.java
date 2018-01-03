/*
 * 		Classe Page : créer, ajout, suppression d'image
 * 
 */

package modeles;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.text.Text;
import main.Main;

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
	
	public void drawPage(int indexPage, int numberOfPages, Main main)
	{
		// draw each photo of the page
		/*for (Photo p : listePhotos)
		{
			p.drawPhoto(main);
		}
		*/
		// add caption of the index of the page
		Text t = new Text (550, 620, String.valueOf(numero+1) );
		main.getRoot().getChildren().addAll(t);
		// add all component to the scene
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
