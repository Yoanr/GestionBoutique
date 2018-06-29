package vue;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modele.boutique.Boutique;
import modele.client.Client;
import modele.outils.DonneeManager;
import modele.stock.Article;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VueGraphique extends Application implements Affichage {

	private ComboBox<String> comboBoxType;
	private Button addButton;
	private Group root;
	private Scene scene;
	private TableView table;
	private ObservableList<Map> allData;

	public static void main(String[] args) {
		Application.launch(VueGraphique.class, args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Gestionnaire de la boutique");

		initComponent(primaryStage);

		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void initComponent(Stage primaryStage) {
		root = new Group();
		scene = new Scene(root, 700, 500, Color.WHITE);

		comboBoxType = new ComboBox<>();
		comboBoxType.getItems().addAll("Choisissez...", "Article", "Clients", "Commandes");
		comboBoxType.setValue("Choisissez...");
		comboBoxType.valueProperty().addListener(event -> {
			updateTable();
		});

		addButton = new Button();
		addButton.setText("Ajouter");
		addButton.setOnAction(event -> ajouter());

		allData = FXCollections.observableArrayList();
		table = new TableView(allData);
		table.setEditable(true);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setPrefWidth(500);
		table.setPrefHeight(300);
		table.setVisible(false);

		VBox vBoxCombo = new VBox(5);
		vBoxCombo.getChildren().addAll(comboBoxType, table);
		vBoxCombo.setLayoutX(50);
		vBoxCombo.setLayoutY(50);

		HBox hBoxButton = new HBox(100);
		hBoxButton.getChildren().addAll(addButton);
		hBoxButton.setLayoutX(150);
		hBoxButton.setLayoutY(400);
		hBoxButton.setAlignment(Pos.BOTTOM_CENTER);

		MenuBar menuBar = new MenuBar();
		Menu menuFichier = new Menu("Fichier");
		MenuItem menuOpen = new MenuItem("Ouvrir");
		menuOpen.setOnAction(event -> ouvrirFichier(primaryStage));
		MenuItem menuSave = new MenuItem("Sauvegarder");
		menuSave.setOnAction(event -> sauvegarderFichier(primaryStage));
		menuFichier.getItems().addAll(menuOpen, menuSave);
		Menu menuAide = new Menu("Aide");
		menuBar.getMenus().addAll(menuFichier, menuAide);
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

		root.getChildren().addAll(menuBar, vBoxCombo, hBoxButton);
	}

	private void ouvrirFichier(Stage primaryStage) {
		final FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Ouvrir un fichier XML");
		File file = fileChooser.showOpenDialog(primaryStage);
		if (file != null) {
			DonneeManager.lire(file.getAbsolutePath());
		}
	}

	private void sauvegarderFichier(Stage primaryStage) {
		final FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Sauvegarder un fichier XML");
		File file = fileChooser.showSaveDialog(primaryStage);
		if (file != null) {
			DonneeManager.ecrire(file.getAbsolutePath());
		}
	}

	private void updateTable() {
		String typeData = comboBoxType.getSelectionModel().getSelectedItem();

		table.getColumns().clear();
		table.getItems().clear();
		allData.clear();

		table.setVisible(!"Choisissez...".equals(typeData));

		switch (typeData) {
		case "Article": {
			TableColumn<Map, String> clm1 = new TableColumn<>("Quantité");
			TableColumn<Map, String> clm2 = new TableColumn<>("Type");
			TableColumn<Map, String> clm3 = new TableColumn<>("Référence");
			TableColumn<Map, String> clm4 = new TableColumn<>("Marque");
			TableColumn<Map, String> clm5 = new TableColumn<>("Prix");
			clm1.setCellValueFactory(new MapValueFactory("Quantité"));
			clm2.setCellValueFactory(new MapValueFactory("Type"));
			clm3.setCellValueFactory(new MapValueFactory("Référence"));
			clm4.setCellValueFactory(new MapValueFactory("Marque"));
			clm5.setCellValueFactory(new MapValueFactory("Prix"));

			table.getColumns().setAll(clm1, clm2, clm3, clm4, clm5);
			for (Map.Entry<Article, Integer> entry : Boutique.getInstance().getStocksMap().entrySet()) {

				Map<String, String> dataRow = new HashMap<>();
				int quantite = entry.getValue();
				Article article = entry.getKey();
				dataRow.put("Quantité", String.valueOf(quantite));
				dataRow.put("Type", String.valueOf(article.toString().split(" ")[0]));
				dataRow.put("Référence", String.valueOf(article.getReference()));
				dataRow.put("Marque", String.valueOf(article.getMarque()));
				dataRow.put("Prix", String.valueOf(article.getPrix()));
				allData.addAll(dataRow);
			}

			break;
		}
		case "Clients": {
			TableColumn<Map, String> clm1 = new TableColumn<>("Identifiant");
			TableColumn<Map, String> clm2 = new TableColumn<>("Nom");
			TableColumn<Map, String> clm3 = new TableColumn<>("Prénom");
			TableColumn<Map, String> clm4 = new TableColumn<>("Adresse");

			clm1.setCellValueFactory(new MapValueFactory("Identifiant"));
			clm2.setCellValueFactory(new MapValueFactory("Nom"));
			clm3.setCellValueFactory(new MapValueFactory("Prénom"));
			clm4.setCellValueFactory(new MapValueFactory("Adresse"));

			table.getColumns().addAll(clm1, clm2, clm3, clm4);
			for (Client client : Boutique.getInstance().getClientList()) {

				Map<String, String> dataRow = new HashMap<>();
				dataRow.put("Identifiant", String.valueOf(client.getId()));
				dataRow.put("Nom", client.getNom());
				dataRow.put("Prénom", client.getPrenom());
				dataRow.put("Adresse", client.getAdresse());
				allData.addAll(dataRow);
			}
			break;
		}
		case "Commandes": {
			TableColumn<Map, String> clm1 = new TableColumn<>("Nom client");
			TableColumn<Map, String> clm2 = new TableColumn<>("Date");
			TableColumn<Map, String> clm3 = new TableColumn<>("Frais de port");
			TableColumn<Map, String> clm4 = new TableColumn<>("Quantité");
			TableColumn<Map, String> clm5 = new TableColumn<>("Prix");

			break;
		}
		default: {

		}
		}

	}

	private void aide() {
		// TODO
	}

	@Override
	public String[] ajouter() {
		return new String[0];
	}

	// TODO tableview, affichage

	@Override
	public String[] utilisateurAction() {
		return null;
	}

	@Override
	public void afficherAide(String s) {

	}

	@Override
	public void msgModele(String msg) {

	}

	@Override
	public <T> void afficher(List<T> listClient) {

	}

	@Override
	public void afficherMenu() {
		// TODO Auto-generated method stub

	}

	@Override
	public String supprimer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getLignesCommande() {
		return null;
	}

	@Override
	public String modifier() {
		return null;
	}

	@Override
	public String getClientid() {
		return null;
	}

	@Override
	public String modifierstock(int quantite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] ajouterArticle(String typeArticle) {
		return new String[0];
	}
}
