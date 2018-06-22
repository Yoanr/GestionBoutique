package vue;

import modele.client.Client;

import java.util.List;

public class VueGraphique implements Affichage {

    
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
}
