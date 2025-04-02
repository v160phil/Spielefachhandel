import java.sql.*;

public class Spiel {
    
    private String name;
    private String genre;
    private int produktNr;
    private Date veroeffentlichungsdatum;
    private double einzelpreis;
    private static int counter = 0;

    public Spiel(String name){
        this.name = name;
        this.produktNr = ++counter;
    }

    public String getName(){
        return this.name;
    }

    public String getGenre(){
        return this.genre;
    }

    public int getProduktNr(){
        return this.produktNr;
    }

    public Date getVeroeffentlichungsdatum(){
        return this.veroeffentlichungsdatum;
    }

    public double getEinzelpreis(){
        return this.einzelpreis;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public void setVeroeffentlichungsdatum(Date datum){
        this.veroeffentlichungsdatum = datum;
    }

    public void setEinzelpreis(double einzelpreis){
        this.einzelpreis = einzelpreis;
    }

}
