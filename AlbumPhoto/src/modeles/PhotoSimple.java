/*
 * 		Classe Photo : créer une photo, ajouter un titre
 * 
 */

package modeles;

public class PhotoSimple implements Photo {
	
	// Attributs
	String titre;
	
	// Constructeur	
	public PhotoSimple(String titre) {
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

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	
}
