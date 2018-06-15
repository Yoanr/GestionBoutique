package modele.stock;

public abstract class Article extends ObjetVendable {

    private double coutObtention;

    public Article(String reference, String nom , String marque, double prixUnitaire) {
        super(nom, reference);
        this.marque = marque;
        this.prix = prixUnitaire;
    }

    public double getCoutObtention() {
        return coutObtention;
    }

    public abstract String descriptionArticle();

    @Override
    public String toString() {
        return descriptionArticle();
    }
}
