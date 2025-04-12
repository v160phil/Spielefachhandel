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
import spielefachhandel.Spiel;
import spielefachhandel.Spielefachhandel;

public class SpielSuche extends JPanel {
    
    Spielefachhandel sfh;
    JFrame frame;

    JLabel lbl_eingabe = new JLabel("Geben Sie eine Produktnummer ein:");

    JTextField produktNrFeld = new JTextField();

    JButton btn_spielSuchen = new JButton("Spiel suchen");

    JScrollPane tableScrollPane = new JScrollPane();

    public SpielSuche(Spielefachhandel sfh, JFrame frame){

        this.sfh = sfh;
        this.frame = frame;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(new Tabellenauswahl());

        this.add(Box.createVerticalStrut(10));

        this.add(lbl_eingabe);
        this.add(produktNrFeld);
        this.add(btn_spielSuchen);

        btn_spielSuchen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                spielSuchen();
            }
        });

    }

    public void spielSuchen(){

        try {
            int produktNr = Integer.parseInt(produktNrFeld.getText());
            Spiel s = sfh.sucheSpiel(produktNr);

            if (s != null) {
                String[] spaltenNamen =  { "produktNr", "name", "genre", "einzelpreis", "veroeffentlichungsdatum" };

                DefaultTableModel model = new DefaultTableModel(spaltenNamen, 0);
                JTable table = new JTable(model);
                tableScrollPane.setViewportView(table);
                model.addRow(new Object[] {
                    s.getProduktNr(), s.getName(), s.getGenre(), s.getEinzelpreis(), s.getVeroeffentlichungsdatum()
                });

                this.add(tableScrollPane);
                this.revalidate();
                this.repaint();

            } else {
                JOptionPane.showMessageDialog(frame, "Spiel nicht gefunden.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Bitte eine gültige Produktnummer eingeben.");
        }

    }

}
