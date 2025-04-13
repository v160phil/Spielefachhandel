package spielefachhandel.Screens;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import spielefachhandel.Aktualisierbar;
import spielefachhandel.Spiel;
import spielefachhandel.Spielefachhandel;

public class SpielAusgabe extends JPanel implements Aktualisierbar{
    
    Spielefachhandel sfh;

    JScrollPane tableScrollPane = new JScrollPane();

    String[] spaltenNamen = { "produktNr", "name", "genre", "einzelpreis", "veroeffentlichungsdatum" };
    DefaultTableModel model = new DefaultTableModel(spaltenNamen, 0);
    JTable table = new JTable(model);

    public SpielAusgabe(Spielefachhandel sfh){

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
        
        for (Spiel s : sfh.getAlleSpiele()) {
            model.addRow(new Object[] { s.getProduktNr(), s.getName(), s.getGenre(), s.getEinzelpreis(), s.getVeroeffentlichungsdatum() });
        }
    }

}
