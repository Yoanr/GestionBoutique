package modele.stock;

public abstract class ObjetVendable {
    protected String nom;
    protected String reference;
    protected String marque;
    protected double prix;

    public ObjetVendable(String nom, String reference) {
        this.nom = nom;
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
}


