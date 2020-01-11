package controller;

import ingredient.cafe;
import ingredient.chocolat;
import ingredient.lait;
import ingredient.sucre;

public class boisson {
	
	private String nom;
	private int prix;
	
	private int qteCafe;
	private int qteChocolat;
	private int qteSucre;
	private int qteLait;
	
	public boisson(String nom, int qteCafe, int qteChocolat, int qteSucre, int qteLait) {
		this.nom = nom;
		this.qteCafe = qteCafe;
		this.qteChocolat = qteChocolat;
		this.qteLait = qteLait;
		this.qteSucre = qteSucre;
		
		this.prix = cafe.prix*qteCafe + chocolat.prix*qteChocolat + sucre.prix*qteSucre + lait.prix*qteLait;
	}
	
	public boisson(String nom, int qteCafe, int qteChocolat, int qteLait) {
		this.nom = nom;
		this.qteCafe = qteCafe;
		this.qteChocolat = qteChocolat;
		this.qteLait = qteLait;
		this.qteSucre = 0;
		
		this.prix = cafe.prix*qteCafe + chocolat.prix*qteChocolat + lait.prix*qteLait;
	}
	
	public boisson(String nom) {
		this.nom = nom;
		this.prix = 0;
		this.qteCafe = 0;
		this.qteSucre = 0;
		this.qteChocolat = 0;
		this.qteLait = 0;
	}
	
	public void ajoutCafe(int quantite)
	{
		if(this.qteCafe + quantite >= 0)
		{
			this.qteCafe += quantite;
			this.prix += cafe.prix;
		}
		else
		{
			System.out.println("Impossible d'ajouter une quantité négative!!!!");
		}
	}
	
	public void ajoutSucre(int quantite)
	{
		if(this.qteSucre + quantite >= 0)
		{
			this.qteSucre += quantite;
			this.prix += sucre.prix;
		}
		else
		{
			System.out.println("Impossible d'ajouter une quantité négative!!!!");
		}
	}
	
	public void ajoutChocolat(int quantite)
	{
		if(this.qteChocolat + quantite >= 0)
		{
			this.qteChocolat += quantite;
			this.prix += chocolat.prix;
		}
		else
		{
			System.out.println("Impossible d'ajouter une quantité négative!!!!");
		}
	}
	
	public void ajoutLait(int quantite)
	{
		if(this.qteLait + quantite >= 0)
		{
			this.qteLait += quantite;
			this.prix += lait.prix;
		}
		else
		{
			System.out.println("Impossible d'ajouter une quantité négative!!!!");
		}
	}

	
	public int getPrix()
	{
		return prix;
	}
	
	public String getNom() {
		return nom;
	}

	public int getqteCafe() {
		return qteCafe;
	}

	public void setqteCafe(int qteCafe) {
		this.qteCafe = qteCafe;
	}

	public int getqteSucre() {
		return qteSucre;
	}

	public void setqteSucre(int qteSucre) {
		this.qteSucre = qteSucre;
	}

	public int getqteChocolat() {
		return qteChocolat;
	}

	public void setqteChocolat(int qteChocolat) {
		this.qteChocolat = qteChocolat;
	}

	public int getqteLait() {
		return qteLait;
	}

	public void setqteLait(int qteLait) {
		this.qteLait = qteLait;
	}

	public void checkPrix() {
		this.prix = cafe.prix*qteCafe + chocolat.prix*qteChocolat + lait.prix*qteLait + sucre.prix*qteSucre;
	}

}
