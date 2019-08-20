package fr.glossairedef.models;

import fr.glossairedef.vue.Main;

public interface GestionDefinition {

	public default void creerNouvelleDefinition(String nom, String texte, String nomCategorie) {
		
		int i = 0;
		
		for(i = 0; i < Main.categories.length; i++) {
			
			if(null != Main.categories[i]) {
				if(nomCategorie.equals(Main.categories[i].getNom())){
					
					Main.categories[i].getDefinitions().add(new Definition(nom, texte));
					
				}
			}
		}
	}
}
