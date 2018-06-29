package vue;

import java.util.List;

import modele.client.Client;


/**
 *Interface Affichage permettant de gerer la vue d'un terminal et d'une interface graphique
 */
public interface Affichage {

	/**
	 * fonction permetant de recuperer les arguments pour ajouter un element
	 * @return arguments de l'utilisateur
	 */
	String[] ajouter();

	/**
	 * fonction permetant d'afficher les aides
	 * @param typeDeCommande
	 */
	void afficherAide(String s);

	/**
	 * fonction permetant d'afficher le menu
	 */
	void afficherMenu();

	/**
	 * fonction affichant le message de reussite ou d'echoue d'une action utilisateur
	 * @param msg
	 */
	void msgModele(String msg);

	/**
	 * fonction affichant une liste d'element
	 * @param list
	 */
	<T> void afficher(List<T> list);

	/**
	 * retourne la commande souhaité d'un utilisateur (Readme)
	 * @return String[]
	 */
	String[] utilisateurAction();

	/**
	 * fonction qui recupere la reference d'un element
	 * @return reference
	 */
	String supprimer();

	/**
	 * fonction qui recuperes les lignes d'une commande (Commande Metier)
	 * @return liste de chaine de caractere
	 */
	List<String[]> getLignesCommande();

	/**
	 * fonction qui modifie un element à l'aide de sa reference
	 * @return reference
	 */
	String modifier();

	/**
	 * fonction qui recupere l'id d'un client 
	 * @return idClient
	 */
	String getClientid();

	/**
	 * fonction qui retourne la nouvelle quantite d'un article
	 * @param quantite
	 * @return
	 */
	String modifierstock(int quantite);

	/**
	 * fonction qui recupere les parametres de l'article à ajouter
	 * @param typeArticle
	 * @return String[]
	 */
	String[] ajouterArticle(String typeArticle);
}
