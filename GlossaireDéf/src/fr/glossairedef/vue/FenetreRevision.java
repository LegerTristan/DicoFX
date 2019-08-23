package fr.glossairedef.vue;

import fr.glossairedef.controleur.ControleurRetour;
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
	
	private Button btnLancement;
	private Button btnSuivant;
	
	private TextField tfNom;
	
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
		
		btnLancement = new Button("Commencer");
		btnLancement.setPadding(new Insets(20));
		btnLancement.setFont(new Font(15));
		
		btnLancement.setOnMouseClicked((MouseEvent event) -> new ControleurRevisionNom(btnLancement, cbCategories.getSelectionModel().getSelectedItem(), this.fenetre).handle(event));
	}
	
	private void positionnement() {

		tableau.add(lbCategorie, 0, 0);
		tableau.add(cbCategories, 1, 0);
		
		hbBtn.getChildren().addAll(btnLancement, btnRetour);
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
				lbIntroduction.setFont(new Font(15));
				
				lbIntroduction.setAlignment(Pos.TOP_CENTER);
				lbIntroduction.setTextAlignment(TextAlignment.CENTER);
				
				lbEnonce.setPrefHeight(Constante.HAUTEUR_FENETRE / 8);
				lbEnonce.setMaxHeight(Constante.HAUTEUR_FENETRE / 6);
				lbEnonce.setFont(new Font(15));
				
				lbEnonce.setAlignment(Pos.CENTER);
				lbEnonce.setTextAlignment(TextAlignment.CENTER);
				
				tfNom = new TextField();
				tfNom.setPromptText("Réponse");
				tfNom.setTooltip(new Tooltip("Insère ta réponse ici !"));
				
				btnSuivant.setOnMouseClicked((event -> new ControleurRevisionNom(btnSuivant, tfNom).handle(event)));
				
				hbBtn.getChildren().add(btnSuivant);
				vbRevision.getChildren().addAll(lbIntroduction, lbEnonce, tfNom, hbBtn);
				
				vbRevision.setAlignment(Pos.CENTER);
				
				
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
	
	public void afficherQuestionSuivante(String valeurRecup) {

		lbEnonce = new Label();
		System.out.println(valeurRecup);
		if(valeurRecup == null) {
			
			valeurRecup = "";
		}
		System.out.println(iterateur);
		if(iterateur < Main.categories[idCategorie].getDefinitions().size()) {
			if(Main.categories[idCategorie].getDefinitions().get(iterateur).getNom().equals(valeurRecup)) {
				
				noteActuel++;
			}
		}
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
				lbIntroduction.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
				lbIntroduction.setMaxHeight(Constante.HAUTEUR_FENETRE / 10);
				lbIntroduction.setFont(new Font(15));
				
				lbIntroduction.setAlignment(Pos.TOP_CENTER);
				lbIntroduction.setTextAlignment(TextAlignment.CENTER);
				
				lbEnonce.setPrefHeight(Constante.HAUTEUR_FENETRE / 8);
				lbEnonce.setMaxHeight(Constante.HAUTEUR_FENETRE / 6);
				lbEnonce.setFont(new Font(15));
				
				lbEnonce.setAlignment(Pos.CENTER);
				lbEnonce.setTextAlignment(TextAlignment.CENTER);
				
				tfNom = new TextField();
				tfNom.setPromptText("Réponse");
				tfNom.setTooltip(new Tooltip("Insère ta réponse ici !"));
				
				btnSuivant.setOnMouseClicked((event -> new ControleurRevisionNom(btnSuivant, tfNom).handle(event)));
				
				hbBtn.getChildren().add(btnSuivant);
				vbRevision.getChildren().addAll(lbIntroduction, lbEnonce, tfNom, hbBtn);
				
				vbRevision.setAlignment(Pos.CENTER);
				
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
		
		hbBtn.setAlignment(Pos.CENTER);
		
		lbInfoBot = new Label("Voici ton score final :D !");
		lbInfoBot.setMaxWidth(Constante.LARGEUR_FENETRE);
		lbInfoBot.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
		lbInfoBot.setFont(new Font(15));
		
		lbInfoBot.setAlignment(Pos.CENTER);
		lbInfoBot.setTextAlignment(TextAlignment.CENTER);
		
		lbResultat = new Label(noteActuel + "/" + Main.categories[idCategorie].getNoteMax());
		lbResultat.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
		lbResultat.setFont(new Font(30));
		
		lbResultat.setAlignment(Pos.CENTER);
		lbResultat.setTextAlignment(TextAlignment.CENTER);
		
		btnRetour = new Button("Revenir au menu");
		btnRetour.setPadding(new Insets(20));
		btnRetour.setFont(new Font(15));
		
		btnRetour.addEventFilter(MouseEvent.MOUSE_CLICKED, new ControleurRetour(btnRetour));
		
		hbBtn.getChildren().add(btnRetour);
		
		vbRevision.getChildren().addAll(lbInfoBot, lbResultat, hbBtn);
		vbRevision.setAlignment(Pos.CENTER);
		
		scPrincipal = new Scene(vbRevision, Constante.LARGEUR_FENETRE, Constante.HAUTEUR_FENETRE);
		fenetre.setScene(scPrincipal);
	}

	private String renvoieTxtDeDefinition(String nomCategorie, int idCategorie) {

		if(iterateur < Main.categories[idCategorie].getDefinitions().size()) {
			
			return (null != Main.categories[idCategorie].getDefinitions().get(iterateur)) ? 
					Main.categories[idCategorie].getDefinitions().get(iterateur).getDefinition() : null;
		}
		return null;
		
	}

}
