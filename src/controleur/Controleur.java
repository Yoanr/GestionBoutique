package controleur;

import java.util.List;

import modele.boutique.Boutique;
import modele.client.Client;
import modele.commande.Commande;
import modele.outils.DonneeManager;
import modele.stock.Stylo;
import vue.Affichage;
import vue.VueGraphique;
import vue.VueTerminal;

public class Controleur {
	
    private static String[] commandes = {"afficher","ajouter","modifier","supprimer"};
    private static  String[] commandes2 = {"client","commande","article"};
    Boutique boutique = Boutique.getInstance();
    Affichage affichage;
    
    public Controleur(String arg) {
    	
    	boutique.ajouterClient(new Client("toto","tata","titi"));
    	boutique.ajouterCommande(new Commande("toto","tata",new Stylo("lol","lol","lol",2.2),2));;
    	DonneeManager.ecrire();
    	
         if("commandLine".equals(arg)) {
        	 this.affichage = new VueTerminal();
        	 controllerCommandLine();
         }
            
        else
            controllerGraphique();
    }
    
    private boolean interpreter(String[] arguments) {
    	if (arguments[0].equals(commandes[0])) {
            if(arguments[1].equals(commandes2[0])) {
            	
                List<Client> listeClient = boutique.getClientList();
                this.affichage.afficher(listeClient);
                
            }else if(arguments[1].equals(commandes2[1])) {
                
            	List<Commande> listeCommande = boutique.getCommandeList();
                this.affichage.afficher(listeCommande);
            	
            }else if(arguments[1].equals(commandes2[2])) {
                System.out.println(" article");
            }

        } else if (arguments[0].equals(commandes[1])) {
            System.out.println("ajouter");
            if(arguments[1].equals(commandes2[0])) {
                System.out.println(" client");
            }else if(arguments[1].equals(commandes2[1])) {
                System.out.println(" commande");
            }else if(arguments[1].equals(commandes2[2])) {
                System.out.println(" article");
            }


        } else if (arguments[0].equals(commandes[2])) {
            System.out.println("modifier");
        } else if (arguments[0].equals(commandes[3])) {
            System.out.println("supprimer");
        }else if(arguments[0].equals("quitter")) {
            return true;
        }
    	return false;
    }

    private void controllerCommandLine(){
    	boolean quitter = false;
    	
    	String[] arguments = new String[2];
    	
    	while(!quitter) {
    		arguments = this.affichage.utilisateurAction();
    		quitter = this.interpreter(arguments);
    	}
    }

    private void controllerGraphique(){

    }
}