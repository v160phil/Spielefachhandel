package spielefachhandel.Screens;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import spielefachhandel.Aktualisierbar;
import spielefachhandel.Rechnung;
import spielefachhandel.Spielefachhandel;

public class RechnungAusgabe extends JPanel implements Aktualisierbar{

    Spielefachhandel sfh;

    JScrollPane tableScrollPane = new JScrollPane();

    String[] spaltenNamen = { "rechnungsNr", "rechnungsdatum", "kundenNr" };
    DefaultTableModel model = new DefaultTableModel(spaltenNamen, 0);
    JTable table = new JTable(model);

    public RechnungAusgabe(Spielefachhandel sfh){

        this.sfh = sfh;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(new Tabellenauswahl());

        this.add(Box.createVerticalStrut(10));

        tableScrollPane.setViewportView(table);

        this.add(tableScrollPane);
        aktualisieren();

    }

    @Override
    public void aktualisieren(){

        model.setRowCount(0);
        
        for (Rechnung r : sfh.getAlleRechnungen()) {
            model.addRow(new Object[] { r.getRechnungsNr(), r.getRechnungsdatum(), r.getMeinKunde().getKundenNr() });
        }

    }
}
