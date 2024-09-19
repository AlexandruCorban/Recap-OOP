import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        Carte carte1 = new Carte("The Da Vinci Code", 2003, "Dan Brown");
        Carte carte2 = new Carte("Lolita", 1955, "Vladimir Nabokov");
        Revista revista1 = new Revista("Time", 2007, "Edward Felsenthal", 1);
        Revista revista2 = new Revista("Avantaje", 2009, "Ringier Romania", 4);

        biblioteca.adaugaItem(carte1);
        biblioteca.adaugaItem(carte2);
        biblioteca.adaugaItem(revista1);
        biblioteca.adaugaItem(revista2);

        System.out.println("Itemele din biblioteca:");
        biblioteca.afiseazaIteme();

        System.out.println("\nImprumut cartea: " + carte1.getTitlu());
        biblioteca.imprumutaItem(carte1, "Ion Popescu", LocalDate.now(), LocalDate.now().plusDays(14));

        System.out.println("\nIncearca sa imprumuti aceeasi carte:");
        biblioteca.imprumutaItem(revista2, "Maria Ionescu", LocalDate.now(), LocalDate.now().plusDays(7));

        System.out.println("\nImprumut cartea din nou: " + carte1.getTitlu());
        biblioteca.imprumutaItem(revista1, "Maria Ionescu", LocalDate.now(), LocalDate.now().plusDays(7));

        System.out.println("\nImprumuturile curente:");
        biblioteca.afiseazaImprumuturi();
    }
}