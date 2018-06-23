package controleur;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

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
        DonneeManager.lire();
        if("commandLine".equals(arg)) {
            this.affichage = new VueTerminal();
            controllerCommandLine();
        }

        else
            controllerGraphique();
    }

    private boolean interpreter(String[] arguments) {

        if (arguments[0].equals(VueTerminal.commandes.get(0))) {

            controllerAfficher(arguments);

        } else if (arguments[0].equals(VueTerminal.commandes.get(1))) {

            controllerAjouter(arguments);

        } else if (arguments[0].equals(VueTerminal.commandes.get(2))) {

            controllerModifier(arguments);

        } else if (arguments[0].equals(VueTerminal.commandes.get(3))) {
            controllerSupprimer(arguments);

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

    private void controllerAjouter(String[] arguments) {
        String[] s;
        String msg=Boutique.DEFAULT;
        try {

            if(arguments[1].equals(VueTerminal.commandes2.get(0))) {
                this.affichage.afficherAide(VueTerminal.commandes2.get(0));
                s = this.affichage.ajouter();
                msg = boutique.ajouterClient(s);

            }else if(arguments[1].equals(VueTerminal.commandes2.get(1))) {
            	
                this.affichage.afficherAide(VueTerminal.commandes2.get(1));
                s = this.affichage.ajouter();
                
                if(!boutique.verifClient(Integer.parseInt(s[0]))) {
                	 this.affichage.msgModele(Boutique.AJOUTE_ERROR);
                	return;
                }
                Commande c = boutique.ajouterCommande(s);
                List<String[]> lignesCommande;
                lignesCommande = this.affichage.getLignesCommande();
                if(lignesCommande.size() == 0) {
                	boutique.supprimerCommande(String.valueOf(c.getId()));
                }
                for(int i=0;i<lignesCommande.size();i++) {
                	String reference = lignesCommande.get(i)[0];
                	int quantite = Integer.parseInt(lignesCommande.get(i)[1]);
                	if(!boutique.verifStock(reference,quantite)) {
                		boutique.supprimerCommande(String.valueOf(c.getId()));
                		 this.affichage.msgModele(Boutique.AJOUTE_ERROR);
                		return;
                	}else {
                		boutique.ajouterLigne(c,reference,quantite);
                	}
                	
                }
                
            }else if(arguments[1].equals(VueTerminal.commandes2.get(2))) {
                this.affichage.afficherAide(VueTerminal.commandes2.get(2));
                s = this.affichage.ajouter();
                msg = boutique.ajouterArticle(s);
            }

        }catch(Exception ArrayIndexOutOfBoundsException) {
            msg = Boutique.ARGS_ERROR;
        }
        DonneeManager.ecrire();
        this.affichage.msgModele(msg);

    }

    private void controllerAfficher(String[] arguments) {
        List<?> liste = null;
        String msg=Boutique.DEFAULT;
        if(arguments[1].equals(VueTerminal.commandes2.get(0))) {
            liste = boutique.getClientList();
        }else if(arguments[1].equals(VueTerminal.commandes2.get(1))) {
        	if(arguments[2] == null) {
        		return;
        	}
        	if(arguments[2].equals(VueTerminal.commandes3.get(0))){
        		System.out.println("Saississez l'id du client :");
        		String idClient = affichage.getClientid();
        		if(!boutique.verifClient(Integer.parseInt(idClient))){
        			this.affichage.msgModele(Boutique.CLIENT_ERROR); 
        			return;
        		}else {
        			liste = boutique.getCommandeListByClient(Integer.parseInt(idClient));
        		}
        		
        	}else if(arguments[2].equals(VueTerminal.commandes3.get(1))){
        		liste = boutique.getCommandeList();
        	}else {
        		return;
        	}
            
        }else if(arguments[1].equals(VueTerminal.commandes2.get(2))) {
            liste = boutique.getStocksList();
        }else if(arguments[1].equals(VueTerminal.commandes2.get(3))) {
       	 liste = boutique.getBoutiqueInfo();
       }else if(arguments[1].equals(VueTerminal.commandes2.get(4))) {
            this.affichage.afficherMenu();
            return;
       }else {
    	   return;
       }
        this.affichage.afficher(liste);
    }

    private void controllerModifier(String[] arguments) {
    	String msg=Boutique.DEFAULT;
    	String s;
    	
    	if(arguments[1].equals(VueTerminal.commandes2.get(2))) {
    		s = this.affichage.modifier();
    		String[] ss;
    		// STOCK stock = boutique.getStockbyId(Integer.parseInt(s));
    		// ss = this.affichage.modifierstock(stock); afficher ancien stock
    		// boutique.modifierstock(ss);
        }else if(arguments[1].equals(VueTerminal.commandes2.get(4))) {
        	//s = this.affichage.modifier();
        	//ss = this.affichage.modifierBoutique(stock); afficher ancien boutique
        	//boutique.modifierstock(ss);
        }else {
        	return;
        }
    	
    	DonneeManager.ecrire();
    	this.affichage.msgModele(msg);
    }

    private void controllerSupprimer(String[] arguments) {
    	String s = this.affichage.supprimer();
    	String msg=Boutique.DEFAULT;
    	if(arguments[1].equals(VueTerminal.commandes2.get(0))) {
        	msg = boutique.supprimerClient(s);  // TODO supprimer les commandes associé !!!
        	
        }else if(arguments[1].equals(VueTerminal.commandes2.get(1))) {
        	msg = boutique.supprimerCommande(s);
        	
        }else if(arguments[1].equals(VueTerminal.commandes2.get(2))) {
        	msg = boutique.supprimerArticle(s);
        }
    	 this.affichage.msgModele(msg);
    	
    }
}