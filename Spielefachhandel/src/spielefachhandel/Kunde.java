package spielefachhandel;
import java.util.ArrayList;

public class Kunde {

    private String vorname;
    private String name;
    private int kundenNr;
    private String plz;
    private String ort;
    private String strasse;
    private String email;
    private String telefonnummer;
    private ArrayList<Rechnung> meineRechnungen;

    public Kunde(String plz, String ort, String strasse, String email, String telefonnummer, String name, String vorname){
        this.plz = plz;
        this.ort = ort;
        this.strasse = strasse;
        this.email = email;
        this.telefonnummer = telefonnummer;
        this.name = name;
        this.vorname = vorname;
        this.meineRechnungen = new ArrayList<>();
    }

    public String getVorname(){
        return this.vorname;
    }

    public String getName(){
        return this.name;
    }

    public int getKundenNr(){
        return this.kundenNr;
    }

    public String getPlz(){
        return this.plz;
    }

    public String getOrt(){
        return this.ort;
    }

    public String getStrasse(){
        return this.strasse;
    }

    public String getEmail(){
        return this.email;
    }

    public String getTelefonnummer(){
        return this.telefonnummer;
    }

    public ArrayList<Rechnung> getMeineRechnungen(){
        return this.meineRechnungen;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPlz(String plz){
        this.plz = plz;
    }

    public void setOrt(String ort){
        this.ort = ort;
    }

    public void setStrasse(String strasse){
        this.strasse = strasse;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setTelefonnummer(String telefonnummer){
        this.telefonnummer = telefonnummer;
    }

    public void setKundenNr(int kundenNr){
        this.kundenNr = kundenNr;
    }

}