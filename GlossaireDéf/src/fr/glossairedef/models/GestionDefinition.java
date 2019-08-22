package fr.glossairedef.models;

import fr.glossairedef.vue.Main;

public interface GestionDefinition extends GestionCategorie {

	public default void creerNouvelleDefinition(String nom, String texte, String nomCategorie) {
		
		int i = 0;
		
		for(i = 0; i < Main.categories.length; i++) {
			
			if(null != Main.categories[i]) {
				if(nomCategorie.equals(Main.categories[i].getNom())){
					
					Main.categories[i].getDefinitions().add(new Definition(nom, texte));
					
				}
			}
		}
		SauvegardeAutomatique.autoSave();
	}
	
	public default void supprimerDefinition(String nomDefinition, String nomCategorie) {
		
		int idCategorie = determinerIdCategorie(nomCategorie);
		
		for(int j = 0; j < Main.categories[idCategorie].getDefinitions().size(); j++) {
			
			if(null != Main.categories[idCategorie]) {
				if(nomDefinition.equals(Main.categories[idCategorie].getDefinitions().get(j).getNom())){

					Main.categories[idCategorie].getDefinitions().remove(j);
					
				}
			}
		}
		SauvegardeAutomatique.autoSave();
	}
}
