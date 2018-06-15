package vue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import modele.client.Client;

public class VueTerminal implements Affichage{

    public static final List<String> commandes = Collections.unmodifiableList(Arrays.asList("afficher","ajouter","modifier","supprimer"));
    public static final List<String> commandes2 = Collections.unmodifiableList(Arrays.asList("client","commande","article"));

    public VueTerminal() {
    	this.afficherMenu();
    }

    public String[] utilisateurAction() {
    	String[] arguments = new String[2]; 
    	
    	Scanner scanIn =new Scanner(System.in);
    	
    	do{
    		System.out.println("Action ?");
    		arguments[0] = scanIn.nextLine();
    	}while(!commandes.contains(arguments[0]));
    	
    	if(!(arguments[0].equals("quitter"))) {
    		
    	 	do {
    	 		System.out.println("Sur qui ?");
    	 		arguments[1] = scanIn.nextLine();
    	 	}while(!commandes2.contains(arguments[1]));
        	
    	}else {
    		arguments[1] = null;
    		scanIn.close();
    	}
   
		return arguments;
    }

    
    @Override
    public String[] ajouter() {
    	Scanner scanInn =new Scanner(System.in);
    	String s = scanInn.nextLine();
    	return s.split(" ");
    }
    
    @Override
    public void msgModele(String msg) {
    		System.out.println(msg);
    }
    
    public void afficherAide(String type) {
    	if(type.equals(commandes2.get(0))) {
    		System.out.println("exemple : <nom> <prenom> <adresse>");
    	}else if(type.equals(commandes2.get(1))) {
    		System.out.println("exemple : <nomClient> <date> <reference objet vendable> <quantite>");
    	}else if(type.equals(commandes2.get(2))) {
    		System.out.println("exemple :<type article> <ref article> <marque> <prixUnitaire> <quantite>");
    	}
		
	}

	public void afficherMenu() {
    	System.out.println("***********                MENU                ***********");
    	System.out.println("**     Action : afficher, ajouter, modifier, supprimer  **");                         
    	System.out.println("**                 (ou quitter)                         **");
    	System.out.println("**	   Sur quoi ? : client, commande, article       **");
    	System.out.println("**                                                      **");
    	System.out.println("**********************************************************");
    }

    @Override
    public <T> void afficher(List<T> listClient) {
        for (T element : listClient){
            System.out.println(element.toString());
        }
    }

    /*
    private static void application() {
        String s = "";
        boolean stop = false;

        while (!stop) {

            try {
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                s = bufferRead.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] args = s.split(" ");

            Interpreteur interpreteur = new Interpreteur(args);
            if (interpreteur.interprete()) {
                stop = true;
            }

        }
    }*/
}