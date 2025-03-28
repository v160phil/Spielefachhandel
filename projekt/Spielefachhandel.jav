import java.util.arraylist;
class Spielefachhandel{
    private String name;
    private arraylist<Kunde> alleKunden;
    private arraylist<Rechnung> alleRechnungen;
    private arraylist<Position> allePositionen;
    private arraylist<Spiel> alleSpiele;

    public Spielefachhandel(String name){
        this.name = name;
        alleKunden = new Arraylist<>();
        alleRechnungen = new Arraylist<>();
        allePositionen = new Arraylist<>();
        alleSpiele = new Arraylist<>();
    }
    public String getName(){
        return name;
    }
    public void addKunde(String name, String vorname){
        Kunde neuerkunde = new Kunde(name,vorname);
        alleKunden.add(neuerkunde);

    }
    public void addSpiel(String name){
        Spiel neuesSpiel = new Spiel(name,vorname);
        alleSpiele.add(neuesSpiel);
    }
    public void erstelleRechnung(Kunde derkunde){
        Rechnung neueRechnung = new Rechnung(derkunde);
        alleRechnungen.add(neueRechnung);

    }
    public void erstellePosition(Spiel s, Rechnung r){
       Rechnung meineRechnung = r;
       Spiel meinSpiel = s;
        Position neuePosition = new Position(meinSpiel, meineRechnung);
        allePositionen.add(neuePosition);
    }
    public Arraylist<Kunde> getAlleKunden(){
        return alleKunden;
    }
    public Arraylist<Rechnung> getAlleRechnungen(){
        return alleRechnungen;
    }
    public Arraylist<Position> getAllePositionen(){
        return allePositionen;
    }
    public Arraylist<Spiel> getAlleSpiele(){
        return alleSpiele;
    }
    public Kunde sucheKunden(int kundenNr){
        for (Kunde k:alleKunden){
            if (k.getKundenNr()==kundenNr){
                return k;
            }
        }
        return null;
    }
    public Rechnung sucheRechnung(int rechnungsNr){
        for (Rechnung r:alleRechnungen){
            if (r.getRechnungsNr()==rechnungsNr){
                return r;
            }
        }
        return null;
    }
     public Position suchePosition(int iD){
        for (Position p:allePositionen){
            if (p.getAutoID()==iD){
                return p;
            }
        }
        return null;
    }
     public Spiel sucheSpiel(int produktNr){
        for (Spiel s:alleSpiele){
            if (s.getProduktNr()==produktNr){
                return s;
            }
        }
    }

}