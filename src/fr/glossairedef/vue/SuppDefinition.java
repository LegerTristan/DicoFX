package fr.glossairedef.vue;

import fr.glossairedef.controleur.ControleurSuppDef;
import fr.glossairedef.models.ChargementComboBox;
import fr.glossairedef.models.Constante;
import fr.glossairedef.models.MiseEnPageFenetre;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SuppDefinition extends MiseEnPageFenetre implements ChargementComboBox {
	
	private Button btnSupp;
	
	private Label lbNom;
	private Label lbDefinition;
	private Label lbCategories;
	
	private ComboBox<String> cbCategories;
	
	private String nomDef;

	private ComboBox<String> cbDefinitions;
	
	
	/*
	 * Cette classe affiche la sc�ne concernant la suppression d'une d�finition
	 */
	
	public SuppDefinition(Stage fenetre) {

		this.fenetre = fenetre;
	}

	public SuppDefinition(Stage fenetre, String nomDefinition) {

		this(fenetre);
		this.nomDef = nomDefinition;
	}

	public void afficherScene() {
		
		cbCategories = new ComboBox<String>();
		cbCategories.setPrefSize(Constante.LARGEUR_COMBOBOX, Constante.HAUTEUR_COMBOBOX);
		cbCategories = this.chargerComboBoxCategories();
		cbCategories.setOnAction((event) -> { this.chargerCbDef();});
		
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
		erreur.setHeaderText("Nous n'avons pas r�ussi � charger la liste des cat�gories");
		erreur.setContentText("Il se peut que vous ne poss�diez pas encore de cat�gories dans votre glossaire. Sans �a, vous ne pouvez pas cr�er de d�finitions.");
		
		erreur.showAndWait();
	}

	private void initialisation() {

		
		
		btnSupp = new Button("Supprimer cette d�finition");
		btnSupp.setPadding(new Insets(20));
		btnSupp.setFont(new Font(15));
		
		btnSupp.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> new ControleurSuppDef(btnSupp, cbCategories.getSelectionModel().getSelectedItem(), cbDefinitions.getSelectionModel().getSelectedItem()).handle(event));
		
		lbInfoBot= new Label("Quelle nouvelle d�finition tu veux supprimer si cruellement aujourd'hui :'( ? ");
		
		lbNom= new Label("Nom : ");
		lbNom.setFont(new Font(15));
		
		lbDefinition= new Label("D�finition : ");
		lbDefinition.setFont(new Font(15));
		
		lbCategories= new Label("Liste des cat�gories :");
		lbCategories.setMaxWidth(Constante.LARGEUR_FENETRE);
		lbCategories.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
		lbCategories.setFont(new Font(15));
		
		cbDefinitions = new ComboBox<String>();
		cbDefinitions.setPrefSize(Constante.LARGEUR_COMBOBOX, Constante.HAUTEUR_COMBOBOX);
	}
	
	private void positionnement() {

		tableau.add(lbCategories, 0, 0);
		tableau.add(cbCategories, 1, 0);
		
		hbBtn.getChildren().addAll(btnSupp, btnRetour);

	}

	public void afficherSceneConfirmation() {
		
		lbConfirmation = new Label("La d�finition " + nomDef + " a bien �t� supprim�e :D !" );
		
		super.miseEnPageConfirmationCommune();
	}
	
	private void chargerCbDef() {

		if(null != cbCategories.getSelectionModel().getSelectedItem()) {
			
			this.cbDefinitions = this.chargerComboBoxDefinition(cbCategories.getSelectionModel().getSelectedIndex());

			tableau = new GridPane();
			
			tableau.setAlignment(Pos.CENTER);
			tableau.setVgap(15);
			tableau.setHgap(15);
			
			tableau.add(lbCategories, 0, 0);
			tableau.add(cbCategories, 1, 0);
			tableau.add(lbDefinition, 0, 1);
			tableau.add(cbDefinitions, 1, 1);
			
			root.setCenter(tableau);
			
		}
	}

}
