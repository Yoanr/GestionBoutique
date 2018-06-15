package vue;

import java.util.List;

import modele.client.Client;

public interface Affichage {

 
    String[] ajouter();
    
    void afficherAide(String s);
    
    void msgModele(String msg);

    <T> void afficher (List<T> list);

    String[] utilisateurAction();
}
