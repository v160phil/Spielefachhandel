public class Position {
 
    private int autoID;
    private int anzahl;
    private static int autoNummer = 0;
    private Rechnung meineRechnung;
    private Spiel meinSpiel;

    public Position(Spiel s, Rechnung r){
        this.meinSpiel = s;
        this.meineRechnung = r;
        this.autoID = ++autoNummer;
    }

    public int getAnzahl(){
        return this.anzahl;
    }

    public void setAnzahl(int anzahl){
        this.anzahl = anzahl;
    }

    public Rechnung getMeineRechnung(){
        return this.meineRechnung;
    }

    public Spiel getMeinSpiel(){
        return this.meinSpiel;
    }

    public int getAutoID(){
        return this.autoID;
    }

}
