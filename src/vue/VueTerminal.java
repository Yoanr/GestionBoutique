package vue;
import modele.outils.BaseDonnee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VueTerminal {

    private String[] arguments;
    private String[] commandes = {"afficher","ajouter","modifier","supprimer"};
    private String[] commandes2 = {"clients","commandes"};
    private BaseDonnee baseDonnee;

    public VueTerminal(String[] s) {
        this.arguments = new String[s.length];
        int indice = 0;

        for (indice = 0; indice < s.length; indice++) {
            this.arguments[indice] = s[indice];
        }
        this.baseDonnee = BaseDonnee.getInstance();


    }

    public boolean interprete() {

        if (this.arguments[0].equals(this.commandes[0])) {
            System.out.println("afficher");
            if(this.arguments[1].equals(this.commandes2[0])) {
                System.out.println(" client");
            }else if(this.arguments[1].equals(this.commandes2[1])) {
                System.out.println(" commande");
            }

        } else if (this.arguments[0].equals(this.commandes[1])) {
            System.out.println("ajouter");
            if(this.arguments[1].equals(this.commandes2[0])) {
                System.out.println(" client");
            }else if(this.arguments[1].equals(this.commandes2[1])) {
                System.out.println(" commande");
            }


        } else if (this.arguments[0].equals(this.commandes[2])) {
            System.out.println("modifier");
        } else if (this.arguments[0].equals(this.commandes[3])) {
            System.out.println("supprimer");
        }else if(this.arguments[0].equals("quitter")) {
            return true;
        }
        return false;

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