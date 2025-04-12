package spielefachhandel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import spielefachhandel.Screens.Hauptmenue;
import spielefachhandel.Screens.KundeAusgabe;
import spielefachhandel.Screens.KundeHinzufuegen;
import spielefachhandel.Screens.KundeSuche;
import spielefachhandel.Screens.PositionAusgabe;
import spielefachhandel.Screens.PositionHinzufuegen;
import spielefachhandel.Screens.PositionSuche;
import spielefachhandel.Screens.RechnungAusgabe;
import spielefachhandel.Screens.RechnungHinzufuegen;
import spielefachhandel.Screens.RechnungSuche;
import spielefachhandel.Screens.SpielAusgabe;
import spielefachhandel.Screens.SpielHinzufuegen;
import spielefachhandel.Screens.SpielSuche;
import spielefachhandel.Screens.Startbildschirm;
import spielefachhandel.Screens.Tabellenauswahl;
import java.util.HashMap;

public class SpielefachhandelGUI {

    static JButton btn_zurueck;

    public static Aktionauswahl aktionauswahl = Aktionauswahl.KEINE;

    static Spielefachhandel sfh1;

    private static Screen currentScreen = Screen.STARTBILDSCHIRM;

    static JFrame frame;

    static JPanel mainPanel;


    private static HashMap<Screen, JPanel> screens; 

    public static void goToScreen(Screen new_screen) {
        mainPanel.remove(screens.get(currentScreen));
        currentScreen = new_screen;
        mainPanel.add(screens.get(new_screen), BorderLayout.CENTER);

        btn_zurueck.setVisible(new_screen != Screen.STARTBILDSCHIRM && new_screen != Screen.HAUPTMENUE);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {

        DatabaseManager.getConnection();

        sfh1 = new Spielefachhandel("Java Store");

        screens = new HashMap<>() {{
            put(Screen.HAUPTMENUE, new Hauptmenue());
            put(Screen.STARTBILDSCHIRM, new Startbildschirm());
            put(Screen.TABELLENAUSWAHL, new Tabellenauswahl());
            put(Screen.KUNDEAUSGABE, new KundeAusgabe(sfh1));
            put(Screen.RECHNUNGAUSGABE, new RechnungAusgabe(sfh1));
            put(Screen.KUNDE_EINFUEGEN, new KundeHinzufuegen(sfh1, frame));
            put(Screen.RECHNUNG_EINFUEGEN, new RechnungHinzufuegen(sfh1, frame));
            put(Screen.POSITION_EINFUEGEN, new PositionHinzufuegen(sfh1, frame));
            put(Screen.SPIEL_EINFUEGEN, new SpielHinzufuegen(sfh1, frame));
            put(Screen.KUNDESUCHEN, new KundeSuche(sfh1, frame));
            put(Screen.RECHNUNGSUCHEN, new RechnungSuche(sfh1, frame));
            put(Screen.SPIELAUSGABE, new SpielAusgabe(sfh1));
            put(Screen.SPIELSUCHEN, new SpielSuche(sfh1, frame));
            put(Screen.POSITIONAUSGABE, new PositionAusgabe(sfh1));
            put(Screen.POSITIONSUCHEN, new PositionSuche(sfh1, frame));
        }};

        SwingUtilities.invokeLater(() -> {

            frame = new JFrame("Verwaltung des Spielefachhandels");
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout(10, 10));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            frame.add(mainPanel);

            btn_zurueck = new JButton("Zurück");
            btn_zurueck.setVisible(false);

            btn_zurueck.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    goToScreen(Screen.HAUPTMENUE);
                }
                
            });

            goToScreen(Screen.STARTBILDSCHIRM);

            mainPanel.add(btn_zurueck, BorderLayout.SOUTH);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    DatabaseManager.closeConnection();
                }
            });

            frame.setVisible(true);

        });

    }

}