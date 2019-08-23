package fr.glossairedef.models;

import fr.glossairedef.controleur.ControleurRetour;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public abstract class MiseEnPageFenetre {

	protected Stage fenetre;
	
	protected Scene scPrincipal;
	protected Scene scConfirmation;
	
	protected BorderPane root;
	protected GridPane tableau;
	
	protected HBox hbBtn;
	protected VBox vbConfirmation;
	
	protected Button btnRetour;
	
	protected Label lbInfoBot;
	protected Label lbConfirmation;
	
	protected void miseEnPageCommune() {
		
		root = new BorderPane();

		scPrincipal = new Scene(root, Constante.LARGEUR_FENETRE, Constante.HAUTEUR_FENETRE);
		
		tableau = new GridPane();
		
		
		tableau.setAlignment(Pos.CENTER);
		tableau.setVgap(15);
		tableau.setHgap(15);
		
		hbBtn = new HBox();
		hbBtn.setMaxHeight(Constante.HAUTEUR_FENETRE / 5);
		hbBtn.setPrefHeight(Constante.HAUTEUR_FENETRE / 5);
		hbBtn.setBorder(new Border( 
				new BorderStroke(Color.BLACK,Color.BLACK, Color.BLACK, Color.BLACK,
						BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
						CornerRadii.EMPTY, new BorderWidths(3), null)));
		
		hbBtn.setAlignment(Pos.CENTER);
		hbBtn.setSpacing(50);
		
		btnRetour = new Button("Revenir en arrière");
		btnRetour.setPadding(new Insets(20));
		btnRetour.setFont(new Font(15));
		
		btnRetour.addEventFilter(MouseEvent.MOUSE_CLICKED, new ControleurRetour(btnRetour));
		
		lbInfoBot.setMaxWidth(Constante.LARGEUR_FENETRE);
		lbInfoBot.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
		lbInfoBot.setFont(new Font(15));
		
		lbInfoBot.setAlignment(Pos.CENTER);
		lbInfoBot.setTextAlignment(TextAlignment.CENTER);
		
		root.setCenter(tableau);
		root.setTop(lbInfoBot);
		root.setBottom(hbBtn);
		
	}
	
	protected void miseEnPageConfirmationCommune() {
		
		vbConfirmation = new VBox();
		
		scConfirmation = new Scene(vbConfirmation, Constante.LARGEUR_FENETRE, Constante.HAUTEUR_FENETRE);
		
		lbConfirmation.setMaxWidth(Constante.LARGEUR_FENETRE);
		lbConfirmation.setPrefHeight(Constante.HAUTEUR_FENETRE / 10);
		lbConfirmation.setFont(new Font(15));
		
		lbConfirmation.setAlignment(Pos.CENTER);
		
		btnRetour = new Button("Revenir au menu");
		btnRetour.setPadding(new Insets(20));
		btnRetour.setFont(new Font(15));
		
		btnRetour.addEventFilter(MouseEvent.MOUSE_CLICKED, new ControleurRetour(btnRetour));	
		
		vbConfirmation.getChildren().addAll(lbConfirmation, btnRetour);
		vbConfirmation.setAlignment(Pos.CENTER);
		vbConfirmation.setSpacing(50);
		
		fenetre.setScene(scConfirmation);
		
	}
	
}
