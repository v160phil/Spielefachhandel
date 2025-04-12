package spielefachhandel.Screens;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import spielefachhandel.Kunde;
import spielefachhandel.Spielefachhandel;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class KundeAusgabe extends JPanel {
    
    Spielefachhandel sfh;

    JScrollPane tableScrollPane = new JScrollPane();

    String[] spaltenNamen = { "kundenNr", "vorname", "name", "plz", "strasse", "ort", "email",
                                    "telefonnummer" };
    DefaultTableModel model = new DefaultTableModel(spaltenNamen, 0);
    JTable table = new JTable(model);

    public KundeAusgabe(Spielefachhandel sfh){

        this.sfh = sfh;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(new Tabellenauswahl());

        this.add(Box.createVerticalStrut(10));

        tableScrollPane.setViewportView(table);

        this.add(tableScrollPane);
        kundenAusgeben();
    }

    public void kundenAusgeben(){

        for (Kunde k : sfh.getAlleKunden()) {
            model.addRow(new Object[] { k.getKundenNr(), k.getVorname(), k.getName(), k.getPlz(),
                    k.getStrasse(), k.getOrt(), k.getEmail(), k.getTelefonnummer() });
        }

    }

}
