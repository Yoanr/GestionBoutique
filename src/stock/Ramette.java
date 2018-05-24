package stock;

public class Ramette extends Article {
    private String grammage;

    public Ramette(String nom, String reference, String marque, double prixUnitaire, String grammage) {
        super(nom, reference, marque, prixUnitaire);
        this.grammage = grammage;
    }

    public String getGrammage() {
        return grammage;
    }

    public void setGrammage(String grammage) {
        this.grammage = grammage;
    }

    @Override
    public String toString() {
        return "Ramette " + grammage + " " + reference;
    }

}
