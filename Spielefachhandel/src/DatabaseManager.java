import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {
 
    private static final String URL = "jdbc:mysql://localhost:3306/spielefachhandel";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    // Stellt eine Verbindung zur Datenbank her
    public static Connection getConnection() {
        if (connection == null) {
            try {

                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Verbindung erfolgreich!");
    
            } catch (SQLException e) {

                System.out.println("Verbindung fehlgeschlagen: " + e.getMessage());
                
            }
        }
        return connection;
    }

    public static ArrayList<Kunde> ladeKunden(){
        ArrayList<Kunde> kundenListe = new ArrayList<>();
        String sql = "SELECT * FROM kunde";
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()) {
                String vorname = rs.getString("vorname");
                String name = rs.getString("name");
                String plz = rs.getString("plz");
                String strasse = rs.getString("strasse");
                String ort = rs.getString("ort");
                String email = rs.getString("email");
                String telefonnummer = rs.getString("telefonnummer");
                Kunde kunde = new Kunde(name, vorname);
                kunde.setEmail(email);
                kunde.setOrt(ort);
                kunde.setPlz(plz);
                kunde.setStrasse(strasse);
                kunde.setTelefonnummer(telefonnummer);
                kundenListe.add(kunde);

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return kundenListe;
    }

    public static ArrayList<Spiel> ladeSpiele(){
        ArrayList<Spiel> spieleListe = new ArrayList<>();
        String sql = "SELECT * FROM spiel";
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()) {
                String name = rs.getString("name");
                String genre = rs.getString("genre");
                double einzelpreis = rs.getDouble("einzelpreis");
                Date veroeffentlichungsdatum = rs.getDate("veroeffentlichungsdatum");
                Spiel spiel = new Spiel(name);
                spiel.setEinzelpreis(einzelpreis);
                spiel.setGenre(genre);
                spiel.setVeroeffentlichungsdatum(veroeffentlichungsdatum);
                spieleListe.add(spiel);

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return spieleListe;
    }

    public static ArrayList<Rechnung> ladeRechnungen(ArrayList<Kunde> alleKunden){
        ArrayList<Rechnung> rechnungsListe = new ArrayList<>();
        String sql = "SELECT * FROM rechnung";
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()) {
                Date rechnungsdatum = rs.getDate("rechnungsdatum");
                int kundenNr = rs.getInt("kundenNr");
                for (Kunde k : alleKunden){
                    if (k.getKundenNr() == kundenNr) {
                        Rechnung rechnung = new Rechnung(k);
                        rechnung.setRechnungsdatum(rechnungsdatum);
                        rechnungsListe.add(rechnung);
                    }
                }

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return rechnungsListe;
    }

    public static ArrayList<Position> ladePositionen(ArrayList<Rechnung> alleRechnungen, ArrayList<Spiel> alleSpiele){
        ArrayList<Position> positionsListe = new ArrayList<>();
        String sql = "SELECT * FROM position";
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()) {
                int anzahl = rs.getInt("anzahl");
                int rechnungsNr = rs.getInt("rechnungsNr");
                int produktNr = rs.getInt("produktNr");
                for (Rechnung r : alleRechnungen){
                    if (r.getRechnungsNr() == rechnungsNr) {
                        Rechnung tempRechnung = r;
                        for (Spiel s : alleSpiele) {
                            if (s.getProduktNr() == produktNr) {
                                Spiel tempSpiel = s;
                                Position position = new Position(tempSpiel, tempRechnung);
                                position.setAnzahl(anzahl);
                                positionsListe.add(position);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return positionsListe;
    }

    // Schließt die Verbindung
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Datenbankverbindung geschlossen.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
