package modele.stock;

public class Ramette extends Article {
    private double dimL;
    private double dimH;

    public Ramette(String reference, String marque, double prixUnitaire) {
        super(reference, marque, prixUnitaire);
    }

    public Ramette(String reference, String marque, double prixUnitaire, double dimH, double dimL) {
        super(reference, marque, prixUnitaire);
        this.dimH = dimH;
        this.dimL = dimL;
    }
    @Override
    public String toString() {
        return "Ramette{" +
                "reference='" + reference + '\'' +
                ", marque='" + marque + '\'' +
                ", dimL=" + dimL +
                ", dimH=" + dimH +
                ", prix=" + prix +
                '}';
    }
}
