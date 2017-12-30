/*
 * 		Classe Photo : créer une photo, ajouter un titre
 * 
 */

package modeles;

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
