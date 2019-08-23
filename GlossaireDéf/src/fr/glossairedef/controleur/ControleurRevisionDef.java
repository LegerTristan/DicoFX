package fr.glossairedef.controleur;

import fr.glossairedef.vue.FenetreRevision;
import fr.glossairedef.vue.Main;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControleurRevisionDef implements EventHandler<MouseEvent> {

	private Stage fenetre;
	
	private Button btnClicked;
	private Button btnLancement;
	private Button btnSuivant;
	
	private String nomCategorie;
	private String reponse;
	
	public ControleurRevisionDef(Stage fenetre, Button btn) {
		
		this.fenetre = fenetre;
		this.btnClicked = btn;
	}

	public ControleurRevisionDef(Button btn, String nom, Stage fenetre) {

		this.btnLancement = btn;
		this.nomCategorie = nom;
		this.fenetre = fenetre;
	}
	
	public ControleurRevisionDef(Button btn, TextArea taDef) {

		btnSuivant = btn;
		reponse = taDef.getText();
	}

	@Override
	public void handle(MouseEvent event) {
		
		FenetreRevision revision = new FenetreRevision(this.fenetre);
		
		if(event.getSource() == btnClicked) {
			
			revision.afficherSceneChoix();
		}
		
		if(event.getSource() == btnLancement) {
			
			FenetreRevision.idCategorie = revision.determinerIdCategorie(nomCategorie);
			revision.afficherSceneRevisionDef();
		}
		
		if(event.getSource() == btnSuivant) {
			
			FenetreRevision questionSuivante = new FenetreRevision(Main.fenetre);
			
			if(reponse == null) {
				
				reponse = "";
			}
			if(FenetreRevision.getIterateur() < Main.categories[FenetreRevision.getIdCategorie()].getDefinitions().size()) {
				if(Main.categories[FenetreRevision.getIdCategorie()].getDefinitions().get(FenetreRevision.getIterateur()).getDefinition().equals(reponse)) {
					
					FenetreRevision.setNoteActuel(FenetreRevision.getNoteActuel() + 1);
				}
			}
			questionSuivante.afficherQuestionSuivanteDef(reponse);
		}
	}

}
