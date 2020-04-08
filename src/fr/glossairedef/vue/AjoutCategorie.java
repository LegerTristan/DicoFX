package fr.glossairedef.vue;

import fr.glossairedef.controleur.ControleurAjoutCategorie;
import fr.glossairedef.models.Constante;
import fr.glossairedef.models.MiseEnPageFenetre;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AjoutCategorie extends MiseEnPageFenetre {

	private Button btnAdd;
	
	private Label lbNom;
	
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

		scPrincipal = new Scene(root, Constante.LARGEUR_FENETRE, Constante.HAUTEUR_FENETRE);
		
		this.initialisation();
		
		super.miseEnPageCommune();
		
		this.positionnement();
		
		fenetre.setScene(scPrincipal);
	}

	private void initialisation() {
		
		lbInfoBot= new Label("Tu veux créer une nouvelle catégorie aujourd'hui ?");
		
		lbNom= new Label("Nom : ");
		lbNom.setFont(new Font(15));
		
		tfNom = new TextField();
		tfNom.setPromptText("Bot");
		tfNom.setTooltip(new Tooltip("Entre ici le nom de ta catégorie !"));
		
		btnAdd = new Button("Valider");
		btnAdd.setPadding(new Insets(20));
		btnAdd.setFont(new Font(15));
		
		btnAdd.setOnMouseClicked((MouseEvent event) -> new ControleurAjoutCategorie(btnAdd, tfNom).handle(event));
	}
	
	private void positionnement() {

		tableau.add(lbNom, 0, 0);
		tableau.add(tfNom, 1, 0);
		
		hbBtn.getChildren().addAll(btnAdd, btnRetour);
	}
	
	public void afficherSceneConfirmation() {
		
		lbConfirmation = new Label("La catégorie " + nomCategorie + " a bien été crée :D !" );
		
		super.miseEnPageConfirmationCommune();
	}
}
