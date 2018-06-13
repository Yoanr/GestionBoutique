package modele.stock;

public final class ArticleFactory {

    private  ArticleFactory instance = new ArticleFactory();

    public ArticleFactory getInstance(){return  instance;}

    private ArticleFactory() {
    }


    Article creerArticle(String typeArticle, String nom, String reference, String marque, double prixUnitaire) {
        typeArticle = typeArticle.toLowerCase();

        switch (typeArticle) {
            case "stylo":
                return new Stylo(nom, reference, marque, prixUnitaire);

            case "Ramette":
                return new Ramette(nom, reference, marque, prixUnitaire);

            default:
                System.out.println("Impossible de creer " + typeArticle + ", type d'article inconnue");
                return null;
        }
    }
}
