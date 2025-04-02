import java.sql.Date;
import java.util.ArrayList;

public class Rechnung {
    
    private int rechnungsNr;
    private static int autoNr = 0;
    private Kunde meinKunde;
    private Date rechnungsdatum;
    private ArrayList<Position> diePositionen;

    public Rechnung(Kunde derKunde){
        this.meinKunde = derKunde;
        this.rechnungsNr = ++autoNr;
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

    public void setRechnungsdatum(Date rechnungsdatum){
        this.rechnungsdatum = rechnungsdatum;
    }

}
