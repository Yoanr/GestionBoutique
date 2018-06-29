package modele.stock;

import java.util.ArrayList;
import java.util.List;
/**
 * Cette classe est utilisée pour représenter un Lot.
 * (Ensemble d'article)
 *
 */
public class Lot extends ObjetVendable {
	private Article type;
	private static int nbArticle;

	private double reduc;
	private List<Article> articles;

	/**
	   * un constructeur de la classe ObjetVendable
	   *
	   * @param reference
	   * @param article
	   * @param reduction
	   */
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

	/**
	   * un constructeur de la classe Lot
	   *
	   * @param reference
	   * @param article
	   * @param reduction
	   * @param quantite
	   */
    public Lot (String reference, Article article,double reduction, int quantite){
        this(reference, article, reduction);
        for (int index = 1; index< quantite; index++){
            addArticle(article);
        }
    }

    /**
	   * Cette méthode ajoute un article au lot
	   *
	   * @param article
	   */
	public void addArticle(Article article) {
		nbArticle++;
		articles.add(article);
		updatePrix();
	}
/**
 * Cette méthode supprime un article du lot
 * @param reference
 */
	public void removeArticle(String reference) {
		if (nbArticle > 0) {
			for (Article article : articles) {
				if (article.reference.equals(reference)) {
					articles.remove(article);
					nbArticle--;
					updatePrix();
					return;
				}

			}
		}
	}
/**
 * modifie le prix en fonction de la reduction
 */
	private void updatePrix() {
		prix = 0;
		for (Article article : articles) {
			this.prix += article.getPrix();
		}
		prix = prix * (100 - reduc) / 100;
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

    /**
	   * Cette méthode renvoie une chaîne de caractères qui représente
	   * un lot
	   *
	   * @return Une chaîne de caractère
	   */
    @Override
    public String toString() {
        return "Lot de "+ articles.size() +" " + type.getClass().getSimpleName() +"(s) "  +
                ", reference='" + reference + '\'' +
                ", referenceArticle='" + marque + '\'' +
                ", quantité'"+getQuantite() +'\''+
                ", reduc=" + reduc +
                ", prix=" + String.format("%.2f",prix);
    }
}
