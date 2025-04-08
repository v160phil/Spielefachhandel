import java.util.ArrayList;
import java.sql.Date;

public class Spielefachhandel {
    
    private String name;
    private ArrayList<Kunde> alleKunden;
    private ArrayList<Rechnung> alleRechnungen;
    private ArrayList<Position> allePositionen;
    private ArrayList<Spiel> alleSpiele;

    public Spielefachhandel(String name){
        this.name = name;
        this.alleKunden = new ArrayList<>();
        this.alleKunden = DatabaseManager.ladeKunden();
        this.alleSpiele = new ArrayList<>();
        this.alleSpiele = DatabaseManager.ladeSpiele();
        this.alleRechnungen = new ArrayList<>();
        this.alleRechnungen = DatabaseManager.ladeRechnungen(this.alleKunden);
        this.allePositionen = new ArrayList<>();
        this.allePositionen = DatabaseManager.ladePositionen(this.alleRechnungen, this.alleSpiele);
    }

    public String getName(){
        return this.name;
    }

    public Kunde addKunde(String plz, String ort, String strasse, String email, String telefonnummer, String name, String vorname){
        Kunde k = new Kunde(plz, ort, strasse, email, telefonnummer, name, vorname);
        this.alleKunden.add(k);
        return k;
    }

    public Spiel addSpiel(String name, String genre, Date veroeffentlichungsdatum, double einzelpreis){
        Spiel s = new Spiel(name, genre, veroeffentlichungsdatum, einzelpreis);
        this.alleSpiele.add(s);
        return s;
    }

    public Rechnung erstelleRechnung(Date rechnungsdatum, Kunde derKunde){
        Rechnung r = new Rechnung(rechnungsdatum, derKunde);
        this.alleRechnungen.add(r);
        return r;
    }

    public Position erstellePosition(int anzahl, Spiel s, Rechnung r){
        Position p = new Position(anzahl, s, r);
        this.allePositionen.add(p);
        return p;
    }

    public ArrayList<Kunde> getAlleKunden(){
        return this.alleKunden;
    }

    public ArrayList<Rechnung> getAlleRechnungen(){
        return this.alleRechnungen;
    }

    public ArrayList<Position> getAllePositionen(){
        return this.allePositionen;
    }

    public ArrayList<Spiel> getAlleSpiele(){
        return this.alleSpiele;
    }

    public Kunde sucheKunde(int kundenNr){
        for (Kunde k : alleKunden) {
            if (k.getKundenNr() == kundenNr) {
                return k;
            }
        }
        return null;
    }

    public Rechnung sucheRechnung(int rechnungsNr){
        for (Rechnung r : alleRechnungen) {
            if (r.getRechnungsNr() == rechnungsNr) {
                return r;
            }
        }
        return null;
    }

    public Position suchePosition(int id){
        for (Position p : allePositionen) {
            if (p.getID() == id) {
                return p;
            }
        }
        return null;
    }

    public Spiel sucheSpiel(int produktNr){
        for (Spiel s : alleSpiele) {
            if (s.getProduktNr() == produktNr) {
                return s;
            }
        }
        return null;
    }

}
