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
            JLabel lbl_aktionauswahl, lbl_begruessungsText, lbl_tabelle_auswahl, lbl_eingabe;
            JButton btn_start, btn_daten_ausgeben, btn_daten_suchen, btn_daten_einfuegen, btn_kunde, btn_rechnung,
                    btn_position, btn_spiel, btn_zurueck, btn_kundenSuchen, btn_rechnungSuchen, btn_positionSuchen, btn_spielSuchen;
            JScrollPane tableScrollPane;
            JTextField kundennummerFeld, rechnungsnummerFeld, idFeld, produktnummerFeld;

            // GUI erstellen und Elemente hinzufuegen
            frame = new JFrame("Verwaltung des Spielefachhandels");
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
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
                                model.addRow(new Object[] { p.getAutoID(), p.getAnzahl(), p.getMeineRechnung().getRechnungsNr(),
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
                                p.getAutoID(), p.getAnzahl(), p.getMeineRechnung().getRechnungsNr(), p.getMeinSpiel().getProduktNr()
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
