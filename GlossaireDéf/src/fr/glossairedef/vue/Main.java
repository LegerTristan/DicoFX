package fr.glossairedef.vue;

import fr.glossairedef.controleur.ControleurAjoutCategorie;
import fr.glossairedef.controleur.ControleurConsulterCategorie;
import fr.glossairedef.controleur.ControleurRevisionDef;
import fr.glossairedef.controleur.ControleurRevisionNom;
import fr.glossairedef.controleur.ControleurAjoutDef;
import fr.glossairedef.controleur.ControleurSuppCategorie;
import fr.glossairedef.controleur.ControleurSuppDef;
import fr.glossairedef.models.Categorie;
import fr.glossairedef.models.ChargementAutomatique;
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
	
	private Button btnGestionDef;
	private Button btnAddDef;
	private Button btnSuppDef;
	
	private Button btnGestionCategorie;
	private Button btnAddCategorie;
	private Button btnSuppCategorie;
	private Button btnConsulterCategorie;
	
	private Button btnReviser;
	private Button btnReviserDef;
	private Button btnReviserNom;
	
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
		fenetre.setTitle("Ton glossaire de d�finition");
		fenetre.show();
	}

	private void initialisation() {

		vbBtn = new VBox();
		
		btnGestionDef = new Button("Gestion des d�finitions");
		btnGestionDef.setPadding(new Insets(20));
		btnGestionDef.setFont(new Font(15));
		
		btnGestionDef.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> fenetre.setScene(this.afficherMenuDefinition()));
		btnGestionDef.setOnMouseEntered((MouseEvent event) -> lbExplication.setText("En cliquant sur ce bouton, tu pourras g�rer les d�finitions :) !"));
		btnGestionDef.setOnMouseExited((MouseEvent event) -> lbExplication.setText(" "));
		
		btnGestionCategorie = new Button("Gestion des cat�gories");
		btnGestionCategorie.setPadding(new Insets(20));
		btnGestionCategorie.setFont(new Font(15));
		
		btnGestionCategorie.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> fenetre.setScene(this.afficherMenuCategorie()));
		btnGestionCategorie.setOnMouseEntered((MouseEvent event) -> lbExplication.setText("Gr�ce � ce bouton, tu pourras acc�der au menu des cat�gories :) !"));
		btnGestionCategorie.setOnMouseExited((MouseEvent event) -> lbExplication.setText(" "));
		
		btnReviser = new Button("R�viser");
		btnReviser.setPadding(new Insets(20, 70, 20, 70));
		btnReviser.setFont(new Font(15));
		
		btnReviser.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> fenetre.setScene(this.afficherMenuRevision()));
		btnReviser.setOnMouseEntered((MouseEvent event) -> lbExplication.setText("T'es d'humeur � r�viser ? Parce que moi non :D !"));
		btnReviser.setOnMouseExited((MouseEvent event) -> lbExplication.setText(" "));
		
		btnQuitter = new Button("Revenir au bureau");
		btnQuitter.setPadding(new Insets(20, 35, 20, 35));
		btnQuitter.setFont(new Font(15));
		
		btnQuitter.setOnMouseClicked((MouseEvent event) -> fenetre.close());
		btnQuitter.setOnMouseEntered((MouseEvent event) -> lbExplication.setText("Clique sur ce bouton et tu pourras revenir � ton bureau ;) "));
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
		
		btnGestionDef.setTranslateY(-40);
		
		btnReviser.setTranslateY(40);
		
		btnQuitter.setTranslateY(80);
		
		lbAccueil.setTextAlignment(TextAlignment.CENTER);
		lbAccueil.setAlignment(Pos.BOTTOM_CENTER);
		
		lbExplication.setTextAlignment(TextAlignment.CENTER);
		lbExplication.setAlignment(Pos.CENTER);
		
		vbBtn.setAlignment(Pos.CENTER);
		vbBtn.getChildren().addAll(btnGestionDef, btnGestionCategorie, btnReviser, btnQuitter);
		
		this.root.setTop(lbAccueil);
		
		this.root.setCenter(vbBtn);
		
		this.root.setBottom(lbExplication);
	}

	public void afficherScene(Stage fenetre) {

		this.initialisation();
		this.positionnement();
		fenetre.setScene(scMenu);
		
	}
	
	private Scene afficherMenuCategorie() {
		
		btnAddCategorie = new Button("Ajouter une cat�gorie");
		btnAddCategorie.setPadding(new Insets(20));
		btnAddCategorie.setFont(new Font(15));
		
		btnAddCategorie.addEventFilter(MouseEvent.MOUSE_CLICKED, new ControleurAjoutCategorie(Main.fenetre, this.btnAddCategorie));
		btnAddCategorie.setOnMouseEntered((MouseEvent event) -> lbExplication.setText("Ajoute une nouvelle cat�gorie � ton glossaire pour ranger tes d�finitions :D !"));
		btnAddCategorie.setOnMouseExited((MouseEvent event) -> lbExplication.setText(" "));
		
		btnAddCategorie.setTranslateY(-80);
		
		btnSuppCategorie = new Button("Supprimer une cat�gorie");
		btnSuppCategorie.setPadding(new Insets(20));
		btnSuppCategorie.setFont(new Font(15));
		
		btnSuppCategorie.addEventFilter(MouseEvent.MOUSE_CLICKED, new ControleurSuppCategorie(Main.fenetre, this.btnSuppCategorie));
		btnSuppCategorie.setOnMouseEntered((MouseEvent event) -> lbExplication.setText("Ce bouton te permet de supprimer une cat�gorie dont tu ne vois plus l'utilit�."));
		btnSuppCategorie.setOnMouseExited((MouseEvent event) -> lbExplication.setText(" "));
		
		btnSuppCategorie.setTranslateY(- 40);
		
		btnConsulterCategorie = new Button("Consulter des cat�gories");
		btnConsulterCategorie.setPadding(new Insets(20));
		btnConsulterCategorie.setFont(new Font(15));
		
		btnConsulterCategorie.addEventFilter(MouseEvent.MOUSE_CLICKED, new ControleurConsulterCategorie(Main.fenetre, this.btnConsulterCategorie));
		btnConsulterCategorie.setOnMouseEntered((MouseEvent event) -> lbExplication.setText("Jette un coup d'oeil au cat�gorie que tu as d�j� cr�e :) !"));
		btnConsulterCategorie.setOnMouseExited((MouseEvent event) -> lbExplication.setText(" "));
		
		btnQuitter.setTranslateY(40);
		
		btnQuitter.setOnMouseClicked((MouseEvent event) -> {this.initialisation(); this.positionnement();});
		btnQuitter.setText("Revenir au menu pr�c�dent");
		btnQuitter.setOnMouseEntered((MouseEvent event) -> lbExplication.setText("Clique sur ce bouton et tu reviendras au menu pr�c�dent ;) !"));
		
		lbAccueil.setText("Tu veux qu'on g�re les cat�gories aujourd'hui :) ?");
		
		vbBtn.getChildren().removeAll(btnGestionCategorie, btnGestionDef, btnQuitter, btnReviser);
		vbBtn.getChildren().addAll(btnAddCategorie, btnSuppCategorie, btnConsulterCategorie, btnQuitter);
		
		scMenu.setRoot(root);

		return scMenu;
	}

	private Scene afficherMenuDefinition() {

		btnAddDef = new Button("Ajouter une d�finition");
		btnAddDef.setPadding(new Insets(20));
		btnAddDef.setFont(new Font(15));
		
		btnAddDef.addEventFilter(MouseEvent.MOUSE_CLICKED, new ControleurAjoutDef(Main.fenetre, this.btnAddDef));
		btnAddDef.setOnMouseEntered((MouseEvent event) -> lbExplication.setText("Ajoute une nouvelle d�finition dans une cat�gorie existante � ton glossaire :D !"));
		btnAddDef.setOnMouseExited((MouseEvent event) -> lbExplication.setText(" "));
		
		btnAddDef.setTranslateY(-100);
		
		btnSuppDef = new Button("Supprimer une d�finition");
		btnSuppDef.setPadding(new Insets(20));
		btnSuppDef.setFont(new Font(15));
		
		btnSuppDef.addEventFilter(MouseEvent.MOUSE_CLICKED, new ControleurSuppDef(Main.fenetre, this.btnSuppDef));
		btnSuppDef.setOnMouseEntered((MouseEvent event) -> lbExplication.setText("Supprime une d�finition d'une de tes cat�gories :D !"));
		btnSuppDef.setOnMouseExited((MouseEvent event) -> lbExplication.setText(" "));
		
		btnSuppDef.setTranslateY(-70);
		
		btnQuitter.setTranslateY(150);
		
		btnQuitter.setOnMouseClicked((MouseEvent event) -> {this.initialisation(); this.positionnement();});
		btnQuitter.setText("Revenir au menu pr�c�dent");
		btnQuitter.setOnMouseEntered((MouseEvent event) -> lbExplication.setText("Clique sur ce bouton et tu reviendras au menu pr�c�dent ;) !"));
		
		lbAccueil.setText("Tu veux qu'on g�re les d�finitions aujourd'hui :) ?");
		
		vbBtn.getChildren().removeAll(btnGestionCategorie, btnGestionDef, btnReviser);
		vbBtn.getChildren().addAll(btnAddDef, btnSuppDef);
		
		scMenu.setRoot(root);

		return scMenu;
	}
	
	private Scene afficherMenuRevision() {

		btnReviserNom = new Button("R�viser le nom");
		btnReviserNom.setPadding(new Insets(20));
		btnReviserNom.setFont(new Font(15));
		
		btnReviserNom.addEventFilter(MouseEvent.MOUSE_CLICKED, new ControleurRevisionNom(Main.fenetre, this.btnReviserNom));
		btnReviserNom.setOnMouseEntered((MouseEvent event) -> lbExplication.setText("Je te donne la d�finition et toi tu me donnes le nom de la d�finition d'accord :D ?"));
		btnReviserNom.setOnMouseExited((MouseEvent event) -> lbExplication.setText(" "));
		
		btnReviserNom.setTranslateY(-150);
		
		btnReviserDef = new Button("R�viser la d�finition");
		btnReviserDef.setPadding(new Insets(20));
		btnReviserDef.setFont(new Font(15));
		
		btnReviserDef.addEventFilter(MouseEvent.MOUSE_CLICKED, new ControleurRevisionDef(Main.fenetre, this.btnReviserDef));
		btnReviserDef.setOnMouseEntered((MouseEvent event) -> lbExplication.setText("Je te donne le nom et toi tu me donnes la d�finition MOT POUR MOT d'accord :D ?"));
		btnReviserDef.setOnMouseExited((MouseEvent event) -> lbExplication.setText(" "));

		
		btnQuitter.setTranslateY(150);
		
		btnQuitter.setOnMouseClicked((MouseEvent event) -> {this.initialisation(); this.positionnement();});
		btnQuitter.setText("Revenir au menu pr�c�dent");
		btnQuitter.setOnMouseEntered((MouseEvent event) -> lbExplication.setText("Clique sur ce bouton et tu reviendras au menu pr�c�dent ;) !"));
		
		lbAccueil.setText("Je te le dis d'avance, tu recommences jusqu'� avoir la note maximale !");
		
		vbBtn.getChildren().removeAll(btnGestionCategorie, btnGestionDef, btnReviser);
		vbBtn.getChildren().addAll(btnReviserDef, btnReviserNom);
		
		scMenu.setRoot(root);

		return scMenu;
	}

	public static void main(String[] args) {

		categories = new Categorie[10];
		ChargementAutomatique.autoLoad();
		launch(args);

	}
}
