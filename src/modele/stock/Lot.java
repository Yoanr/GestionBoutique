package modele.stock;

import java.util.ArrayList;
import java.util.List;

public class Lot extends ObjetVendable {
    private Article type;
    private static int nbArticle;
    private int reduc;
    private List<Article> articles;

    public Lot(String reference, Article article) {
        super(article.toString(), reference);
        this.type = article;
        marque = type.getMarque();

        articles = new ArrayList<>();
        articles.add(article);

        nbArticle = 1;

        reduc = 10;

        updatePrix();
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
            System.out.println("Article "+ reference + " non trouvée");
        }
        else
            System.out.println("Lot " + nom + " vide");
    }

    private void updatePrix(){
        prix = 0;
        for (Article article : articles)
        {
            this.prix += article.getPrixUnitaire();
        }
        prix = prix * (100-reduc)/100;
    }

    public String getMarque() {
        return marque;
    }

    public double getPrix() {
        return prix;
    }
}
