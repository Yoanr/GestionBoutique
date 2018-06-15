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
import org.w3c.dom.*;
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

            xmlToData(document.getDocumentElement());



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

        //Clients
        Element clients = document.createElement("clients");
        for (Client client : Boutique.getInstance().getClientList()){
            Element clientElement = document.createElement("client");

            clientElement.setAttribute("id", String.valueOf(client.getId()));
            clientElement.setAttribute("nom", client.getNom());
            clientElement.setAttribute("prenom", client.getPrenom());
            clientElement.setAttribute("adresse", client.getAdresse());

            clients.appendChild(clientElement);
        }
        racine.appendChild(clients);


        //Commandes
        Element commandes = document.createElement("commandes");
        for (Commande commande : Boutique.getInstance().getCommandeList()){
            Element commandeElement = document.createElement("commande");

            commandeElement.setAttribute("id", String.valueOf(commande.getId()));
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

        //Stocks
        Element stocks = document.createElement("stocks");
        for(Map.Entry<Article, Integer> entry : Boutique.getInstance().getStocksMap().entrySet()) {
            int quantite = entry.getValue();
            Article article = entry.getKey();

            Class<?> currentClass = entry.getKey().getClass();
            Field[] fields = currentClass.getDeclaredFields();
            Element articleElement = document.createElement("Article");
            articleElement.setAttribute("quantite", String.valueOf(quantite));
            articleElement.setAttribute("type", currentClass.getSimpleName().toLowerCase());

            //For a specific article
            Element specificArticle = document.createElement(currentClass.getSimpleName().toLowerCase());
            specificArticle.setAttribute("nom", article.getNom());
            specificArticle.setAttribute("reference", article.getReference());
            specificArticle.setAttribute("marque", article.getMarque());
            specificArticle.setAttribute("prix", String.valueOf(article.getPrix()));
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    specificArticle.setAttribute(field.getName().toString(), field.get(entry.getKey()) != null? field.get(entry.getKey()).toString() : "");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            articleElement.appendChild(specificArticle);
            stocks.appendChild(articleElement);
        }
        racine.appendChild(stocks);


        return racine;
    }

    private static void xmlToData(Element element){
        // do something with the current node instead of System.out
        System.out.println(element.getNodeName());

        switch (element.getNodeName()){
            case "client":{
                Boutique.getInstance().ajouterClient(
                        new Client(element.getAttribute("nom"),
                                    element.getAttribute("prenom"),
                                    element.getAttribute("adresse")));

                //todo check l id ajouté
                break;
            }
            case "commandes":{

                break;
            }
            case "stocks" : {

                break;
            }
        }


        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                //calls this method for all the children which is Element
                Element currentElement = (Element) currentNode;
                xmlToData(currentElement);
            }
        }
    }
/*
        if (racine != null){
           //Client

           for (int indexRacineChild = 0; indexRacineChild <= racine.getChildNodes().getLength(); indexRacineChild++){
               Element currentElement =(Element) racine.getChildNodes().item(indexRacineChild);





        }*/

}
