package controleur;

import vue.VueGraphique;
import vue.VueTerminal;

public class Controleur {
    public Controleur(String arg) {
         if("commandLine".equals(arg))
            controllerCommandLine();
        else
            controllerGraphique();
    }

    private void controllerCommandLine(){
    }

    private void controllerGraphique(){

    }
}