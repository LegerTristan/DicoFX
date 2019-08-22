package fr.glossairedef.models;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import fr.glossairedef.vue.Main;

public class SauvegardeAutomatique {

	public static void autoSave() {
		
		ObjectOutputStream oos;
		
		try{
			
			oos = new ObjectOutputStream( 
					new BufferedOutputStream( 
							new FileOutputStream(
									new File("save/save.txt"))));
			
			for(int i = 0; i < Main.categories.length; i++) {
				if(null != Main.categories[i]) {
					
					oos.writeObject(Main.categories[i]);
					
				}
			}
			
			oos.close();
				
			}catch(FileNotFoundException fnfe) {
				
				fnfe.printStackTrace();
				
			} catch (IOException io) {
				
				io.printStackTrace();
			}
	}
}
