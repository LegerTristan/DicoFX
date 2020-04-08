package fr.glossairedef.vue;

import fr.glossairedef.models.ChargementComboBox;
import fr.glossairedef.models.Constante;
import fr.glossairedef.models.MiseEnPageFenetre;
import fr.glossairedef.models.GestionCategorie;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ConsultationCategorie extends MiseEnPageFenetre implements ChargementComboBox, GestionCategorie{
	
	private VBox vbDef;

	private Label lbListeCategorie;
	private Label lbListeDef;
	private Label lbPresentDef;
	
	private TextArea taDefinition;
	
	private ComboBox<String> cbCategories;
	private ComboBox<String> cbDefinitions;
	
	public ConsultationCategorie(Stage fenetre) {

		this.fenetre = fenetre;
	}

	public void afficherScene() {
		
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
		
		vbDef = new VBox();
		vbDef.setMaxHeight(Constante.HAUTEUR_FENETRE);
		vbDef.setPrefHeight(Constante.HAUTEUR_FENETRE / 4);
		vbDef.setPrefHeight(Constante.LARGEUR_FENETRE / 5);
		vbDef.setBorder(new Border( 
				new BorderStroke(Color.BLACK,Color.BLACK, Color.BLACK, Color.BLACK,
						BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID,
						CornerRadii.EMPTY, new BorderWidths(3), null)));
		
		lbInfoBot= new Label("Sélectionne la catégorie que tu souhaites modifier !");
		
		lbListeCategorie= new Label("Liste des catégories :");
		lbListeCategorie.setFont(new Font(15));
		
		lbListeDef= new Label("Liste des définitions : ");
		lbListeDef.setFont(new Font(15));
		
		lbPresentDef = new Label("Définitions présentes dans cette catégorie : ");
		lbPresentDef.setMaxWidth(Constante.LARGEUR_FENETRE / 2);
		lbPresentDef.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
		lbPresentDef.setFont(new Font(15));
		
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
		
		hbBtn.getChildren().addAll(btnRetour);
		
		vbDef.getChildren().addAll(lbPresentDef, taDefinition);
		vbDef.setAlignment(Pos.CENTER);
		vbDef.setSpacing(50);
		
		lbListeDef.setAlignment(Pos.CENTER);
		lbListeDef.setTextAlignment(TextAlignment.CENTER);
		
		lbPresentDef.setAlignment(Pos.CENTER);

		root.setRight(vbDef);
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
			
			int idCategorie = this.determinerIdCategorie(cbCategories.getSelectionModel().getSelectedItem());
			
			this.cbDefinitions = this.chargerComboBoxDefinition(idCategorie);

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
