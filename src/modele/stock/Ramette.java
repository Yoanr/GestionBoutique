package modele.stock;

public class Ramette extends Article {
    private String grammage;
    private double dimL;
    private double dimH;

    public Ramette(String nom, String reference, String marque, double prixUnitaire) {
        super(nom, reference, marque, prixUnitaire);
    }

    public Ramette(String nom, String reference, String marque, double prixUnitaire, String grammage, double dimH, double dimL) {
        super(nom, reference, marque, prixUnitaire);
        this.grammage = grammage;
        this.dimH = dimH;
        this.dimL = dimL;
    }

    public String getGrammage() {
        return grammage;
    }

    public void setGrammage(String grammage) {
        this.grammage = grammage;
    }

    @Override
    public String descriptionArticle() {
        return "Ramette " + grammage + " " + reference;
    }
}
