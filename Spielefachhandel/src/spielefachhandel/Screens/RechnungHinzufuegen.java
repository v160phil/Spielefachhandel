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
import spielefachhandel.Rechnung;
import spielefachhandel.Spielefachhandel;

public class RechnungHinzufuegen extends JPanel {
    
    Spielefachhandel sfh;
    JFrame frame;

    JTextField rechnungsdatumFeld = new JTextField(5);
    JTextField kundenNrFeld = new JTextField(5);

    JButton btn_rechnungSpeichern = new JButton("Rechnung speichern");

    JLabel lbl_rechnungsdatum = new JLabel("Rechnungsdatum (im Format JJJJ-MM-TT):");
    JLabel lbl_kundenNr = new JLabel("Die zugehörige Kundennummer:");

    public RechnungHinzufuegen(Spielefachhandel sfh, JFrame frame){

        this.sfh = sfh;
        this.frame = frame;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(new Tabellenauswahl());

        this.add(Box.createVerticalStrut(10));

        var grid_neue_rechnung = new JPanel();
        grid_neue_rechnung.setLayout(new GridLayout(2, 2));
        grid_neue_rechnung.add(lbl_rechnungsdatum);
        grid_neue_rechnung.add(rechnungsdatumFeld);
        grid_neue_rechnung.add(lbl_kundenNr);
        grid_neue_rechnung.add(kundenNrFeld);

        this.add(grid_neue_rechnung);

        this.add(btn_rechnungSpeichern);

        btn_rechnungSpeichern.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rechnungErstellen();
            }
            
        });

    }

    public void rechnungErstellen(){
        try {

            Date datum = Date.valueOf(rechnungsdatumFeld.getText());
            int kundenNr = Integer.parseInt(kundenNrFeld.getText());
            Rechnung neueRechnung = sfh.erstelleRechnung(datum, sfh.sucheKunde(kundenNr));

            DatabaseManager.insertRechnung(neueRechnung);
            JOptionPane.showMessageDialog(frame, "Rechnung erfolgreich hinzugefügt!");

            rechnungsdatumFeld.setText("");
            kundenNrFeld.setText("");

        } catch (Exception ex){
            JOptionPane.showMessageDialog(frame, "Fehler beim Einfügen der Rechnung.");
            ex.printStackTrace();
        }
    }

}
