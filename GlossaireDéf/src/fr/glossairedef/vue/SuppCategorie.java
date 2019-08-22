package fr.glossairedef.vue;

import fr.glossairedef.controleur.ControleurSuppCategorie;
import fr.glossairedef.models.ChargementComboBox;
import fr.glossairedef.models.Constante;
import fr.glossairedef.models.FenetreEdition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SuppCategorie extends FenetreEdition implements ChargementComboBox {
	
	private Button btnSupp;
	
	private Label lbListeCategorie;
	
	private ComboBox<String> cbCategories;
	
	private String nomCategorie;

	public SuppCategorie(Stage fenetre) {

		this.fenetre = fenetre;
		
	}


	public SuppCategorie(Stage fenetre, String nom) {

		this.fenetre = fenetre;
		this.nomCategorie = nom;
	}


	public void afficherScene() {
		
		cbCategories = new ComboBox<String>();
		cbCategories.setPrefSize(Constante.LARGEUR_COMBOBOX, Constante.HAUTEUR_COMBOBOX);
		cbCategories = this.chargerComboBoxCategories();
		
		
		if(cbCategories.getItems().isEmpty()) {
			
			this.renvoieErreur();
			
		}
		else {
		
			this.initialisation();
			
			super.miseEnPageCommune();
			
			this.positionnement();
			
			fenetre.setScene(scPrincipal);
		}
		
	}


	private void renvoieErreur() {

		Alert erreur = new Alert(AlertType.ERROR);
		erreur.setTitle("Erreur de chargement");
		erreur.setHeaderText("Nous n'avons pas réussi à charger la liste des catégories");
		erreur.setContentText("Il se peut que vous ne possèdiez pas encore de catégories dans votre glossaire.");
		
		erreur.showAndWait();
		
	}


	private void initialisation() {
		
		lbInfoBot= new Label("Tu veux supprimer quelle catégorie aujourd'hui ?");
		
		lbListeCategorie= new Label("Liste des catégories : ");
		lbListeCategorie.setMaxWidth(Constante.LARGEUR_FENETRE);
		lbListeCategorie.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
		lbListeCategorie.setFont(new Font(15));
		
		btnSupp = new Button("Supprimer cette catégorie");
		btnSupp.setPadding(new Insets(20));
		btnSupp.setFont(new Font(15));
		
		btnSupp.setOnMouseClicked((MouseEvent event) -> new ControleurSuppCategorie(btnSupp, cbCategories.getSelectionModel().getSelectedItem()).handle(event));
	}
	
	private void positionnement() {
		
		tableau.add(lbListeCategorie, 0, 0);
		tableau.add(cbCategories, 1, 0);
		
		hbBtn.getChildren().addAll(btnSupp, btnRetour);
		hbBtn.setAlignment(Pos.CENTER);
		hbBtn.setSpacing(50);
	}


	public void afficherSceneConfirmation() {

		lbConfirmation = new Label("La catégorie " + nomCategorie + " a bien été supprimée :D !" );
		
		super.miseEnPageConfirmationCommune();
	}

}