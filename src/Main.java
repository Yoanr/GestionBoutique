import controleur.Controleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author yoan,olivier
 *
 */
public class Main {
    /**
     * fonction Main de l'application
     * @param args
     */
    public static void main(String[] args) {
        //TODO decomment pour la vue graphique
        // if (args.length == 1) {

        Controleur controleur = new Controleur(args[0]);

        //}else {
        //  System.err.println("Erreur, Nombre d'arguments incorrect");
        //}

        // System.out.println("Fin gestion boutique");

    }
}
