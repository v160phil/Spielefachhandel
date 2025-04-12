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
import spielefachhandel.Position;
import spielefachhandel.Spielefachhandel;

public class PositionSuche extends JPanel{

    Spielefachhandel sfh;
    JFrame frame;

    JLabel lbl_eingabe = new JLabel("Geben Sie eine ID ein:");

    JTextField idFeld = new JTextField();

    JButton btn_positionSuchen = new JButton("Position suchen");

    JScrollPane tableScrollPane = new JScrollPane();
    
    public PositionSuche(Spielefachhandel sfh, JFrame frame){

        this.sfh = sfh;
        this.frame = frame;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(new Tabellenauswahl());

        this.add(Box.createVerticalStrut(10));

        this.add(lbl_eingabe);
        this.add(idFeld);
        this.add(btn_positionSuchen);

        btn_positionSuchen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                positionSuchen();
            }
        });

    }

    public void positionSuchen(){

        try {
            int id = Integer.parseInt(idFeld.getText());
            Position p = sfh.suchePosition(id);

            if (p != null) {
                String[] spaltenNamen =  { "autoID", "anzahl", "rechnungsNr", "produktNr" };

                DefaultTableModel model = new DefaultTableModel(spaltenNamen, 0);
                JTable table = new JTable(model);
                tableScrollPane.setViewportView(table);
                model.addRow(new Object[] {
                    p.getID(), p.getAnzahl(), p.getMeineRechnung().getRechnungsNr(), p.getMeinSpiel().getProduktNr()
                });

                this.add(tableScrollPane);
                this.revalidate();
                this.repaint();

            } else {
                JOptionPane.showMessageDialog(frame, "Position nicht gefunden.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Bitte eine gültige ID eingeben.");
        }
    }

}
