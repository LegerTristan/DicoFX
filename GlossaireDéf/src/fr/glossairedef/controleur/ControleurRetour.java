package fr.glossairedef.controleur;

import fr.glossairedef.vue.Main;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ControleurRetour implements EventHandler<MouseEvent> {

	private Button btnRetour;
	
	public ControleurRetour(Button btn) {
		
		this.btnRetour = btn;
	}
	@Override
	public void handle(MouseEvent event) {

		if(event.getSource() == btnRetour) {
			
			Main glossaire = new Main();
			glossaire.afficherScene(Main.fenetre);
		}
		
	}

}
