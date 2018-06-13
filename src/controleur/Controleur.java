package controleur;

import vue.VueGraphique;
import vue.VueTerminal;

public class Controleur {
    public Controleur(String [] args) {
        if (args.length == 1 && "command Line".equals(args[0]))
            controllerCommandLine();
        else
            controllerGraphique();


    }

    private void controllerCommandLine(){
    }

    private void controllerGraphique(){

    }
}