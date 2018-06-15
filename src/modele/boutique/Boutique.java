package modele.boutique;

import modele.client.Client;
import modele.commande.Commande;
import modele.stock.Article;
import modele.stock.ArticleFactory;
import modele.stock.ObjetVendable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Boutique {

    private double loyer;
    private double salaire;
    private double charge;
    private double  ca;
    private double benefice;

    public static final String DEFAULT = "";
    public static final String ARGS_ERROR = "Nombre d'arguments incorrect";
    public static final String AJOUTE = "Elément ajouté";
    public static final String SUPPRIME = "Elément supprimé";
    public static final String AJOUTE_ERROR = "Elément non ajouté";
    public static final String SUPPRIME_ERROR = "Elément non supprimé";

    private List<Client> clientList = new ArrayList<>();
    private List<Commande> commandeList= new ArrayList<>();
    private HashMap<Article, Integer > stocks = new HashMap<>();

    private static Boutique instance = new Boutique();

    private Boutique() {
    }

    public static Boutique getInstance(){return instance;}

    public List<Client> getClientList() {
        return clientList;
    }

    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public HashMap<Article, Integer> getStocksMap() {
        return stocks;
    }

    public List<String> getStocksList() {
        List<String> stockList = new ArrayList<>();

        for (Map.Entry<Article, Integer> entry : stocks.entrySet()) {
            int quantite = entry.getValue();
            Article article = entry.getKey();

            StringBuilder stringBuilder = new StringBuilder(String.valueOf(quantite));
            stringBuilder.append(" - ");
            stringBuilder.append(article.toString());
            stockList.add(stringBuilder.toString());
        }
        return stockList;
    }


    public String supprimerClient(int idClient){
        for (Client client : clientList){
            if (client.getId() == idClient){
                clientList.remove(client);
                return SUPPRIME;
            }
        }
        return SUPPRIME_ERROR;
    }

    public String ajouterClient(String [] clientArgs) {
        return ajouterClient(new Client(clientArgs[0], clientArgs[1], clientArgs[2]));
    }

    public String ajouterClient(Client newClient) {
         if (! clientList.contains(newClient)) {
            clientList.add(newClient);
           return AJOUTE;
        }
        return AJOUTE_ERROR;
    }

    public String ajouterCommande(){
        return AJOUTE;
    }

    public String ajouterCommande(Commande c){
        if (! commandeList.contains(c)) {
            commandeList.add(c);
            return AJOUTE;
        }
        return AJOUTE_ERROR;
    }


    public String ajouterArticle(String [] articleTab){

        return ajouterArticle(ArticleFactory.getInstance().creerArticle(articleTab[0], articleTab[1], articleTab[2], Double.parseDouble(articleTab[3])), Integer.parseInt(articleTab[4]));
    }

    public String ajouterArticle(Article article, int quantite){
       if (! stocks.containsKey(article)) {
            stocks.put(article, quantite );
            return AJOUTE;
        }
        return AJOUTE_ERROR;
    }

    public Article getArticleByReference(String reference){
        for (Article article : stocks.keySet()) {
            if(article.getReference().equals(reference))
                return article;
        }
        return null;
    }

    public String getBoutiqueInfo (){
        return null;
    }
}
