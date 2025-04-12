package spielefachhandel;
public class Position {
 
    private int ID;
    private int anzahl;
    private Rechnung meineRechnung;
    private Spiel meinSpiel;

    public Position(int anzahl, Spiel s, Rechnung r){
        this.anzahl = anzahl;
        this.meinSpiel = s;
        this.meineRechnung = r;
    }

    public int getAnzahl(){
        return this.anzahl;
    }

    public Rechnung getMeineRechnung(){
        return this.meineRechnung;
    }

    public Spiel getMeinSpiel(){
        return this.meinSpiel;
    }

    public int getID(){
        return this.ID;
    }

    public void setID(int ID){
        this.ID = ID;
    }

}
