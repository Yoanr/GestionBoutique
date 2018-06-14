import controleur.Controleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1) {
            Controleur controleur = new Controleur(args[0]);
        }else {
            System.err.println("Erreur, Nombre d'arguments incorrect");
    }
    
    System.out.println("Fin gestion boutique");
    
 }
}
