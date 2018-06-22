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

            controllerAfficher(arguments[1]);

        } else if (arguments[0].equals(VueTerminal.commandes.get(1))) {

            controllerAjouter(arguments[1]);

        } else if (arguments[0].equals(VueTerminal.commandes.get(2))) {

            controllerModifier(arguments[1]);

        } else if (arguments[0].equals(VueTerminal.commandes.get(3))) {
            controllerSupprimer(arguments[1]);

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

    private void controllerAjouter(String argument) {
        String[] s;
        String msg=Boutique.DEFAULT;
        try {

            if(argument.equals(VueTerminal.commandes2.get(0))) {
                this.affichage.afficherAide(VueTerminal.commandes2.get(0));
                s = this.affichage.ajouter();
                msg = boutique.ajouterClient(s);

            }else if(argument.equals(VueTerminal.commandes2.get(1))) {
                this.affichage.afficherAide(VueTerminal.commandes2.get(1));
                s = this.affichage.ajouter();
                System.out.println(" commande");
            }else if(argument.equals(VueTerminal.commandes2.get(2))) {
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

    private void controllerAfficher(String argument) {
        List<?> liste = null;
        if(argument.equals(VueTerminal.commandes2.get(0))) {
            liste = boutique.getClientList();
        }else if(argument.equals(VueTerminal.commandes2.get(1))) {
            liste = boutique.getCommandeList();
        }else if(argument.equals(VueTerminal.commandes2.get(2))) {
            liste = boutique.getStocksList();
        }else if(argument.equals(VueTerminal.commandes2.get(3))) {
            this.affichage.afficherMenu();
            return;
        }
        this.affichage.afficher(liste);
    }

    private void controllerModifier(String argument) {

    }

    private void controllerSupprimer(String argument) {

    }
}