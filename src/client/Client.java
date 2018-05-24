package client;

public class Client {
    private String nom;
    private String adresse;
    private int identifiant;
    private static int cptId = 1;

    public Client(String nom) {
        this.nom = nom;
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
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", identifiant=" + identifiant +
                '}';
    }
}
