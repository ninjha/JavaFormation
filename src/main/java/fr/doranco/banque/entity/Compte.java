package fr.doranco.banque.entity;

public class Compte {
	
	/**
	 * Le solde du compte à l'instant actuel
	 */
	private String titre;
	
	/**
	 * 
	 */
	private float solde;
	
	/**
	 * Le taux applicable pour le calcul des intérêts annuels d'un compte bancaire
	 */
	private float taux;
	
	public Compte(){
		
	}

	public Compte(String titre, float solde, float taux) {
		super();
		this.titre = titre;
		this.solde = solde;
		this.taux = taux;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}

	public float getTaux() {
		return taux;
	}

	public void setTaux(float taux) {
		this.taux = taux;
	}

	@Override
	public String toString() {
		return "Compte [titre=" + titre + ", solde=" + solde + ", taux=" + taux + "]";
	}
	
}
