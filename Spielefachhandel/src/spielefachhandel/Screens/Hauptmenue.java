package spielefachhandel.Screens;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import spielefachhandel.SpielefachhandelGUI;
import spielefachhandel.Aktionauswahl;
import spielefachhandel.Screen;

public class Hauptmenue extends JPanel{
    
    JLabel lbl_auswahl = new JLabel("Was möchten Sie tun?");

    JButton btn_daten_ausgeben = new JButton("Daten ausgeben");
    JButton btn_daten_suchen = new JButton("Nach einem Datensatz suchen");
    JButton btn_daten_einfuegen = new JButton("Datensatz einfügen");

    public Hauptmenue(){

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(lbl_auswahl);
        this.add(Box.createVerticalStrut(10));
        this.add(btn_daten_ausgeben);
        this.add(Box.createVerticalStrut(10));
        this.add(btn_daten_suchen);
        this.add(Box.createVerticalStrut(10));
        this.add(btn_daten_einfuegen);

        btn_daten_ausgeben.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpielefachhandelGUI.goToScreen(Screen.TABELLENAUSWAHL);
                SpielefachhandelGUI.aktionauswahl = Aktionauswahl.DATENANZEIGE;
            }
            
        });

        btn_daten_suchen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                SpielefachhandelGUI.goToScreen(Screen.TABELLENAUSWAHL);
                SpielefachhandelGUI.aktionauswahl = Aktionauswahl.DATENSUCHE;

            }
            
        });

        btn_daten_einfuegen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SpielefachhandelGUI.goToScreen(Screen.TABELLENAUSWAHL);
                SpielefachhandelGUI.aktionauswahl = Aktionauswahl.DATENEINTRAG;
            }
            
        });

    }

}
