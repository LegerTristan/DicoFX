package fr.glossairedef.controleur;

import fr.glossairedef.vue.FenetreRevision;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControleurRevisionDef implements EventHandler<MouseEvent> {

	private Stage fenetre;
	
	private Button btnClicked;
	private Button btnLancement;
	
	private String nomCategorie;
	
	public ControleurRevisionDef(Stage fenetre, Button btn) {
		
		this.fenetre = fenetre;
		this.btnClicked = btn;
	}

	@Override
	public void handle(MouseEvent event) {
		
		FenetreRevision revision = new FenetreRevision(this.fenetre);
		
		if(event.getSource() == btnClicked) {
			
			revision.afficherSceneChoix();
			
		}
	}

}
