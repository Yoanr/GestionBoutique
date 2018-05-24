package client;

import java.util.ArrayList;

public class ClientList {

    private ArrayList<Client> clientList;

    public ClientList() {
        this.clientList = new ArrayList<>();
    }

    public ArrayList<Client> getClientList() {
        return clientList;
    }

    public int getClientListSize(){
        return clientList.size();
    }

    public boolean retirerClient(int idClient){
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

    public boolean addClient(Client newClient) {
        boolean added = false;

        if (! clientList.contains(newClient)) {
            clientList.add(newClient);
            added = true;
        }
        return added;
    }

    @Override
    public String toString() {
        return "ClientList{" +
                "clientList=" + clientList +
                '}';
    }
}
