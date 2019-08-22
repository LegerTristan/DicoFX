package fr.glossairedef.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Categorie implements Serializable {

	private static final long serialVersionUID = 1L;
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
