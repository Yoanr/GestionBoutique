package modele.stock;
/**
 * Cette classe est utilisée pour représenter une ramette.
 *
 */
public class Ramette extends Article {
	private double dimL;
	private double dimH;

	/**
	   * un constructeur de la classe ramette
	   *
	   * @param reference
	   * @param marque
	   * @param prixUnitaire
	   * @param marque
	   */
	public Ramette(String reference, String marque, double prixUnitaire) {
		super(reference, marque, prixUnitaire);
	}

	/**
	   * un constructeur de la classe ramette
	   *
	   * @param reference
	   * @param marque
	   * @param prixUnitaire
	   * @param marque
	   * @param dimensionHauteur
	   * @param dimensionLargeur
	   */
	public Ramette(String reference, String marque, double prixUnitaire, double dimH, double dimL) {
		super(reference, marque, prixUnitaire);
		this.dimH = dimH;
		this.dimL = dimL;
	}

	/**
	   * Cette méthode renvoie une chaîne de caractères qui représente
	   * une ramette
	   *
	   * @return Une chaîne de caractère
	   */
	@Override
	public String toString() {
		return "Ramette{" + "reference='" + reference + '\'' + ", marque='" + marque + '\'' + ", dimL=" + dimL
				+ ", dimH=" + dimH + ", prix=" + prix + '}';
	}
}
