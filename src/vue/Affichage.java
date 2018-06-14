package vue;

import java.util.List;

import modele.client.Client;

public interface Affichage {

    void afficherClient(List<Client> listeClient);
    void afficherCommande();
    void afficherArticle();
    String[] utilisateurAction();
}
