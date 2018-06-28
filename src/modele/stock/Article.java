package modele.stock;

public abstract class Article extends ObjetVendable {

    private double coutObtention;

    public Article(String reference , String marque, double prixUnitaire) {
        super(reference);
        this.marque = marque;
        this.prix = prixUnitaire;
    }

    public double getCoutObtention() {
        return coutObtention;
    }
}
