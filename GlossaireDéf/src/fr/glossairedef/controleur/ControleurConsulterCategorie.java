package fr.glossairedef.controleur;

import fr.glossairedef.vue.ConsultationCategorie;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControleurConsulterCategorie implements EventHandler<MouseEvent> {

	private Stage fenetre;
	
	private Button btnConsulter;
	
	public ControleurConsulterCategorie(Stage fenetre, Button btn) {
		
		this.fenetre = fenetre;
		this.btnConsulter = btn;
		
	}
	@Override
	public void handle(MouseEvent event) {

		if(event.getSource() == btnConsulter) {
			
			ConsultationCategorie consulterCategorie = new ConsultationCategorie(this.fenetre);
			consulterCategorie.afficherScene();
			
		}
		
	}

}
