public class Interpreteur {

	private String[] arguments;
	private String[] commandes = {"afficher","ajouter","modifier","supprimer"};
	private String[] commandes2 = {"clients","commandes"};
	private outils.BaseDonnee baseDonnee;
	
	public Interpreteur(String[] s) {
		this.arguments = new String[s.length];
		int indice = 0;

		for (indice = 0; indice < s.length; indice++) {
			this.arguments[indice] = s[indice];
		}
		this.baseDonnee = outils.BaseDonnee.getInstance();


	}

	public boolean interprete() {

		if (this.arguments[0].equals(this.commandes[0])) {
			System.out.println("afficher");
			if(this.arguments[1].equals(this.commandes2[0])) {
				System.out.println(" client");
			}else if(this.arguments[1].equals(this.commandes2[1])) {
				System.out.println(" commande");
			}
			
		} else if (this.arguments[0].equals(this.commandes[1])) {
			System.out.println("ajouter");
			if(this.arguments[1].equals(this.commandes2[0])) {
				System.out.println(" client");
			}else if(this.arguments[1].equals(this.commandes2[1])) {
				System.out.println(" commande");
			}
			
			
		} else if (this.arguments[0].equals(this.commandes[2])) {
			System.out.println("modifier");
		} else if (this.arguments[0].equals(this.commandes[3])) {
			System.out.println("supprimer");
		}else if(this.arguments[0].equals("quitter")) {
			return true;
		}
		return false;

	}

}
