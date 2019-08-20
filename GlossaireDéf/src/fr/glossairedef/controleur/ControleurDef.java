package fr.glossairedef.controleur;

import fr.glossairedef.models.GestionDefinition;
import fr.glossairedef.vue.AjoutDefinition;
import fr.glossairedef.vue.Main;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControleurDef implements EventHandler<MouseEvent>, GestionDefinition{

	private Stage fenetre;
	
	private Button btnClicked;
	private Button btnValidite;
	
	private String nom;
	private String texte;
	private String nomCategorie;
	
	public ControleurDef(Stage fenetre, Button btn) {


		this.btnClicked = btn;
		this.fenetre = fenetre;
		
	}

	public ControleurDef(Button btn, TextField tfNom, TextArea taDefinition, String nomCategorie) {

		this.btnValidite = btn;
		this.nom = tfNom.getText();
		this.texte = taDefinition.getText();
		this.nomCategorie = nomCategorie;
		
	}

	@Override
	public void handle(MouseEvent event) {

		if(event.getSource() == btnClicked) {
			
			AjoutDefinition addDef = new AjoutDefinition(this.fenetre);
			addDef.afficherScene();
		}
		
		if(event.getSource() == btnValidite) {
			
			if(verifierNom() && verifierTexte() && v�rifierCategorie()) {
				
				this.creerNouvelleDefinition(nom, texte, nomCategorie);
				
				AjoutDefinition addDef = new AjoutDefinition(Main.fenetre, this.nom);
				addDef.afficherSceneConfirmation();
				
				
			}
			
			else {
				Alert erreur = new Alert(AlertType.ERROR);
				erreur.setTitle("Erreur d'entr�e d'information(s)");
				erreur.setHeaderText("Erreur d'identification du nom ou du texte de la d�fintion");
				erreur.setContentText("Veuillez saisir un nom et un texte pour la d�finition que vous souhaitez cr�er.");
				
				erreur.showAndWait();
			}
		}
		
	}

	private boolean v�rifierCategorie() {
		return null != nomCategorie;
	}

	private boolean verifierTexte() {
		return null != texte && !("".equals(texte));
	}

	private boolean verifierNom() {
		return null != nom && !("".equals(nom));
	}

}
