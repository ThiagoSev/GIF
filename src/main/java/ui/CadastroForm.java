package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.FlowLayout;

public class CadastroForm extends JFrame {

    public CadastroForm() {

        setTitle("Cadastro");

        setSize(300, 300);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new FlowLayout());

        add(new JLabel("Nome"));
        add(new JTextField(20));

        add(new JLabel("Senha"));
        add(new JPasswordField(20));

        add(new JLabel("Apelido"));
        add(new JTextField(20));

        add(new JLabel("Data"));
        add(new JTextField(20));

        JButton botao = new JButton("Cadastrar");

        add(botao);
    }
}