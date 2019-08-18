package fr.glossairedef.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.glossairedef.vue.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;

public interface GestionCategorie {

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
			erreur.setTitle("Erreur de cr�ation");
			erreur.setHeaderText("Erreur dans l'ajout de cette cat�gorie � la liste");
			erreur.setContentText("Vous avez atteint la limite de cat�gorie possible, il vous faut en supprimer pour en cr�er d'autres");
			
			erreur.showAndWait();
		}
		
		for( i = 0; i < Main.categories.length; i++) {
			
			System.out.println(i + " " + Main.categories[i]);
		}
		
		System.out.println("\n");
		
		return false;
	}
	
	public default void supprimerCategorie(String nomCategorie) {
		
		for(int i = 0; i < Main.categories.length; i++) {
			
			if(null != Main.categories[i]) {
				if(nomCategorie.equals(Main.categories[i].getNom())){
					
					Main.categories[i] = null;
				}
			}
			
		}
	}
}
