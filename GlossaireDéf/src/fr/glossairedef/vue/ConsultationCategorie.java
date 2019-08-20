package fr.glossairedef.vue;

import fr.glossairedef.controleur.ControleurRetour;
import fr.glossairedef.models.ChargementComboBox;
import fr.glossairedef.models.Constante;
import fr.glossairedef.models.GestionCategorie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
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

public class ConsultationCategorie implements ChargementComboBox, GestionCategorie{

	private Stage fenetre;
	
	private Scene scConsultationCategorie;
	
	private BorderPane root;
	private GridPane tableau;
	
	private HBox hbBtn;
	private VBox vbDef;
	
	private Label lbInfoBot;
	private Label lbListeCategorie;
	private Label lbListeDef;
	private Label lbPresentDef;
	
	private Button btnRetour;
	
	private TextArea taDefinition;
	
	private ComboBox<String> cbCategories;
	private ComboBox<String> cbDefinitions;
	
	public ConsultationCategorie(Stage fenetre) {

		this.fenetre = fenetre;
	}

	public void afficherScene() {
		
		root = new BorderPane();

		scConsultationCategorie = new Scene(root, Constante.LARGEUR_FENETRE, Constante.HAUTEUR_FENETRE);
		
		cbCategories = new ComboBox<String>();
		cbCategories.setTooltip(new Tooltip("Sélectionne ici ta catégorie !"));
		cbCategories.setPrefSize(Constante.LARGEUR_COMBOBOX, Constante.HAUTEUR_COMBOBOX);
		cbCategories = this.chargerComboBoxCategories();
		cbCategories.setOnAction((event) -> {this.afficherDef(cbCategories.getSelectionModel().getSelectedItem()); this.chargerCbDef();});
		
		if(cbCategories.getItems().isEmpty()) {
			
			this.renvoieErreur();
			
		}
		else {
		
			this.initialisation();
			
			this.positionnement();
			
			fenetre.setScene(scConsultationCategorie);
		}
		
	}
	
	private void renvoieErreur() {

		Alert erreur = new Alert(AlertType.ERROR);
		erreur.setTitle("Erreur de chargement");
		erreur.setHeaderText("Nous n'avons pas réussi à charger la liste des catégories");
		erreur.setContentText("Il se peut que vous ne possèdiez pas encore de catégories dans votre glossaire.");
		
		erreur.showAndWait();
		
		Main glossaire = new Main();
		glossaire.afficherScene(Main.fenetre);
		
	}

	private void initialisation() {

		tableau = new GridPane();
		tableau.setMaxHeight(Constante.HAUTEUR_FENETRE / 2);
		tableau.setPrefHeight(Constante.HAUTEUR_FENETRE / 3);
		
		hbBtn = new HBox();
		hbBtn.setMaxHeight(Constante.HAUTEUR_FENETRE / 5);
		hbBtn.setPrefHeight(Constante.HAUTEUR_FENETRE / 5);
		hbBtn.setBorder(new Border( 
				new BorderStroke(Color.BLACK,Color.BLACK, Color.BLACK, Color.BLACK,
						BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
						CornerRadii.EMPTY, new BorderWidths(3), null)));
		
		vbDef = new VBox();
		vbDef.setMaxHeight(Constante.HAUTEUR_FENETRE);
		vbDef.setPrefHeight(Constante.HAUTEUR_FENETRE / 4);
		vbDef.setPrefHeight(Constante.LARGEUR_FENETRE / 5);
		vbDef.setBorder(new Border( 
				new BorderStroke(Color.BLACK,Color.BLACK, Color.BLACK, Color.BLACK,
						BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID,
						CornerRadii.EMPTY, new BorderWidths(3), null)));
		
		lbInfoBot= new Label("Sélectionne la catégorie que tu souhaites modifier !");
		lbInfoBot.setMaxWidth(Constante.LARGEUR_FENETRE);
		lbInfoBot.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
		lbInfoBot.setFont(new Font(15));
		
		lbListeCategorie= new Label("Liste des catégories :");
		lbListeCategorie.setFont(new Font(15));
		
		lbListeDef= new Label("Liste des définitions : ");
		lbListeDef.setFont(new Font(15));
		
		lbPresentDef = new Label("Définitions présentes dans cette catégorie : ");
		lbPresentDef.setMaxWidth(Constante.LARGEUR_FENETRE / 2);
		lbPresentDef.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
		lbPresentDef.setFont(new Font(15));
		
		btnRetour = new Button("Revenir en arrière");
		btnRetour.setPadding(new Insets(20));
		btnRetour.setFont(new Font(15));
		
		btnRetour.addEventFilter(MouseEvent.MOUSE_CLICKED, new ControleurRetour(btnRetour));
		
		taDefinition = new TextArea();
		taDefinition.setPrefHeight(Constante.HAUTEUR_FENETRE / 2);
		taDefinition.setMaxHeight(Constante.HAUTEUR_FENETRE / 2);
		taDefinition.setPrefWidth(Constante.LARGEUR_FENETRE / 2);
		taDefinition.setTranslateX(1);
		taDefinition.setTranslateY(5);
		taDefinition.setTooltip(new Tooltip("Ici apparaît les définitions liés à cette catégorie !"));
		taDefinition.setEditable(false);
		
		cbDefinitions = new ComboBox<String>();
		cbDefinitions.setPrefSize(Constante.LARGEUR_COMBOBOX, Constante.HAUTEUR_COMBOBOX);
	}
	
	private void positionnement() {

		tableau.add(lbListeCategorie, 0, 0);
		tableau.add(cbCategories, 1, 0);
		
		tableau.setAlignment(Pos.CENTER);
		tableau.setVgap(15);
		tableau.setHgap(25);
		
		hbBtn.getChildren().addAll(btnRetour);
		hbBtn.setAlignment(Pos.CENTER);
		hbBtn.setSpacing(50);
		
		vbDef.getChildren().addAll(lbPresentDef, taDefinition);
		vbDef.setAlignment(Pos.CENTER);
		vbDef.setSpacing(50);
		
		lbInfoBot.setAlignment(Pos.CENTER);
		lbInfoBot.setTextAlignment(TextAlignment.CENTER);
		
		lbListeDef.setAlignment(Pos.CENTER);
		lbListeDef.setTextAlignment(TextAlignment.CENTER);
		
		lbPresentDef.setAlignment(Pos.CENTER);
		
		root.setTop(lbInfoBot);
		root.setCenter(tableau);
		root.setRight(vbDef);
		root.setBottom(hbBtn);

	}
	
	private void afficherDef(String nomCategorie) {
		
		String str = "";
		for(int i = 0; i < Main.categories.length; i++) {
			
			if(null != Main.categories[i]) {
				if(verifierIdenticiteDuNom(nomCategorie, i)){
					
					for(int j = 0; j < Main.categories[i].getDefinitions().size(); j++) {
						
						str = str + Main.categories[i].getDefinitions().get(j).getNom() + "\n";
						
					}
					taDefinition.setText(str);
				}
			}
		}
		
	}
	
	private void chargerCbDef() {

		if(null != cbCategories.getSelectionModel().getSelectedItem()) {
			
			this.cbDefinitions = this.chargerComboBoxDefinition(cbCategories.getSelectionModel().getSelectedIndex());

			tableau = new GridPane();
			tableau.setMaxHeight(Constante.HAUTEUR_FENETRE / 2);
			tableau.setPrefHeight(Constante.HAUTEUR_FENETRE / 3);
			
			tableau.setAlignment(Pos.CENTER);
			tableau.setVgap(15);
			tableau.setHgap(15);
			
			tableau.add(lbListeCategorie, 0, 0);
			tableau.add(cbCategories, 1, 0);
			tableau.add(lbListeDef, 0, 1);
			tableau.add(cbDefinitions, 1, 1);
			
			root.setCenter(tableau);
		}
	}
	
}
