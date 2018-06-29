package modele.stock;

/**
 * Classe representant un Article
 */
public abstract class Article extends ObjetVendable {
	
	/**
	 * Constructeur d'un article 
	 * @param reference
	 * @param marque
	 * @param prixUnitaire
	 */
	public Article(String reference, String marque, double prixUnitaire) {
		super(reference);
		this.marque = marque;
		this.prix = prixUnitaire;
	}
}
