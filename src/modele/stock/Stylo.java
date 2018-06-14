package modele.stock;

public class Stylo extends Article {
    public enum Couleur{
        ROUGE,BLEU,VERT,JAUNE,NOIR
    }

    private Couleur couleur;

    public Stylo(String nom, String reference, String marque, double prixUnitaire) {
        super(nom, reference, marque, prixUnitaire);
    }


    public Stylo(String nom, String reference, String marque, double prixUnitaire, Couleur couleur) {
        super(nom, reference, marque, prixUnitaire);
        this.couleur = couleur;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    @Override
    public String descriptionArticle() {
        return "Stylo " + couleur + " " + reference;
    }
}