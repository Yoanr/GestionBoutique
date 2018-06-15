package vue;

import java.util.List;

import modele.client.Client;

public interface Affichage {

 
    String[] ajouter(String type);
    
    void aFonctionne(boolean b);

    <T> void afficher (List<T> list);

    String[] utilisateurAction();
}
