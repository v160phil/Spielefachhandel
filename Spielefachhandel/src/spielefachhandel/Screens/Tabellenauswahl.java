package spielefachhandel.Screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import spielefachhandel.Screen;
import spielefachhandel.SpielefachhandelGUI;

public class Tabellenauswahl extends JPanel{
    
    JLabel lbl_tabellenauswahl = new JLabel("Wählen Sie eine Tabelle aus:");

    JButton btn_kunde = new JButton("Kunde");
    JButton btn_rechnung = new JButton("Rechnung");
    JButton btn_position = new JButton("Position");
    JButton btn_spiel = new JButton("Spiel");

    public Tabellenauswahl(){

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.add(lbl_tabellenauswahl);
        this.add(Box.createHorizontalStrut(10));
        this.add(btn_kunde);
        this.add(Box.createHorizontalStrut(10));
        this.add(btn_rechnung);
        this.add(Box.createHorizontalStrut(10));
        this.add(btn_position);
        this.add(Box.createHorizontalStrut(10));
        this.add(btn_spiel);

        btn_kunde.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switch (SpielefachhandelGUI.aktionauswahl) {
                    case DATENANZEIGE:
                        SpielefachhandelGUI.goToScreen(Screen.KUNDEAUSGABE);
                        break;
                
                    case DATENSUCHE:
                        SpielefachhandelGUI.goToScreen(Screen.KUNDESUCHEN);
                        break;

                    case DATENEINTRAG:
                        SpielefachhandelGUI.goToScreen(Screen.KUNDE_EINFUEGEN);
                        break;

                    default:
                        break;
                }
            }
            
        });

        btn_rechnung.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switch (SpielefachhandelGUI.aktionauswahl) {
                    case DATENANZEIGE:
                        SpielefachhandelGUI.goToScreen(Screen.RECHNUNGAUSGABE);
                        break;
                
                    case DATENSUCHE:
                        SpielefachhandelGUI.goToScreen(Screen.RECHNUNGSUCHEN);
                        break;

                    case DATENEINTRAG:
                        SpielefachhandelGUI.goToScreen(Screen.RECHNUNG_EINFUEGEN);
                        break;

                    default:
                        break;
                }
            }
            
        });

        btn_position.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switch (SpielefachhandelGUI.aktionauswahl) {
                    case DATENANZEIGE:
                        SpielefachhandelGUI.goToScreen(Screen.POSITIONAUSGABE);
                        break;
                
                    case DATENSUCHE:
                        SpielefachhandelGUI.goToScreen(Screen.POSITIONSUCHEN);
                        break;

                    case DATENEINTRAG:
                        SpielefachhandelGUI.goToScreen(Screen.POSITION_EINFUEGEN);
                        break;

                    default:
                        break;
                }
            }
            
        });

        btn_spiel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switch (SpielefachhandelGUI.aktionauswahl) {
                    case DATENANZEIGE:
                        SpielefachhandelGUI.goToScreen(Screen.SPIELAUSGABE);
                        break;
                
                    case DATENSUCHE:
                        SpielefachhandelGUI.goToScreen(Screen.SPIELSUCHEN);
                        break;

                    case DATENEINTRAG:
                        SpielefachhandelGUI.goToScreen(Screen.SPIEL_EINFUEGEN);
                        break;

                    default:
                        break;
                }
            }
            
        });

    }

}
