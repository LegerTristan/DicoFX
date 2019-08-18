package fr.glossairedef.models;

import fr.glossairedef.vue.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public interface GestionDefinition {

	public default void creerNouvelleDefinition(String nom, String texte, String nomCategorie) {
		
		int i, k = 0;
		
		for(i = 0; i < Main.categories.length; i++) {
			
			if(null != Main.categories[i]) {
				if(nomCategorie.equals(Main.categories[i].getNom())){
					
					Main.categories[i].getDefinitions().add(new Definition(nom, texte));
					
					k = i;
				}
			}
		}
		
		for(int j = 0; j < Main.categories[k].getDefinitions().size(); j++) {
			
			System.out.println(Main.categories[k].getDefinitions().get(j).getNom());
		}
	}
}
