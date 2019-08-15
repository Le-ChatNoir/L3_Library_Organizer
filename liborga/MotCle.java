package liborga;

import java.util.Set;
import java.util.HashSet;

/**
 * Représente un mot-clé associé à un livre.
 */
public class MotCle {

	// Attributs statiques -------------------------------------------------

	/**
	 * Ensemble de tous les mots clés existants.
	 */
	@SuppressWarnings("unused")
	private static final Set<MotCle> MOTSCLES = new HashSet<>();

	// Attributs d'instances -----------------------------------------------

	/**
	 * Mot clé (public et non modifiable).
	 */
	public final String MOT;

	// Constructeur --------------------------------------------------------

	/**
	 * Constructeur unique et privé.
	 * 
	 * @param mot Mot clé
	 */
	private MotCle(String mot) {
		this.MOT = mot;
	}

	// Accesseurs et usines à objets ---------------------------------------

	/**
	 * Si possible, renvoie une instance de MotCle correspondant à une chaîne de
	 * caractères.
	 * <p>
	 * 
	 * Le résultat est null si le paramètre est null ou s'il correspond à la chaîne
	 * vide.
	 * <p>
	 * 
	 * Si les informations fournies permettent de désigner une instance existante,
	 * c'est cette instance qui est renvoyée en résultat. Sinon :
	 * <ul>
	 * <li>soit la création n'est pas demandée et dans ce cas null est renvoyé en
	 * résultat</li>
	 * <li>soit la création est demandée et dans ce cas une nouvelle instance de
	 * MotCle est créée, rajoutée à la liste des mots clés existants et renvoyée en
	 * résultat</li>
	 * </ul>
	 * 
	 * @param mot     Mot clé à créer ou à récupérer
	 * @param getOnly indique si on ne veut que récupérer une instance existante
	 *                (pas de création dans ce cas)
	 * @return selon les cas : null, ou une instance existante, ou une nouvelle
	 *         instance
	 */
	private static MotCle getOrCreate(String mot, boolean getOnly) {
		// TODO à compléter...
		if (mot == null || mot.contentEquals(""))
			return null; //
		MotCle nouvMot = new MotCle(mot);
		for (MotCle m : MOTSCLES)
			if (m.equals(nouvMot))
				return m;
		if (getOnly)
			return null;
		MOTSCLES.add(nouvMot);
		return nouvMot; // <- TODO résultat à adapter
	}

	/**
	 * Si possible, renvoie une instance de MotCle correspondant à une chaîne de
	 * caractères.
	 * <p>
	 * 
	 * Le résultat est null si le paramètre est null ou s'il correspond à la chaîne
	 * vide.
	 * <p>
	 * 
	 * Si les informations fournies permettent de désigner une instance existante,
	 * c'est cette instance qui est renvoyée en résultat. Sinon, une nouvelle
	 * instance de MotCle est créée, rajoutée à la liste des mots clés existants et
	 * renvoyée en résultat
	 * 
	 * @param mot Mot clé à créer ou à récupérer
	 * @return selon les cas : null, ou une instance existante, ou une nouvelle
	 *         instance
	 */
	public static MotCle getOrCreate(String mot) {
		return getOrCreate(mot, false);
	}

	/**
	 * Si possible, renvoie une instance de MotCle correspondant à une chaîne de
	 * caractères.
	 * <p>
	 * 
	 * Le résultat est null si le paramètre est null ou s'il correspond à la chaîne
	 * vide.
	 * <p>
	 * 
	 * Si les informations fournies permettent de désigner une instance existante,
	 * c'est cette instance qui est renvoyée en résultat. Sinon, null est renvoyé en
	 * résultat.
	 * 
	 * @param mot Mot clé à récupérer
	 * @return selon les cas : null, ou une instance existante
	 */
	public static MotCle get(String mot) {
		return getOrCreate(mot, true);
	}

	// toString et equals --------------------------------------------------

	@Override
	public String toString() {
		// TODO à compléter...
		return this.MOT.toUpperCase(); // <- TODO résultat à adapter
	}

	@Override
	public boolean equals(Object obj) {
		// TODO à compléter...
		MotCle mc;
		if(!(obj instanceof MotCle)) return false;
		mc = (MotCle) obj;
		boolean result = (this.MOT.equalsIgnoreCase(mc.MOT));
		return result; // <- TODO résultat à adapter
	}



	// Requêtes ------------------------------------------------------------

	/**
	 * Sélection de mots clés à partir d'un mot.
	 * 
	 * @param motClePartielOuComplet mot clé ou partie du mot clé à rechercher
	 * @return ensemble des mots clés qui correspondent au paramètre fourni,
	 *         indépendamment de la casse
	 */
	public static Set<MotCle> select(String motClePartielOuComplet) {
		// TODO à compléter...
		String sub = motClePartielOuComplet.toLowerCase();
		Set<MotCle> motSet = new HashSet<>();

		for(MotCle m : MOTSCLES){
			if(m.MOT.toLowerCase().contains(sub)){
				motSet.add(m);
			}
		}
		return motSet; // <- TODO résultat à adapter
	}
}
