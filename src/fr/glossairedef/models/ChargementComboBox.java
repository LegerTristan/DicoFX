package fr.glossairedef.models;

import java.util.ArrayList;
import java.util.List;

import fr.glossairedef.vue.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public interface ChargementComboBox {
	
	/*
	 * Interface gérant le chargement d'une ComboBox que ce soit une définition ou une catégorie
	 */

	public default ComboBox<String>chargerComboBoxCategories(){
			
			ComboBox<String> cb = new ComboBox<String>();
			List<String> test2 = new ArrayList<String>();
			
			for(int i = 0; i < Main.categories.length; i++) {
				
				if( null != Main.categories[i]) {
					
					test2.add(Main.categories[i].getNom());
					
				}
			}
			
			ObservableList<String> test = FXCollections.observableList(test2);
			cb.setItems(test);
			
		return cb;
	}
	
	public default ComboBox<String>chargerComboBoxDefinition(int i){
		
		ComboBox<String> cb = new ComboBox<String>();
		List<String> list = new ArrayList<String>();
		
		for(int j = 0; j < Main.categories[i].getDefinitions().size(); j++) {
				
			list.add(Main.categories[i].getDefinitions().get(j).getNom());
				
		}
		
		ObservableList<String> test = FXCollections.observableList(list);
		cb.setItems(test);
		
	return cb;
	}

}
