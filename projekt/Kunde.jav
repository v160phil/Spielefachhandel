import java.util.arraylist;

class Kunde{
    private String vorname;
    private String name;
    private int kundenNr;
    private String plz;
    private String ort;
    private String strasse;
    private String email;
    private String telefonnummer;
    private static int zaehler = 0;
    private arraylist<Rechnung> meineRechnungen;

    public Kunde(String name, String vorname){
        this.name = name;
        this.vorname = vorname;
        meineRechnungen = new Arraylist<>();
    }
    public String getVorname(){
        return vorname;
    }
    public String getName(){
        return name;
    }
    public int getKundenNr(){
        return kundenNr;
    }
    public String getPLZ(){
        return plz;
    }
    public String getOrt(){
        return Ort;
    }
    public String getStrasse(){
        return strasse;
    }
    public String getEmail(){
        return email;
    }
    public String getTelefonnummer(){
        return telefonnummer;
    }
    public Rechnung getMeineRechnungen(){
        return meineRechnungen;
    }
    public void setName(String Name){
        this.name = name;
    }
    public void setPLZ(String plz){
        this.plz = plz;
    }
    public void setOrt(String ort){
        this.ort = ort
    }
    public void setStrasse(String strasse){
        this.strasse = strasse
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setTelefonnumer(String telefonnummer){
        this.telefonnummer = telefonnummer;
    }
}