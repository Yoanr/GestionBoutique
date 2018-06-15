package modele.client;

public class Client {
    private String nom;
    private String prenom;
    private String adresse;
    private int identifiant;
    private static int cptId = 1;

    public Client(String nom, String prenom, String adresse) {
        this.nom = nom;
        this.prenom =prenom;
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

    public void display(){
        System.out.println("Nom : " + nom + ", identifiant : " + identifiant);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (!getNom().equals(client.getNom())) return false;
        return getPrenom().equals(client.getPrenom());
    }

    @Override
    public int hashCode() {
        int result = getNom().hashCode();
        result = 31 * result + getPrenom().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", identifiant=" + identifiant +
                '}';
    }

    public String getPrenom() {
        return prenom;
    }

    public int getIdentifiant() {
        return identifiant;
    }
}
