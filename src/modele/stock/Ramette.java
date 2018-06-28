package modele.stock;

public class Ramette extends Article {
    private String grammage;
    private double dimL;
    private double dimH;

    public Ramette(String reference, String marque, double prixUnitaire) {
        super(reference, marque, prixUnitaire);
    }

    public Ramette(String reference, String marque, double prixUnitaire, String grammage, double dimH, double dimL) {
        super(reference, marque, prixUnitaire);
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
    public String toString() {
        return "Ramette{" +
                "reference='" + reference + '\'' +
                ", marque='" + marque + '\'' +
                ", grammage='" + grammage + '\'' +
                ", dimL=" + dimL +
                ", dimH=" + dimH +
                ", prix=" + prix +
                '}';
    }
}
