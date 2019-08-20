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
				erreur.setTitle("Suppression d'un élément");
				erreur.setHeaderText("Suppression de l'élément " + nomDefinition);
				erreur.setContentText("Êtes-vous sûr(e) de vouloir supprimer cette définition ?");
				
				erreur.showAndWait();
				
				if(erreur.getResult() == ButtonType.OK) {
					
					this.supprimerDefinition(nomDefinition, idCategorie);
					
					SuppDefinition suppDefinition = new SuppDefinition(Main.fenetre, this.nomDefinition);
					suppDefinition.afficherSceneConfirmation();
					
				}
			}
			else {
				
				Alert erreur = new Alert(AlertType.ERROR);
				erreur.setTitle("Erreur d'entrée d'information(s)");
				erreur.setHeaderText("Un élément n'a été sélectionné");
				erreur.setContentText("Veuillez sélectionner la catégorie et/ou la définition que vous souhaitez supprimer");
				
				erreur.showAndWait();
			}
				
		}
		
	}
}
