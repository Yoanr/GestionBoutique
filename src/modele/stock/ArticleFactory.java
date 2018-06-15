package modele.stock;

public final class ArticleFactory {

    private  static ArticleFactory instance = new ArticleFactory();

    public static ArticleFactory getInstance(){return  instance;}

    private ArticleFactory() {
    }


    public Article creerArticle(String typeArticle, String reference, String marque, double prixUnitaire) {
        typeArticle = typeArticle.toLowerCase();

        switch (typeArticle) {
            case "stylo":
                return new Stylo(reference, marque, prixUnitaire);

            case "Ramette":
                return new Ramette(reference, marque, prixUnitaire);

            default:
                System.out.println("Impossible de creer " + typeArticle + ", type d'article inconnue");
                return null;
        }
    }
}
