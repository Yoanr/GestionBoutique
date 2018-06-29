package modele.stock;
/**
 * Cette classe est utilisée pour représenter un stylo.
 *
 */
public class Stylo extends Article {
	public enum Couleur {
		ROUGE, BLEU, VERT, JAUNE, NOIR
	}

	private Couleur couleur;

	/**
	   * un constructeur de la classe stylo
	   *
	   * @param reference
	   * @param marque
	   * @param prixUnitaire
	   * @param marque
	   */
	
	public Stylo(String reference, String marque, double prixUnitaire) {
		super(reference, marque, prixUnitaire);
	}

	/**
	   * un constructeur de la classe stylo
	   *
	   * @param reference
	   * @param marque
	   * @param prixUnitaire
	   * @param marque
	   * @param couleur
	   */
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

	/**
	   * Cette méthode renvoie une chaîne de caractères qui représente
	   * un stylo
	   *
	   * @return Une chaîne de caractère
	   */
	@Override
	public String toString() {
		return "Stylo{" + "reference='" + reference + '\'' + ", couleur=" + couleur + ", marque='" + marque + '\''
				+ ", prix=" + prix + '}';
	}
}
