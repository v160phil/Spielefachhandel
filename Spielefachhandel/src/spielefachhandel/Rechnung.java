package spielefachhandel;
import java.sql.Date;
import java.util.ArrayList;

public class Rechnung {
    
    private int rechnungsNr;
    private Kunde meinKunde;
    private Date rechnungsdatum;
    private ArrayList<Position> diePositionen;

    public Rechnung(Date rechnungsdatum, Kunde derKunde){
        this.rechnungsdatum = rechnungsdatum;
        this.meinKunde = derKunde;
        this.diePositionen = new ArrayList<>();
    }

    public int getRechnungsNr(){
        return this.rechnungsNr;
    }

    public Date getRechnungsdatum(){
        return this.rechnungsdatum;
    }

    public ArrayList<Position> getDiePositionen(){
        return this.diePositionen;
    }

    public Kunde getMeinKunde(){
        return this.meinKunde;
    }

    public void setRechnungsNr(int rechnungsNr){
        this.rechnungsNr = rechnungsNr;
    }

}
