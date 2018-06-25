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
    public static final List<String> commandes2 = Collections.unmodifiableList(Arrays.asList("client","commande","article","boutique","menu"));
    public static final List<String> commandes3 = Collections.unmodifiableList(Arrays.asList("client","boutique"));

    private Scanner scanInn;
    public VueTerminal() {
    	this.afficherMenu();
    	scanInn = new Scanner(System.in);
    }

    public String[] utilisateurAction() {
    	String argument = new String(); 
    	String arguments[] = new String[3];
    	try {
    		do {
    	    	System.out.println("Que souhaitez-vous ?");
        		argument = scanInn.nextLine();
        		String arg[] = argument.split(" ");
        		if(arg.length < 2) {
        			arguments[0] = arg[0];
        			arguments[1] = "";
        			arguments[2] = "";
        			
        		}else if(arg.length == 2) {
        			arguments[0] = arg[0];
        			arguments[1] = arg[1];
        			arguments[2] = "";
        		}else if(arg.length == 3) {
        			arguments[0] = arg[0];
        			arguments[1] = arg[1];
        			arguments[2] = arg[2];
        		}
        		
        		if(arguments[0].equals("quitter")) {
        			scanInn.close();
        			arguments[1] = "menu";	
        		}
        	}while(!commandes.contains(arguments[0]) || !commandes2.contains(arguments[1])  );
    		
    	}catch(ArrayIndexOutOfBoundsException e) {
    	}
    	
    	
		return arguments;
    }

    
    @Override
    public String[] ajouter() {
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
    	System.out.println("***********                MENU                 ***********");
    	System.out.println("**     Action : afficher, ajouter, modifier, supprimer   **");                         
    	System.out.println("**                 (ou quitter)                          **");
    	System.out.println("**	   Sur quoi ? : client, commande, article,menu   **");
    	System.out.println("**     si commande : client ou boutique ?                **");
    	System.out.println("**   exemple : <ajouter> <client>                        **");
    	System.out.println("**   exemple : <afficher> <commande> <boutique>          **");
    	System.out.println("**   exemple : <quitter>                                 **");
    	System.out.println("***********************************************************");
    }

    @Override
    public <T> void afficher(List<T> listClient) {
        for (T element : listClient){
            System.out.println(element.toString());
        }
    }

	@Override
	public String supprimer() {
		System.out.println("Saississez l'identifiant que vous voulez supprimer :");
    	String s = scanInn.nextLine();
    	return s;
	}

	@Override
	public List<String[]> getLignesCommande() {
		System.out.println("Ajout de ligne :");
		System.out.println("exemple : <item> <quantite> , écrire fin pour arreter");
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
	@Override
	public String modifier(){
		System.out.println("Veuillez saisir la référence/id de l'element :");
		return scanInn.nextLine();
	}

	@Override
	public String getClientid() {
		String id = scanInn.nextLine();
		return id;
	}

	@Override
	public String modifierstock(int quantite) {
		System.out.println("Quantite actuelle : "+quantite);
		System.out.print("Nouvelle quantite : ");
		String newQuantite = scanInn.nextLine();
		return newQuantite;
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