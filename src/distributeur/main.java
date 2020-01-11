package distributeur;


import java.util.Scanner;

import controller.machine;

public class main {
	
	public static void main(String[] args) {
		
		machine mac = new machine();
			boolean exit = false;
			
			Scanner input = new Scanner(System.in);
			
			while(!exit)
			{	
				//TODO faire une fonction menu
				System.out.println("Bienvenue ! Que voulez-vous faire ?\n");
				System.out.println("----------------------------------------------");
				System.out.println("1- Acheter une boisson");
				System.out.println("2- Ajouter une boisson");
				System.out.println("3- Modifier une boisson");
				System.out.println("4- Supprimer une boisson");
				System.out.println("5- Ajouter une quantit� pour un ingr�dient");
				System.out.println("6- V�rifier le stock");
				System.out.println("7- Quitter");
				System.out.println("----------------------------------------------");
				try
				{
					int choice = input.nextInt();
					System.out.println("je suis la !!!");
					switch(choice)
					{
						case 1:
							System.out.println("je suis la");
							acheter(mac);
							break;
						case 2:
							ajouterBoiss(mac);
							break;
						case 3:
							modifier(mac);
							break;
						case 4:
							supprimer(mac);
							break;
						case 5:
							ajouterIngr(mac);
							break;
						case 6:
							getStock(mac);
							break;
						case 7:
							exit = true;
							break;
						default :
							System.out.println("Entr�e invalide");
					}
				}
				catch(Exception e)
				{
					System.out.println("je suis ici");
					System.out.println("Entr�e invalide, veuillez recommencer");
					input.next();
				}
				
			}
			input.close();
		}
		
		public static void acheter(machine mach)
		{
			if(!mach.boissonCree())
			{
				System.out.println("Il n'y a pas de boissons � acheter.");
				return;
			}
			
			System.out.println();
			System.out.println("Quelle boisson voulez vous acheter ?");
			mach.afficherBoissons();
			System.out.println("0 : Annuler");
			
			int rendu = 0;
			try
			{
				Scanner input = new Scanner(System.in);
				int choice = input.nextInt();
				
				if(choice == 0)
					return;
				
				if(choice-1 > mach.getNbBoiss() || choice-1 < 0)
				{
					System.out.println("Entrez le num�ro d'une boisson ou 0 pour quitter");
					acheter(mach);
					return;
				}
				
				System.out.println("Entrez une quantit� de sucre comprise entre 0 et 5 :");
				int qttSucre = input.nextInt();
				
				if(qttSucre < 0 || qttSucre > 5)
				{
					System.out.println("Entrez entre 0 et 5 sucres");
					acheter(mach);
					return;
				}
				mach.ajouterIngredient("Sucre", qttSucre, choice-1);
				
				if(!mach.afficherPrix(choice-1))
				{
					acheter(mach);
					return;
				}
				
				System.out.println("Ins�rez votre argent :");
				System.out.println("(Attention cette machine ne rend pas les centimes)");
				int argent = (int) input.nextFloat();
				rendu = argent;
				if(argent < 0)
					System.out.println("Monaie non reconnue");
				
				rendu = mach.acheterBoisson(choice-1, argent);
				
				System.out.println("Retour de "+rendu+"�");
			}
			catch(Exception e)
			{
				System.out.println("Entr�e invalide, veuillez recommencer");
				System.out.println("Retour de "+rendu+"�");
			}
			
		}
		
		public static void ajouterBoiss(machine mach)
		{
			System.out.println();
			
			if(!mach.boissonLibre())
			{
				System.out.println("Aucun emplacement de boisson disponible");
				return;
			}
			System.out.println("Entrez un nom pour votre boisson :");
			Scanner input = new Scanner(System.in);
			
			try
			{
				String nom = input.next();
				System.out.println("Entrez la quantit� de caf� :");
				int qttCafe = input.nextInt();
				System.out.println("Entrez la quantit� de chocolat :");
				int qttChoc = input.nextInt();
				System.out.println("Entrez la quantit� de lait :");
				int qttLait = input.nextInt();
				
				int qttLiquide = qttCafe + qttChoc + qttLait;
				if(qttLiquide < 1 || qttLiquide > 10)
				{
					System.out.println("entrez entre 1 et 10 liquides");
					System.out.println();
					return;
				}
				
				mach.ajouterBoisson(nom, qttCafe, qttChoc, qttLait);
				System.out.println("valeur : "+qttLiquide);
				System.out.println("vous avez cr�� la boisson : "+nom);
				System.out.println();
			}
			catch(Exception e)
			{
				System.out.println("Entr�e invalide, veuillez recommencer");
				input.next();
			}
			return;
		}
		
		public static void modifier(machine mach)
		{
			if(!mach.boissonCree())
			{
				System.out.println("Il n'y a pas de boissons � modifier.");
				return;
			}
			System.out.println("Choisissez la boisson � modifier :");
			Scanner input = new Scanner(System.in);
			mach.afficherBoissons();
			System.out.println("0 : Annuler");
			try
			{
				int choice = input.nextInt();
				
				if(choice == 0)
					return;
				
				System.out.println("Entrez la nouvelle quantit� de cafe souhait� :");
				int qttCafe = input.nextInt();
				System.out.println("Entrez la nouvelle quantit� de chocolat souhait� :");
				int qttChoc = input.nextInt();
				System.out.println("Entrez la nouvelle quantit� de lait souhait� :");
				int qttLait = input.nextInt();
				
				int qttLiquide = qttCafe + qttChoc + qttLait;
				System.out.println("valeur : "+qttLiquide);
				if(qttLiquide < 1 || qttLiquide > 10)
				{
					System.out.println("entrez entre 1 et 10 liquides");
					System.out.println();
					return;
				}
				System.out.println("vous avez modifi� la boisson.");
				mach.modifierBoisson(choice-1, qttCafe, qttChoc, qttLait);
					
			}
			catch(Exception e)
			{
				System.out.println("Entr�e invalide, veuillez recommencer");
			}
		}
		
		public static void supprimer(machine mach)
		{
			if(!mach.boissonCree())
			{
				System.out.println("Il n'y a pas de boissons � supprimer.");
				return;
			}
			System.out.println();
			
			System.out.println("Quelle boisson voulez vous supprimer?");
			mach.afficherBoissons();
			System.out.println("0 : Annuler");
			
			try
			{
				Scanner input = new Scanner(System.in);
				int choice = input.nextInt();
				
				if(choice == 0)
					return;
				if(!mach.supprimerBoisson(choice-1))
				{
					supprimer(mach);
					return;
				}
				
				System.out.println();
			}
			catch(Exception e)
			{
				System.out.println("Entr�e invalide, veuillez recommencer");
			}
			
		}
		
		public static void ajouterIngr(machine mach)
		{
			System.out.println();
			
			System.out.println("Quel ingr�dient voulez vous ajouter ?");
			System.out.println("1 : Cafe");
			System.out.println("2 : Chocolat");
			System.out.println("3 : Lait");
			System.out.println("4 : Sucre");
			System.out.println("0 : Annuler");
			
			Scanner input = new Scanner(System.in);
			
			try 
			{
				int choice = input.nextInt();
				
				
				int qtt;
				System.out.println("quel quantit� de caf� voulez vous ajouter ?");
				qtt = input.nextInt();
				switch(choice)
				{
					case 0:
						return;
					case 1:
						mach.ajouterStock("Cafe", qtt);break;
					case 2:
						mach.ajouterStock("Chocolat", qtt);break;
					case 3:
						mach.ajouterStock("Lait", qtt);break;
					case 4:
						mach.ajouterStock("Sucre", qtt);break;
					default :
						System.out.println("Entr�e invalide");
						ajouterIngr(mach);
						break;
				}
				
			}
			catch(Exception e)
			{
				System.out.println("Entr�e invalide, veuillez recommencer");
				input.next();
			}
		}
		
		public static void getStock(machine mach)
		{
			System.out.println();
			mach.verifierStock();
			System.out.println();
		}

}
