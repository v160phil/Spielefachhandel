package spielefachhandel.Screens;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import spielefachhandel.DatabaseManager;
import spielefachhandel.Kunde;
import spielefachhandel.Spielefachhandel;
import javax.swing.JOptionPane;

public class KundeHinzufuegen extends JPanel {

    Spielefachhandel sfh;
    JFrame frame;

    JTextField kundeVornameFeld = new JTextField();
    JTextField kundeNameFeld = new JTextField();
    JTextField kundePlzFeld = new JTextField();
    JTextField kundeStrasseFeld = new JTextField();
    JTextField kundeOrtFeld = new JTextField();
    JTextField kundeEmailFeld = new JTextField();
    JTextField kundeTelefonFeld = new JTextField();

    JButton btn_kundenSpeichern = new JButton("Kunden speichern");

    JLabel lbl_vorname = new JLabel("Vorname:");
    JLabel lbl_name = new JLabel("Name:");
    JLabel lbl_Plz = new JLabel("PLZ:");
    JLabel lbl_Strasse = new JLabel("Straße:");
    JLabel lbl_Ort = new JLabel("Ort:");
    JLabel lbl_Email = new JLabel("E-Mail:");
    JLabel lbl_Telefon = new JLabel("Telefon:");

    public KundeHinzufuegen(Spielefachhandel sfh, JFrame frame) {

            this.sfh = sfh;
            this.frame = frame;
            
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            this.add(new Tabellenauswahl());

            this.add(Box.createVerticalStrut(10));
            
            var grid_neuer_kunde = new JPanel();
            grid_neuer_kunde.setLayout(new GridLayout(7, 2));
            grid_neuer_kunde.add(lbl_vorname);
            grid_neuer_kunde.add(kundeVornameFeld);
            grid_neuer_kunde.add(lbl_name);
            grid_neuer_kunde.add(kundeNameFeld);
            grid_neuer_kunde.add(lbl_Plz);
            grid_neuer_kunde.add(kundePlzFeld);
            grid_neuer_kunde.add(lbl_Strasse);
            grid_neuer_kunde.add(kundeStrasseFeld);
            grid_neuer_kunde.add(lbl_Ort);
            grid_neuer_kunde.add(kundeOrtFeld);
            grid_neuer_kunde.add(lbl_Email);
            grid_neuer_kunde.add(kundeEmailFeld);
            grid_neuer_kunde.add(lbl_Telefon);
            grid_neuer_kunde.add(kundeTelefonFeld);

            this.add(grid_neuer_kunde);

            this.add(btn_kundenSpeichern);

            btn_kundenSpeichern.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    kundeErstellen();
                }
                
            });
    }

    public void kundeErstellen() {
        try {

            String vorname = kundeVornameFeld.getText();
            String name = kundeNameFeld.getText();
            String plz = kundePlzFeld.getText();
            String strasse = kundeStrasseFeld.getText();
            String ort = kundeOrtFeld.getText();
            String email = kundeEmailFeld.getText();
            String telefon = kundeTelefonFeld.getText();

            Kunde neuerKunde = sfh.addKunde(plz, ort, strasse, email, telefon, name, vorname);
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
}
