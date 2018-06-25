package modele.boutique;

import modele.client.Client;
import modele.commande.Commande;
import modele.stock.Article;
import modele.stock.ArticleFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Boutique {

    private String nom;
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
    public static final String MODIFIE_ERROR = "Modification de l'élément impossible";
    public static final String MODIFIE = "Modification reussi";
    public static final String CLIENT_ERROR = "Client non existant";

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

    public List<String> getBoutiqueInfo(){
        List<String> liste =  new ArrayList<String>();
        liste.add(toString());
        return liste;
    }

    public HashMap<Article, Integer> getStocksMap() {
        return stocks;
    }

    public List<String> getStocksList() {
        List<String> stockList = new ArrayList<>();

        for (Map.Entry<Article, Integer> entry : stocks.entrySet()) {
            int quantite = entry.getValue();
            Article article = entry.getKey();

            StringBuilder stringBuilder = new StringBuilder("Quantité : ");

            stringBuilder.append(String.valueOf(quantite));
            stringBuilder.append(" - ");
            stringBuilder.append(article.toString());
            stockList.add(stringBuilder.toString());
        }
        System.out.println(stockList);
        return stockList;
    }


    public String supprimerClient(String clientId){
        int idClient = Integer.parseInt(clientId);
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

    public Commande ajouterCommande(String [] commandeArgs){
        switch (commandeArgs.length) {
            case 3:
                return ajouterCommande(new Commande(Integer.parseInt(commandeArgs[0]), commandeArgs[1], Double.parseDouble(commandeArgs[2])));

        }
        return null;
    }

    public Commande ajouterCommande(Commande c){
        if (! commandeList.contains(c)) {
            commandeList.add(c);
            return c;
        }
        return null;
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

    public String supprimerCommande(String commandeID){
        int idCommande =  Integer.parseInt(commandeID);
        for (Commande commande : commandeList){
            if (commande.getId() == idCommande){
                commandeList.remove(commande);
                return SUPPRIME;
            }
        }
        return SUPPRIME_ERROR;
    }

    public boolean verifClient(int idClient){
        for (Client client : clientList){
            if(client.getId() == idClient)
                return true;
        }
        return false;
    }

    public String supprimerArticle(String articleReference){
        Article  article = getArticleByReference(articleReference);
        if (article != null) {
            int oldQuantite = stocks.get(article);
            stocks.put(article, oldQuantite -1);
            return SUPPRIME;
        }
        return SUPPRIME_ERROR;
    }

    public boolean verifStock(String referenceArticle, int quantite){
        Article  article = getArticleByReference(referenceArticle);
        if (article == null) return false;
        if (stocks.get(article) < quantite) return false;

        return true;
    }

    public void ajouterLigne(Commande c, String reference, int quantite){
        c.ajoutObjet(getArticleByReference(reference), quantite);
    }

    public int getQuantiteById(String reference){
        Article  article = getArticleByReference(reference);

        if(article == null || !stocks.containsKey(article)) return -1;
        return stocks.get(article);
    }

    public String modifierStock(int newQuantité, String reference){
        Article article = getArticleByReference(reference);

        if(article == null || !stocks.containsKey(article)) return MODIFIE_ERROR;

        stocks.put(article, newQuantité);
        return MODIFIE;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getLoyer() {
        return loyer;
    }

    public void setLoyer(double loyer) {
        this.loyer = loyer;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public double getCa() {
        return ca;
    }

    public void setCa(double ca) {
        this.ca = ca;
    }

    public double getBenefice() {
        return benefice;
    }

    public void setBenefice(double benefice) {
        this.benefice = benefice;
    }


    @Override
    public String toString() {
        return "Boutique{" +
                "loyer=" + loyer +
                ", salaire=" + salaire +
                ", charge=" + charge +
                ", ca=" + ca +
                ", benefice=" + benefice +
                '}';
    }

    public List<?> getCommandeListByClient(int idClient) {
        List<Commande> l = commandeList;
        List<Commande> l2 = new ArrayList<Commande>();
        for (Commande element : l){
            if(element.getIdClient() == idClient) {
                l2.add(element);
            }
        }
        return l2;
    }
}
