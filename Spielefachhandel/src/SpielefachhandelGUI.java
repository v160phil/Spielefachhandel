import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;

public class SpielefachhandelGUI {

    private static Aktionauswahl aktionauswahl = Aktionauswahl.KEINE;

    public static void main(String[] args) {

        DatabaseManager.getConnection();

        Spielefachhandel sfh1 = new Spielefachhandel("Java Store");

        SwingUtilities.invokeLater(() -> {

            // Elemente definieren
            JFrame frame;
            JLabel lbl_aktionauswahl, lbl_begruessungsText, lbl_tabelle_auswahl, lbl_eingabe,
            lbl_vorname, lbl_name, lbl_Plz, lbl_Strasse, lbl_Ort, lbl_Email, lbl_Telefon,
            lbl_datum, lbl_kunde, lbl_genre, lbl_preis, lbl_anzahl, lbl_rechnung, lbl_spiel;
            JButton btn_start, btn_daten_ausgeben, btn_daten_suchen, btn_daten_einfuegen, btn_kunde, btn_rechnung,
                    btn_position, btn_spiel, btn_zurueck, btn_kundenSuchen, btn_rechnungSuchen, btn_positionSuchen, btn_spielSuchen,
                    btn_kundenSpeichern, btn_rechnungSpeichern, btn_spielSpeichern, btn_positionSpeichern;
            JScrollPane tableScrollPane;
            JTextField kundennummerFeld, rechnungsnummerFeld, idFeld, produktnummerFeld,
            kundeVornameFeld, kundeNameFeld, kundePlzFeld, kundeStrasseFeld, kundeOrtFeld,
            kundeEmailFeld, kundeTelefonFeld, datumFeld, spielNameFeld, preisFeld, genreFeld, anzahlFeld;

            // GUI erstellen und Elemente hinzufuegen
            frame = new JFrame("Verwaltung des Spielefachhandels");
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
            lbl_begruessungsText = new JLabel("Willkommen bei der Administration des Spielefachhandels!");
            frame.add(lbl_begruessungsText);
            lbl_aktionauswahl = new JLabel("Was möchten Sie tun?");
            lbl_aktionauswahl.setVisible(false);
            frame.add(lbl_aktionauswahl);
            btn_daten_ausgeben = new JButton("Daten ausgeben");
            btn_daten_ausgeben.setVisible(false);
            frame.add(btn_daten_ausgeben);
            btn_daten_suchen = new JButton("Nach einem Datensatz suchen");
            btn_daten_suchen.setVisible(false);
            frame.add(btn_daten_suchen);
            btn_daten_einfuegen = new JButton("Datensatz einfügen");
            btn_daten_einfuegen.setVisible(false);
            frame.add(btn_daten_einfuegen);
            lbl_tabelle_auswahl = new JLabel("Wählen Sie eine Tabelle aus:");
            lbl_tabelle_auswahl.setVisible(false);
            frame.add(lbl_tabelle_auswahl);
            btn_kunde = new JButton("Kunde");
            btn_kunde.setVisible(false);
            frame.add(btn_kunde);
            btn_rechnung = new JButton("Rechnung");
            btn_rechnung.setVisible(false);
            frame.add(btn_rechnung);
            btn_position = new JButton("Position");
            btn_position.setVisible(false);
            frame.add(btn_position);
            btn_spiel = new JButton("Spiel");
            btn_spiel.setVisible(false);
            frame.add(btn_spiel);
            btn_zurueck = new JButton("Zurück");
            btn_zurueck.setVisible(false);
            frame.add(btn_zurueck);
            btn_start = new JButton("Starten");
            frame.add(btn_start);
            tableScrollPane = new JScrollPane();
            tableScrollPane.setVisible(false);
            frame.add(tableScrollPane);
            lbl_eingabe = new JLabel();
            lbl_eingabe.setVisible(false);
            frame.add(lbl_eingabe);
            kundennummerFeld = new JTextField(10);
            kundennummerFeld.setVisible(false);
            frame.add(kundennummerFeld);
            rechnungsnummerFeld = new JTextField(10);
            rechnungsnummerFeld.setVisible(false);
            frame.add(rechnungsnummerFeld);
            idFeld = new JTextField(10);
            idFeld.setVisible(false);
            frame.add(idFeld);
            produktnummerFeld = new JTextField(10);
            produktnummerFeld.setVisible(false);
            frame.add(produktnummerFeld);
            btn_kundenSuchen = new JButton("Kunden suchen");
            btn_kundenSuchen.setVisible(false);
            frame.add(btn_kundenSuchen);
            btn_rechnungSuchen = new JButton("Rechnung suchen");
            btn_rechnungSuchen.setVisible(false);
            frame.add(btn_rechnungSuchen);
            btn_positionSuchen = new JButton("Position suchen");
            btn_positionSuchen.setVisible(false);
            frame.add(btn_positionSuchen);
            btn_spielSuchen = new JButton("Spiel suchen");
            btn_spielSuchen.setVisible(false);
            frame.add(btn_spielSuchen);
            kundeVornameFeld = new JTextField(10);
            kundeVornameFeld.setVisible(false);
            frame.add(kundeVornameFeld);
            kundeNameFeld = new JTextField(10);
            kundeNameFeld.setVisible(false);
            frame.add(kundeNameFeld);
            kundePlzFeld = new JTextField(10);
            kundePlzFeld.setVisible(false);
            frame.add(kundePlzFeld);
            kundeStrasseFeld = new JTextField(10);
            kundeStrasseFeld.setVisible(false);
            frame.add(kundeStrasseFeld);
            kundeOrtFeld = new JTextField(10);
            kundeOrtFeld.setVisible(false);
            frame.add(kundeOrtFeld);
            kundeEmailFeld = new JTextField(10);
            kundeEmailFeld.setVisible(false);
            frame.add(kundeEmailFeld);
            kundeTelefonFeld = new JTextField(10);
            kundeTelefonFeld.setVisible(false);
            frame.add(kundeTelefonFeld);
            btn_kundenSpeichern = new JButton("Kunden speichern");
            btn_kundenSpeichern.setVisible(false);
            frame.add(btn_kundenSpeichern);
            lbl_vorname = new JLabel("Vorname:");
            lbl_vorname.setVisible(false);
            frame.add(lbl_vorname);
            lbl_name = new JLabel("Name:");
            lbl_name.setVisible(false);
            frame.add(lbl_name);
            lbl_Plz = new JLabel("PLZ:");
            lbl_Plz.setVisible(false);
            frame.add(lbl_Plz);
            lbl_Strasse = new JLabel("Straße:");
            lbl_Strasse.setVisible(false);
            frame.add(lbl_Strasse);
            lbl_Ort = new JLabel("Ort:");
            lbl_Ort.setVisible(false);
            frame.add(lbl_Ort);
            lbl_Email = new JLabel("E-Mail:");
            lbl_Email.setVisible(false);
            frame.add(lbl_Email);
            lbl_Telefon = new JLabel("Telefon:");
            lbl_Telefon.setVisible(false);
            frame.add(lbl_Telefon);
            lbl_datum = new JLabel("Datum (im Format JJJJ-MM-TT)");
            lbl_datum.setVisible(false);
            frame.add(lbl_datum);
            lbl_kunde = new JLabel("Die zugehörige Kundennummer:");
            lbl_kunde.setVisible(false);
            frame.add(lbl_kunde);
            datumFeld = new JTextField(10);
            datumFeld.setVisible(false);
            frame.add(datumFeld);
            btn_rechnungSpeichern = new JButton("Rechnung speichern");
            btn_rechnungSpeichern.setVisible(false);
            frame.add(btn_rechnungSpeichern);
            btn_spielSpeichern = new JButton("Spiel speichern");
            btn_spielSpeichern.setVisible(false);
            frame.add(btn_spielSpeichern);
            lbl_genre = new JLabel("Genre:");
            lbl_genre.setVisible(false);
            frame.add(lbl_genre);
            lbl_preis = new JLabel("Preis:");
            lbl_preis.setVisible(false);
            frame.add(lbl_preis);
            spielNameFeld = new JTextField(10);
            spielNameFeld.setVisible(false);
            frame.add(spielNameFeld);
            preisFeld = new JTextField(10);
            preisFeld.setVisible(false);
            frame.add(preisFeld);
            genreFeld = new JTextField(10);
            genreFeld.setVisible(false);
            frame.add(genreFeld);
            lbl_spiel = new JLabel("Die zugehörige Produktnummer:");
            lbl_spiel.setVisible(false);
            frame.add(lbl_spiel);
            lbl_rechnung = new JLabel("Die zugehörige Rechnungsnummer:");
            lbl_rechnung.setVisible(false);
            frame.add(lbl_rechnung);
            btn_positionSpeichern = new JButton("Position speichern");
            btn_positionSpeichern.setVisible(false);
            frame.add(btn_positionSpeichern);
            lbl_anzahl = new JLabel("Anzahl:");
            lbl_anzahl.setVisible(false);
            frame.add(lbl_anzahl);
            anzahlFeld = new JTextField(10);
            anzahlFeld.setVisible(false);
            frame.add(anzahlFeld);

            // Aktionen durchfuehren
            btn_start.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setVisibilityOfComponents(false, lbl_begruessungsText, btn_start);
                    setVisibilityOfComponents(true, lbl_aktionauswahl, btn_daten_ausgeben, btn_daten_suchen, btn_daten_einfuegen);
                }
            });

            btn_daten_ausgeben.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setVisibilityOfComponents(false,
                            lbl_aktionauswahl, btn_daten_ausgeben, btn_daten_suchen, btn_daten_einfuegen);
                    setVisibilityOfComponents(true,
                            lbl_tabelle_auswahl, btn_kunde, btn_rechnung, btn_position,
                            btn_spiel);
                    aktionauswahl = Aktionauswahl.DATENANZEIGE;
                }
            });

            btn_daten_suchen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setVisibilityOfComponents(false,
                            lbl_aktionauswahl, btn_daten_ausgeben, btn_daten_suchen, btn_daten_einfuegen);
                    setVisibilityOfComponents(true,
                            lbl_tabelle_auswahl, btn_kunde, btn_rechnung, btn_position,
                            btn_spiel);
                    aktionauswahl = Aktionauswahl.DATENSUCHE;
                }
            });

            btn_daten_einfuegen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setVisibilityOfComponents(false,
                            lbl_aktionauswahl, btn_daten_ausgeben, btn_daten_suchen, btn_daten_einfuegen);
                    setVisibilityOfComponents(true,
                            lbl_tabelle_auswahl, btn_kunde, btn_rechnung, btn_position,
                            btn_spiel);
                    aktionauswahl = Aktionauswahl.DATENEINTRAG;
                }
            });

            btn_kunde.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {

                    String[] spaltenNamen = { "kundenNr", "vorname", "name", "plz", "strasse", "ort", "email",
                                    "telefonnummer" };
                    DefaultTableModel model = new DefaultTableModel(spaltenNamen, 0);
                    JTable table = new JTable(model);
                    tableScrollPane.setViewportView(table);

                    switch (aktionauswahl) {

                        case DATENANZEIGE:

                            for (Kunde k : sfh1.getAlleKunden()) {
                                model.addRow(new Object[] { k.getKundenNr(), k.getVorname(), k.getName(), k.getPlz(),
                                        k.getStrasse(), k.getOrt(), k.getEmail(), k.getTelefonnummer() });
                            }
                            tableScrollPane.setVisible(true);

                            frame.revalidate();
                            frame.repaint();
                            break;
                        
                        case DATENSUCHE:

                            lbl_eingabe.setText("Geben Sie eine Kundennummer ein:");
                            setVisibilityOfComponents(false, lbl_tabelle_auswahl, btn_kunde, btn_rechnung, btn_position, btn_spiel, tableScrollPane);
                            setVisibilityOfComponents(true, lbl_eingabe, kundennummerFeld, btn_kundenSuchen);
                            break;

                        case DATENEINTRAG:
                            setVisibilityOfComponents(false, lbl_tabelle_auswahl, btn_kunde, btn_rechnung, btn_position, btn_spiel, tableScrollPane);
                            setVisibilityOfComponents(true, lbl_vorname, kundeVornameFeld, lbl_name, kundeNameFeld, lbl_Plz, kundePlzFeld, lbl_Strasse, 
                            kundeStrasseFeld, lbl_Ort, kundeOrtFeld, lbl_Email, kundeEmailFeld, lbl_Telefon, kundeTelefonFeld, btn_kundenSpeichern);
                            break;

                        default:
                            break;

                    }
                }
            });

            btn_spiel.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    String[] spaltenNamen = { "produktNr", "name", "genre", "einzelpreis", "veroeffentlichungsdatum" };
                    DefaultTableModel model = new DefaultTableModel(spaltenNamen, 0);
                    JTable table = new JTable(model);
                    tableScrollPane.setViewportView(table);

                    switch (aktionauswahl) {

                        case DATENANZEIGE:

                            for (Spiel s : sfh1.getAlleSpiele()) {
                                model.addRow(new Object[] { s.getProduktNr(), s.getName(), s.getGenre(), s.getEinzelpreis(),
                                        s.getVeroeffentlichungsdatum() });
                            }
                            tableScrollPane.setVisible(true);
    
                            frame.revalidate();
                            frame.repaint();
                            break;
                    
                        case DATENSUCHE:

                            lbl_eingabe.setText("Geben Sie eine Produktnummer ein:");
                            setVisibilityOfComponents(false, lbl_tabelle_auswahl, btn_kunde, btn_rechnung, btn_position, btn_spiel, tableScrollPane);
                            setVisibilityOfComponents(true, lbl_eingabe, produktnummerFeld, btn_spielSuchen);
                            break;

                        case DATENEINTRAG:
                            lbl_datum.setText("Veröffentlichungsdatum (im Format JJJJ-MM-TT)");
                            datumFeld.setText("");
                            setVisibilityOfComponents(false, lbl_tabelle_auswahl, btn_kunde, btn_rechnung, btn_position, btn_spiel, tableScrollPane);
                            setVisibilityOfComponents(true, lbl_name, spielNameFeld, lbl_genre, genreFeld, lbl_preis, preisFeld, lbl_datum, datumFeld, btn_spielSpeichern);
                            break;

                        default:
                            break;
                    }
                }
            });

            btn_rechnung.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    String[] spaltenNamen = { "rechnungsNr", "rechnungsdatum", "kundenNr" };
                    DefaultTableModel model = new DefaultTableModel(spaltenNamen, 0);
                    JTable table = new JTable(model);
                    tableScrollPane.setViewportView(table);

                    switch (aktionauswahl) {

                        case DATENANZEIGE:
                            for (Rechnung r : sfh1.getAlleRechnungen()) {
                                model.addRow(new Object[] { r.getRechnungsNr(), r.getRechnungsdatum(),
                                        r.getMeinKunde().getKundenNr() });
                            }
                            tableScrollPane.setVisible(true);
    
                            frame.revalidate();
                            frame.repaint();
                            break;
                    
                        case DATENSUCHE:

                            lbl_eingabe.setText("Geben Sie eine Rechnungsnummer ein:");
                            setVisibilityOfComponents(false, lbl_tabelle_auswahl, btn_kunde, btn_rechnung, btn_position, btn_spiel, tableScrollPane);
                            setVisibilityOfComponents(true, lbl_eingabe, rechnungsnummerFeld, btn_rechnungSuchen);
                            break;

                        case DATENEINTRAG:
                            lbl_datum.setText("Rechnungsdatum (im Format JJJJ-MM-TT)");
                            datumFeld.setText("");
                            kundennummerFeld.setText("");
                            setVisibilityOfComponents(false, lbl_tabelle_auswahl, btn_kunde, btn_rechnung, btn_position, btn_spiel, tableScrollPane);
                            setVisibilityOfComponents(true, lbl_datum, datumFeld, lbl_kunde, kundennummerFeld, btn_rechnungSpeichern);
                            break;

                        default:
                            break;
                    }
                }
            });

            btn_position.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    String[] spaltenNamen = { "autoID", "anzahl", "rechnungsNr", "produktNr" };
                    DefaultTableModel model = new DefaultTableModel(spaltenNamen, 0);
                    JTable table = new JTable(model);
                    tableScrollPane.setViewportView(table);

                    switch (aktionauswahl) {

                        case DATENANZEIGE:
                            for (Position p : sfh1.getAllePositionen()) {
                                model.addRow(new Object[] { p.getID(), p.getAnzahl(), p.getMeineRechnung().getRechnungsNr(),
                                        p.getMeinSpiel().getProduktNr() });
                            }
                            tableScrollPane.setVisible(true);
    
                            frame.revalidate();
                            frame.repaint();
                            break;
                    
                        case DATENSUCHE:

                            lbl_eingabe.setText("Geben Sie eine ID ein:");
                            setVisibilityOfComponents(false, lbl_tabelle_auswahl, btn_kunde, btn_rechnung, btn_position, btn_spiel, tableScrollPane);
                            setVisibilityOfComponents(true, lbl_eingabe, idFeld, btn_positionSuchen);
                            break;

                        case DATENEINTRAG:
                            rechnungsnummerFeld.setText("");
                            produktnummerFeld.setText("");
                            setVisibilityOfComponents(false, lbl_tabelle_auswahl, btn_kunde, btn_rechnung, btn_position, btn_spiel, tableScrollPane);
                            setVisibilityOfComponents(true, lbl_anzahl, anzahlFeld, lbl_rechnung, rechnungsnummerFeld, lbl_spiel, produktnummerFeld, btn_positionSpeichern);
                            break;

                        default:
                            break;
                    }
                }
            });
            
            btn_kundenSuchen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        int kundenNr = Integer.parseInt(kundennummerFeld.getText());
                        Kunde k = sfh1.sucheKunde(kundenNr);
            
                        if (k != null) {
                            DefaultTableModel model = new DefaultTableModel(
                                new String[] { "kundenNr", "vorname", "name", "plz", "strasse", "ort", "email", "telefonnummer" }, 0
                            );
                            JTable table = new JTable(model);
                            tableScrollPane.setViewportView(table);
            
                            model.addRow(new Object[] {
                                k.getKundenNr(), k.getVorname(), k.getName(), k.getPlz(),
                                k.getStrasse(), k.getOrt(), k.getEmail(), k.getTelefonnummer()
                            });
            
                            setVisibilityOfComponents(true, tableScrollPane);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Kunde nicht gefunden.");
                        }
            
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Bitte eine gültige Kundennummer eingeben.");
                    }
            
                    frame.revalidate();
                    frame.repaint();
                }
            });
            
            btn_rechnungSuchen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        int rechnungsNr = Integer.parseInt(rechnungsnummerFeld.getText());
                        Rechnung r = sfh1.sucheRechnung(rechnungsNr);
            
                        if (r != null) {
                            DefaultTableModel model = new DefaultTableModel(
                                new String[] { "rechnungsNr", "rechnungsdatum", "kundenNr" }, 0
                            );
                            JTable table = new JTable(model);
                            tableScrollPane.setViewportView(table);
            
                            model.addRow(new Object[] {
                                r.getRechnungsNr(), r.getRechnungsdatum(), r.getMeinKunde().getKundenNr()
                            });
            
                            setVisibilityOfComponents(true, tableScrollPane);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Rechnung nicht gefunden.");
                        }
            
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Bitte eine gültige Rechnungsnummer eingeben.");
                    }
            
                    frame.revalidate();
                    frame.repaint();
                }
            });

            btn_positionSuchen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        int id = Integer.parseInt(idFeld.getText());
                        Position p = sfh1.suchePosition(id);
            
                        if (p != null) {
                            DefaultTableModel model = new DefaultTableModel(
                                new String[] { "autoID", "anzahl", "rechnungsNr", "produktNr" }, 0
                            );
                            JTable table = new JTable(model);
                            tableScrollPane.setViewportView(table);
            
                            model.addRow(new Object[] {
                                p.getID(), p.getAnzahl(), p.getMeineRechnung().getRechnungsNr(), p.getMeinSpiel().getProduktNr()
                            });
            
                            setVisibilityOfComponents(true, tableScrollPane);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Position nicht gefunden.");
                        }
            
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Bitte eine gültige ID eingeben.");
                    }
            
                    frame.revalidate();
                    frame.repaint();
                }
            });

            btn_spielSuchen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        int produktNr = Integer.parseInt(produktnummerFeld.getText());
                        Spiel s = sfh1.sucheSpiel(produktNr);
            
                        if (s != null) {
                            DefaultTableModel model = new DefaultTableModel(
                                new String[] { "produktNr", "name", "genre", "einzelpreis", "veroeffentlichungsdatum" }, 0
                            );
                            JTable table = new JTable(model);
                            tableScrollPane.setViewportView(table);
            
                            model.addRow(new Object[] {
                                s.getProduktNr(), s.getName(), s.getGenre(), s.getEinzelpreis(), s.getVeroeffentlichungsdatum()
                            });
            
                            setVisibilityOfComponents(true, tableScrollPane);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Spiel nicht gefunden.");
                        }
            
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Bitte eine gültige Produktnummer eingeben.");
                    }
            
                    frame.revalidate();
                    frame.repaint();
                }
            });

            btn_kundenSpeichern.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e){

                    try {

                        String vorname = kundeVornameFeld.getText();
                        String name = kundeNameFeld.getText();
                        String plz = kundePlzFeld.getText();
                        String strasse = kundeStrasseFeld.getText();
                        String ort = kundeOrtFeld.getText();
                        String email = kundeEmailFeld.getText();
                        String telefon = kundeTelefonFeld.getText();
                        Kunde neuerKunde = sfh1.addKunde(plz, ort, strasse, email, telefon, name, vorname);

                        DatabaseManager.insertKunde(neuerKunde);
                        JOptionPane.showMessageDialog(frame, "Kunde erfolgreich hinzugefügt!");

                        kundeVornameFeld.setText("");
                        kundeNameFeld.setText("");
                        kundePlzFeld.setText("");
                        kundeStrasseFeld.setText("");
                        kundeOrtFeld.setText("");
                        kundeEmailFeld.setText("");
                        kundeTelefonFeld.setText("");

                    } catch (Exception ex){
                        JOptionPane.showMessageDialog(frame, "Fehler beim Einfügen des Kunden.");
                        ex.printStackTrace();
                    }
                }
            });

            btn_rechnungSpeichern.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e){

                    try {

                        Date datum = Date.valueOf(datumFeld.getText());
                        int kundenNr = Integer.parseInt(kundennummerFeld.getText());
                        Rechnung neueRechnung = sfh1.erstelleRechnung(datum, sfh1.sucheKunde(kundenNr));

                        DatabaseManager.insertRechnung(neueRechnung);
                        JOptionPane.showMessageDialog(frame, "Rechnung erfolgreich hinzugefügt!");

                        datumFeld.setText("");
                        kundennummerFeld.setText("");

                    } catch (Exception ex){
                        JOptionPane.showMessageDialog(frame, "Fehler beim Einfügen der Rechnung.");
                        ex.printStackTrace();
                    }
                }
            });

            btn_spielSpeichern.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e){

                    try {

                        String name = spielNameFeld.getText();
                        String genre = genreFeld.getText();
                        double einzelpreis = Double.parseDouble(preisFeld.getText());
                        Date veroeffentlichungsdatum = Date.valueOf(datumFeld.getText());
                        Spiel neuesSpiel = sfh1.addSpiel(name, genre, veroeffentlichungsdatum, einzelpreis);

                        DatabaseManager.insertSpiel(neuesSpiel);
                        JOptionPane.showMessageDialog(frame, "Spiel erfolgreich hinzugefügt!");

                        spielNameFeld.setText("");
                        genreFeld.setText("");
                        preisFeld.setText("");
                        datumFeld.setText("");

                    } catch (Exception ex){
                        JOptionPane.showMessageDialog(frame, "Fehler beim Einfügen des Spiels.");
                        ex.printStackTrace();
                    }
                }
            });

            btn_positionSpeichern.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e){

                    try {

                        int anzahl = Integer.parseInt(anzahlFeld.getText());
                        int rechnungsNr = Integer.parseInt(rechnungsnummerFeld.getText());
                        int produktNr = Integer.parseInt(produktnummerFeld.getText());
                        Position neuePosition = sfh1.erstellePosition(anzahl, sfh1.sucheSpiel(produktNr), sfh1.sucheRechnung(rechnungsNr));

                        DatabaseManager.insertPosition(neuePosition);
                        JOptionPane.showMessageDialog(frame, "Position erfolgreich hinzugefügt!");

                        anzahlFeld.setText("");
                        rechnungsnummerFeld.setText("");
                        produktnummerFeld.setText("");

                    } catch (Exception ex){
                        JOptionPane.showMessageDialog(frame, "Fehler beim Einfügen der Position.");
                        ex.printStackTrace();
                    }
                }
            });

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    DatabaseManager.closeConnection();
                }
            });

            frame.setVisible(true);

        });

    }

    private static void setVisibilityOfComponents(boolean visibility, JComponent... components) {
        for (JComponent component : components) {
            component.setVisible(visibility);
        }
    }

}
