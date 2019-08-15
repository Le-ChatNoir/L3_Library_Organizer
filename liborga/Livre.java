package liborga;

import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

/**
 * Représente un livre caractérisé par un titre, un auteur et une série
 * éventuellement vide de mots clés.
 */
public class Livre {

	// Attributs statiques -------------------------------------------------

	/**
	 * Ensemble de tous les livres existants.
	 */
	@SuppressWarnings("unused")
	private static final Set<Livre> LIVRES = new HashSet<>();

	// Attributs d'instances -----------------------------------------------

	/**
	 * Titre du livre (public et non modifiable).
	 */
	public final String TITRE;

	/**
	 * Référence de l'auteur (publique et non modifiable).
	 * <p>
	 * 
	 * La référence est par construction différente de null.
	 */
	public final Auteur AUTEUR;

	/**
	 * Ensemble éventuellement vide des mots clés du livre.
	 * <p>
	 * 
	 * L'ensemble ne peut pas être mis à jour à l'extérieur de la classe. Il est
	 * donc privé et un accesseur spécifique permet d'en récupérer une vue
	 * constante.
	 */
	private final Set<MotCle> MOTSCLES;

	// Constructeur --------------------------------------------------------

	/**
	 * Constructeur unique et privé.
	 * 
	 * @param titre  Titre du livre.
	 * @param auteur Auteur du livre.
	 */
	private Livre(String titre, Auteur auteur) {
		this.TITRE = titre;
		this.AUTEUR = auteur;
		this.MOTSCLES = new HashSet<>();
	}

	// Accesseurs et usines à objets ---------------------------------------

	/**
	 * Rend les mots clés accessibles en lecture seule.
	 * 
	 * @return vue non-modifiable de l'attribut MOTSCLES.
	 */
	public Set<MotCle> getMotsCles() {
		return Collections.unmodifiableSet(this.MOTSCLES);
	}

	/**
	 * Rajoute un mot clé au livre si possible.
	 * 
	 * @param mot Mot clé à rajouter.
	 * @return true si le mot correspond à un mot clé qui n'était pas déjà associé
	 *         au livre avant (false sinon).
	 */
	public boolean addMotCle(String mot) {
		MotCle m = MotCle.getOrCreate(mot);
		if (m == null)
			return false; // le mot n'est pas valide
		// Résultat :
		return this.MOTSCLES.add(m); // le résultat de add indique si le mot est nouveau
	}

	/**
	 * Si possible, renvoie une instance de Livre correspondant à un titre de livre,
	 * à un nom d'auteur et à un prénom d'auteur.
	 * <p>
	 * 
	 * Le résultat est null si une de ces trois informations est null. Le résultat
	 * est également null si le titre ou le nom de l'auteur correspond à la chaîne
	 * vide.
	 * <p>
	 * 
	 * Si les informations fournies permettent de désigner une instance existante,
	 * c'est cette instance qui est renvoyée en résultat. Sinon :
	 * <ul>
	 * <li>soit la création n'est pas demandée et dans ce cas null est renvoyé en
	 * résultat</li>
	 * <li>soit la création est demandée et dans ce cas une nouvelle instance de
	 * Livre est créée, rajoutée à la liste des livres existants et renvoyée en
	 * résultat</li>
	 * </ul>
	 * 
	 * @param titre        Titre du livre à créer ou à récupérer
	 * @param nomAuteur    Nom de l'auteur du livre à créer ou à récupérer
	 * @param prenomAuteur Prénom de l'auteur du livre à créer ou à récupérer
	 * @param getOnly      indique si on ne veut que récupérer une instance
	 *                     existante (pas de création dans ce cas)
	 * @return selon les cas : null, ou une instance existante, ou une nouvelle
	 *         instance
	 */
	private static Livre getOrCreate(String titre, String nomAuteur, String prenomAuteur, boolean getOnly) {
		if (titre == null || titre.contentEquals(""))
			return null; 
		Auteur a = Auteur.getOrCreate(nomAuteur, prenomAuteur);
		if(a == null)
			return null;
		Livre nouvLivre = new Livre(titre, a); 
		for (Livre l : LIVRES)
			if (l.equals(nouvLivre))
				return l;
		if (getOnly)
			return null; 
		LIVRES.add(nouvLivre); 
		return nouvLivre;
	}

	/**
	 * Si possible, renvoie une instance de Livre correspondant à un titre de livre,
	 * à un nom d'auteur et à un prénom d'auteur.
	 * <p>
	 * 
	 * Le résultat est null si une de ces trois informations est null. Le résultat
	 * est également null si le titre ou le nom de l'auteur correspond à la chaîne
	 * vide.
	 * <p>
	 * 
	 * Si les informations fournies permettent de désigner une instance existante,
	 * c'est cette instance qui est renvoyée en résultat. Sinon, une nouvelle
	 * instance de Livre est créée, rajoutée à la liste des livres existants et
	 * renvoyée en résultat.
	 * 
	 * @param titre        Titre du livre à créer ou à récupérer
	 * @param nomAuteur    Nom de l'auteur du livre à créer ou à récupérer
	 * @param prenomAuteur Prénom de l'auteur du livre à créer ou à récupérer
	 * @return selon les cas : null, ou une instance existante, ou une nouvelle
	 *         instance
	 */
	public static Livre getOrCreate(String titre, String nomAuteur, String prenomAuteur) {
		return getOrCreate(titre, nomAuteur, prenomAuteur, false);
	}

	/**
	 * Si possible, renvoie une instance de Livre correspondant à un titre de livre,
	 * à un nom d'auteur et à un prénom d'auteur.
	 * <p>
	 * 
	 * Le résultat est null si une de ces trois informations est null. Le résultat
	 * est également null si le titre ou le nom de l'auteur correspond à la chaîne
	 * vide.
	 * <p>
	 * 
	 * Si les informations fournies permettent de désigner une instance existante,
	 * c'est cette instance qui est renvoyée en résultat. Sinon, null est renvoyé en
	 * résultat.
	 * 
	 * @param titre        Titre du livre à récupérer
	 * @param nomAuteur    Nom de l'auteur du livre à récupérer
	 * @param prenomAuteur Prénom de l'auteur du livre à récupérer
	 * @return selon les cas : null, ou une instance existante
	 */
	public static Livre get(String titre, String nomAuteur, String prenomAuteur) {
		return getOrCreate(titre, nomAuteur, prenomAuteur, true);
	}

	// toString et equals --------------------------------------------------

	@Override
	public String toString() {
		// TODO à compléter...
		String nomLivre = "\"" + String.valueOf(this.TITRE.charAt(0)).toUpperCase() + this.TITRE.substring(1).toLowerCase() + "\" (" + this.AUTEUR + ")";
		return nomLivre; // <- TODO résultat à adapter
	}

	@Override
	public boolean equals(Object obj) {
		// TODO à compléter...
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Livre))
			return false;
		Livre subject = (Livre) obj;
		boolean result = (this.TITRE.equalsIgnoreCase(subject.TITRE) && this.AUTEUR.equals(subject.AUTEUR));
		return result; // <- TODO résultat à adapter
	}


	// Requêtes ------------------------------------------------------------

	/**
	 * Sélection de livres à partir d'un titre.
	 * 
	 * @param titrePartielOuComplet titre ou partie du titre à rechercher
	 * @return ensemble des livres dont le titre correspond au paramètre fourni,
	 *         indépendamment de la casse
	 */
	public static Set<Livre> select(String titrePartielOuComplet) {
		// TODO à compléter...
		String sub = titrePartielOuComplet.toLowerCase();
		Set<Livre> livreSet = new HashSet<>();

		for(Livre l : LIVRES){
			if(l.TITRE.toLowerCase().contains(sub)){
				livreSet.add(l);
			}
		}
		return livreSet;
	}

	public static Set<Livre> selectParAuteur(String nomDAuteurPartielOuComplet, String prenomDAuteurPartielOuComplet) {
		// TODO à compléter...
		Set<Livre> livresSet = new HashSet<>();
		Set<Auteur> auteurs = Auteur.select(nomDAuteurPartielOuComplet, prenomDAuteurPartielOuComplet);

		for(Livre l : LIVRES){
			if(auteurs.contains(l.AUTEUR)){
				livresSet.add(l);
			}
		}
		return livresSet;
	}


	/**
	 * Sélection de livres à partir d'un mot clé.
	 * 
	 * @param motClePartielOuComplet mot clé ou partie du mot clé à rechercher
	 * @return ensemble des livres dont au moins un mot clé correspond au paramètre
	 *         fourni, indépendamment de la casse
	 */
	public static Set<Livre> selectParMotCle(String motClePartielOuComplet) {
		// TODO à compléter...
		Set<Livre> livresSet = new HashSet<>();
		Set<MotCle> motsSet = MotCle.select(motClePartielOuComplet);

		for(Livre l : LIVRES){
			if(!Collections.disjoint(l.MOTSCLES, motsSet))
				livresSet.add(l);
		}
		return livresSet; // <- TODO résultat à adapter
	}
}
