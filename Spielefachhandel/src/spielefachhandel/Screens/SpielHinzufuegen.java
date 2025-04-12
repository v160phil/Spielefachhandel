package spielefachhandel.Screens;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import spielefachhandel.DatabaseManager;
import spielefachhandel.Spiel;
import spielefachhandel.Spielefachhandel;

public class SpielHinzufuegen extends JPanel{
    
    Spielefachhandel sfh;
    JFrame frame;

    JTextField nameFeld = new JTextField();
    JTextField genreFeld = new JTextField();
    JTextField einzelpreisFeld = new JTextField();
    JTextField datumFeld = new JTextField();

    JButton btn_spielSpeichern = new JButton("Spiel speichern");

    JLabel lbl_name = new JLabel("Name:");
    JLabel lbl_genre = new JLabel("Genre:");
    JLabel lbl_einzelpreis = new JLabel("Einzelpreis:");
    JLabel lbl_datum = new JLabel("Veröffentlichungsdatum (im Format JJJJ-MM-TT):");

    public SpielHinzufuegen(Spielefachhandel sfh, JFrame frame){

        this.sfh = sfh;
        this.frame = frame;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(new Tabellenauswahl());

        this.add(Box.createVerticalStrut(10));

        var grid_neues_spiel = new JPanel();
        grid_neues_spiel.setLayout(new GridLayout(4, 2));
        grid_neues_spiel.add(lbl_name);
        grid_neues_spiel.add(nameFeld);
        grid_neues_spiel.add(lbl_genre);
        grid_neues_spiel.add(genreFeld);
        grid_neues_spiel.add(lbl_einzelpreis);
        grid_neues_spiel.add(einzelpreisFeld);
        grid_neues_spiel.add(lbl_datum);
        grid_neues_spiel.add(datumFeld);

        this.add(grid_neues_spiel);

        this.add(btn_spielSpeichern);

        btn_spielSpeichern.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                spielErstellen();
            }
            
        });

    }

    public void spielErstellen(){
        try {

            String name = nameFeld.getText();
            String genre = genreFeld.getText();
            double einzelpreis = Double.parseDouble(einzelpreisFeld.getText());
            Date veroeffentlichungsdatum = Date.valueOf(datumFeld.getText());
            Spiel neuesSpiel = sfh.addSpiel(name, genre, veroeffentlichungsdatum, einzelpreis);

            DatabaseManager.insertSpiel(neuesSpiel);
            JOptionPane.showMessageDialog(frame, "Spiel erfolgreich hinzugefügt!");

            nameFeld.setText("");
            genreFeld.setText("");
            einzelpreisFeld.setText("");
            datumFeld.setText("");

        } catch (Exception ex){
            JOptionPane.showMessageDialog(frame, "Fehler beim Einfügen des Spiels.");
            ex.printStackTrace();
        }
    }

}
