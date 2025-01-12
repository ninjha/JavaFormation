package fr.doranco.banque.entity;

import java.util.HashSet;
import java.util.Set;

public class Client {
	
	private String nom;
	private String prenom;
	private String email;
	private String password;
	private Set<Compte>comptes = new HashSet<Compte>();
	
	public Client() {

	}

	public Client(String nom, String prenom, String email, String password, Set<Compte> comptes) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.comptes = comptes;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Set<Compte> comptes) {
		this.comptes = comptes;
	}

	@Override
	public String toString() {
		return "Client [nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password
				+ ", comptes=" + comptes + "]";
	}
		
}
