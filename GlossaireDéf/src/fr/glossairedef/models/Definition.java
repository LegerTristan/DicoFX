package fr.glossairedef.models;

public class Definition {

	private String nom;
	private String definition;
	
	public Definition(String nom, String definition) {
		
		this.nom = nom;
		this.definition = definition;
		
	}

	public String getNom() {
		return nom;
	}
}
