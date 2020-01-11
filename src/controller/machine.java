package controller;

import ingredient.cafe;
import ingredient.chocolat;
import ingredient.lait;
import ingredient.sucre;
import ingredient.methodIngredient;

public class machine {
	
	private methodIngredient[] listIngredients;
	private boisson[] boissons;
	
	public machine()
	{
		this.listIngredients = new methodIngredient[4];
		this.listIngredients[0] = new cafe();
		this.listIngredients[1] = new chocolat();
		this.listIngredients[2] = new lait();
		this.listIngredients[3] = new sucre();
		
		this.boissons = new boisson[5];
	}
	
	public boolean boissonLibre()
	{
		for(int i = 0; i < this.boissons.length; i++)
		{
			if(this.boissons[i] == null)
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean boissonCree()
	{
		for(int i = 0; i < this.boissons.length; i++)
		{
			if(this.boissons[i] != null)
			{
				return true;
			}
		}
		return false;
	}
	
	public int acheterBoisson(int boiss, int argent)
	{
		//véririfie que le solde est suffisant
		if(this.boissons[boiss].getPrix() > argent)
		{
			System.out.println("Vous n'avez pas assez d'argent");
			return argent;
		}
		
		if(this.boissons[boiss].getqteCafe() <= this.listIngredients[0].getQuantite())
		{
			if(this.boissons[boiss].getqteChocolat() <= this.listIngredients[1].getQuantite())
			{
				if(this.boissons[boiss].getqteLait() <= this.listIngredients[2].getQuantite())
				{
						if(this.boissons[boiss].getqteSucre() > this.listIngredients[3].getQuantite())
						{
							System.out.println("Il n'y a pas assez de sucre, veuillez remplir la machine");
							return argent;
						}
					}
					
				else
				{
					System.out.println("Il n'y a pas assez de lait, veuillez remplir la machine");
					return argent;
				}
			}
			else
			{
				System.out.println("Il n'y a pas assez de chocolat, veuillez remplir la machine");
				return argent;
			}
		}
		else
		{
			System.out.println("Il n'y a pas assez de café, veuillez remplir la machine");
			return argent;
		}
		
		this.listIngredients[0].consommer(this.boissons[boiss].getqteCafe());
		this.listIngredients[1].consommer(this.boissons[boiss].getqteChocolat());
		this.listIngredients[2].consommer(this.boissons[boiss].getqteLait());
		this.listIngredients[3].consommer(this.boissons[boiss].getqteSucre());
		
		System.out.println("Vous avez acheté la boisson "+boissons[boiss].getNom()+" !" );
		return argent-this.boissons[boiss].getPrix();
	}
	
	public void ajouterBoisson(String nom, int nbCafe, int nbChocolat, int nbLait, int nbSucre)
	{
		//vérification que le nom n'est pas déjà pris
		for(int i = 0; i < this.boissons.length; i++)
		{
			if(this.boissons[i] != null)
			{
				if(this.boissons[i].getNom() == nom)
				{
					System.out.println("Echec de l'ajout, nom déja pris");
					return;
				}
			}
		}
		
		//création de la nouvelle boisson
		boisson boisson = new boisson(nom, nbCafe, nbChocolat, nbSucre, nbLait);
		for(int i = 0; i < this.boissons.length; i++)
		{
			if(this.boissons[i] == null)
			{
				this.boissons[i] = boisson;
				System.out.println("Votre boisson a bien été ajoutée.");
				return;
			}
		}
		//Si toute les emplacements étaient pris affichage d'une erreur
		System.out.println("Echec de l'ajout, nombre maximum de boissons atteint.");
	}
	
	public void ajouterBoisson(String nom, int nbCafe, int nbChocolat, int nbLait)
	{
		//vérification que le nom n'est pas déjà pris
		for(int i = 0; i < this.boissons.length; i++)
		{
			if(this.boissons[i] != null)
			{
				if(this.boissons[i].getNom() == nom)
				{
					System.out.println("Echec de l'ajout, nom déja pris");
					return;
				}
			}
		}
		
		//création de la nouvelle boisson
		boisson boisson = new boisson(nom, nbCafe, nbChocolat, nbLait);
		for(int i = 0; i < this.boissons.length; i++)
		{
			if(this.boissons[i] == null)
			{
				this.boissons[i] = boisson;
				System.out.println("Votre boisson a bien été ajoutée.");
				return;
			}
		}
		//Si toute les emplacements étaient pris affichage d'une erreur
		System.out.println("Echec de l'ajout, nombre maximum de boissons atteint.");
	}
	
	public void modifierBoisson(int boiss, int nbCafe, int nbChocolat, int nbLait)
	{
		this.boissons[boiss].setqteCafe(nbCafe);
		this.boissons[boiss].setqteChocolat(nbChocolat);
		this.boissons[boiss].setqteLait(nbLait);
		this.boissons[boiss].checkPrix();
	}
	
	public boolean supprimerBoisson(int boiss)
	{
		//Supprime une boisson si cette boisson n'est pas nulle
		if(this.boissons[boiss] != null)
			this.boissons[boiss] = null;
		else
		{
			System.out.println("Cette boisson n'existe pas");
			return false;
		}
		//vérifie qu'il n'y a pas de "trou" dans la liste des boissons
		for(int i = this.boissons.length-1; i > 0; i--)
		{
			//Si une boisson n'est pas nulle
			if(this.boissons[i] != null)
			{
				//et qu'un boisson avant elle est nulle
				if(this.boissons[i-1] == null)
				{
					// on intervertis les deux boissons 
					boisson backup = this.boissons[i];
					this.boissons[i] = this.boissons[i-1];
					this.boissons[i-1] = backup;
				}
			}
		}
		System.out.println("La boisson a bien été supprimée");
		return true;
	}
	
	public void ajouterIngredient(String ingr, int quantite, int boiss)
	{
		//récupère la quantité d'ingrédient disponible
		switch(ingr)
		{
			case "Cafe":
				this.boissons[boiss].ajoutCafe(quantite);
				break;
			case "Chocolat":
				this.boissons[boiss].ajoutChocolat(quantite);
				break;
			case "Lait":
				this.boissons[boiss].ajoutLait(quantite);
				break;
			case "Sucre":
				this.boissons[boiss].ajoutSucre(quantite);
				break;
		}
	}
	
	public void ajouterStock(String ingr, int quantite)
	{
		switch(ingr)
		{
			case "Cafe":
				this.listIngredients[0].recharger(quantite);
				break;
			case "Chocolat":
				this.listIngredients[1].recharger(quantite);
				break;
			case "Lait":
				this.listIngredients[2].recharger(quantite);
				break;
			case "Sucre":
				this.listIngredients[3].recharger(quantite);
				break;
			case "The":
				this.listIngredients[4].recharger(quantite);
				break;
		}
	}
	
	public void verifierStock()
	{
		for(int i = 0; i < this.listIngredients.length; i++)
		{
			this.listIngredients[i].afficherQuantite();
		}
	}
	
	public void afficherBoissons()
	{
		for(int i = 0; i < this.boissons.length; i++)
		{
			if(this.boissons[i] != null)
			{
				System.out.println(i+1+" : "+boissons[i].getNom());
			}
		}
	}
	
	public boolean afficherPrix(int boiss)
	{
		if(boissons[boiss] != null)
		{
			int prix = boissons[boiss].getPrix();
			System.out.println("Votre boisson coute : " + prix);
			return true;
		}
		else
		{
			System.out.println("Cette boisson n'existe pas");
			return false;
		}
	}
	
	public int getStockCafe()
	{
		int stockIngr = this.listIngredients[0].getQuantite();
		return stockIngr;
	}
	public int getStockChocolat()
	{
		int stockIngr = this.listIngredients[1].getQuantite();
		return stockIngr;
	}
	public int getStockLait()
	{
		int stockIngr = this.listIngredients[2].getQuantite();
		return stockIngr;
	}
	public int getStockSucre()
	{
		int stockIngr = this.listIngredients[3].getQuantite();
		return stockIngr;
	}
	public int getStockThe()
	{
		int stockIngr = this.listIngredients[4].getQuantite();
		return stockIngr;
	}
	
	
	
	public int getNbBoiss()
	{
		return this.boissons.length;
	}
}
