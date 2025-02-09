package fr.doranco.banque.metier;

import java.util.HashSet;
import java.util.Set;

import fr.doranco.banque.entity.Client;
import fr.doranco.banque.entity.Compte;

public abstract class ClientMetier {

	private static Set<Client> clients = new HashSet<>();
	
	/**
	 * Permet de boucler tous les comptes d'un clent.
	 * @throws Exception : Si l'opération ne se déroule pas comme prévu.
	 * @since 2025-01-10
	 * @author Salon.
	 * @see CompteMetier#bouclerComptes();
	 */
	
	public static void bouclerComptes() {
		for (Client client : clients) {
			for (Compte compte : client.getComptes()) {
				try {
					CompteMetier.boucler(compte);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void afficherSoldes() {
		for (Client client : ClientMetier.getClients()) {
			System.out.println(client.getNom() + " " + client.getPrenom());
			for (Compte compte : client.getComptes()) {
				System.out.println("  -> Compte '" + compte.getTitre() + "' : " + compte.getSolde());
			}
		}
	}

	public static Set<Client> getClients() {
		return clients;
	}
	
	public static boolean virementExterne(Client client1, Client client2, float montant) {
		if(montant > 6000) {
			System.out.println("Le montant d'un virement ne peut exceder 6000€");
			return false;
		}
		
		for(Compte compteCl1 : client1.getComptes()) {
			if(compteCl1.getTitre().equals("courant")) {
				System.out.println("Compte '" + compteCl1.getTitre() + "' de " + client1.getNom() + " " + client1.getPrenom() + " trouvé");
				for(Compte compteCl2 : client2.getComptes()) {
					if(compteCl2.getTitre().equals("courant")) {
						System.out.println("Compte '" + compteCl2.getTitre() + "' de " + client2.getNom() + " " + client2.getPrenom() + " trouvé");
						compteCl1.setSolde(compteCl1.getSolde() - montant); 
						compteCl2.setSolde(compteCl2.getSolde() + montant);
						return true;
					}
				}
			}
		}
		System.out.println("Aucun compte trouvé");
		return false;
	}
	
	public static boolean virementInterne(Client client, String source, String destination, float montant) {
		
		if(montant <= 0) {
			System.out.println("Le montant ne peut être égal ou inférieur à 0");
			return false;
		}
		
		if(!source.equalsIgnoreCase("epargne") && !source.equalsIgnoreCase("courant")){
			System.out.println("Le titre du compte source est incorrecte.");
			return false;
		}  
		
		if( !destination.equalsIgnoreCase("epargne") && !destination.equalsIgnoreCase("courant")){
			System.out.println("Le titre du compte destination est incorrecte.");
			return false;	
		}
		
		Compte compte1 = null;
		Compte compte2 = null;
		
		for(Compte compte : client.getComptes()) {
			if(compte.getTitre().equalsIgnoreCase(source)) {
				compte1 = compte;
			}else if(compte.getTitre().equalsIgnoreCase(destination)) {
				compte2 = compte;
			}			
		}
		
		if (compte1 == null || compte2 == null) {
	        System.out.println("Un ou plusieurs comptes n'ont pas été trouvés.");
	        return false;
	    }
		
		
		if(compte1.getSolde() < montant) {
			System.out.println(",Solde insuffisant pour effectuer le virement.");
			return false;
		}else {
			compte2.setSolde(compte2.getSolde() + montant);
			compte1.setSolde(compte1.getSolde() - montant);
			
			/* 
				La logique voudrait que l'on vérifie le plafond du compte épargne au moment où l'on récupère les informations du compte,
			 	puis que l'on revérifie après les opérations pour s'assurer de la conformité des règles métier. 
			 	Ce qui signifierai de placer 2fois la même conditions. On pourrait aussi créer une classe spécique qui gererait les conditions
			 	pour éffectuer les opération.
			 	Cependant, que le plafond soit atteint avant ou après les opérations n'a pas d'importance : 
			 	dans tous les cas, dépasser la limite autorisée rend l'opération invalide.
			 	Par conséquent, nous plaçons la vérification du plafond juste après les opérations pour simplifier la logique.
			 	Exemple 1 : Epargne = 24000, montant = 1300 => Epargne après opération : 24000 + 1300 = 25300 => Retourne false (plafond dépassé)
			 	Exemple 2 : Epargne = 25000, montant = 300  => Epargne après opération : 25000 + 300 = 25300 => Retourne false (plafond dépassé)
			 	Les deux cas illustrent que la vérification après l'opération capture bien tous les scénarios. 
			*/

			if(compte1.getTitre().equals("epargne") && compte2.getSolde() >= 25000) {
				System.out.println("Le plafond du compte destination est atteint");
				return false;
			}
			 
			//Appeller une méthode qui gère les requêtes SQL ici
			return true;
		}	
	}
}
