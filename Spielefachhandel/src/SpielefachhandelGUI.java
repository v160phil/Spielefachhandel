import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

public class SpielefachhandelGUI {

    private static Aktionauswahl aktionauswahl = Aktionauswahl.KEINE;

    public static void main(String[] args) {

        DatabaseManager.getConnection();

        Spielefachhandel sfh1 = new Spielefachhandel("Java Store");

        SwingUtilities.invokeLater(() -> {

            // Elemente definieren
            JFrame frame;
            JLabel auswahl, begruessungsText, tabelle_auswahl;
            JButton startKnopf, daten_ausgeben, daten_suchen, daten_einfuegen, tabelle_kunde, tabelle_rechnung,
                    tabelle_position, tabelle_spiel, zurueck;
            JScrollPane tableScrollPane;

            // GUI erstellen und Elemente hinzufuegen
            frame = new JFrame("Verwaltung des Spielefachhandels");
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            begruessungsText = new JLabel("Willkommen bei der Administration des Spielefachhandels!");
            frame.add(begruessungsText);
            auswahl = new JLabel("Was möchten Sie tun?");
            auswahl.setVisible(false);
            frame.add(auswahl);
            daten_ausgeben = new JButton("Daten ausgeben");
            daten_ausgeben.setVisible(false);
            frame.add(daten_ausgeben);
            daten_suchen = new JButton("Nach einem Datensatz suchen");
            daten_suchen.setVisible(false);
            frame.add(daten_suchen);
            daten_einfuegen = new JButton("Datensatz einfügen");
            daten_einfuegen.setVisible(false);
            frame.add(daten_einfuegen);
            tabelle_auswahl = new JLabel("Wählen Sie eine Tabelle aus:");
            tabelle_auswahl.setVisible(false);
            frame.add(tabelle_auswahl);
            tabelle_kunde = new JButton("Kunde");
            tabelle_kunde.setVisible(false);
            frame.add(tabelle_kunde);
            tabelle_rechnung = new JButton("Rechnung");
            tabelle_rechnung.setVisible(false);
            frame.add(tabelle_rechnung);
            tabelle_position = new JButton("Position");
            tabelle_position.setVisible(false);
            frame.add(tabelle_position);
            tabelle_spiel = new JButton("Spiel");
            tabelle_spiel.setVisible(false);
            frame.add(tabelle_spiel);
            zurueck = new JButton("Zurück");
            zurueck.setVisible(false);
            frame.add(zurueck);
            startKnopf = new JButton("Starten");
            frame.add(startKnopf);
            tableScrollPane = new JScrollPane();
            tableScrollPane.setVisible(false);
            frame.add(tableScrollPane);

            // Aktionen durchfuehren
            startKnopf.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setVisibilityOfComponents(false, begruessungsText, startKnopf);
                    setVisibilityOfComponents(true, auswahl, daten_ausgeben, daten_suchen, daten_einfuegen);
                }
            });

            daten_ausgeben.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setVisibilityOfComponents(false,
                            auswahl, daten_ausgeben, daten_suchen, daten_einfuegen);
                    setVisibilityOfComponents(true,
                            tabelle_auswahl, tabelle_kunde, tabelle_rechnung, tabelle_position,
                            tabelle_spiel, zurueck);
                    aktionauswahl = Aktionauswahl.DATENANZEIGE;
                }
            });

            daten_suchen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setVisibilityOfComponents(false,
                            auswahl, daten_ausgeben, daten_suchen, daten_einfuegen);
                    setVisibilityOfComponents(true,
                            tabelle_auswahl, tabelle_kunde, tabelle_rechnung, tabelle_position,
                            tabelle_spiel, zurueck);
                    aktionauswahl = Aktionauswahl.DATENSUCHE;
                }
            });

            daten_einfuegen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setVisibilityOfComponents(false,
                            auswahl, daten_ausgeben, daten_suchen, daten_einfuegen);
                    setVisibilityOfComponents(true,
                            tabelle_auswahl, tabelle_kunde, tabelle_rechnung, tabelle_position,
                            tabelle_spiel, zurueck);
                    aktionauswahl = Aktionauswahl.DATENEINTRAG;
                }
            });

            tabelle_kunde.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                    switch (aktionauswahl) {
                        case DATENANZEIGE:
                            String[] spaltenNamen = { "kundenNr", "vorname", "name", "plz", "strasse", "ort", "email",
                                    "telefonnummer" };
                            DefaultTableModel model = new DefaultTableModel(spaltenNamen, 0);
                            for (Kunde k : sfh1.getAlleKunden()) {
                                model.addRow(new Object[] { k.getKundenNr(), k.getVorname(), k.getName(), k.getPlz(),
                                        k.getStrasse(), k.getOrt(), k.getEmail(), k.getTelefonnummer() });
                            }

                            JTable table = new JTable(model);
                            tableScrollPane.setViewportView(table);
                            tableScrollPane.setVisible(true);

                            frame.revalidate();
                            frame.repaint();
                            break;
                    
                        default:
                            break;
                    }
                }
            });

            tabelle_spiel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    switch (aktionauswahl) {
                        case DATENANZEIGE:
                            String[] spaltenNamen = { "produktNr", "name", "genre", "einzelpreis", "veroeffentlichungsdatum" };
                            DefaultTableModel model = new DefaultTableModel(spaltenNamen, 0);
                            for (Spiel s : sfh1.getAlleSpiele()) {
                                model.addRow(new Object[] { s.getProduktNr(), s.getName(), s.getGenre(), s.getEinzelpreis(),
                                        s.getVeroeffentlichungsdatum() });
                            }
    
                            JTable table = new JTable(model);
                            tableScrollPane.setViewportView(table);
                            tableScrollPane.setVisible(true);
    
                            frame.revalidate();
                            frame.repaint();
                            break;
                    
                        default:
                            break;
                    }
                }
            });

            tabelle_rechnung.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    switch (aktionauswahl) {
                        case DATENANZEIGE:
                            String[] spaltenNamen = { "rechnungsNr", "rechnungsdatum", "kundenNr" };
                            DefaultTableModel model = new DefaultTableModel(spaltenNamen, 0);
                            for (Rechnung r : sfh1.getAlleRechnungen()) {
                                model.addRow(new Object[] { r.getRechnungsNr(), r.getRechnungsdatum(),
                                        r.getMeinKunde().getKundenNr() });
                            }
    
                            JTable table = new JTable(model);
                            tableScrollPane.setViewportView(table);
                            tableScrollPane.setVisible(true);
    
                            frame.revalidate();
                            frame.repaint();
                            break;
                    
                        default:
                            break;
                    }
                }
            });

            tabelle_position.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    switch (aktionauswahl) {
                        case DATENANZEIGE:
                            String[] spaltenNamen = { "autoID", "anzahl", "rechnungsNr", "produktNr" };
                            DefaultTableModel model = new DefaultTableModel(spaltenNamen, 0);
                            for (Position p : sfh1.getAllePositionen()) {
                                model.addRow(new Object[] { p.getAutoID(), p.getAnzahl(), p.getMeineRechnung().getRechnungsNr(),
                                        p.getMeinSpiel().getProduktNr() });
                            }
    
                            JTable table = new JTable(model);
                            tableScrollPane.setViewportView(table);
                            tableScrollPane.setVisible(true);
    
                            frame.revalidate();
                            frame.repaint();
                            break;
                    
                        default:
                            break;
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