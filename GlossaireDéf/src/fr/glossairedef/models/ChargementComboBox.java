package fr.glossairedef.models;

import java.util.ArrayList;
import java.util.List;

import fr.glossairedef.vue.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public interface ChargementComboBox {

	public default ComboBox<String>chargerComboBox(){
			
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

}
