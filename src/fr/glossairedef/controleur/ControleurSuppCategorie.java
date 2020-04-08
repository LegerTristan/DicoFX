package fr.glossairedef.controleur;

import fr.glossairedef.models.GestionCategorie;
import fr.glossairedef.vue.Main;
import fr.glossairedef.vue.SuppCategorie;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControleurSuppCategorie implements EventHandler<MouseEvent>, GestionCategorie {

	private Stage fenetre;
	
	private Button btnClicked;

	private Button btnSupp;

	private String nomCategorie;
	
	public ControleurSuppCategorie(Stage fenetre, Button btn) {

		this.fenetre = fenetre;
		this.btnClicked = btn;
		
	}

	public ControleurSuppCategorie(Button btn, String nom) {

		this.btnSupp = btn;
		this.nomCategorie = nom;
	}

	@Override
	public void handle(MouseEvent event) {

		if(event.getSource() == btnClicked) {
			
			SuppCategorie suppCategorie = new SuppCategorie(this.fenetre);
			suppCategorie.afficherScene();
		}
		
		if(event.getSource() == btnSupp) {
			
			if(null != nomCategorie) {
				
				Alert erreur = new Alert(AlertType.CONFIRMATION);
				erreur.setTitle("Suppression d'un �l�ment");
				erreur.setHeaderText("Suppression de l'�l�ment " + nomCategorie);
				erreur.setContentText("�tes-vous s�r(e) de vouloir supprimer cette cat�gorie ?");
				
				erreur.showAndWait();
				
				if(erreur.getResult() == ButtonType.OK) {
					
					this.supprimerCategorie(nomCategorie);
					
					SuppCategorie suppCategorie = new SuppCategorie(Main.fenetre, this.nomCategorie);
					suppCategorie.afficherSceneConfirmation();
					
				}
			}
			else {
				
				Alert erreur = new Alert(AlertType.ERROR);
				erreur.setTitle("Erreur d'entr�e d'information(s)");
				erreur.setHeaderText("Aucun �l�ment n'a �t� s�lectionn�");
				erreur.setContentText("Veuillez s�lectionner la cat�gorie que vous souhaitez supprimer");
				
				erreur.showAndWait();
			}
				
		}
		
	}

}
