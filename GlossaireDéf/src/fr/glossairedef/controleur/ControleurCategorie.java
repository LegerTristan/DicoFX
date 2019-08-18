package fr.glossairedef.controleur;

import fr.glossairedef.models.GestionCategorie;
import fr.glossairedef.vue.AjoutCategorie;
import fr.glossairedef.vue.Main;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControleurCategorie implements EventHandler<MouseEvent>, GestionCategorie {

	private Stage fenetre;
	
	private Button btnClicked;
	private Button btnRetour;
	private Button btnValidite;

	private String nomCategorie;
	
	public ControleurCategorie(Button btn) {
	
		this.btnRetour = btn;
	 
	}

	public ControleurCategorie(Stage fenetre, Button btn) {

		this.fenetre = fenetre;
		this.btnClicked = btn;
		
	}

	public ControleurCategorie(Button btn, TextField nom) {

		this.btnValidite = btn;
		this.nomCategorie = nom.getText();
		
	}

	@Override
	public void handle(MouseEvent event) {

		if(event.getSource() == btnClicked) {
			
			AjoutCategorie addCategorie = new AjoutCategorie(this.fenetre);
			addCategorie.afficherScene();
			
		}
		
		if(event.getSource() == btnRetour) {
			
			Main glossaire = new Main();
			glossaire.afficherScene(Main.fenetre);
			
		}
		
		if(event.getSource() == btnValidite) {

			if(verifierNomCategorie()) {

				if(this.creerNouvelleCategorie(nomCategorie)){
				
					AjoutCategorie addCategorie = new AjoutCategorie(Main.fenetre, this.nomCategorie);
					addCategorie.afficherSceneConfirmation();
				}
			}
			else {
				
				Alert erreur = new Alert(AlertType.ERROR);
				erreur.setTitle("Erreur d'entrée d'information(s)");
				erreur.setHeaderText("Erreur d'identification du nom de la catégorie");
				erreur.setContentText("Veuillez saisir un nom pour la catégorie que vous souhaitez créer.");
				
				erreur.showAndWait();
				
			}
		}
		
	}

	private boolean verifierNomCategorie() {
		return null != nomCategorie && !("".equals(nomCategorie));
	}

}
