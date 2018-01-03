/*
 * 		Classe Album : créer, nomme un album, ajoute, supprime une page
 * 
 */

package modeles;

import java.util.ArrayList;
import java.util.List;

public class Album {
	
	// Attributs
	String nom;
	List<Page> listePages;
	
	// Constructeur
	public Album(String nom) {
		super();
		this.nom = nom;
		this.listePages = new ArrayList<Page>();
		this.listePages.add(new Page());
	}

	// Getters & Setters
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Page> getListePages() {
		return listePages;
	}

	public void setListePages(List<Page> listePages) {
		this.listePages = listePages;
	}
	
}
