package modele.outils;

import modele.boutique.Boutique;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import modele.client.Client;
import modele.commande.Commande;
import modele.stock.Article;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class DonneeManager {
    private static final String XMLFILE = "boutique.xml";

    public static void lire() {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(new File(XMLFILE));




        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void ecrire(){
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document= builder.newDocument();

            document.appendChild(dataToXml(document))
            ;
            final TransformerFactory transformerFactory = TransformerFactory.newInstance();
            final Transformer transformer = transformerFactory.newTransformer();
            final DOMSource source = new DOMSource(document);
            final StreamResult sortie = new StreamResult(new File(XMLFILE));
            //final StreamResult result = new StreamResult(System.out);

            //prologue
            transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

            //formatage
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            //sortie
            transformer.transform(source, sortie);


        }catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private static Element dataToXml(Document document){
        Element racine = document.createElement("boutique");

        Element clients = document.createElement("clients");
        for (Client client : Boutique.getInstance().getClientList()){
            Element clientElement = document.createElement("client");

            clientElement.setAttribute("identifiant", String.valueOf(client.getId()));
            clientElement.setAttribute("nom", client.getNom());
            clientElement.setAttribute("prenom", client.getPrenom());
            clientElement.setAttribute("adresse", client.getAdresse());

            clients.appendChild(clientElement);
        }
        racine.appendChild(clients);

        Element commandes = document.createElement("commandes");
        for (Commande commande : Boutique.getInstance().getCommandeList()){
            Element commandeElement = document.createElement("commande");

            commandeElement.setAttribute("identifiant", String.valueOf(commande.getId()));
            commandeElement.setAttribute("nomClient", commande.getNomClient());
            commandeElement.setAttribute("date", commande.getDate());
            commandeElement.setAttribute("date", commande.getDate());
            commandeElement.setAttribute("fraisDePort", String.valueOf(commande.getFraisDePort()));

            for (Commande.LigneDeCommande ligneDeCommande : commande.getLignes()){
                int cpt = 1;
                Element ligneCommandeElement = document.createElement("ligneCommande"+cpt);

                ligneCommandeElement.setAttribute("quantité", String.valueOf(ligneDeCommande.getQuantite()));
                ligneCommandeElement.setTextContent(ligneDeCommande.getObjet().getNom());

                commandeElement.appendChild(ligneCommandeElement);
            }

            commandeElement.setAttribute("prix", String.valueOf(commande.getPrixTotal()));
            commandes.appendChild(commandeElement);
        }
        racine.appendChild(commandes);

        Element stocks = document.createElement("stocks");
        for(Map.Entry<Article, Integer> entry : Boutique.getInstance().getStocks().entrySet()) {
            int quantite = entry.getValue();

            Class<?> c = entry.getKey().getClass();
            Field[] fields = c.getDeclaredFields();
            Element articleElement = document.createElement(c.getClass().getSimpleName().toLowerCase());

            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    System.out.println(field.getName().toString() + " " + field.get(entry.getKey()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                stocks.appendChild(articleElement);
            }
        }
        racine.appendChild(stocks);


        return racine;
    }

    private static void xmlToData(Document document){
        Element racine = document.getDocumentElement();


        //TODO
    }
}
