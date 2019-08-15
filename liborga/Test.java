package liborga;

class Test {

	public static void main(String[] args) {
		Livre l;
		l = Livre.getOrCreate("Les Misérables", "Hugo", "Victor");
		l.addMotCle("roman");
		l.addMotCle("social");
		l = Livre.getOrCreate("Le Roman bourgeois", "Furetière", "Antoine");
		l.addMotCle("ROMAN");
		l.addMotCle("comédie");
		l = Livre.getOrCreate("Marie Tudor", "HUGO", "victor");
		l.addMotCle("théatre");
		l.addMotCle("historique");
		l = Livre.getOrCreate("Germinal", "Zola", "Émile");
		l = Livre.getOrCreate("GERMINAL", "zola", "émile");
		l.addMotCle("roman");
		l.addMotCle("social");
		l = Livre.getOrCreate("Le Bourgeois gentilhomme", "Molière", "");
		l.addMotCle("théatre");
		l.addMotCle("comédie");

		System.out.println("----- Livres -----");
		System.out.println("Tous les livres : " + Livre.select(""));
		System.out.println("Livres contenant \"bourgeois\" : " + Livre.select("bourgeois"));
		System.out.println("Livres de Victor Hugo : " + Livre.selectParAuteur("Hugo", "Victor"));
		System.out.println("Livres liés au théatre : " + Livre.selectParMotCle("théatre"));

		System.out.println("\n----- Auteurs -----");
		System.out.println("Tous les auteurs : " + Auteur.select("", ""));
		System.out.println("Auteurs dont le nom contient un \"o\" : " + Auteur.select("o", ""));
		System.out.println("Auteurs dont le prénom contient un \"o\" : " + Auteur.select("", "o"));

		System.out.println("\n----- Mots clés -----");
		System.out.println("Tous les mots clés : " + MotCle.select(""));
		System.out.println("Mots clé contenant \"om\": " + MotCle.select("om"));
	}

}

/*

----- Livres -----
Tous les livres : ["Le Roman bourgeois" (FURETIÈRE Antoine), "Marie Tudor" (HUGO Victor), "Germinal" (ZOLA Émile), "Les Misérables" (HUGO Victor), "Le Bourgeois gentilhomme" (MOLIÈRE)]
Livres contenant "bourgeois" : ["Le Roman bourgeois" (FURETIÈRE Antoine), "Le Bourgeois gentilhomme" (MOLIÈRE)]
Livres de Victor Hugo : ["Marie Tudor" (HUGO Victor), "Les Misérables" (HUGO Victor)]
Livres liés au théatre : ["Marie Tudor" (HUGO Victor), "Le Bourgeois gentilhomme" (MOLIÈRE)]

----- Auteurs -----
Tous les auteurs : [HUGO Victor, MOLIÈRE, FURETIÈRE Antoine, ZOLA Émile]
Auteurs dont le nom contient un "o" : [HUGO Victor, MOLIÈRE, ZOLA Émile]
Auteurs dont le prénom contient un "o" : [HUGO Victor, FURETIÈRE Antoine]

----- Mots clés -----
Tous les mots clés : [COMÉDIE, THÉATRE, SOCIAL, ROMAN, HISTORIQUE]
Mots clé contenant "om": [COMÉDIE, ROMAN]

*/
