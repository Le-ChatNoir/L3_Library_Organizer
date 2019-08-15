package liborga;

import java.util.Set;
import java.util.HashSet;

/**
 * Représente un auteur caractérisé par un nom et un prénom.
 */
public class Auteur {

	// Attributs statiques -------------------------------------------------

	/**
	 * Ensemble de tous les auteurs existants.
	 */
	@SuppressWarnings("unused")
	private static final Set<Auteur> AUTEURS = new HashSet<>();

	// Attributs d'instances -----------------------------------------------

	/**
	 * Nom de l'auteur (public et non modifiable).
	 */
	public final String NOM;

	/**
	 * Prénom de l'auteur (public et non modifiable).
	 */
	public final String PRENOM;

	// Constructeur --------------------------------------------------------

	/**
	 * Constructeur unique et privé.
	 * 
	 * @param nom    Nom de l'auteur.
	 * @param prenom Prénom de l'auteur.
	 */
	private Auteur(String nom, String prenom) {
		this.NOM = nom;
		this.PRENOM = prenom;
	}

	// Accesseurs et usines à objets ---------------------------------------

	/**
	 * Si possible, renvoie une instance de Auteur correspondant à un nom et à un
	 * prénom.
	 * <p>
	 * 
	 * Le résultat est null si une de ces deux informations est null. Le résultat
	 * est également null si le nom de l'auteur correspond à la chaîne vide.
	 * <p>
	 * 
	 * Si les informations fournies permettent de désigner une instance existante,
	 * c'est cette instance qui est renvoyée en résultat. Sinon :
	 * <ul>
	 * <li>soit la création n'est pas demandée et dans ce cas null est renvoyé en
	 * résultat</li>
	 * <li>soit la création est demandée et dans ce cas une nouvelle instance de
	 * Auteur est créée, rajoutée à la liste des auteurs existants et renvoyée en
	 * résultat</li>
	 * </ul>
	 * 
	 * @param nom     Nom de l'auteur à créer ou à récupérer
	 * @param prenom  Prénom de l'auteur à créer ou à récupérer
	 * @param getOnly indique si on ne veut que récupérer une instance existante
	 *                (pas de création dans ce cas)
	 * @return selon les cas : null, ou une instance existante, ou une nouvelle
	 *         instance
	 */
	private static Auteur getOrCreate(String nom, String prenom, boolean getOnly) {
		// TODO à compléter...
		if (nom == null || nom.contentEquals("") || prenom == null)
			return null; //
		Auteur nouvelAuteur = new Auteur(nom, prenom);
		for (Auteur a : AUTEURS)
			if (a.equals(nouvelAuteur))
				return a;
		if (getOnly)
			return null;
		AUTEURS.add(nouvelAuteur);
		return nouvelAuteur;
	}

	/**
	 * Si possible, renvoie une instance de Auteur correspondant à un nom et à un
	 * prénom.
	 * <p>
	 * 
	 * Le résultat est null si une de ces deux informations est null. Le résultat
	 * est également null si le nom de l'auteur correspond à la chaîne vide.
	 * <p>
	 * 
	 * Si les informations fournies permettent de désigner une instance existante,
	 * c'est cette instance qui est renvoyée en résultat. Sinon, une nouvelle
	 * instance de Auteur est créée, rajoutée à la liste des auteurs existants et
	 * renvoyée en résultat.
	 * 
	 * @param nom    Nom de l'auteur à créer ou à récupérer
	 * @param prenom Prénom de l'auteur à créer ou à récupérer
	 * @return selon les cas : null, ou une instance existante, ou une nouvelle
	 *         instance
	 */
	public static Auteur getOrCreate(String nom, String prenom) {
		return getOrCreate(nom, prenom, false);
	}

	/**
	 * Si possible, renvoie une instance de Auteur correspondant à un nom et à un
	 * prénom.
	 * <p>
	 * 
	 * Le résultat est null si une de ces deux informations est null. Le résultat
	 * est également null si le nom de l'auteur correspond à la chaîne vide.
	 * <p>
	 * 
	 * Si les informations fournies permettent de désigner une instance existante,
	 * c'est cette instance qui est renvoyée en résultat. Sinon, null est renvoyé en
	 * résultat.
	 * 
	 * @param nom    Nom de l'auteur à récupérer
	 * @param prenom Prénom de l'auteur à récupérer
	 * @return selon les cas : null, ou une instance existante
	 */
	public static Auteur get(String nom, String prenom) {
		return getOrCreate(nom, prenom, true);
	}

	// toString et equals --------------------------------------------------

	@Override
	public String toString() {
		// TODO à compléter...
		String prenomAut, nomAut;
		nomAut = this.NOM.toUpperCase();
		if(this.PRENOM.length()>0){
			prenomAut = " " + String.valueOf(this.PRENOM.charAt(0)).toUpperCase() + (this.PRENOM.substring(1)).toLowerCase();
		} else {
			prenomAut = "";
		}
		return nomAut + prenomAut; // <- TODO résultat à adapter
	}

	@Override
	public boolean equals(Object obj) {
		// TODO à compléter...
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Auteur))
			return false;
		Auteur subject = (Auteur) obj;
		boolean result = (this.NOM.equalsIgnoreCase(subject.NOM) && this.PRENOM.equalsIgnoreCase(subject.PRENOM));
		return result; // <- TODO résultat à adapter
	}



	// Requêtes ------------------------------------------------------------

	/**
	 * Sélection d'auteurs à partir d'un nom et d'un prénom.
	 * 
	 * @param nomDAuteurPartielOuComplet    nom ou partie du nom à rechercher
	 * @param prenomDAuteurPartielOuComplet prénom ou partie du prénom de l'auteur à
	 *                                      rechercher
	 * @return ensemble des auteurs dont le nom et le prénom correspondent aux
	 *         paramètres fournis, indépendamment de la casse
	 */
	public static Set<Auteur> select(String nomDAuteurPartielOuComplet, String prenomDAuteurPartielOuComplet) {
		// TODO à compléter...
		String subNom = nomDAuteurPartielOuComplet.toLowerCase();
		String subPrenom = prenomDAuteurPartielOuComplet.toLowerCase();
		Set<Auteur> auteursSet = new HashSet<>();

		for(Auteur a : AUTEURS){
			if(a.NOM.toLowerCase().contains(subNom) && a.PRENOM.toLowerCase().contains(subPrenom)){
				auteursSet.add(a);
			}
		}
		return auteursSet;
	}
}
