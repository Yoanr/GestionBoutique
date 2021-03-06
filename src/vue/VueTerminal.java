package vue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Represente la vue coté terminal (Ligne de commande)
 */
public class VueTerminal implements Affichage {

	/**
	 * List contenant la 1er catégorie des mots de recherche
	 */
	public static final List<String> commandes = Collections
			.unmodifiableList(Arrays.asList("afficher", "ajouter", "modifier", "supprimer", "quitter"));
	/**
	 * List contenant la 2eme catégorie des mots de recherche
	 */
	public static final List<String> commandes2 = Collections
			.unmodifiableList(Arrays.asList("clients", "commandes", "stock", "boutique", "menu", "lots"));
	/**
	 * List contenant la 3eme catégorie des mots de recherche
	 */
	public static final List<String> commandes3 = Collections.unmodifiableList(Arrays.asList("client", "boutique"));

	/**
	 * Objet permettant de lire les instructions utilisateur
	 */
	private Scanner scanInn;

	
	/**
	 * Constructeur de VueTerminal
	 */
	public VueTerminal() {
		this.afficherMenu();
		scanInn = new Scanner(System.in);
	}

	/** 
	 * Fonction recuperant l'action de l'utilisateur
	 * @return String[]
	 */
	public String[] utilisateurAction() {
		String argument = new String();
		String arguments[] = new String[3];
		try {
			do {
				System.out.print("Que souhaitez-vous ? > ");
				argument = scanInn.nextLine();
				String arg[] = argument.split(" ");
				if (arg.length < 2) {
					arguments[0] = arg[0];
					arguments[1] = "";
					arguments[2] = "";

				} else if (arg.length == 2) {
					arguments[0] = arg[0];
					arguments[1] = arg[1];
					arguments[2] = "";
				} else if (arg.length == 3) {
					arguments[0] = arg[0];
					arguments[1] = arg[1];
					arguments[2] = arg[2];
				}else {
					for(int i=0;i<3;i++) {
					arguments[i] = "";
					}
				}

				if (arguments[0].equals("quitter")) {
					scanInn.close();
					arguments[1] = "menu";
				}
			} while (!commandes.contains(arguments[0]) || !commandes2.contains(arguments[1]));

		} catch (ArrayIndexOutOfBoundsException e) {
		}

		return arguments;
	}

	/** 
	 * fonction proposé par l'interface Affichage retournant les arguments d'un ajout général
	 */
	@Override
	public String[] ajouter() {
		String s = scanInn.nextLine();
		return s.split(" ");
	}

	/** 
	 * fonction proposé par l'interface Affichage permettant d'afficher le message du model
	 */
	@Override
	public void msgModele(String msg) {
		System.out.println(msg);
	}

	/**
	 * fonction permettant d'afficher les arguments d'une commande que l'utilisateur souhaite executer
	 * @param type d'un article
	 */
	public void afficherAide(String type) {
		if (type.equals(commandes2.get(0))) {
			System.out.println("exemple : <nom> <prenom> <adresse>");
		} else if (type.equals(commandes2.get(1))) {
			System.out.println("exemple commande : <idClient> <date> <frais de port>");
		} else if (type.equals(commandes2.get(2))) {
			System.out.println("exemple :ajouter stock <stylo/ramette>");
		} else if (type.equals(commandes2.get(3))) {
			System.out.println("exemple :<nom> <loyer> <salaire>");
		} else if (type.equals(commandes2.get(5))) {
			System.out.println("exemple :<ref lot> <ref article> <quantite article> <reduction>");
		}

	}

	/** 
	 * fonction permettant d'afficher le menu
	 */
	public void afficherMenu() {
		System.out.println("***********                MENU                 ***********");
		System.out.println("**     Action : afficher, ajouter, modifier, supprimer   **");
		System.out.println("**                 (ou quitter)                          **");
		System.out.println("**	   Sur quoi ? : lots, clients, commandes, stock, menu  **");
		System.out.println("**     si commande : clients ou boutique ?               **");
		System.out.println("**   exemple : <ajouter> <clients/commandes/...>         **");
		System.out.println("**   exemple : <afficher> <commandes> <boutique/client>  **");
		System.out.println("**   exemple : <quitter>                                 **");
		System.out.println("***********************************************************");
	}

	/**
	 * fonction proposé par l'interface Affichage qui affiche une liste d'objet en utilisant le toString()
	 * @param list général
	 */
	@Override
	public <T> void afficher(List<T> listClient) {
		for (T element : listClient) {
			System.out.println(element.toString());
		}
	}

	/**
	 * fonction proposé par l'interface Affichage qui supprime un element à partir de sa reference 
	 * @return String
	 */
	@Override
	public String supprimer() {
		System.out.println("Saississez l'identifiant que vous voulez supprimer :");
		String s = scanInn.nextLine();
		return s;
	}

	/**
	 * fonction proposé par l'interface Affichage qui permet d'ajouter une commande
	 * @return Liste de chaine de caractere
	 */
	@Override
	public List<String[]> getLignesCommande() {
		System.out.println("Ajout de ligne :");
		System.out.println("exemple : <reference> <quantite> , ecrire fin pour arreter");
		List<String[]> lignes = new ArrayList<>();
		boolean ajouter = true;
		int indice_ligne = 1;
		while (ajouter) {

			System.out.print(indice_ligne + " ) ");
			String ligne = scanInn.nextLine();

			if (ligne.equals("fin")) {
				ajouter = false;
			} else {

				lignes.add(ligne.split(" "));
			}
			indice_ligne++;
		}
		return lignes;
	}

	/**
	 * fonction proposé par l'interface Affichage qui permet de modifier un element à partir de sa reference
	 * @return String
	 */
	@Override
	public String modifier() {
		System.out.println("Veuillez saisir la reference/id de l'element :");
		return scanInn.nextLine();
	}

	/**
	 * fonction proposé par l'interface Affichage 
	 * @return String
	 */
	@Override
	public String getClientid() {
		System.out.println("Saississez l'id du client :");
		String id = scanInn.nextLine();
		return id;
	}

	/**
	 * fonction proposé par l'interface Affichage pour modifier la quantite d'un article
	 * @return String
	 */
	@Override
	public String modifierstock(int quantite) {
		System.out.println("Quantite actuelle : " + quantite);
		System.out.print("Nouvelle quantite : ");
		String newQuantite = scanInn.nextLine();
		return newQuantite;
	}

	/**
	 * fonction proposé par l'interface Affichage permettant d'ajouter un article
	 */
	@Override
	public String[] ajouterArticle(String typeArticle) {
		if (typeArticle.equals("stylo")) {
			System.out.println("stylo : <ref article> <marque> <prixUnitaire> <couleur> <quantite>");

		} else if (typeArticle.equals("ramette")) {
			System.out.println(
					"ramette : <ref article> <marque> <prixUnitaire> <dimension hauteur> <dimension largeur> <quantite>");
		} else {
			String[] ss = new String[1];
			ss[0] = "LOL";
			return ss;
		}
		String s = scanInn.nextLine();

		return s.split(" ");
	}
}