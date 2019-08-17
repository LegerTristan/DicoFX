package fr.glossairedef.controleur;

import fr.glossairedef.models.Categorie;
import fr.glossairedef.models.Definition;
import fr.glossairedef.vue.AjoutDefinition;
import fr.glossairedef.vue.Main;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControleurDef implements EventHandler<MouseEvent> {

	private Stage fenetre;
	
	private Button btnClicked;
	private Button btnRetour;
	private Button btnValidite;
	
	private String nom;
	private String texte;
	
	public ControleurDef(Button btn) {

		this.btnRetour = btn;
	}
	
	public ControleurDef(Stage fenetre, Button btn) {


		this.btnClicked = btn;
		this.fenetre = fenetre;
		
	}

	public ControleurDef(Button btn, TextField tfNom, TextArea taDefinition) {

		btnValidite = btn;
		nom = tfNom.getText();
		texte = taDefinition.getText();
		
	}

	@Override
	public void handle(MouseEvent event) {

		if(event.getSource() == btnClicked) {
			
			AjoutDefinition addDef = new AjoutDefinition(this.fenetre);
			addDef.afficherScene();
		}
		
		if(event.getSource() == btnRetour) {
			
			Main glossaire = new Main();
			glossaire.afficherScene(Main.fenetre);
		}
		
		if(event.getSource() == btnValidite) {
			
			if(verifierNom() && null != texte && !("".equals(texte))) {

				Main.categories[0] = new Categorie("test", null);
				Main.categories[0].getDefinitions().add(new Definition(nom, texte));
				
				System.out.println(Main.categories[0].getDefinitions().toArray()[0]);
				
				AjoutDefinition addDef = new AjoutDefinition(Main.fenetre, this.nom);
				addDef.afficherSceneConfirmation();
			}
		}
		
	}

	private boolean verifierNom() {
		return null != nom && !("".equals(nom));
	}

}
