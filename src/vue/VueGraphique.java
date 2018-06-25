package vue;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modele.outils.DonneeManager;

import java.io.File;
import java.util.List;

public class VueGraphique extends Application implements Affichage {

    private ComboBox<String> comboBoxType;
    private Button addButton ;
    private Button deleteButton ;
    private Button modifyButton ;
    private Group root ;
    private Scene scene ;
    private TableView table;

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

    private void initComponent(Stage primaryStage){
        root = new Group();
        scene = new Scene(root, 700, 500, Color.WHITE);

        comboBoxType = new ComboBox<>();
        comboBoxType.getItems().addAll("Article","Boutique", "Clients", "Commandes");
        comboBoxType.setValue("Boutique");

        addButton = new Button();
        addButton.setText("Ajouter");
        addButton.setOnAction(event -> ajouter());

        modifyButton = new Button();
        modifyButton.setText("Modifier");
        modifyButton.setOnAction(event -> System.out.println("modifiiier"));

        deleteButton = new Button();
        deleteButton.setText("Supprimer");
        deleteButton.setOnAction(event -> supprimer());

        table = new TableView();
        table.setEditable(true);

        VBox vBoxCombo = new VBox(5);
        vBoxCombo.getChildren().addAll(comboBoxType, table);
        vBoxCombo.setLayoutX(50);
        vBoxCombo.setLayoutY(50);

        HBox hBoxButton = new HBox(100);
        hBoxButton.getChildren().addAll(addButton, modifyButton, deleteButton);
        hBoxButton.setLayoutX(150);
        hBoxButton.setLayoutY(400);
        hBoxButton.setAlignment(Pos.BOTTOM_CENTER);


        MenuBar menuBar = new MenuBar();
        Menu menuFichier = new Menu("Fichier");
        MenuItem menuOpen = new MenuItem("Ouvrir");
        menuOpen.setOnAction(event->ouvrirFichier(primaryStage));
        MenuItem menuSave = new MenuItem("Sauvegarder");
        menuSave.setOnAction(event->sauvegarderFichier(primaryStage));
        menuFichier.getItems().addAll(menuOpen, menuSave);
        Menu menuAide   = new Menu("Aide");
        menuBar.getMenus().addAll(menuFichier, menuAide);
        menuBar.prefWidthProperty (). bind (primaryStage.widthProperty ());


        root.getChildren().addAll(menuBar,vBoxCombo, hBoxButton);
    }


    private void ouvrirFichier(Stage primaryStage){
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir un fichier XML");
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            DonneeManager.lire(file.getAbsolutePath());
        }
    }
    private void sauvegarderFichier(Stage primaryStage){
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sauvegarder un fichier XML");
        File file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
            DonneeManager.ecrire(file.getAbsolutePath());
        }
    }

    private void aide(){
        //TODO
    }

    //TODO tableview, affichage

    @Override
    public String[] utilisateurAction() {
        return null;
    }

    @Override
    public String[] ajouter() {
        return new String[0];
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
	public String modifierstock(it quantite) {
		// TODO Auto-generated method stub
		return null;
	}
}
