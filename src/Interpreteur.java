
public class Interpreteur {
	
	private String[] arguments; 
	
	public Interpreteur(String[] s) {
		int indice=0;
		
		for(indice=0;indice<s.length;indice++) {
			this.arguments[indice] = s[indice];
		}
			
		}
		public boolean interprete() {
			
			switch(this.arguments[0]) {
			case "afficher" : System.out.println("lister");
								return false;	
			case "ajouter" : System.out.println("ajouter");
							return false;
			case "modifier" : System.out.println("modifier");
							return false;
							
			case "quitter" : return true;
			
			
		}
			return false;
		
	}

}
