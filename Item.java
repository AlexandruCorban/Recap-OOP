import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Item {
    private String titlu;
    private int anPublicare;

    public Item(String titlu, int anPublicare) {
        this.titlu = titlu;
        this.anPublicare = anPublicare;
    }

    public String getTitlu() {
        return titlu;
    }

    public int getanPublicare() {
        return anPublicare;
    }

    public abstract String getDetalii();
}

class Carte extends Item implements Imprumutabil {
    private String autor;
    private String numeCititor;
    private LocalDate dataReturnare;
    private boolean disponibil = true;

    public Carte(String titlu, int anPublicare, String autor) {
        super(titlu, anPublicare);
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public void imprumuta(String numeCititor, LocalDate dataReturnare) {
        if (disponibil) {
            this.numeCititor = numeCititor;
            this.dataReturnare = dataReturnare;
            this.disponibil = false;
            System.out.println(getTitlu() + " a fost imprumutata de " + numeCititor);
        } else {
            System.out.println(getTitlu() + " nu este disponibila.");
        }
    }

    @Override
    public void returneaza() {
        if (!disponibil) {
            System.out.println(getTitlu() + " a fost returnata.");
            numeCititor = null;
            disponibil = true;
        } else {
            System.out.println(getTitlu() + " nu este imprumutata.");
        }
    }

    @Override
    public boolean esteDisponibil() {
        return disponibil;
    }

    public String getNumeCititor() {
        return numeCititor;
    }

    public LocalDate getDataReturnare() {
        return dataReturnare;
    }

    @Override
    public String getDetalii() {
        return "Carte: " + getTitlu() + " de " + getAutor() + ", publicata in anul " + getanPublicare();
    }
}

class Revista extends Item implements Imprumutabil {
    private String autor;
    private String numeCititor;
    private LocalDate dataReturnare;
    private int numarEditie;
    private boolean disponibil = true;

    public Revista(String titlu, int anPublicare, String autor, int numarEditie) {
        super(titlu, anPublicare);
        this.autor = autor;
        this.numarEditie = numarEditie;
    }

    public String getAutor() {
        return autor;
    }

    public int getNumarEditie() {
        return numarEditie;
    }

    @Override
    public void imprumuta(String numeCititor, LocalDate dataReturnare) {
        if (disponibil) {
            this.numeCititor = numeCititor;
            this.dataReturnare = dataReturnare;
            this.disponibil = false;
            System.out.println(getTitlu() + " a fost imprumutata de " + numeCititor);
        } else {
            System.out.println(getTitlu() + " nu este disponibila.");
        }
    }

    @Override
    public void returneaza() {
        if (!disponibil) {
            System.out.println(getTitlu() + " a fost returnata.");
            numeCititor = null;
            disponibil = true;
        } else {
            System.out.println(getTitlu() + " nu este imprumutata.");
        }
    }

    @Override
    public boolean esteDisponibil() {
        return disponibil;
    }

    public String getNumeCititor() {
        return numeCititor;
    }

    public LocalDate getDataReturnare() {
        return dataReturnare;
    }

    @Override
    public String getDetalii() {
        return "Revista: " + getTitlu() + " de " + getAutor() + ", publicata in anul " + getanPublicare() +
                ", Numar editie: " + getNumarEditie();
    }
}

class Biblioteca {
    private List<Item> iteme = new ArrayList<>();
    private List<Imprumut> imprumuturi = new ArrayList<>();

    public void adaugaItem(Item item) {
        iteme.add(item);
    }

    public void eliminaItem(Item item) {
        iteme.remove(item);
    }

    public Item cautaDupaTitlu(String titlu) {
        for (Item item : iteme) {
            if (item.getTitlu().equalsIgnoreCase(titlu)) {
                return item;
            }
        }
        return null;
    }

    public List<Item> cautaDupaAutor(String autor) {
        List<Item> rezultate = new ArrayList<>();
        for (Item item : iteme) {
            if (item instanceof Carte) {
                Carte carte = (Carte) item;
                if (carte.getAutor().equalsIgnoreCase(autor)) {
                    rezultate.add(carte);
                }
            }
        }
        return rezultate;
    }

    public void afiseazaIteme() {
        for (Item item : iteme) {
            System.out.println(item.getDetalii());
        }
    }

    public void imprumutaItem(Item item, String numeCititor, LocalDate dataImprumutari, LocalDate dataReturnare) {
        if (item instanceof Imprumutabil) {
            ((Imprumutabil) item).imprumuta(numeCititor, dataReturnare);
            imprumuturi.add(new Imprumut((Imprumutabil) item, numeCititor, dataImprumutari, dataReturnare));
        }
    }

    public void returneazaItem(Item item) {
        if (item instanceof Imprumutabil) {
            ((Imprumutabil) item).returneaza();
        }
    }

    public void afiseazaImprumuturi() {
        for (Imprumut imprumut : imprumuturi) {
            System.out.println(imprumut);
        }
    }
}


class Imprumut implements Imprumutabil {
    private Imprumutabil item;
    private String numeCititor;
    private LocalDate dataImprumutare;
    private LocalDate dataReturnare;

    public Imprumut(Imprumutabil item, String numeCititor, LocalDate dataImprumutare, LocalDate dataReturnare) {
        this.item = item;
        this.numeCititor = numeCititor;
        this.dataImprumutare = dataImprumutare;
        this.dataReturnare = dataReturnare;
    }

    public Imprumutabil getItem() {
        return item;
    }

    @Override
    public void imprumuta(String numeCititor, LocalDate dataReturnare) {
        this.numeCititor = numeCititor;
        this.dataReturnare = dataReturnare;
    }

    @Override
    public void returneaza() {
        numeCititor = null;
    }

    @Override
    public boolean esteDisponibil() {
        return false;
    }

    public String getNumeCititor() {
        return numeCititor;
    }

    public LocalDate getDataImprumutare() {
        return dataImprumutare;
    }

    public LocalDate getDataReturnare() {
        return dataReturnare;
    }

    @Override
    public String toString() {
        return "Imprumut{" +
                "item=" + ((Item) item).getDetalii() +
                ", numeCititor='" + numeCititor + '\'' +
                ", dataImprumutare=" + dataImprumutare +
                ", dataReturnare=" + dataReturnare +
                '}';
    }
}

