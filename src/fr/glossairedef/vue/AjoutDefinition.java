package fr.glossairedef.vue;

import fr.glossairedef.controleur.ControleurAjoutDef;
import fr.glossairedef.models.ChargementComboBox;
import fr.glossairedef.models.Constante;
import fr.glossairedef.models.MiseEnPageFenetre;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AjoutDefinition extends MiseEnPageFenetre implements ChargementComboBox {
	
	private Button btnAdd;

	private Label lbNom;
	private Label lbDefinition;
	private Label lbCategories;
	
	private TextField tfNom;
	
	private TextArea taDefinition;
	
	private ComboBox<String> cbCategories;
	
	private String nomDef;
	
	public AjoutDefinition(Stage fenetre) {

		this.fenetre = fenetre;
	}

	public AjoutDefinition(Stage fenetre, String nom) {

		this(fenetre);
		this.nomDef = nom;
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
		erreur.setContentText("Il se peut que vous ne possèdiez pas encore de catégories dans votre glossaire. Sans ça, vous ne pouvez pas créer de définitions.");
		
		erreur.showAndWait();
	}

	private void initialisation() {
		
		btnAdd = new Button("Valider");
		btnAdd.setPadding(new Insets(20));
		btnAdd.setFont(new Font(15));
		
		btnAdd.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> new ControleurAjoutDef(btnAdd, tfNom, taDefinition, cbCategories.getSelectionModel().getSelectedItem()).handle(event));
		
		lbInfoBot= new Label("Quelle nouvelle définition tu veux ajouter aujourd'hui ? ");
		
		lbNom= new Label("Nom : ");
		lbNom.setFont(new Font(15));
		
		lbDefinition= new Label("Définition : ");
		lbDefinition.setFont(new Font(15));
		
		lbCategories= new Label("Liste des catégories :");
		lbCategories.setMaxWidth(Constante.LARGEUR_FENETRE);
		lbCategories.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
		lbCategories.setFont(new Font(15));
		
		
		tfNom = new TextField();
		tfNom.setPromptText("InfoBot");
		tfNom.setTooltip(new Tooltip("Entre ici le nom de ta définition !"));
		
		taDefinition = new TextArea();
		taDefinition.setTooltip(new Tooltip("Entre ici ta définition !"));
		taDefinition.setPromptText("Bot crée à but informatif par le créateur de cette application. Outre son refrain routinier, il n'est pas très utile...");
		
		
		
	}
	
	private void positionnement() {

		tableau.add(lbNom, 0, 0);
		tableau.add(tfNom, 1, 0);
		tableau.add(lbDefinition, 0, 1);
		tableau.add(taDefinition, 1, 1);
		tableau.add(lbCategories, 0, 2);
		tableau.add(cbCategories, 1, 2);
		
		hbBtn.getChildren().addAll(btnAdd, btnRetour);
	}

	public void afficherSceneConfirmation() {

		lbConfirmation = new Label("La définition " + nomDef + " a bien été crée :D !" );

		super.miseEnPageConfirmationCommune();
	}

}
