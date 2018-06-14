package modele.commande;

import modele.stock.ObjetVendable;

import java.util.ArrayList;

public class Commande {private String nomClient;
    private String date;
    private  int id;
    private double prixTotal;
    private ArrayList<LigneDeCommande> lignes;
    private double fraisDePort;
    private static int cptId = 1;

    public Commande(String nomClient, String date, ObjetVendable ObjetVendable, int quantite) {
        this.nomClient = nomClient;
        this.date = date;
        lignes = new ArrayList<>();
        addLigne(new LigneDeCommande(ObjetVendable, quantite));
        updatePrix();
        this.id = cptId++;
    }

    public class  LigneDeCommande {
        private ObjetVendable ObjetVendable;
        private int quantite;


        public LigneDeCommande(ObjetVendable ObjetVendable, int quantite) {
            this.ObjetVendable = ObjetVendable;
            this.quantite = quantite;

        }
        public ObjetVendable getObjet() {
            return ObjetVendable;
        }

        public int getQuantite() {
            return quantite;
        }

    }

    public void ajoutObjet(ObjetVendable ObjetVendable, int quantite){
        addLigne(new LigneDeCommande(ObjetVendable, quantite));
    }

    private void addLigne(LigneDeCommande LigneDeCommande){
        lignes.add(LigneDeCommande);
        updatePrix();
    }

    public void removeLigne(String reference){
        if(lignes.size() >0) {
            for(LigneDeCommande LigneDeCommande : lignes){
                if(LigneDeCommande.getObjet().getReference().equals(reference)){
                    lignes.remove(LigneDeCommande);
                    updatePrix();
                    return;
                }

            }
            System.out.println("Article "+ reference + " non trouvée");
        }
    }

    private void updatePrix(){
        prixTotal = 0;
        for (LigneDeCommande LigneDeCommande : lignes){
            prixTotal += LigneDeCommande.getObjet().getPrix() * LigneDeCommande.getQuantite();
        }
    }

    @Override
    public String toString() {
        String facture =  "Facture n° " + id + "\tNom du client= " + nomClient + "\tDate= " + date + "\n\n";

        facture += String.format("%10s\t%10s\t%20s\t%10s\t%10s\t%10s","Quant." , "Ref." ,  "Nom" , "Marque" , "PU (€)", "PT (€)\n");
        for (LigneDeCommande LigneDeCommande : lignes){
            facture +=  String.format("%10s\t",LigneDeCommande.getQuantite())+
                    String.format("%10s\t",LigneDeCommande.getObjet().getReference()) +
                    String.format("%20s\t",LigneDeCommande.getObjet().getNom()) +
                    String.format("%10s\t",LigneDeCommande.getObjet().getMarque()) +
                    String.format("%10s\t",LigneDeCommande.getObjet().getPrix()) +
                    String.format("%10s\n",LigneDeCommande.getObjet().getPrix() * LigneDeCommande.getQuantite());
        }

        facture += "\nprixTotal= " + prixTotal + " euros";

        return facture;
    }
}
