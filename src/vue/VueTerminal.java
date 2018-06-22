package vue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import modele.client.Client;

public class VueTerminal implements Affichage{

    public static final List<String> commandes = Collections.unmodifiableList(Arrays.asList("afficher","ajouter","modifier","supprimer","quitter"));
    public static final List<String> commandes2 = Collections.unmodifiableList(Arrays.asList("client","commande","article","menu"));

    public VueTerminal() {
    	this.afficherMenu();
    }

    public String[] utilisateurAction() {
    	String argument = new String(); 
    	String arguments[] = new String[2];
    	Scanner scanIn =new Scanner(System.in);
    	try {
    		do {
    	    	System.out.println("Que souhaitez-vous ?");
        		argument = scanIn.nextLine();
        		String arg[] = argument.split(" ");
        		
        		if(arg.length < 2) {
        			arguments[0] = arg[0];
        			arguments[1] = "";
        			
        		}else {
        			arguments[0] = arg[0];
        			arguments[1] = arg[1];
        		}
        		
        		if(arguments[0].equals("quitter")) {
        			arguments[1] = "menu";	
        		}
        	}while(!commandes.contains(arguments[0]) || !commandes2.contains(arguments[1])  );
    		
    	}catch(ArrayIndexOutOfBoundsException e) {
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
    		System.out.println("exemple commande : <idClient> <date> <port>");
    	}else if(type.equals(commandes2.get(2))) {
    		System.out.println("exemple :<type article> <ref article> <marque> <prixUnitaire> <quantite>");
    	}
		
	}

	public void afficherMenu() {
    	System.out.println("***********                MENU                ***********");
    	System.out.println("**     Action : afficher, ajouter, modifier, supprimer  **");                         
    	System.out.println("**                 (ou quitter)                         **");
    	System.out.println("**	   Sur quoi ? : client, commande, article,menu  **");
    	System.out.println("**                                                      **");
    	System.out.println("** exemple : <afficher> <client> / <quitter>            **");
    	System.out.println("**********************************************************");
    }

    @Override
    public <T> void afficher(List<T> listClient) {
        for (T element : listClient){
            System.out.println(element.toString());
        }
    }

	@Override
	public String supprimer() {
		Scanner scanInn =new Scanner(System.in);
    	String s = scanInn.nextLine();
    	return s;
	}

	@Override
	public List<String[]> getLignesCommande() {
		System.out.println("Ajout de ligne :");
		System.out.println("exemple : <item> <quantite>");
		Scanner scanInn =new Scanner(System.in);
    	List<String[]> lignes = new ArrayList<>();
    	boolean ajouter = true;
    	int indice_ligne=1;
    	while(ajouter) {
    		
    		System.out.print(indice_ligne+" ) ");
        	String ligne = scanInn.nextLine();
        	
    		if(ligne.equals("fin")) {
    			ajouter = false;
    		}else {
    			
    			lignes.add(ligne.split(" "));
    		}
    		indice_ligne++;
    	}
		return lignes;
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