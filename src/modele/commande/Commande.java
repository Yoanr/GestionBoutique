package modele.commande;

import modele.stock.ObjetVendable;

import java.util.ArrayList;
/**
 * Classe representant une Commande
 *
 */
public class Commande {

	private int idClient;
	private String date;
	private int id;
	private double prixTotal;
	private ArrayList<LigneDeCommande> lignes;
	private double fraisDePort;
	private static int cptId = 1;

	
	/**
	 * constructeur d'une commande
	 * @param idClient
	 * @param date
	 * @param fraisDePort
	 */
	public Commande(int idClient, String date, double fraisDePort) {
		this.idClient = idClient;
		this.date = date;
		this.fraisDePort = fraisDePort;
		lignes = new ArrayList<>();
		updatePrix();
		this.id = cptId++;
	}

	/**
	 * Constructeur d'une commande
	 * @param idClient
	 * @param date
	 * @param fraisDePort
	 * @param ObjetVendable
	 * @param quantite
	 */
	public Commande(int idClient, String date, double fraisDePort, ObjetVendable ObjetVendable, int quantite) {
		this(idClient, date, fraisDePort);
		addLigne(new LigneDeCommande(ObjetVendable, quantite));
	}

	/**
	 * Classe interne d'une commande representant une ligne de cette commande
	 */
	public class LigneDeCommande {
		private ObjetVendable ObjetVendable;
		private int quantite;

		/**
		 * Constructeur d'une  ligne de commande
		 * @param ObjetVendable
		 * @param quantite
		 */
		public LigneDeCommande(ObjetVendable ObjetVendable, int quantite) {
			this.ObjetVendable = ObjetVendable;
			this.quantite = quantite;

		}

		public ObjetVendable getObjet() {
			return ObjetVendable;
		}

		public int getQuantite() {
			return quantite;
		}

	}

	/**
	 * Ajouter un objetVendable et de sa quantite dans cette ligne 
	 * @param ObjetVendable
	 * @param quantite
	 */
	public void ajoutObjet(ObjetVendable ObjetVendable, int quantite) {
		addLigne(new LigneDeCommande(ObjetVendable, quantite));
	}

	
	/**
	 * ajout d'une ligne
	 * @param LigneDeCommande
	 */
	private void addLigne(LigneDeCommande LigneDeCommande) {
		lignes.add(LigneDeCommande);
		updatePrix();
	}

	
	/**
	 * supprimer une ligne par sa reference
	 * @param reference
	 */
	public void removeLigne(String reference) {
		if (lignes.size() > 0) {
			for (LigneDeCommande LigneDeCommande : lignes) {
				if (LigneDeCommande.getObjet().getReference().equals(reference)) {
					lignes.remove(LigneDeCommande);
					updatePrix();
					return;
				}

			}
			System.out.println("Article " + reference + " non trouvée");
		}
	}

	/**
	 * fonction permettent de supprimer la commande et donc diminuer l'indice de commande 
	 * (indice qui est assigné a chaque nouvelles commandes
	 */
	public static void suppCommande(){
		cptId--;
	}

	/**
	 * Change le prix dynamiquement
	 */
	private void updatePrix() {
		prixTotal = 0;
		for (LigneDeCommande LigneDeCommande : lignes) {
			prixTotal += LigneDeCommande.getObjet().getPrix() * LigneDeCommande.getQuantite();
		}
	}

	public int getIdClient() {
		return idClient;
	}

	public String getDate() {
		return date;
	}

	public int getId() {
		return id;
	}

	public double getPrixTotal() {
		return prixTotal;
	}

	public ArrayList<LigneDeCommande> getLignes() {
		return lignes;
	}

	public double getFraisDePort() {
		return fraisDePort;
	}

	/**
	   * Cette méthode renvoie une chaîne de caractères qui représente
	   * une commande
	   *
	   * @return Une chaîne de caractère
	   */
	@Override
	public String toString() {
		String facture = "Facture n° " + id + "\tNom du client= " + idClient + "\tDate= " + date + "\tFrais de Port "+ fraisDePort +"\n\n";

		facture += String.format("%10s\t%10s\t%10s\t%10s\t%10s", "Quant.", "Ref.", "Marque", "PU (€)",
				"PT (€)\n");
		for (LigneDeCommande LigneDeCommande : lignes) {
			facture += String.format("%10s\t", LigneDeCommande.getQuantite())
					+ String.format("%10s\t", LigneDeCommande.getObjet().getReference())
					+ String.format("%10s\t", LigneDeCommande.getObjet().getMarque())
					+ String.format("%10.2f\t", LigneDeCommande.getObjet().getPrix())
					+ String.format("%9.2f\n", LigneDeCommande.getObjet().getPrix() * LigneDeCommande.getQuantite());
		}

		facture += String.format("\nprixTotal= %.2f euros", prixTotal);

		return facture;
	}
}
