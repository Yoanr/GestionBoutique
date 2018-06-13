package modele.stock;

public abstract class Article extends ObjetVendable {

    private double coutObtention;

    public Article(String nom, String reference, String marque, double prixUnitaire) {
        super(nom, reference);
        this.marque = marque;
        this.prix = prixUnitaire;
    }

    public String getMarque() {
        return marque;
    }

    public double getPrixUnitaire() {
        return prix;
    }
}
