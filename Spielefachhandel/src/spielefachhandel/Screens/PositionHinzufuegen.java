package spielefachhandel.Screens;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import spielefachhandel.DatabaseManager;
import spielefachhandel.Position;
import spielefachhandel.Spielefachhandel;

public class PositionHinzufuegen extends JPanel{

    Spielefachhandel sfh;

    JFrame frame;

    JTextField anzahlFeld = new JTextField();
    JTextField rechnungsNrFeld = new JTextField();
    JTextField produktNrFeld = new JTextField();

    JButton btn_positionSpeichern = new JButton("Position speichern");

    JLabel lbl_anzahl = new JLabel("Anzahl:");
    JLabel lbl_rechnungsNr = new JLabel("Die zugehörige Rechnungsnummer:");
    JLabel lbl_produktNr = new JLabel("Die zugehörige Produktnummer:");

    public PositionHinzufuegen(Spielefachhandel sfh, JFrame frame){

        this.sfh = sfh;
        this.frame = frame;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(new Tabellenauswahl());
        
        this.add(Box.createVerticalStrut(10));

        var grid_neue_position = new JPanel();
        grid_neue_position.setLayout(new GridLayout(3, 2));
        grid_neue_position.add(lbl_anzahl);
        grid_neue_position.add(anzahlFeld);
        grid_neue_position.add(lbl_rechnungsNr);
        grid_neue_position.add(rechnungsNrFeld);
        grid_neue_position.add(lbl_produktNr);
        grid_neue_position.add(produktNrFeld);

        this.add(grid_neue_position);

        this.add(btn_positionSpeichern);

        btn_positionSpeichern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                positionErstellen();
            }
        });

    }

    public void positionErstellen(){

        try {

            int anzahl = Integer.parseInt(anzahlFeld.getText());
            int rechnungsNr = Integer.parseInt(rechnungsNrFeld.getText());
            int produktNr = Integer.parseInt(produktNrFeld.getText());
            Position neuePosition = sfh.erstellePosition(anzahl, sfh.sucheSpiel(produktNr), sfh.sucheRechnung(rechnungsNr));

            DatabaseManager.insertPosition(neuePosition);
            JOptionPane.showMessageDialog(frame, "Position erfolgreich hinzugefügt!");

            anzahlFeld.setText("");
            rechnungsNrFeld.setText("");
            produktNrFeld.setText("");

        } catch (Exception ex){
            JOptionPane.showMessageDialog(frame, "Fehler beim Einfügen der Position.");
            ex.printStackTrace();
        }

    }

}
