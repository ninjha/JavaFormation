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
		 
		 Client c2 = new Client();
		 c2.setNom("TOTO");
		 c2.setPrenom("Albert");
		 c2.setPassword("rerreee");
		 c2.setEmail("xdxdto@hhkh.fr");
		 
		 Compte c11 = new Compte("courant", 10000f, 0.01f);
		 Compte c12 = new Compte("Epargne", 5000f, 0.01f);
		 c1.getComptes().add(c11);
		 c1.getComptes().add(c12);
		 
		 Compte c21 = new Compte("courant", 15000f, 0.01f);
		 Compte c22 = new Compte("Epargne", 4000f, 0.01f);
		 c2.getComptes().add(c21);
		 c2.getComptes().add(c22);
		 
		 ClientMetier.getClients().add(c1);
		 ClientMetier.getClients().add(c2);
		 
		 System.out.println("soldes des comptes avant bouclages");
		 System.out.println("----------------------------------");
		 ClientMetier.afficherSoldes();
		 System.out.println("----------------------------------");
		 
		 
		 //Instruction de test pour les virement interne sur plusieurs compte
		 System.out.println("----------------------------------");
		 System.out.println("Virement de " + c1.getNom() + " " + c1.getPrenom() + " => " + c2.getNom() + " " + c2.getPrenom());
		 System.out.println("----------------------------------");
		 ClientMetier.virementExterne(c1, c2, 1000);
		 for (Client client : ClientMetier.getClients()) {
			System.out.println(client.getNom()+ " " + client.getPrenom());
			System.out.println("-------------");
			System.out.println("Epargne => courant 299€ : "  + ClientMetier.virementInterne(client, "courant", "epargne" ,159f));
			 System.out.println("----------------------------------");
			for(Compte compte : client.getComptes())
				System.out.println(" -> compte '" + compte.getTitre() + "' : " + compte.getSolde());
			System.out.println("----------------------------------");
		 }
		 
		 
	}

}
