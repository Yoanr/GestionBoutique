package modele.stock;

import modele.client.Client;
import modele.commande.Commande;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Modele {

    private List<Client> clientList = new ArrayList<>();
    private List<Commande> commandeList= new ArrayList<>();
    private HashMap<Integer, Article> stocks = new HashMap<>();

    private Modele instance = new Modele();

    private Modele() {
    }

    public Modele getInstance(){return instance;}

    public List<Client> getClientList() {
        return clientList;
    }

    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public HashMap<Integer, Article> getStocks() {
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

    public void ajouterCommande(Commande c){

    }

    public void getBoutiqueInfo (){

    }
}
