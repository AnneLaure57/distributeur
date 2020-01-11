package ingredient;

public interface methodIngredient {
	
	public boolean estVide();
    public abstract void consommer(int nbQuantite);
	public abstract void recharger(int nbQuantite);
	public abstract void afficherQuantite();
	public abstract int getQuantite();

}
