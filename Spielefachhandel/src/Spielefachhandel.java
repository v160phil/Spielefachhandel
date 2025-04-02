import java.util.ArrayList;

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

    public void addKunde(String name, String vorname){
        this.alleKunden.add(new Kunde(name, vorname));
    }

    public void addSpiel(String name){
        this.alleSpiele.add(new Spiel(name));
    }

    public void erstelleRechnung(Kunde derKunde){
        this.alleRechnungen.add(new Rechnung(derKunde));
    }

    public void erstellePosition(Spiel s, Rechnung r){
        this.allePositionen.add(new Position(s, r));
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
            if (p.getAutoID() == id) {
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
