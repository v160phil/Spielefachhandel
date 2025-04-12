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

public class Startbildschirm extends JPanel {
    
    JLabel lbl_start = new JLabel("Willkommen bei der Administration des Spielefachhandels!");

    JButton btn_start = new JButton("Starten");

    public Startbildschirm(){

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(lbl_start);
        this.add(Box.createVerticalStrut(10));
        this.add(btn_start);

        btn_start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                SpielefachhandelGUI.goToScreen(Screen.HAUPTMENUE);
            }
        });

    }

}
