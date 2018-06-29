package modele.stock;

public class Stylo extends Article {
	public enum Couleur {
		ROUGE, BLEU, VERT, JAUNE, NOIR
	}

	private Couleur couleur;

	public Stylo(String reference, String marque, double prixUnitaire) {
		super(reference, marque, prixUnitaire);
	}

	public Stylo(String reference, String marque, double prixUnitaire, Couleur couleur) {
		super(reference, marque, prixUnitaire);
		this.couleur = couleur;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}

	@Override
	public String toString() {
		return "Stylo{" + "reference='" + reference + '\'' + ", couleur=" + couleur + ", marque='" + marque + '\''
				+ ", prix=" + prix + '}';
	}
}
