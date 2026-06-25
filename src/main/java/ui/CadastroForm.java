package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import model.Usuario;
import service.UsuarioService;

public class CadastroForm extends JFrame {

    private static final Color BACKGROUND = new Color(18, 22, 28);
    private static final Color HEADER = new Color(19, 23, 30);
    private static final Color CARD = new Color(25, 43, 60);
    private static final Color CARD_DARK = new Color(18, 22, 29);
    private static final Color BORDER = new Color(43, 76, 104);
    private static final Color GREEN = new Color(52, 168, 83);
    private static final Color MUTED = new Color(154, 164, 178);
    private static final Color WHITE = new Color(245, 247, 250);

    private JTextField txtNome;
    private JPasswordField txtSenha;
    private JTextField txtApelido;
    private JTextField txtData;
    private JLabel lblMensagem;

    public CadastroForm() {
        setTitle("GIF - Cadastro");
        setSize(1180, 820);
        setMinimumSize(new Dimension(900, 620));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(criarConteudo());
    }

    private JPanel criarConteudo() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BACKGROUND);
        root.add(criarTopo(), BorderLayout.NORTH);
        root.add(criarCentro(), BorderLayout.CENTER);
        root.add(criarRodape(), BorderLayout.SOUTH);
        return root;
    }

    private JPanel criarTopo() {
        JPanel topo = new JPanel(new BorderLayout());
        topo.setBackground(HEADER);
        topo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER));
        topo.setPreferredSize(new Dimension(0, 64));

        JPanel logo = new JPanel();
        logo.setOpaque(false);
        logo.setBorder(new EmptyBorder(0, 88, 0, 0));
        logo.setLayout(new BoxLayout(logo, BoxLayout.X_AXIS));
        logo.add(new GameIcon(34, GREEN));
        logo.add(Box.createHorizontalStrut(9));
        logo.add(label("GIF", 24, Font.BOLD, WHITE));

        topo.add(logo, BorderLayout.WEST);
        return topo;
    }

    private JPanel criarCentro() {
        JPanel faixa = new JPanel();
        faixa.setLayout(new BoxLayout(faixa, BoxLayout.Y_AXIS));
        faixa.setBackground(BACKGROUND);
        faixa.setBorder(new EmptyBorder(26, 0, 34, 0));

        JPanel formulario = criarFormulario();
        formulario.setAlignmentX(CENTER_ALIGNMENT);
        faixa.add(formulario);

        JScrollPane scroll = new JScrollPane(faixa);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setBackground(BACKGROUND);
        scroll.getViewport().setBackground(BACKGROUND);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(BACKGROUND);
        container.add(scroll, BorderLayout.CENTER);
        return container;
    }

    private JPanel criarFormulario() {
        RoundedPanel card = new RoundedPanel(CARD, BORDER, 8);
        card.setLayout(new BorderLayout(0, 24));
        card.setBorder(new EmptyBorder(30, 36, 30, 36));

        JPanel titulo = new JPanel();
        titulo.setOpaque(false);
        titulo.setLayout(new BoxLayout(titulo, BoxLayout.Y_AXIS));
        JLabel tituloPrincipal = label("Criar Conta", 30, Font.BOLD, WHITE);
        tituloPrincipal.setAlignmentX(LEFT_ALIGNMENT);
        titulo.add(tituloPrincipal);
        titulo.add(Box.createVerticalStrut(4));
        JLabel subtitulo = label("Preencha seus dados para entrar na plataforma GIF", 14, Font.PLAIN, MUTED);
        subtitulo.setAlignmentX(LEFT_ALIGNMENT);
        titulo.add(subtitulo);

        JPanel linha = new JPanel();
        linha.setBackground(BORDER);
        linha.setPreferredSize(new Dimension(0, 1));

        JPanel campos = new JPanel(new GridBagLayout());
        campos.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 14, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.gridx = 0;

        txtNome = campoTexto("");
        txtSenha = campoSenha();
        txtApelido = campoTexto("");
        txtData = campoTexto("dd/mm/aaaa");

        adicionarCampo(campos, gbc, 0, "Nome", txtNome);
        adicionarCampo(campos, gbc, 1, "Senha", txtSenha);
        adicionarCampo(campos, gbc, 2, "Apelido", txtApelido);
        adicionarCampo(campos, gbc, 3, "Data de nascimento", txtData);

        JButton cadastrar = botaoPrimario("Cadastrar");
        cadastrar.addActionListener(event -> cadastrarUsuario());

        lblMensagem = label(" ", 13, Font.BOLD, MUTED);
        lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel acoes = new JPanel(new BorderLayout(0, 10));
        acoes.setOpaque(false);
        acoes.add(cadastrar, BorderLayout.NORTH);
        acoes.add(lblMensagem, BorderLayout.SOUTH);

        JPanel corpo = new JPanel(new BorderLayout(0, 22));
        corpo.setOpaque(false);
        corpo.add(linha, BorderLayout.NORTH);
        corpo.add(campos, BorderLayout.CENTER);
        corpo.add(acoes, BorderLayout.SOUTH);

        card.add(titulo, BorderLayout.NORTH);
        card.add(corpo, BorderLayout.CENTER);

        Dimension tamanho = card.getPreferredSize();
        card.setPreferredSize(new Dimension(720, tamanho.height));
        card.setMaximumSize(new Dimension(720, tamanho.height));
        return card;
    }

    private void adicionarCampo(JPanel painel, GridBagConstraints gbc, int linha, String rotulo, JTextField campo) {
        gbc.gridy = linha * 2;
        painel.add(label(rotulo, 14, Font.BOLD, WHITE), gbc);
        gbc.gridy = linha * 2 + 1;
        painel.add(campo, gbc);
    }

    private JPanel criarRodape() {
        JPanel rodape = new JPanel(new BorderLayout());
        rodape.setBackground(HEADER);
        rodape.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER),
                new EmptyBorder(10, 92, 10, 92)));
        rodape.setPreferredSize(new Dimension(0, 52));

        JPanel marca = new JPanel();
        marca.setOpaque(false);
        marca.setLayout(new BoxLayout(marca, BoxLayout.X_AXIS));
        marca.add(new GameIcon(24, GREEN));
        marca.add(Box.createHorizontalStrut(8));
        marca.add(label("GIF", 18, Font.BOLD, MUTED));

        JLabel direitos = label("© 2024 Games do Instituto Federal. Todos os direitos reservados.", 13, Font.PLAIN, MUTED);
        JLabel links = label("Termos    Privacidade    Suporte", 13, Font.PLAIN, MUTED);

        rodape.add(marca, BorderLayout.WEST);
        rodape.add(direitos, BorderLayout.CENTER);
        rodape.add(links, BorderLayout.EAST);
        return rodape;
    }

    private void cadastrarUsuario() {
        String nome = txtNome.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();
        String apelido = txtApelido.getText().trim();
        Date dataNascimento = converterData(txtData.getText().trim());

        if (nome.isEmpty() || senha.isEmpty() || apelido.isEmpty() || dataNascimento == null) {
            mensagem("Preencha todos os campos. Use a data no formato dd/mm/aaaa.", false);
            return;
        }

        Usuario usuario = new Usuario(nome, senha, apelido, dataNascimento);
        boolean cadastrado = new UsuarioService().CadastrarUsuario(usuario);
        mensagem(cadastrado ? "Cadastro realizado com sucesso!" : "Nao foi possivel realizar o cadastro.", cadastrado);
    
        this.dispose();
        new LoginForm().setVisible(true);
    }

    private Date converterData(String texto) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            format.setLenient(false);
            return format.parse(texto);
        } catch (ParseException ex) {
            return null;
        }
    }

    private void mensagem(String texto, boolean sucesso) {
        lblMensagem.setForeground(sucesso ? GREEN : new Color(239, 83, 80));
        lblMensagem.setText(texto);
    }

    private JLabel label(String texto, int tamanho, int estilo, Color cor) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", estilo, tamanho));
        label.setForeground(cor);
        return label;
    }

    private JTextField campoTexto(String placeholder) {
        JTextField campo = new JTextField(placeholder);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setForeground(placeholder.isEmpty() ? WHITE : MUTED);
        campo.setBackground(CARD_DARK);
        campo.setCaretColor(WHITE);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(8, 12, 8, 12)));
        campo.setPreferredSize(new Dimension(0, 38));
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!placeholder.isEmpty() && campo.getText().equals(placeholder)) {
                    campo.setText("");
                    campo.setForeground(WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!placeholder.isEmpty() && campo.getText().isBlank()) {
                    campo.setText(placeholder);
                    campo.setForeground(MUTED);
                }
            }
        });
        return campo;
    }

    private JPasswordField campoSenha() {
        JPasswordField campo = new JPasswordField();
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setForeground(WHITE);
        campo.setBackground(CARD_DARK);
        campo.setCaretColor(WHITE);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(8, 12, 8, 12)));
        campo.setPreferredSize(new Dimension(0, 38));
        return campo;
    }

    private JButton botaoPrimario(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setForeground(WHITE);
        botao.setBackground(new Color(49, 83, 108));
        botao.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        return botao;
    }

    private JButton botaoSecundario(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 13));
        botao.setForeground(MUTED);
        botao.setBackground(CARD_DARK);
        botao.setHorizontalAlignment(SwingConstants.LEFT);
        botao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(8, 14, 8, 14)));
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        return botao;
    }

    private static class RoundedPanel extends JPanel {
        private final Color background;
        private final Color border;
        private final int radius;

        RoundedPanel(Color background, Color border, int radius) {
            this.background = background;
            this.border = border;
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(background);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.setColor(border);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class Avatar extends JPanel {
        Avatar() {
            setPreferredSize(new Dimension(96, 96));
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(45, 72, 87));
            g2.fillOval(2, 2, 92, 92);
            g2.setColor(new Color(37, 100, 82));
            g2.setStroke(new java.awt.BasicStroke(3));
            g2.drawOval(2, 2, 92, 92);
            g2.setColor(new Color(164, 176, 186));
            g2.setStroke(new java.awt.BasicStroke(4));
            g2.drawOval(39, 30, 16, 16);
            g2.drawArc(31, 53, 32, 25, 0, 180);
            g2.dispose();
        }
    }

    private static class GameIcon extends JPanel {
        private final int size;
        private final Color color;

        GameIcon(int size, Color color) {
            this.size = size;
            this.color = color;
            setPreferredSize(new Dimension(size, size));
            setMaximumSize(new Dimension(size, size));
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new java.awt.BasicStroke(Math.max(2, size / 12)));
            int y = size / 3;
            g2.drawRoundRect(size / 8, y, size * 3 / 4, size / 2, size / 5, size / 5);
            g2.drawLine(size / 3, y + size / 6, size / 3, y + size / 3);
            g2.drawLine(size / 4, y + size / 4, size * 5 / 12, y + size / 4);
            g2.fillOval(size * 2 / 3, y + size / 5, size / 10, size / 10);
            g2.fillOval(size * 3 / 4, y + size / 3, size / 10, size / 10);
            g2.dispose();
        }
    }
}
