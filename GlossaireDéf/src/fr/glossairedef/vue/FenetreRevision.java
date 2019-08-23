package fr.glossairedef.vue;

import fr.glossairedef.controleur.ControleurRetour;
import fr.glossairedef.controleur.ControleurRevisionDef;
import fr.glossairedef.controleur.ControleurRevisionNom;
import fr.glossairedef.models.ChargementComboBox;
import fr.glossairedef.models.Constante;
import fr.glossairedef.models.GestionDefinition;
import fr.glossairedef.models.MiseEnPageFenetre;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class FenetreRevision extends MiseEnPageFenetre implements ChargementComboBox, GestionDefinition{

	private VBox vbRevision;
	
	private Label lbCategorie;
	private Label lbIntroduction;
	private Label lbEnonce;
	private Label lbResultat;
	
	private Button btnLancementNom;
	private Button btnLancementDef;
	private Button btnSuivant;
	
	private TextField tfNom;
	
	private TextArea taDef;
	
	private ComboBox<String> cbCategories;
	
	private String nomCategorie;
	
	private static int iterateur;
	public static int idCategorie;
	private static int noteActuel;
	
	public FenetreRevision(Stage fenetre) {

		this.fenetre = fenetre;
		
	}

	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	public static int getIterateur() {
		return iterateur;
	}

	public static int getIdCategorie() {
		return idCategorie;
	}

	public static int getNoteActuel() {
		return noteActuel;
	}

	public static void setNoteActuel(int noteActuel) {
		FenetreRevision.noteActuel = noteActuel;
	}

	public void afficherSceneChoix() {

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

		lbInfoBot= new Label("Vas-y, choisis la catégorie que tu veux réviser :) !");
		
		lbCategorie= new Label("Liste des catégories : ");
		lbCategorie.setMaxWidth(Constante.LARGEUR_FENETRE);
		lbCategorie.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
		lbCategorie.setFont(new Font(15));
		
		btnLancementNom = new Button("Réviser le nom");
		btnLancementNom.setPadding(new Insets(20));
		btnLancementNom.setFont(new Font(15));
		
		btnLancementNom.setOnMouseClicked((MouseEvent event) -> new ControleurRevisionNom(btnLancementNom, cbCategories.getSelectionModel().getSelectedItem(), this.fenetre).handle(event));
		
		btnLancementDef = new Button("Réviser la définition");
		btnLancementDef.setPadding(new Insets(20));
		btnLancementDef.setFont(new Font(15));
		
		btnLancementDef.setOnMouseClicked((MouseEvent event) -> new ControleurRevisionDef(btnLancementDef, cbCategories.getSelectionModel().getSelectedItem(), this.fenetre).handle(event));
	}
	
	private void positionnement() {

		tableau.add(lbCategorie, 0, 0);
		tableau.add(cbCategories, 1, 0);
		
		hbBtn.getChildren().addAll(btnLancementNom, btnLancementDef, btnRetour);
		hbBtn.setAlignment(Pos.CENTER);
		hbBtn.setSpacing(50);
		
	}

	public void afficherSceneRevisionNom() {
		
		if(Main.categories[idCategorie].getDefinitions().size() > 0) {
			
			iterateur = 0;
			noteActuel = 0;
			
			lbEnonce = new Label(this.renvoieTxtDeDefinition(nomCategorie, idCategorie));

			if(lbEnonce != null && !("".equals(lbEnonce))) {
				
				vbRevision = new VBox();

				hbBtn = new HBox();
				hbBtn.setMaxHeight(Constante.HAUTEUR_FENETRE / 5);
				hbBtn.setPrefHeight(Constante.HAUTEUR_FENETRE / 5);
				hbBtn.setBorder(new Border( 
						new BorderStroke(Color.BLACK,Color.BLACK, Color.BLACK, Color.BLACK,
								BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
								CornerRadii.EMPTY, new BorderWidths(3), null)));
				
				hbBtn.setAlignment(Pos.CENTER);
				
				btnSuivant = new Button("Suivant");
				btnSuivant.setPadding(new Insets(20));
				btnSuivant.setFont(new Font(15));
				
				lbIntroduction = new Label("Quelle est le nom de de la définition :");
				lbIntroduction.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
				lbIntroduction.setMaxHeight(Constante.HAUTEUR_FENETRE / 10);
				lbIntroduction.setFont(new Font(18));
				
				lbIntroduction.setAlignment(Pos.TOP_CENTER);
				lbIntroduction.setTextAlignment(TextAlignment.CENTER);
				
				lbEnonce.setPrefHeight(Constante.HAUTEUR_FENETRE / 3);
				lbEnonce.setMaxHeight(Constante.HAUTEUR_FENETRE / 3);
				lbEnonce.setFont(new Font(18));
				
				lbEnonce.setAlignment(Pos.CENTER);
				lbEnonce.setTextAlignment(TextAlignment.CENTER);
				
				tfNom = new TextField();
				
				tfNom.setMaxWidth(Constante.LARGEUR_FENETRE / 5);
				tfNom.setMaxHeight(Constante.HAUTEUR_FENETRE / 10);
				tfNom.setPadding(new Insets(10));
				tfNom.setFont(new Font(18));
				tfNom.setPromptText("Réponse");
				tfNom.setTooltip(new Tooltip("Insère ta réponse ici !"));
				
				btnSuivant.setOnMouseClicked((event -> new ControleurRevisionNom(btnSuivant, tfNom).handle(event)));
				
				hbBtn.getChildren().add(btnSuivant);
				vbRevision.getChildren().addAll(lbIntroduction, lbEnonce, tfNom, hbBtn);
				
				vbRevision.setAlignment(Pos.CENTER);
				vbRevision.setSpacing(50);
				
				
				scPrincipal = new Scene(vbRevision, Constante.LARGEUR_FENETRE, Constante.HAUTEUR_FENETRE);
				fenetre.setScene(scPrincipal);
			}
			else {
				
				this.afficherResultat();			
			}
		}
		else {
			
			Alert erreur = new Alert(AlertType.ERROR);
			erreur.setTitle("Erreur de chargement");
			erreur.setHeaderText("Nous n'avons pas réussi à charger la liste des définitions");
			erreur.setContentText("Il se peut que vous ne possèdiez pas encore de définitions dans cette catégorie.");
			
			erreur.showAndWait();
			
		}
	}
	
	public void afficherQuestionSuivanteNom(String valeurRecup) {

		lbEnonce = new Label();
		
		iterateur ++;
		lbEnonce.setText(this.renvoieTxtDeDefinition(nomCategorie, idCategorie));
		if(lbEnonce != null) {
			if(!("".equals(lbEnonce.getText())) && iterateur < Main.categories[idCategorie].getDefinitions().size()) {
				
				vbRevision = new VBox();

				hbBtn = new HBox();
				hbBtn.setMaxHeight(Constante.HAUTEUR_FENETRE / 5);
				hbBtn.setPrefHeight(Constante.HAUTEUR_FENETRE / 5);
				hbBtn.setBorder(new Border( 
						new BorderStroke(Color.BLACK,Color.BLACK, Color.BLACK, Color.BLACK,
								BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
								CornerRadii.EMPTY, new BorderWidths(3), null)));
				
				hbBtn.setAlignment(Pos.CENTER);
				
				btnSuivant = new Button("Suivant");
				btnSuivant.setPadding(new Insets(20));
				btnSuivant.setFont(new Font(15));
				
				lbIntroduction = new Label("Quelle est le nom de de la définition :");
				lbIntroduction.setPrefHeight(Constante.HAUTEUR_FENETRE / 8);
				lbIntroduction.setMaxHeight(Constante.HAUTEUR_FENETRE / 8);
				lbIntroduction.setFont(new Font(18));
				
				lbIntroduction.setAlignment(Pos.TOP_CENTER);
				lbIntroduction.setTextAlignment(TextAlignment.CENTER);
				
				lbEnonce.setPrefHeight(Constante.HAUTEUR_FENETRE / 3);
				lbEnonce.setMaxHeight(Constante.HAUTEUR_FENETRE / 3);
				lbEnonce.setFont(new Font(18));
				
				lbEnonce.setAlignment(Pos.CENTER);
				lbEnonce.setTextAlignment(TextAlignment.CENTER);
				
				tfNom = new TextField();

				tfNom.setMaxWidth(Constante.LARGEUR_FENETRE / 5);
				tfNom.setMaxHeight(Constante.HAUTEUR_FENETRE / 10);
				tfNom.setFont(new Font(18));
				tfNom.setPromptText("Réponse");
				tfNom.setTooltip(new Tooltip("Insère ta réponse ici !"));
				
				btnSuivant.setOnMouseClicked((event -> new ControleurRevisionNom(btnSuivant, tfNom).handle(event)));
				
				hbBtn.getChildren().add(btnSuivant);
				vbRevision.getChildren().addAll(lbIntroduction, lbEnonce, tfNom, hbBtn);
				
				vbRevision.setAlignment(Pos.CENTER);
				vbRevision.setSpacing(50);
				
				scPrincipal = new Scene(vbRevision, Constante.LARGEUR_FENETRE, Constante.HAUTEUR_FENETRE);
				fenetre.setScene(scPrincipal);
			}
			else {
				
				this.afficherResultat();			
			}
		}
		else {
			
			this.afficherResultat();			
		}
	}

	private void afficherResultat() {

		vbRevision = new VBox();
		
		hbBtn = new HBox();
		hbBtn.setMaxHeight(Constante.HAUTEUR_FENETRE / 5);
		hbBtn.setPrefHeight(Constante.HAUTEUR_FENETRE / 5);
		hbBtn.setBorder(new Border( 
				new BorderStroke(Color.BLACK,Color.BLACK, Color.BLACK, Color.BLACK,
						BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
						CornerRadii.EMPTY, new BorderWidths(3), null)));
		
		hbBtn.setAlignment(Pos.BOTTOM_CENTER);
		
		lbInfoBot = new Label("Voici ton score final :D !");
		lbInfoBot.setMaxWidth(Constante.LARGEUR_FENETRE);
		lbInfoBot.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
		lbInfoBot.setFont(new Font(18));
		lbInfoBot.setMaxHeight(Constante.HAUTEUR_FENETRE / 8);
		
		lbInfoBot.setAlignment(Pos.TOP_CENTER);
		lbInfoBot.setTextAlignment(TextAlignment.CENTER);
		
		lbResultat = new Label(noteActuel + "/" + Main.categories[idCategorie].getNoteMax());
		lbResultat.setMaxHeight(Constante.HAUTEUR_FENETRE);
		lbResultat.setPrefHeight(Constante.HAUTEUR_FENETRE / 2);
		lbResultat.setPadding(new Insets(20));
		lbResultat.setFont(new Font(30));
		
		lbResultat.setAlignment(Pos.CENTER);
		lbResultat.setTextAlignment(TextAlignment.CENTER);
		
		btnRetour = new Button("Revenir au menu");
		btnRetour.setPadding(new Insets(20));
		btnRetour.setFont(new Font(15));
		btnRetour.setAlignment(Pos.BOTTOM_CENTER);
		
		btnRetour.addEventFilter(MouseEvent.MOUSE_CLICKED, new ControleurRetour(btnRetour));
		
		hbBtn.getChildren().add(btnRetour);
		
		vbRevision.getChildren().addAll(lbInfoBot, lbResultat, hbBtn);
		vbRevision.setAlignment(Pos.CENTER);
		
		scPrincipal = new Scene(vbRevision, Constante.LARGEUR_FENETRE, Constante.HAUTEUR_FENETRE);
		fenetre.setScene(scPrincipal);
	}

	

	public void afficherSceneRevisionDef() {

		if(Main.categories[idCategorie].getDefinitions().size() > 0) {
			
			iterateur = 0;
			noteActuel = 0;
			
			lbEnonce = new Label(this.renvoieTxtDeNom(nomCategorie, idCategorie));

			if(lbEnonce != null && !("".equals(lbEnonce))) {
				
				vbRevision = new VBox();

				hbBtn = new HBox();
				hbBtn.setMaxHeight(Constante.HAUTEUR_FENETRE / 5);
				hbBtn.setPrefHeight(Constante.HAUTEUR_FENETRE / 5);
				hbBtn.setBorder(new Border( 
						new BorderStroke(Color.BLACK,Color.BLACK, Color.BLACK, Color.BLACK,
								BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
								CornerRadii.EMPTY, new BorderWidths(3), null)));
				
				hbBtn.setAlignment(Pos.CENTER);
				
				btnSuivant = new Button("Suivant");
				btnSuivant.setPadding(new Insets(20));
				btnSuivant.setFont(new Font(15));
				
				lbIntroduction = new Label("Quelle est la définition du mot :");
				lbIntroduction.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
				lbIntroduction.setMaxHeight(Constante.HAUTEUR_FENETRE / 10);
				lbIntroduction.setFont(new Font(18));
				
				lbIntroduction.setAlignment(Pos.TOP_CENTER);
				lbIntroduction.setTextAlignment(TextAlignment.CENTER);
				
				lbEnonce.setPrefHeight(Constante.HAUTEUR_FENETRE / 3);
				lbEnonce.setMaxHeight(Constante.HAUTEUR_FENETRE / 3);
				lbEnonce.setFont(new Font(18));
				
				lbEnonce.setAlignment(Pos.CENTER);
				lbEnonce.setTextAlignment(TextAlignment.CENTER);
				
				taDef = new TextArea();
				
				taDef.setMaxWidth(Constante.LARGEUR_FENETRE / 2);
				taDef.setMaxHeight(Constante.HAUTEUR_FENETRE / 5);
				taDef.setPadding(new Insets(10));
				taDef.setFont(new Font(18));
				taDef.setPromptText("Réponse");
				taDef.setTooltip(new Tooltip("Insère ta réponse ici !"));
				
				btnSuivant.setOnMouseClicked((event -> new ControleurRevisionDef(btnSuivant, taDef).handle(event)));
				
				hbBtn.getChildren().add(btnSuivant);
				vbRevision.getChildren().addAll(lbIntroduction, lbEnonce, taDef, hbBtn);
				
				vbRevision.setAlignment(Pos.CENTER);
				vbRevision.setSpacing(50);
				
				
				scPrincipal = new Scene(vbRevision, Constante.LARGEUR_FENETRE, Constante.HAUTEUR_FENETRE);
				fenetre.setScene(scPrincipal);
			}
			else {
				
				this.afficherResultat();			
			}
		}
		else {
			
			Alert erreur = new Alert(AlertType.ERROR);
			erreur.setTitle("Erreur de chargement");
			erreur.setHeaderText("Nous n'avons pas réussi à charger la liste des définitions");
			erreur.setContentText("Il se peut que vous ne possèdiez pas encore de définitions dans cette catégorie.");
			
			erreur.showAndWait();
			
		}
	}
	
	public void afficherQuestionSuivanteDef(String valeurRecup) {

		lbEnonce = new Label();
		
		iterateur ++;
		lbEnonce.setText(this.renvoieTxtDeNom(nomCategorie, idCategorie));
		if(lbEnonce != null) {
			if(!("".equals(lbEnonce.getText())) && iterateur < Main.categories[idCategorie].getDefinitions().size()) {
				
				vbRevision = new VBox();

				hbBtn = new HBox();
				hbBtn.setMaxHeight(Constante.HAUTEUR_FENETRE / 5);
				hbBtn.setPrefHeight(Constante.HAUTEUR_FENETRE / 5);
				hbBtn.setBorder(new Border( 
						new BorderStroke(Color.BLACK,Color.BLACK, Color.BLACK, Color.BLACK,
								BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
								CornerRadii.EMPTY, new BorderWidths(3), null)));
				
				hbBtn.setAlignment(Pos.CENTER);
				
				btnSuivant = new Button("Suivant");
				btnSuivant.setPadding(new Insets(20));
				btnSuivant.setFont(new Font(15));
				
				lbIntroduction = new Label("Quelle est la définition du mot :");
				lbIntroduction.setPrefHeight(Constante.HAUTEUR_FENETRE / 8);
				lbIntroduction.setMaxHeight(Constante.HAUTEUR_FENETRE / 8);
				lbIntroduction.setFont(new Font(18));
				
				lbIntroduction.setAlignment(Pos.TOP_CENTER);
				lbIntroduction.setTextAlignment(TextAlignment.CENTER);
				
				lbEnonce.setPrefHeight(Constante.HAUTEUR_FENETRE / 3);
				lbEnonce.setMaxHeight(Constante.HAUTEUR_FENETRE / 3);
				lbEnonce.setFont(new Font(18));
				
				lbEnonce.setAlignment(Pos.CENTER);
				lbEnonce.setTextAlignment(TextAlignment.CENTER);
				
				taDef = new TextArea();

				taDef.setMaxWidth(Constante.LARGEUR_FENETRE / 2);
				taDef.setMaxHeight(Constante.HAUTEUR_FENETRE / 5);
				taDef.setFont(new Font(18));
				taDef.setPromptText("Réponse");
				taDef.setTooltip(new Tooltip("Insère ta réponse ici !"));
				
				btnSuivant.setOnMouseClicked((event -> new ControleurRevisionDef(btnSuivant, taDef).handle(event)));
				
				hbBtn.getChildren().add(btnSuivant);
				vbRevision.getChildren().addAll(lbIntroduction, lbEnonce, taDef, hbBtn);
				
				vbRevision.setAlignment(Pos.CENTER);
				vbRevision.setSpacing(50);
				
				scPrincipal = new Scene(vbRevision, Constante.LARGEUR_FENETRE, Constante.HAUTEUR_FENETRE);
				fenetre.setScene(scPrincipal);
			}
			else {
				
				this.afficherResultat();			
			}
		}
		else {
			
			this.afficherResultat();			
		}
	}
}
