package fr.glossairedef.models;

import java.util.ArrayList;
import java.util.List;

public class Categorie {

	private String nom;
	private List<Definition> definitions;
	
	public Categorie(String nom, List<Definition> definitions) {
		
		this.nom = nom;
		this.definitions = new ArrayList<Definition>();
		
	}

	public List<Definition> getDefinitions() {
		return definitions;
	}

	public String getNom() {
		return nom;
	}
	
}
