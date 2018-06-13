package modele.outils;

public class BaseDonnee {
	
	private BaseDonnee() {
	}
	
	/** Instance unique pré-initialisée */
    private static BaseDonnee baseDonnee = new BaseDonnee();
    
	public static BaseDonnee getInstance() {
		return baseDonnee;
	}

}
