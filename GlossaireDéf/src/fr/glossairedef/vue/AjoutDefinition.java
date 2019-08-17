package fr.glossairedef.vue;

import fr.glossairedef.controleur.ControleurDef;
import fr.glossairedef.models.Constante;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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

public class AjoutDefinition {

	private Stage fenetre;
	
	private Scene scAjoutDef;
	
	private BorderPane root;
	private GridPane tableau;
	
	private HBox hbBtn;
	
	private Button btnAdd;
	private Button btnRetour;
	
	private Label lbInfoBot;
	private Label lbNom;
	private Label lbDefinition;
	
	private TextField tfNom;
	private TextArea taDefinition;
	
	private String nomDef;

	private VBox vbConfirmation;

	private Scene scConfirmation;

	private Label lbConfirmation;
	
	public AjoutDefinition(Stage fenetre) {

		this.fenetre = fenetre;
	}

	public AjoutDefinition(Stage fenetre, String nom) {

		this(fenetre);
		this.nomDef = nom;
	}

	public void afficherScene() {
		
		root = new BorderPane();

		scAjoutDef = new Scene(root, Constante.LARGEUR_FENETRE, Constante.HAUTEUR_FENETRE);
		
		this.initialisation();
		
		this.positionnement();
		
		fenetre.setScene(scAjoutDef);
		
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
		
		btnAdd = new Button("Valider");
		btnAdd.setPadding(new Insets(20));
		btnAdd.setFont(new Font(15));
		
		btnAdd.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> new ControleurDef(btnAdd, tfNom, taDefinition).handle(event));
		
		btnRetour = new Button("Revenir en arrière");
		btnRetour.setPadding(new Insets(20));
		btnRetour.setFont(new Font(15));
		
		btnRetour.addEventFilter(MouseEvent.MOUSE_CLICKED, new ControleurDef(btnRetour));
		
		lbInfoBot= new Label("Quelle nouvelle définition tu veux ajouter aujourd'hui ? ");
		lbInfoBot.setMaxWidth(Constante.LARGEUR_FENETRE);
		lbInfoBot.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
		lbInfoBot.setFont(new Font(15));
		
		lbNom= new Label("Nom : ");
		lbNom.setFont(new Font(15));
		
		lbDefinition= new Label("Définition : ");
		lbDefinition.setFont(new Font(15));
		
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
		
		tableau.setAlignment(Pos.CENTER);
		tableau.setVgap(15);
		tableau.setHgap(15);
		
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
		
		lbConfirmation = new Label("La définition " + nomDef + " a bien été crée :D !" );
		lbConfirmation.setMaxWidth(Constante.LARGEUR_FENETRE);
		lbConfirmation.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
		lbConfirmation.setFont(new Font(15));
		
		lbConfirmation.setAlignment(Pos.CENTER);
		
		btnRetour = new Button("Revenir au menu");
		btnRetour.setPadding(new Insets(20));
		btnRetour.setFont(new Font(15));
		
		btnRetour.addEventFilter(MouseEvent.MOUSE_CLICKED, new ControleurDef(btnRetour));	
		
		vbConfirmation.getChildren().addAll(lbConfirmation, btnRetour);
		vbConfirmation.setAlignment(Pos.CENTER);
		vbConfirmation.setSpacing(50);
		
		fenetre.setScene(scConfirmation);
		
	}

}
