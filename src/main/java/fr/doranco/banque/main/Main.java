package fr.doranco.banque.main;

import fr.doranco.banque.entity.Client;
import fr.doranco.banque.entity.Compte;
import fr.doranco.banque.metier.ClientMetier;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Client c1 = new Client();
		 c1.setNom("CAMUS");
		 c1.setPrenom("titit");
		 c1.setPassword("rere");
		 c1.setEmail("toto@hhkh.fr");
		 
		 Compte c11 = new Compte("Principal", 100f, 0.01f);
		 Compte c12 = new Compte("Epargne", 50f, 0.01f);
		 c1.getComptes().add(c11);
		 c1.getComptes().add(c12);
		 
		 ClientMetier.getClients().add(c1);
		 
		 System.out.println("soldes des comptes avant bouclages");
		 System.out.println("----------------------------------");
		 ClientMetier.afficherSoldes();
		 
		 
		 
		 for (Client client : ClientMetier.getClients()) {
			System.out.println(client.getNom()+ " " + client.getPrenom());
			for(Compte compte : client.getComptes())
				System.out.println(" -> compte '" + compte.getTitre() + "' : " + compte.getSolde());
		 }
		 System.out.println("Lancement de l'opération de bouclage en cours...");
		 ClientMetier.bouclerComptes();
//		 Client c2 = new Client();
//		 c2.getComptes();
		 
		 
	}

}
