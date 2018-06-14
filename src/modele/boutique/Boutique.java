package modele.boutique;

import modele.client.Client;
import modele.commande.Commande;
import modele.stock.Article;
import modele.stock.ObjetVendable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Boutique {

    private double loyer;
    private double salaire;
    private double charge;
    private double  ca;
    private double benefice;

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

    public HashMap<Article, Integer> getStocks() {
        return stocks;
    }


    public boolean supprimerClient(int idClient){
        boolean deleted = false;

        for (Client client : clientList){
            if (client.getId() == idClient){
                clientList.remove(client);
                deleted = true;
                break;
            }
        }
        return deleted;
    }

    public boolean ajouterClient(Client newClient) {
        boolean added = false;

        if (! clientList.contains(newClient)) {
            clientList.add(newClient);
            added = true;
        }
        return added;
    }

    public boolean ajouterCommande(Commande c){
        boolean added = false;

        if (! commandeList.contains(c)) {
            commandeList.add(c);
            added = true;
        }
        return added;
    }

    public boolean ajouterArticle(Article article, int quantite){
        boolean added = false;

        if (! stocks.containsKey(article)) {
            stocks.put(article, quantite  );
            added = true;
        }
        return added;
    }

    public void getBoutiqueInfo (){

    }
}
