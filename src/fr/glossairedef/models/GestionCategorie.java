package fr.glossairedef.models;

import fr.glossairedef.vue.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public interface GestionCategorie {
	
	/*
	 * Interface qui contient toutes les interactions avec les catégories.
	 */

	public default boolean creerNouvelleCategorie(String nom) {
		
		int i = 0;
		
		while(null != Main.categories[i] && i < (Main.categories.length - 1)) {

			i ++;
			
		}

		if(null == Main.categories[i]) {
			
			Main.categories[i] = new Categorie(nom, null);
			
			return true;
				
		}
		else if(i >= (Main.categories.length - 1)) {
		
			Alert erreur = new Alert(AlertType.ERROR);
			erreur.setTitle("Erreur de création");
			erreur.setHeaderText("Erreur dans l'ajout de cette catégorie à la liste");
			erreur.setContentText("Vous avez atteint la limite de catégorie possible, il vous faut en supprimer pour en créer d'autres");
			
			erreur.showAndWait();
		}
		
		SauvegardeAutomatique.autoSave();
		
		return false;
	}
	
	public default void supprimerCategorie(String nomCategorie) {
		
		for(int i = 0; i < Main.categories.length; i++) {
			
			if(null != Main.categories[i]) {
				if(verifierIdenticiteDuNom(nomCategorie, i)){
					
					for(int j = 0; j < Main.categories[i].getDefinitions().size(); j++) {
						Main.categories[i].getDefinitions().remove(j);
					}
					Main.categories[i] = null;
				}
			}
			
		}
		SauvegardeAutomatique.autoSave();
	}

	public default boolean verifierIdenticiteDuNom(String nomCategorie, int i) {
		return nomCategorie.equals(Main.categories[i].getNom());
	}
	
	public default int determinerIdCategorie(String nomCategorie) {
		
		for(int i = 0; i < Main.categories.length; i++) {
			
			if(null != Main.categories[i]) {
				if(verifierIdenticiteDuNom(nomCategorie, i)){
					
					return i;
		
				}
			}
		}
		return -1;
	}
}
