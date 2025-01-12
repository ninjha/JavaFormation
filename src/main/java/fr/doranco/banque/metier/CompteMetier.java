package fr.doranco.banque.metier;

import fr.doranco.banque.entity.Compte;

public abstract class CompteMetier {
	
	/**
	 * Permet de boucler le compte donné en paramètre
	 * @param compte : le compte à boucler.
	 * @throws Exception : Si le compte est {@code NULL} ou un pliusieurs
	 * @since 2025-01-10
	 * @author Salon.
	 * @see {@link ClientMetier#boucler(compte)}
	 */
	
	public static void boucler(Compte compte) throws Exception {
		if (compte == null) {
			throw new NullPointerException("Le compte à boucler est obligatoire!");
		}
		if (compte.getTaux() < 0
				|| compte.getTitre() == null || compte.getTitre().isBlank()) {
			throw new IllegalArgumentException("un ou plusieur paramètres du compte sont incorrects");
		}
		compte.setSolde(compte.getSolde()*(1 + compte.getTaux()));
	}
}
