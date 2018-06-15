package controleur;

import java.util.HashMap;
import java.util.List;

import modele.boutique.Boutique;
import modele.client.Client;
import modele.commande.Commande;
import modele.outils.DonneeManager;
import modele.stock.Article;
import modele.stock.Stylo;
import modele.stock.Stylo.Couleur;
import vue.Affichage;
import vue.VueGraphique;
import vue.VueTerminal;

public class Controleur {
	
    Boutique boutique = Boutique.getInstance();
    Affichage affichage;
    
    public Controleur(String arg) {
    	
    	boutique.ajouterClient(new Client("toto","tata","titi"));
    	boutique.ajouterCommande(new Commande("toto","tata",new Stylo("lol","lol","lol",2.2),2));;
    	boutique.ajouterArticle(new Stylo("lol","lol","lol",2.2,Couleur.BLEU),2);
    	DonneeManager.ecrire();
         if("commandLine".equals(arg)) {
        	 this.affichage = new VueTerminal();
        	 controllerCommandLine();
        	
         }
            
        else
            controllerGraphique();
    }
    
    private boolean interpreter(String[] arguments) {
    	List<?> liste = null;
    	
    	if (arguments[0].equals(VueTerminal.commandes.get(0))) {
            if(arguments[1].equals(VueTerminal.commandes2.get(0))) {
                liste = boutique.getClientList();
            }else if(arguments[1].equals(VueTerminal.commandes2.get(1))) {
            	liste = boutique.getCommandeList();
            }else if(arguments[1].equals(VueTerminal.commandes2.get(2))) {
            	liste = boutique.getStocksList();
            }
            this.affichage.afficher(liste);
            
        } else if (arguments[0].equals(VueTerminal.commandes.get(1))) {
            if(arguments[1].equals(VueTerminal.commandes2.get(0))) {
                String[] s = this.affichage.ajouter(VueTerminal.commandes2.get(0));
                Boolean b = false;
                if(s.length == 3) {
                	Client c = new Client(s[0],s[1],s[2]);
                	 b = boutique.ajouterClient(c);
                }else {
                	System.out.println("error");
                }
                
            	this.affichage.aFonctionne(b);
            	if(b) {
            		DonneeManager.ecrire();
            	}
            	
            	
            }else if(arguments[1].equals(VueTerminal.commandes2.get(1))) {
                System.out.println(" commande");
            }else if(arguments[1].equals(VueTerminal.commandes2.get(2))) {
                System.out.println(" article");
            }


        } else if (arguments[0].equals(VueTerminal.commandes.get(2))) {
            System.out.println("modifier");
        } else if (arguments[0].equals(VueTerminal.commandes.get(3))) {
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