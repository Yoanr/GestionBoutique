package modele.stock;

import javax.print.DocFlavor;

/**
 * ArticleFactory fabrique des articles
 *
 */
public final class ArticleFactory {

	private static ArticleFactory instance = new ArticleFactory();

	public static ArticleFactory getInstance() {
		return instance;
	}

	/**
	 * constructeur privé
	 */
	private ArticleFactory() {
	}

	/**
	 * @param article type
	 * @return Article
	 */
	public Article creerArticle(String[] args) {
		String typeArticle = args[0].toLowerCase();

		switch (typeArticle) {
		case "stylo":
			return new Stylo(args[1], args[2], Double.parseDouble(args[3]),
					Stylo.Couleur.valueOf(args[4].toUpperCase()));

		case "ramette":
			return new Ramette(args[1], args[2], Double.parseDouble(args[3]), Double.parseDouble(args[4]),
					Double.parseDouble(args[5]));

		default:
			System.out.println("Impossible de creer " + typeArticle + ", type d'article inconnue");
			return null;
		}
	}
}
