package ingredient;

public class sucre implements methodIngredient{

	private String nom = "Sucre";
	private int quantite;
	private int stock=1000;
	public static int prix = 3;
	
	public sucre(int quantite) {
		super();
		this.quantite = quantite;
	}
	
	public sucre() {
		super();
		this.quantite = 10;
	}
	
	public boolean estVide(){
		System.out.println("Plus de quantit� de : "+nom);
		return quantite==0;	
	}
	
	@Override
	public void consommer(int nbQuantite) {
		if (quantite > 0) {
			this.quantite -= nbQuantite;
		}else {
			estVide();
		}
	}
	
	@Override
	public void recharger(int nbQuantite) {
		if(this.quantite + nbQuantite > stock)
		{
			this.quantite = stock;
			System.out.println("Limite de stock atteinte (Max 1000)");
			return;
		}
		this.quantite += nbQuantite;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	@Override
	public int getQuantite() {
		return this.quantite;
	}
	
	public void afficherQuantite() {
		System.out.println("Il reste "+quantite+" de"+nom);
	}

}
