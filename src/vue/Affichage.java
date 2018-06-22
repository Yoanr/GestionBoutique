package vue;

import java.util.List;

import modele.client.Client;

public interface Affichage {

 
    String[] ajouter();
    
    void afficherAide(String s);
    
    void afficherMenu();
    
    void msgModele(String msg);

    <T> void afficher (List<T> list);

    String[] utilisateurAction();

	String supprimer();
	
	List<String[]> getLignesCommande();
}
