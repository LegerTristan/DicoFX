package fr.glossairedef.vue;

import fr.glossairedef.controleur.ControleurCategorie;
import fr.glossairedef.models.Constante;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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

public class AjoutCategorie {

	private Stage fenetre;
	
	private Scene scAjoutCategorie;
	private Scene scConfirmation;
	
	private BorderPane root;
	private GridPane tableau;
	
	private VBox vbConfirmation;
	private HBox hbBtn;
	
	private Button btnAdd;
	private Button btnRetour;
	
	private Label lbInfoBot;
	private Label lbNom;
	private Label lbConfirmation;
	
	private TextField tfNom;
	
	private String nomCategorie;

	public AjoutCategorie(Stage fenetre) {

		this.fenetre = fenetre;
		
	}
	
	public AjoutCategorie(Stage fenetre, String nom) {

		this(fenetre);
		this.nomCategorie = nom;
	}

	public void afficherScene() {
		
		root = new BorderPane();

		scAjoutCategorie = new Scene(root, Constante.LARGEUR_FENETRE, Constante.HAUTEUR_FENETRE);
		
		this.initialisation();
		
		this.positionnement();
		
		fenetre.setScene(scAjoutCategorie);
		
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
		
		lbInfoBot= new Label("Tu veux créer une nouvelle catégorie aujourd'hui ?");
		lbInfoBot.setMaxWidth(Constante.LARGEUR_FENETRE);
		lbInfoBot.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
		lbInfoBot.setFont(new Font(15));
		
		lbNom= new Label("Nom : ");
		lbNom.setFont(new Font(15));
		
		tfNom = new TextField();
		tfNom.setPromptText("Bot");
		tfNom.setTooltip(new Tooltip("Entre ici le nom de ta catégorie !"));
		
		btnAdd = new Button("Valider");
		btnAdd.setPadding(new Insets(20));
		btnAdd.setFont(new Font(15));
		
		btnAdd.setOnMouseClicked((MouseEvent event) -> new ControleurCategorie(btnAdd, tfNom).handle(event));
		
		btnRetour = new Button("Revenir en arrière");
		btnRetour.setPadding(new Insets(20));
		btnRetour.setFont(new Font(15));
		
		btnRetour.addEventFilter(MouseEvent.MOUSE_CLICKED, new ControleurCategorie(btnRetour));	
	}
	
	private void positionnement() {

		tableau.add(lbNom, 0, 0);
		tableau.add(tfNom, 1, 0);
		
		tableau.setAlignment(Pos.CENTER);
		tableau.setVgap(15);
		tableau.setHgap(25);
		
		hbBtn.getChildren().addAll(btnAdd, btnRetour);
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
		
		lbConfirmation = new Label("La catégorie " + nomCategorie + " a bien été crée :D !" );
		lbConfirmation.setMaxWidth(Constante.LARGEUR_FENETRE);
		lbConfirmation.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
		lbConfirmation.setFont(new Font(15));
		
		lbConfirmation.setAlignment(Pos.CENTER);
		
		btnRetour = new Button("Revenir au menu");
		btnRetour.setPadding(new Insets(20));
		btnRetour.setFont(new Font(15));
		
		btnRetour.addEventFilter(MouseEvent.MOUSE_CLICKED, new ControleurCategorie(btnRetour));	
		
		vbConfirmation.getChildren().addAll(lbConfirmation, btnRetour);
		vbConfirmation.setAlignment(Pos.CENTER);
		vbConfirmation.setSpacing(50);
		
		fenetre.setScene(scConfirmation);
		
	}
}
