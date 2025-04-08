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
                int kundenNr = rs.getInt("kundenNr");
                String vorname = rs.getString("vorname");
                String name = rs.getString("name");
                String plz = rs.getString("plz");
                String strasse = rs.getString("strasse");
                String ort = rs.getString("ort");
                String email = rs.getString("email");
                String telefonnummer = rs.getString("telefonnummer");
                Kunde kunde = new Kunde(plz, ort, strasse, email, telefonnummer, name, vorname);
                kunde.setKundenNr(kundenNr);
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
                int produktNr = rs.getInt("produktNr");
                String name = rs.getString("name");
                String genre = rs.getString("genre");
                double einzelpreis = rs.getDouble("einzelpreis");
                Date veroeffentlichungsdatum = rs.getDate("veroeffentlichungsdatum");
                Spiel spiel = new Spiel(name, genre, veroeffentlichungsdatum, einzelpreis);
                spiel.setProduktNr(produktNr);
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
                int rechnungsNr = rs.getInt("rechnungsNr");
                Date rechnungsdatum = rs.getDate("rechnungsdatum");
                int kundenNr = rs.getInt("kundenNr");
                for (Kunde k : alleKunden){
                    if (k.getKundenNr() == kundenNr) {
                        Rechnung rechnung = new Rechnung(rechnungsdatum, k);
                        rechnung.setRechnungsNr(rechnungsNr);
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
                int ID = rs.getInt("autoID");
                int anzahl = rs.getInt("anzahl");
                int rechnungsNr = rs.getInt("rechnungsNr");
                int produktNr = rs.getInt("produktNr");
                for (Rechnung r : alleRechnungen){
                    if (r.getRechnungsNr() == rechnungsNr) {
                        Rechnung tempRechnung = r;
                        for (Spiel s : alleSpiele) {
                            if (s.getProduktNr() == produktNr) {
                                Spiel tempSpiel = s;
                                Position position = new Position(anzahl, tempSpiel, tempRechnung);
                                position.setID(ID);
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

    public static int insertKunde(Kunde k){
        String sql = "INSERT INTO kunde (vorname, name, plz, strasse, ort, email, telefonnummer) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setString(1, k.getVorname());
            pstmt.setString(2, k.getName());
            pstmt.setString(3, k.getPlz());
            pstmt.setString(4, k.getStrasse());
            pstmt.setString(5, k.getOrt());
            pstmt.setString(6, k.getEmail());
            pstmt.setString(7, k.getTelefonnummer());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()){
                int kundenNr = rs.getInt(1);
                k.setKundenNr(kundenNr);
                return kundenNr;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public static int insertSpiel(Spiel s){
        String sql = "INSERT INTO spiel (name, genre, einzelpreis, veroeffentlichungsdatum) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setString(1, s.getName());
            pstmt.setString(2, s.getGenre());
            pstmt.setDouble(3, s.getEinzelpreis());
            pstmt.setDate(4, s.getVeroeffentlichungsdatum());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()){
                int produktNr = rs.getInt(1);
                s.setProduktNr(produktNr);
                return produktNr;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public static int insertRechnung(Rechnung r){
        String sql = "INSERT INTO rechnung (rechnungsdatum, kundenNr) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setDate(1, r.getRechnungsdatum());
            pstmt.setInt(2, r.getMeinKunde().getKundenNr());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()){
                int rechnungsNr = rs.getInt(1);
                r.setRechnungsNr(rechnungsNr);
                return rechnungsNr;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public static int insertPosition(Position p){
        String sql = "INSERT INTO position (anzahl, rechnungsNr, produktNr) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setInt(1, p.getAnzahl());
            pstmt.setInt(2, p.getMeineRechnung().getRechnungsNr());
            pstmt.setInt(3, p.getMeinSpiel().getProduktNr());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()){
                int autoID = rs.getInt(1);
                p.setID(autoID);
                return autoID;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
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
