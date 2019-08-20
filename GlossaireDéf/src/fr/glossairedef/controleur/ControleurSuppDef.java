package fr.glossairedef.controleur;

import fr.glossairedef.models.GestionDefinition;
import fr.glossairedef.vue.Main;
import fr.glossairedef.vue.SuppCategorie;
import fr.glossairedef.vue.SuppDefinition;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControleurSuppDef implements EventHandler<MouseEvent>, GestionDefinition {

	private Stage fenetre;
	
	private Button btnClicked;
	private Button btnValide;
	
	private int idCategorie;
	
	private String nomDefinition;
	
	
	public ControleurSuppDef(Stage fenetre, Button btn) {
		
		this.fenetre = fenetre;
		this.btnClicked = btn;
	}
	
	public ControleurSuppDef(Button btn, int idCategorie, String nom2) {

		this.btnValide = btn;
		this.idCategorie = idCategorie;
		this.nomDefinition = nom2;
		
	}

	@Override
	public void handle(MouseEvent event) {

		if(event.getSource() == btnClicked) {
			
			SuppDefinition suppDef = new SuppDefinition(this.fenetre);
			suppDef.afficherScene();
		}
		
		if(event.getSource() == btnValide) {
			
			if(null != nomDefinition) {
				
				Alert erreur = new Alert(AlertType.CONFIRMATION);
				erreur.setTitle("Suppression d'un �l�ment");
				erreur.setHeaderText("Suppression de l'�l�ment " + nomDefinition);
				erreur.setContentText("�tes-vous s�r(e) de vouloir supprimer cette d�finition ?");
				
				erreur.showAndWait();
				
				if(erreur.getResult() == ButtonType.OK) {
					
					this.supprimerDefinition(nomDefinition, idCategorie);
					
					SuppDefinition suppDefinition = new SuppDefinition(Main.fenetre, this.nomDefinition);
					suppDefinition.afficherSceneConfirmation();
					
				}
			}
			else {
				
				Alert erreur = new Alert(AlertType.ERROR);
				erreur.setTitle("Erreur d'entr�e d'information(s)");
				erreur.setHeaderText("Un �l�ment n'a �t� s�lectionn�");
				erreur.setContentText("Veuillez s�lectionner la cat�gorie et/ou la d�finition que vous souhaitez supprimer");
				
				erreur.showAndWait();
			}
				
		}
		
	}
}
