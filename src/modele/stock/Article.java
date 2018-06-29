package modele.stock;

public abstract class Article extends ObjetVendable {
	public Article(String reference, String marque, double prixUnitaire) {
		super(reference);
		this.marque = marque;
		this.prix = prixUnitaire;
	}
}
