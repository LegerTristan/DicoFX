package fr.glossairedef.models;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import fr.glossairedef.vue.Main;

public class ChargementAutomatique {


	public static void autoLoad() {
		
		ObjectInputStream ois;
		int i = 0;
		try{
			
			File f = new File("save/save.txt");
			FileInputStream fis = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fis);

			if(lecteurNEstPasALaFinDuFichier(bis)) {
				ois = new ObjectInputStream(bis); // Obligatoire sinon renvoie une EOFException
				
				while(lecteurNEstPasALaFinDuFichier(bis)) {
					Main.categories[i] = (Categorie) ois.readObject();
					
					i++;
				}
				
				
				
				ois.close();
			}
			
				
		}catch(FileNotFoundException fnfe) {
				
			fnfe.printStackTrace();
				
		}catch (IOException io) {
			
			io.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			
			cnfe.printStackTrace();
		}
	}

	private static boolean lecteurNEstPasALaFinDuFichier(BufferedInputStream bis) throws IOException {
		return bis.available() > 0;
	}
}
