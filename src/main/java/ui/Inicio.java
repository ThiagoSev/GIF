package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Inicio extends JFrame {

        public Inicio() {

                setTitle("Tela Inicial");

                setSize(500, 300);

                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                setLocationRelativeTo(null);

                JPanel panel = new JPanel();

                JLabel label = new JLabel(
                                "PARABÉNS! Login realizado com sucesso 😎");

                panel.add(label);

                add(panel);
        }
}