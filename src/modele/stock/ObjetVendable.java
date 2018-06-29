package modele.stock;
/**
 * Cette classe est utilisée pour représenter un ObjetVendable.
 *
 */
public abstract class ObjetVendable {
	protected String nom;
	protected String reference;
	protected String marque;

	protected double prix;

	/**
	   * un constructeur de la classe ObjetVendable
	   *
	   * @param reference
	   */
	public ObjetVendable(String reference) {
		this.reference = reference;
	}

	public String getNom() {
		return nom;
	}

	public String getReference() {
		return reference;
	}

	public double getPrix() {
		return prix;
	}

	public String getMarque() {
		return marque;
	}

	/**
	   * Cette méthode verifie l'égalité entre 2 objets vendables
	   *
	   * @param ObjetVendable
	   * @return boolean
	   */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ObjetVendable))
			return false;

		ObjetVendable that = (ObjetVendable) o;

		return getReference().equals(that.getReference());
	}

	@Override
	public int hashCode() {
		return getReference().hashCode();
	}
}
