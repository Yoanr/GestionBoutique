package modele.outils;

import modele.boutique.Boutique;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import modele.client.Client;
import modele.commande.Commande;
import modele.stock.Article;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


public class DonneeManager {
    private static final String XMLFILE = "boutique.xml";
    
    private static final Boutique boutiqueInstance =  Boutique.getInstance();

    public static void lire() {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(new File(XMLFILE));

            boutiqueInstance.getStocksList().clear();
            boutiqueInstance.getStocksMap().clear();
            boutiqueInstance.getClientList().clear();
            boutiqueInstance.getCommandeList().clear();

            xmlToData(document);



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
        for (Client client : boutiqueInstance.getClientList()){
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
        for (Commande commande : boutiqueInstance.getCommandeList()){
            Element commandeElement = document.createElement("commande");

            commandeElement.setAttribute("id", String.valueOf(commande.getId()));
            commandeElement.setAttribute("idClient", String.valueOf(commande.getIdClient()));
            commandeElement.setAttribute("date", commande.getDate());
            commandeElement.setAttribute("date", commande.getDate());
            commandeElement.setAttribute("fraisDePort", String.valueOf(commande.getFraisDePort()));

            for (Commande.LigneDeCommande ligneDeCommande : commande.getLignes()){
                Element ligneCommandeElement = document.createElement("ligneCommande");

                ligneCommandeElement.setAttribute("quantite", String.valueOf(ligneDeCommande.getQuantite()));
                ligneCommandeElement.setAttribute("referenceArticle", ligneDeCommande.getObjet().getReference());

                commandeElement.appendChild(ligneCommandeElement);
            }

            commandeElement.setAttribute("prix", String.valueOf(commande.getPrixTotal()));
            commandes.appendChild(commandeElement);
        }
        racine.appendChild(commandes);

        //Stocks
        Element stocks = document.createElement("stocks");
        for(Map.Entry<Article, Integer> entry : boutiqueInstance.getStocksMap().entrySet()) {
            int quantite = entry.getValue();
            Article article = entry.getKey();

            Class<?> currentClass = entry.getKey().getClass();
            Field[] fields = currentClass.getDeclaredFields();
            Element articleElement = document.createElement("article");
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

    private static void xmlToData(Document document) {
        Element racine = document.getDocumentElement();

        //Clients
        NodeList nodeListClient = racine.getElementsByTagName("client");
        for (int index = 0; index < nodeListClient.getLength(); index++) {

            if (!"clients".equals(nodeListClient.item(index).getParentNode().getNodeName())) continue;

            Element currentElementClient = (Element) nodeListClient.item(index);
            String [] clientAttributes = {currentElementClient.getAttribute("nom"),
                    currentElementClient.getAttribute("prenom"),
                    currentElementClient.getAttribute("adresse")};

            boutiqueInstance.ajouterClient(clientAttributes);
        }

        //Articles
        NodeList nodeListArticle = racine.getElementsByTagName("article");
        for (int index = 0; index < nodeListArticle.getLength(); index++) {

            if (!"stocks".equals(nodeListArticle.item(index).getParentNode().getNodeName()))
                continue;

            Element currentElementArticle = (Element) nodeListArticle.item(index);
            Element typeArticle = null;

            for (int indexTypeArticle = 0; indexTypeArticle < currentElementArticle.getChildNodes().getLength(); indexTypeArticle++){
                if (currentElementArticle.getChildNodes().item(indexTypeArticle).getNodeType() != Node.ELEMENT_NODE) continue;
                typeArticle = (Element) currentElementArticle.getChildNodes().item(indexTypeArticle);
            }

            String attributeArticle [] = {typeArticle.getNodeName(),
                    typeArticle.getAttribute("reference"),
                    typeArticle.getAttribute("marque"),
                    typeArticle.getAttribute("prix"),
                    currentElementArticle.getAttribute("quantite")};

            boutiqueInstance.ajouterArticle(attributeArticle);
        }

        //Commandes
        NodeList nodeListCommande = racine.getElementsByTagName("commande");
        for (int index = 0; index < nodeListCommande.getLength(); index++) {

            if (!"commandes".equals(nodeListCommande.item(index).getParentNode().getNodeName())) continue;

            Element currentElementCommande = (Element) nodeListCommande.item(index);

            if(currentElementCommande.getChildNodes().getLength() <= 0) continue;

            Commande currentCommand = new Commande(Integer.parseInt(currentElementCommande.getAttribute("idClient")),
                    currentElementCommande.getAttribute("date"),
                    Double.parseDouble(currentElementCommande.getAttribute("fraisDePort")));

            for (int indexLigne = 0; indexLigne < currentElementCommande.getChildNodes().getLength(); indexLigne++) {

                if ((currentElementCommande.getChildNodes().item(indexLigne).getNodeType() != Node.ELEMENT_NODE)) continue;

                Element currentLigneCommande = (Element) currentElementCommande.getChildNodes().item(indexLigne);
                System.out.println(currentLigneCommande.getNodeName());
                Article articleCmd = boutiqueInstance.getArticleByReference(currentLigneCommande.getAttribute("referenceArticle"));
                currentCommand.ajoutObjet(articleCmd, Integer.parseInt(currentLigneCommande.getAttribute("quantite")));
            }

            boutiqueInstance.ajouterCommande(currentCommand);
        }

    }

}
