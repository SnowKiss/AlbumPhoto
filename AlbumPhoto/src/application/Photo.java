/*
 * 		Classe Photo : créer une photo, ajouter un titre
 * 
 */

package application;

public class Photo {
	
	// Attributs
	String titre;
	
	// Constructeur	
	public Photo(String titre) {
		super();
		this.titre = titre;
	}
	
	// Getters & Setters
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
}
