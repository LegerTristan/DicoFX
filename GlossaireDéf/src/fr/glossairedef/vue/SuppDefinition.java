package fr.glossairedef.vue;

import fr.glossairedef.controleur.ControleurRetour;
import fr.glossairedef.controleur.ControleurSuppDef;
import fr.glossairedef.models.ChargementComboBox;
import fr.glossairedef.models.Constante;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class SuppDefinition implements ChargementComboBox {

	private Stage fenetre;
	
	private Scene scAjoutDef;
	private Scene scConfirmation;
	
	private BorderPane root;
	private GridPane tableau;
	
	private HBox hbBtn;
	private VBox vbConfirmation;
	
	private Button btnSupp;
	private Button btnRetour;
	
	private Label lbInfoBot;
	private Label lbNom;
	private Label lbDefinition;
	private Label lbCategories;
	private Label lbConfirmation;
	
	private ComboBox<String> cbCategories;
	
	private String nomDef;

	private ComboBox<String> cbDefinitions;
	
	public SuppDefinition(Stage fenetre) {

		this.fenetre = fenetre;
	}

	public SuppDefinition(Stage fenetre, String nomDefinition) {

		this(fenetre);
		this.nomDef = nomDefinition;
	}

	public void afficherScene() {
		root = new BorderPane();

		scAjoutDef = new Scene(root, Constante.LARGEUR_FENETRE, Constante.HAUTEUR_FENETRE);
		
		cbCategories = new ComboBox<String>();
		cbCategories.setPrefSize(Constante.LARGEUR_COMBOBOX, Constante.HAUTEUR_COMBOBOX);
		cbCategories = this.chargerComboBoxCategories();
		cbCategories.setOnAction((event) -> { this.chargerCbDef();});
		
		if(cbCategories.getItems().isEmpty()) {
			
			this.renvoieErreur();
			
		}
		else {
			
			this.initialisation();
			
			this.positionnement();
			
			fenetre.setScene(scAjoutDef);
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

		tableau = new GridPane();
		
		hbBtn = new HBox();
		hbBtn.setMaxHeight(Constante.HAUTEUR_FENETRE / 5);
		hbBtn.setPrefHeight(Constante.HAUTEUR_FENETRE / 5);
		hbBtn.setBorder(new Border( 
				new BorderStroke(Color.BLACK,Color.BLACK, Color.BLACK, Color.BLACK,
						BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
						CornerRadii.EMPTY, new BorderWidths(3), null)));
		
		btnSupp = new Button("Supprimer cette définition");
		btnSupp.setPadding(new Insets(20));
		btnSupp.setFont(new Font(15));
		
		btnSupp.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> new ControleurSuppDef(btnSupp, cbCategories.getSelectionModel().getSelectedIndex(), cbDefinitions.getSelectionModel().getSelectedItem()).handle(event));
		
		btnRetour = new Button("Revenir en arrière");
		btnRetour.setPadding(new Insets(20));
		btnRetour.setFont(new Font(15));
		
		btnRetour.addEventFilter(MouseEvent.MOUSE_CLICKED, new ControleurRetour(btnRetour));
		
		lbInfoBot= new Label("Quelle nouvelle définition tu veux supprimer si cruellement aujourd'hui :'( ? ");
		lbInfoBot.setMaxWidth(Constante.LARGEUR_FENETRE);
		lbInfoBot.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
		lbInfoBot.setFont(new Font(15));
		
		lbNom= new Label("Nom : ");
		lbNom.setFont(new Font(15));
		
		lbDefinition= new Label("Définition : ");
		lbDefinition.setFont(new Font(15));
		
		lbCategories= new Label("Liste des catégories :");
		lbCategories.setMaxWidth(Constante.LARGEUR_FENETRE);
		lbCategories.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
		lbCategories.setFont(new Font(15));
		
		cbDefinitions = new ComboBox<String>();
		cbDefinitions.setPrefSize(Constante.LARGEUR_COMBOBOX, Constante.HAUTEUR_COMBOBOX);
		
	}
	
	private void positionnement() {

		tableau.add(lbCategories, 0, 0);
		tableau.add(cbCategories, 1, 0);
		
		tableau.setAlignment(Pos.CENTER);
		tableau.setVgap(15);
		tableau.setHgap(15);
		
		hbBtn.getChildren().addAll(btnSupp, btnRetour);
		hbBtn.setAlignment(Pos.CENTER);
		hbBtn.setSpacing(50);
		
		lbInfoBot.setAlignment(Pos.CENTER);
		lbInfoBot.setTextAlignment(TextAlignment.CENTER);
		
		root.setCenter(tableau);
		root.setTop(lbInfoBot);
		root.setBottom(hbBtn);

	}

	public void afficherSceneConfirmation() {

		vbConfirmation = new VBox();
		
		scConfirmation = new Scene(vbConfirmation, Constante.LARGEUR_FENETRE, Constante.HAUTEUR_FENETRE);
		
		lbConfirmation = new Label("La définition " + nomDef + " a bien été supprimée :D !" );
		lbConfirmation.setMaxWidth(Constante.LARGEUR_FENETRE);
		lbConfirmation.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
		lbConfirmation.setFont(new Font(15));
		
		lbConfirmation.setAlignment(Pos.CENTER);
		
		btnRetour = new Button("Revenir au menu");
		btnRetour.setPadding(new Insets(20));
		btnRetour.setFont(new Font(15));
		
		btnRetour.addEventFilter(MouseEvent.MOUSE_CLICKED, new ControleurRetour(btnRetour));	
		
		vbConfirmation.getChildren().addAll(lbConfirmation, btnRetour);
		vbConfirmation.setAlignment(Pos.CENTER);
		vbConfirmation.setSpacing(50);
		
		fenetre.setScene(scConfirmation);
		
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
