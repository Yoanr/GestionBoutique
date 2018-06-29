package modele.stock;

import java.util.ArrayList;
import java.util.List;

public class Lot extends ObjetVendable {
    private Article type;
    private static int nbArticle;

    private double reduc;
    private List<Article> articles;

    public Lot(String reference, Article article, double reduction) {
        super(reference);
        this.type = article;
        marque = type.getMarque();

        articles = new ArrayList<>();
        articles.add(article);

        this.reduc = reduction;

        nbArticle = 1;
        updatePrix();
    }

    public Lot (String reference, Article article,double reduction, int quantite){
        this(reference, article, reduction);
        for (int index = 1; index< quantite; index++){
            addArticle(article);
        }
    }

    public void addArticle(Article article){
        nbArticle++;
        articles.add(article);
        updatePrix();
    }

    public void removeArticle(String reference){
        if(nbArticle >0) {
            for(Article article : articles){
                if(article.reference.equals(reference)){
                    articles.remove(article);
                    nbArticle--;
                    updatePrix();
                    return;
                }

            }
        }
    }

    private void updatePrix(){
        prix = 0;
        for (Article article : articles)
        {
            this.prix += article.getPrix();
        }
        prix = prix * (100-reduc)/100;
    }

    public String getMarque() {
        return marque;
    }

    public double getPrix() {
        return prix;
    }

    public double getReduc() {
        return reduc;
    }

    public void setReduc(int reduc) {
        this.reduc = reduc;
    }

    public int getQuantite(){
        return articles.size();
    }

    @Override
    public String toString() {
        return "Lot{" +
                "nom='" + "Lot de "+ articles.size() + "(s) " + type.getClass().getSimpleName() + '\'' +
                ", reference='" + reference + '\'' +
                ", referenceArticle='" + marque + '\'' +
                ", reduc=" + reduc +
                ", prix=" + prix +
                '}';
    }
}
