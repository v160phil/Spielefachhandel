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
import spielefachhandel.Rechnung;
import spielefachhandel.Spielefachhandel;

public class RechnungSuche extends JPanel{

    Spielefachhandel sfh;
    JFrame frame;

    JLabel lbl_eingabe = new JLabel("Geben Sie eine Rechnungsnummer ein:");

    JTextField rechnungsNrFeld = new JTextField();

    JButton btn_rechnungSuchen = new JButton("Rechnung suchen");

    JScrollPane tableScrollPane = new JScrollPane();

    public RechnungSuche(Spielefachhandel sfh, JFrame frame){

        this.sfh = sfh;
        this.frame = frame;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(new Tabellenauswahl());

        this.add(Box.createVerticalStrut(10));

        this.add(lbl_eingabe);
        this.add(rechnungsNrFeld);
        this.add(btn_rechnungSuchen);

        btn_rechnungSuchen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rechnungSuchen();
            }
        });

    }

    public void rechnungSuchen(){
        try {
            int rechnungsNr = Integer.parseInt(rechnungsNrFeld.getText());
            Rechnung r = sfh.sucheRechnung(rechnungsNr);

            if (r != null) {
                String[] spaltenNamen =  { "rechnungsNr", "rechnungsdatum", "kundenNr" };

                DefaultTableModel model = new DefaultTableModel(spaltenNamen, 0);
                JTable table = new JTable(model);
                tableScrollPane.setViewportView(table);
                model.addRow(new Object[] {
                    r.getRechnungsNr(), r.getRechnungsdatum(), r.getMeinKunde().getKundenNr()
                });

                this.add(tableScrollPane);
                this.revalidate();
                this.repaint();

            } else {
                JOptionPane.showMessageDialog(frame, "Rechnung nicht gefunden.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Bitte eine gültige Rechnungsnummer eingeben.");
        }
    }

}
