package modele.client;

/**
 *Cette classe represente un client
 */
public class Client {
	private String nom;
	private String prenom;
	private String adresse;
	private int identifiant;
	/**
	 * cptId represente son identifiant dans l'application, incrementé à chaque nouveau client ajouté
	 */
	private static int cptId = 1;

	
	/**
	 * Constructeur d'un client
	 * @param nom
	 * @param prenom
	 * @param adresse
	 */
	public Client(String nom, String prenom, String adresse) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.identifiant = cptId++;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getId() {
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public void display() {
		System.out.println("Nom : " + nom + ", identifiant : " + identifiant);
	}

	/**
	 * fonction permettant de comparer deux client
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Client))
			return false;

		Client client = (Client) o;

		if (getNom() != null ? !getNom().equals(client.getNom()) : client.getNom() != null)
			return false;
		if (getPrenom() != null ? !getPrenom().equals(client.getPrenom()) : client.getPrenom() != null)
			return false;
		return getAdresse() != null ? getAdresse().equals(client.getAdresse()) : client.getAdresse() == null;
	}

	@Override
	public int hashCode() {
		int result = getNom() != null ? getNom().hashCode() : 0;
		result = 31 * result + (getPrenom() != null ? getPrenom().hashCode() : 0);
		result = 31 * result + (getAdresse() != null ? getAdresse().hashCode() : 0);
		return result;
	}
	/**
	   * Cette méthode renvoie une chaîne de caractères qui représente
	   * un client
	   *
	   * @return Une chaîne de caractère
	   */
	@Override
	public String toString() {
		return "Client{" + "identifiant=" + identifiant + ", nom='" + nom + '\'' + ", prenom='" + prenom + '\''
				+ ", adresse='" + adresse + '\'' + '}';
	}

	public String getPrenom() {
		return prenom;
	}

	public int getIdentifiant() {
		return identifiant;
	}
}
