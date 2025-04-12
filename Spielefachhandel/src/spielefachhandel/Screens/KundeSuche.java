package spielefachhandel.Screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import spielefachhandel.Kunde;
import spielefachhandel.Spielefachhandel;

public class KundeSuche extends JPanel{
    
    Spielefachhandel sfh;
    JFrame frame;

    JLabel lbl_eingabe = new JLabel("Geben Sie eine Kundennummer ein:");

    JTextField kundenNrFeld = new JTextField();

    JButton btn_kundenSuchen = new JButton("Kunden suchen");

    JScrollPane tableScrollPane = new JScrollPane();

    public KundeSuche(Spielefachhandel sfh, JFrame frame){

        this.sfh = sfh;
        this.frame = frame;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(new Tabellenauswahl());

        this.add(Box.createVerticalStrut(10));

        this.add(lbl_eingabe);
        this.add(kundenNrFeld);
        this.add(btn_kundenSuchen);

        btn_kundenSuchen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                kundeSuchen();
            }
        });

    }

    public void kundeSuchen(){
        try {
            int kundenNr = Integer.parseInt(kundenNrFeld.getText());
            Kunde k = sfh.sucheKunde(kundenNr);

            if (k != null) {
                String[] spaltenNamen =  { "kundenNr", "vorname", "name", "plz", "strasse", "ort", "email",
                "telefonnummer" };

                DefaultTableModel model = new DefaultTableModel(spaltenNamen, 0);
                JTable table = new JTable(model);
                tableScrollPane.setViewportView(table);
                model.addRow(new Object[] {
                    k.getKundenNr(), k.getVorname(), k.getName(), k.getPlz(),
                    k.getStrasse(), k.getOrt(), k.getEmail(), k.getTelefonnummer()
                });

                this.add(tableScrollPane);
                this.revalidate();
                this.repaint();

            } else {
                JOptionPane.showMessageDialog(frame, "Kunde nicht gefunden.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Bitte eine gültige Kundennummer eingeben.");
        }
    }

}
