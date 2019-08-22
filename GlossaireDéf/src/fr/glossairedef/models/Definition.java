package fr.glossairedef.models;

import java.io.Serializable;

public class Definition implements Serializable {

 static final long serialVersionUID = 1L;
	private String nom;
	private String definition;
	
	public Definition(String nom, String definition) {
		
		this.nom = nom;
		this.definition = definition;
		
	}

	public String getNom() {
		return nom;
	}

	public String getDefinition() {
		return definition;
	}
}
