package vue;

import java.util.List;
import java.util.Scanner;

import modele.client.Client;

public class VueTerminal implements Affichage{

    private String[] commandes = {"afficher","ajouter","modifier","supprimer"};
    private String[] commandes2 = {"client","commande","article"};

    public VueTerminal() {
    	this.afficherMenu();
    }

    public String[] utilisateurAction() {
    	String[] arguments = new String[2]; 
    	
    	Scanner scanIn =new Scanner(System.in);
    	System.out.println("Action ?");
    	arguments[0] = scanIn.nextLine();
    	if(!(arguments[0].equals("quitter"))) {
    	 	System.out.println("Sur qui ?");
        	arguments[1] = scanIn.nextLine();
    	}else {
    		arguments[1] = null;
    		scanIn.close();
    	}
   
   	
		return arguments;
    
    }

    @Override
    public void afficherClient(List<Client> listeClient) {
    	for(Client client : listeClient) {
    		System.out.println(client.toString());
    	}
    }

    @Override
    public void afficherCommande() {

    }

    @Override
    public void afficherArticle() {

    }
    
    public void afficherMenu() {
    	System.out.println("***********                MENU                ***********");
    	System.out.println("**     Action : afficher, ajouter, modifier, supprimer  **");                         
    	System.out.println("**                 (ou quitter)                         **");
    	System.out.println("**	   Sur quoi ? : client, commande, article       **");
    	System.out.println("**                                                      **");
    	System.out.println("**********************************************************");
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