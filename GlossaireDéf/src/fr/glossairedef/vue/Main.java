package fr.glossairedef.vue;

import fr.glossairedef.controleur.ControleurCategorie;
import fr.glossairedef.controleur.ControleurDef;
import fr.glossairedef.models.Categorie;
import fr.glossairedef.models.Constante;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Stage fenetre;
	
	private BorderPane root = new BorderPane();
	
	private Scene scMenu = new Scene(root, Constante.LARGEUR_FENETRE, Constante.HAUTEUR_FENETRE);
	
	private VBox vbBtn;
	
	private Button btnAddDef;
	private Button btnAddCategorie;
	private Button btnQuitter;
	
	private Label lbAccueil;
	private Label lbExplication;
	
	public static Categorie[] categories;

	public Main() {
		
		root = new BorderPane();
		
		scMenu = new Scene(root, Constante.LARGEUR_FENETRE, Constante.HAUTEUR_FENETRE);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		Main.fenetre = stage;
		
		this.initialisation();
		
		this.positionnement();
		
		fenetre.setScene(scMenu);
		fenetre.setTitle("Ton glossaire de définition");
		fenetre.show();
	}

	private void initialisation() {

		vbBtn = new VBox();
		
		btnAddDef = new Button("Ajouter une définition");
		btnAddDef.setPadding(new Insets(20));
		btnAddDef.setFont(new Font(15));
		
		btnAddDef.addEventFilter(MouseEvent.MOUSE_CLICKED, new ControleurDef(Main.fenetre, this.btnAddDef));
		btnAddDef.setOnMouseEntered((MouseEvent event) -> lbExplication.setText("Ajoute une nouvelle définition à ton glossaire et range-la dans une catégorie :D !"));
		btnAddDef.setOnMouseExited((MouseEvent event) -> lbExplication.setText(" "));
		
		btnAddCategorie = new Button("Ajouter une catégorie");
		btnAddCategorie.setPadding(new Insets(20));
		btnAddCategorie.setFont(new Font(15));
		
		btnAddCategorie.addEventFilter(MouseEvent.MOUSE_CLICKED, new ControleurCategorie(Main.fenetre, this.btnAddCategorie));
		btnAddCategorie.setOnMouseEntered((MouseEvent event) -> lbExplication.setText("Ajoute une nouvelle catégorie à ton glossaire pour ranger tes définitions :D !"));
		btnAddCategorie.setOnMouseExited((MouseEvent event) -> lbExplication.setText(" "));
		
		btnQuitter = new Button("Revenir au bureau");
		btnQuitter.setPadding(new Insets(20));
		btnQuitter.setFont(new Font(15));
		
		btnQuitter.setOnMouseClicked((MouseEvent event) -> fenetre.close());
		btnQuitter.setOnMouseEntered((MouseEvent event) -> lbExplication.setText("Clique sur ce bouton et tu pourras revenir à ton bureau ;) "));
		btnQuitter.setOnMouseExited((MouseEvent event) -> lbExplication.setText(" "));
		
		lbAccueil = new Label("Salut je suis InfoBot ! Ton assistant personnel pour te guider sur cette application si tu es perdu(e) !\n"
				+ "Alors, que veux-tu faire aujourd'hui ?");
		
		lbAccueil.setMaxWidth(Constante.LARGEUR_FENETRE);
		lbAccueil.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
		lbAccueil.setFont(new Font(15));
		
		lbExplication = new Label();
		
		lbExplication.setMaxWidth(Constante.LARGEUR_FENETRE);
		lbExplication.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
		lbExplication.setFont(new Font(15));
		
		lbExplication.setBorder(new Border( 
				new BorderStroke(Color.BLACK,Color.BLACK, Color.BLACK, Color.BLACK,
						BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
						CornerRadii.EMPTY, new BorderWidths(3), null)));
	}
	
	private void positionnement() {
		
		btnAddCategorie.setTranslateY(50);
		
		btnQuitter.setTranslateY(100);
		
		lbAccueil.setTextAlignment(TextAlignment.CENTER);
		lbAccueil.setAlignment(Pos.BOTTOM_CENTER);
		
		lbExplication.setTextAlignment(TextAlignment.CENTER);
		lbExplication.setAlignment(Pos.CENTER);
		
		vbBtn.setAlignment(Pos.CENTER);
		vbBtn.getChildren().addAll(btnAddDef, btnAddCategorie, btnQuitter);
		
		this.root.setTop(lbAccueil);
		
		this.root.setCenter(vbBtn);
		
		
		this.root.setBottom(lbExplication);
	}

	public void afficherScene(Stage fenetre) {

		this.initialisation();
		this.positionnement();
		fenetre.setScene(scMenu);
		
	}
	public static void main(String[] args) {

		categories = new Categorie[10];
		
		launch(args);

	}
}
