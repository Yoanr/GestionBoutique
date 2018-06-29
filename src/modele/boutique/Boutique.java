package modele.boutique;

import modele.client.Client;
import modele.commande.Commande;
import modele.stock.Article;
import modele.stock.ArticleFactory;
import modele.stock.Lot;
import vue.ErreurManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Cette classe represente le modele de la boutique
 */
public final class Boutique {

	private String nom;
	private double loyer;
	private double salaire;
	private double charge;
	private double ca;
	private double benefice;

	/**
	 * liste de clients
	 */
	private List<Client> clientList = new ArrayList<>();
	/**
	 * liste de commandes
	 */
	private List<Commande> commandeList = new ArrayList<>();
	/**
	 * liste de stock
	 */
	private HashMap<Article, Integer> stocks = new HashMap<>();
	/**
	 * liste de lots
	 */
	private List<Lot> lotList = new ArrayList<>();

	private static Boutique instance = new Boutique();

	private Boutique() {
	}

	public static Boutique getInstance() {
		return instance;
	}

	public List<Client> getClientList() {
		return clientList;
	}

	public List<Commande> getCommandeList() {
		return commandeList;
	}

	/**
	 * recupere les informations de la boutique
	 * @return List<String>
	 */
	public List<String> getBoutiqueInfo() {
		List<String> liste = new ArrayList<>();
		liste.add(toString());
		return liste;
	}

	public HashMap<Article, Integer> getStocksMap() {
		return stocks;
	}

	/**
	 * Recuperer la liste des stocks de manieres dynamique et deja pret pour la vue
	 * @return List<String>
	 */
	public List<String> getStocksList() {
		List<String> stockList = new ArrayList<>();

		for (Map.Entry<Article, Integer> entry : stocks.entrySet()) {
			int quantite = entry.getValue();
			Article article = entry.getKey();

			String stringBuilder = "Quantité : " + String.valueOf(quantite) + " - " + article.toString();

			stockList.add(stringBuilder);
		}
		return stockList;
	}

	/**
	 * Supprimer un client par l'identifiant d'un client
	 * @param clientId
	 * @return message à la vue
	 */
	public String supprimerClient(String clientId) {
		int idClient = Integer.parseInt(clientId);
		for (Client client : clientList) {
			if (client.getId() == idClient) {
				clientList.remove(client);

				for (Commande commande : getCommandeListByClient(idClient)) {
					commandeList.remove(commande);
				}

				return ErreurManager.SUPPRIME;
			}
		}
		return ErreurManager.SUPPRIME_ERROR;
	}

	/**
	 * ajouter un client
	 * @param clientArgs
	 * @return message pour la vue
	 */
	public String ajouterClient(String[] clientArgs) {
		return ajouterClient(new Client(clientArgs[0], clientArgs[1], clientArgs[2]));
	}


	/**
	 * ajouter un client
	 * @param newClient
	 * @return message pour la vue
	 */
	private String ajouterClient(Client newClient) {
		if (!clientList.contains(newClient)) {
			clientList.add(newClient);
			return ErreurManager.AJOUTE;
		}
		return ErreurManager.AJOUTE_ERROR;
	}


	/**
	 * ajouter une commande avec une liste d'argument
	 * @param commandeArgs
	 * @return
	 */
	public Commande ajouterCommande(String[] commandeArgs) {
		if (commandeArgs.length == 3) {
			return ajouterCommande(new Commande(Integer.parseInt(commandeArgs[0]), commandeArgs[1],
					Double.parseDouble(commandeArgs[2])));
		}
		return null;
	}

	/**
	 * ajouter une commande par un objet commande
	 * @param commande
	 * @return commande
	 */
	public Commande ajouterCommande(Commande c) {
		if (!commandeList.contains(c)) {
			commandeList.add(c);

			return c;
		}
		return null;
	}
	/**
	 * ajouter un article avec la fabrique
	 * @param articleTab
	 * @return
	 */
	public String ajouterArticle(String[] articleTab) {
		switch (articleTab[0].toLowerCase()) {
			case "stylo":
				return ajouterArticle(ArticleFactory.getInstance().creerArticle(articleTab),
						Integer.parseInt(articleTab[5]));
			case "ramette":
				return ajouterArticle(ArticleFactory.getInstance().creerArticle(articleTab),
						Integer.parseInt(articleTab[6]));
			default:
				return ErreurManager.AJOUTE_ERROR;
		}
	}

	/**
	 * ajouter un article
	 * @param article
	 * @param quantite
	 * @return message pour la vue
	 */
	private String ajouterArticle(Article article, int quantite) {
		if (article != null) {
			stocks.put(article, quantite);
			return ErreurManager.AJOUTE;

		}
		return ErreurManager.AJOUTE_ERROR;
	}


	/**
	 * recuperer l'article par reference
	 * @param reference
	 * @return Article
	 */
	public Article getArticleByReference(String reference) {
		for (Article article : stocks.keySet()) {
			if (article.getReference().equals(reference))
				return article;
		}
		return null;
	}

	/**
	 * recuperer le lot par reference
	 * @param reference
	 * @return Lot
	 */
	public Lot getLotByReference(String reference) {
		for (Lot lot : lotList) {
			if (lot.getReference().equals(reference))
				return lot;
		}
		return null;
	}

	/**
	 * supprimer une commande
	 * @param commandeID
	 * @return message à la vue
	 */
	public String supprimerCommande(String commandeID) {
		int idCommande = Integer.parseInt(commandeID);
		for (Commande commande : commandeList) {
			if (commande.getId() == idCommande) {
				commandeList.remove(commande);
				Commande.suppCommande();

				for (Commande.LigneDeCommande ligneDeCommande : commande.getLignes()) {
					if (ligneDeCommande.getObjet() instanceof Article) {
						Article article = (Article) ligneDeCommande.getObjet();
						if (article != null)
							ajouterArticle((Article) ligneDeCommande.getObjet(),
									ligneDeCommande.getQuantite() + stocks.get(ligneDeCommande.getObjet()));
					}
				}
				return ErreurManager.SUPPRIME;
			}
		}
		return ErreurManager.SUPPRIME_ERROR;
	}

	/**
	 * verfier un client
	 * @param idClient
	 * @return boolean
	 */
	public boolean verifClient(int idClient) {
		for (Client client : clientList) {
			if (client.getId() == idClient)
				return true;
		}
		return false;
	}

	/**
	 * supprimer un article par reference
	 * @param articleReference
	 * @return message a la vue
	 */
	public String supprimerArticle(String articleReference) {
		Article article = getArticleByReference(articleReference);
		if (article != null) {

			stocks.remove(article);
			return ErreurManager.SUPPRIME;
		}
		return ErreurManager.SUPPRIME_ERROR;
	}


	/**
	 * verifier les stock
	 * @param referenceArticle
	 * @param quantite
	 * @return boolean
	 */
	public boolean verifStock(String referenceArticle, int quantite) {
		Article article = getArticleByReference(referenceArticle);
		Lot lot = getLotByReference(referenceArticle);
		return (article != null && stocks.get(article) >= quantite) || lot != null;
	}


	/**
	 * ajouter une ligne
	 * @param commande
	 * @param reference
	 * @param quantite
	 */
	public void ajouterLigne(Commande c, String reference, int quantite) {

		Article article = getArticleByReference(reference);
		Lot lot = getLotByReference(reference);
		if (article != null) {
			c.ajoutObjet(article, quantite);
			stocks.put(article, stocks.get(article) - quantite);
		} else if(getLotByReference(reference) != null){
			int quantiteInList = lot.getQuantite();
			if (quantiteInList >= quantite){
				c.ajoutObjet(getLotByReference(reference), quantite);
			}
		}
	}


	/**
	 * recuperer la quantite d'un article dans la boutique
	 * @param reference
	 * @return quantite
	 */
	public int getQuantiteById(String reference) {
		Article article = getArticleByReference(reference);

		if (article == null || !stocks.containsKey(article))
			return -1;
		return stocks.get(article);
	}


	/**
	 * modifier les stock
	 * @param newQuantite
	 * @param reference
	 * @return message à la vue
	 */
	public String modifierStock(int newQuantite, String reference) {
		Article article = getArticleByReference(reference);

		if (article == null || !stocks.containsKey(article))
			return ErreurManager.MODIFIE_ERROR;

		stocks.put(article, newQuantite);
		return ErreurManager.MODIFIE;

	}

	/** ajouter un lot
	 *
	 * @param referenceLot
	 * @param referenceArticle
	 * @param quantite
	 * @param reduction
	 * @return message d'erreur
	 */
	public String ajouterLot(String referenceLot, String referenceArticle, int quantite, double reduction) {
		Lot lot = getArticleByReference(referenceArticle) != null
				? new Lot(referenceLot, getArticleByReference(referenceArticle), reduction, quantite)
				: null;

		if (lot != null) {
			lotList.add(lot);
			return ErreurManager.AJOUTE;
		} else {
			return ErreurManager.AJOUTE_ERROR;
		}
	}

	/**
	 * supprimer un lot par reference
	 * @param referenceLot
	 * @return message ala vue
	 */
	public String supprimerLot(String referenceLot) {
		for (Lot lot : lotList) {
			if (lot.getReference().equals(referenceLot)) {
				lotList.remove(lot);
				return ErreurManager.SUPPRIME;
			}
		}
		return ErreurManager.SUPPRIME_ERROR;
	}

	// values {"nom", "loyer", "salaire", "CA"}
	/**
	 * modifier les info de la boutique
	 * @param values
	 * @return message à la vue
	 */
	public String modifierInfoBoutique(String[] values) {
		setNom(values[0]);
		setLoyer(Double.parseDouble(values[1]));
		setSalaire(Double.parseDouble(values[2]));
		setCharge(salaire + loyer);
		setBenefice(ca - charge);
		setCa();

		return ErreurManager.MODIFIE;
	}

	public List<Lot> getLots() {
		return lotList;
	}
	/**
	 * recuperer a liste de lot pour la vue
	 * @return
	 */
	public List<String> getLotsList(){
		List<String> stringList = new ArrayList<>();
		lotList.forEach(lot->stringList.add(lot.toString()));
		return stringList;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getLoyer() {
		return loyer;
	}

	public void setLoyer(double loyer) {
		this.loyer = loyer;
	}

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	public double getCa() {
		return ca;
	}

	public void setCa(double ca) {
		this.ca = ca;
	}

	/**
	 * calcul dynamiquement le chiffre d'affaire en fonction des commandes passées
	 */
	public void setCa() {
		Double somme = 0.0;
		for (Commande commande : commandeList) {
			somme += commande.getPrixTotal();
		}
		this.ca = somme;
	}

	public double getBenefice() {
		return benefice;
	}

	public void setBenefice(double benefice) {
		this.benefice = benefice;
	}
	/**
	 * Cette méthode renvoie une chaîne de caractères qui représente
	 * la boutique
	 *
	 * @return Une chaîne de caractère
	 */
	@Override
	public String toString() {
		return "Boutique " + nom  + ", loyer=" + loyer + ", salaire=" + salaire + ", charge=" + charge
				+ ", ca=" + ca + ", benefice=" + benefice ;
	}

	/**
	 * recuperer la commande d'un client
	 * @param idClient
	 * @return Liste de commande
	 */
	public List<Commande> getCommandeListByClient(int idClient) {
		List<Commande> l = commandeList;
		List<Commande> l2 = new ArrayList<>();
		for (Commande element : l) {
			if (element.getIdClient() == idClient) {
				l2.add(element);
			}
		}
		return l2;
	}
}
