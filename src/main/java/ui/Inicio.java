package ui;

import dao.JogoDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import model.CarrinhoModel;
import model.Jogo;
import model.Usuario;
import service.CarrinhoService;
import util.Sessao;

public class Inicio extends JFrame {

    private static final Color FUNDO = new Color(22, 26, 33);
    private static final Color TOPO = new Color(24, 28, 36);
    private static final Color PAINEL = new Color(27, 44, 60);
    private static final Color BORDA = new Color(44, 82, 112);
    private static final Color VERDE = new Color(59, 185, 86);
    private static final Color TEXTO_SUAVE = new Color(164, 174, 186);

    private Usuario usuarioLogado;
    private CarrinhoModel carrinhoDoUsuario;
    private List<Jogo> jogosDaLoja;
    private JPanel vitrinePanel;
    private JButton btnCarrinho;

    public Inicio() {
        this.usuarioLogado = Sessao.getUsuarioLogado();
        carregarCarrinho();
        carregarJogos();
        configurarJanela();
        construirTela();
    }

    private void configurarJanela() {
        setTitle("GIF - Loja");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(960, 720));
        setSize(1220, 780);
        setLocationRelativeTo(null);
    }

    private void carregarCarrinho() {
        if (usuarioLogado == null) {
            return;
        }

        CarrinhoService service = new CarrinhoService();
        carrinhoDoUsuario = service.BuscarCarrinho(usuarioLogado);
    }

    private void carregarJogos() {
        jogosDaLoja = new ArrayList<>();

        try {
            List<Jogo> jogosBanco = new JogoDAO().listarJogos();
            for (Jogo jogoResumo : jogosBanco) {
                Jogo jogoCompleto = new JogoDAO().buscarPorId(jogoResumo.getId());
                if (jogoCompleto != null) {
                    jogosDaLoja.add(completarJogo(jogoCompleto));
                }
            }
        } catch (Exception ex) {
            jogosDaLoja.clear();
        }

        if (jogosDaLoja.isEmpty()) {
            jogosDaLoja.addAll(criarJogosDeExemplo());
        }
    }

    private Jogo completarJogo(Jogo jogo) {
        if (estaVazio(jogo.getDescricao())) {
            jogo.setDescricao("Explore uma experiencia completa na loja GIF, com progresso, desafios e compra integrada ao carrinho.");
        }
        if (jogo.getPrecoPadrao() == null) {
            jogo.setPrecoPadrao(new BigDecimal("49.90"));
        }
        if (jogo.getDataLancamento() == null) {
            jogo.setDataLancamento(LocalDateTime.now());
        }
        if (estaVazio(jogo.getImagem()) || !new File(jogo.getImagem()).exists()) {
            jogo.setImagem(caminhoImagem("stardewValley.jpg"));
        }
        return jogo;
    }

    private List<Jogo> criarJogosDeExemplo() {
        List<Jogo> jogos = new ArrayList<>();
        jogos.add(criarJogo("Campus Simulator 2024", "Simulacao", "Monte sua rotina, encare trabalhos, provas e decisoes que mudam sua jornada academica.", "49.90", "stardewValley.jpg"));
        jogos.add(criarJogo("Calculo IV: O Despertar", "Puzzle", "Resolva desafios matematicos em fases cada vez mais intensas.", "39.90", "inside.jpg"));
        jogos.add(criarJogo("TCC: The Final Boss", "Acao", "Organize referencias, sobreviva as bancas e derrote o prazo final.", "29.90", "DeadCells.jpg"));
        jogos.add(criarJogo("Laboratorio Infinito", "Estrategia", "Teste misturas, descubra padroes e desbloqueie novas pesquisas.", "24.90", "celeste.jpg"));
        jogos.add(criarJogo("Codigo em Silksong", "Aventura", "Avance por mapas cheios de logica, bugs e pequenas vitorias.", "59.90", "silksong.jpeg"));
        jogos.add(criarJogo("Cuphead: Semana de Provas", "Arcade", "Uma maratona rapida, bonita e impiedosa contra atividades acumuladas.", "19.90", "cuphead.jpg"));
        return jogos;
    }

    private Jogo criarJogo(String titulo, String subtitulo, String descricao, String preco, String imagem) {
        Jogo jogo = new Jogo();
        jogo.setTitulo(titulo);
        jogo.setSubtitulo(subtitulo);
        jogo.setDescricao(descricao);
        jogo.setPrecoPadrao(new BigDecimal(preco));
        jogo.setDataLancamento(LocalDateTime.now());
        jogo.setImagem(caminhoImagem(imagem));
        return jogo;
    }

    private String caminhoImagem(String nomeImagem) {
        return new File("GIF-main/imagens/jogos/" + nomeImagem).getAbsolutePath();
    }

    private void construirTela() {
        JPanel raiz = new JPanel(new BorderLayout());
        raiz.setBackground(FUNDO);

        raiz.add(criarBarraSuperior(), BorderLayout.NORTH);

        vitrinePanel = new JPanel();
        vitrinePanel.setLayout(new BoxLayout(vitrinePanel, BoxLayout.Y_AXIS));
        vitrinePanel.setBackground(FUNDO);
        vitrinePanel.setBorder(BorderFactory.createEmptyBorder(26, 32, 40, 32));
        vitrinePanel.add(criarDestaque());
        vitrinePanel.add(Box.createVerticalStrut(34));
        vitrinePanel.add(criarOfertas());

        JScrollPane scroll = new JScrollPane(vitrinePanel);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(18);
        scroll.getViewport().setBackground(FUNDO);
        raiz.add(scroll, BorderLayout.CENTER);

        setContentPane(raiz);
    }

    private JPanel criarBarraSuperior() {
        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(TOPO);
        barra.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, BORDA),
                BorderFactory.createEmptyBorder(12, 54, 12, 54)
        ));

        JPanel navegacao = new JPanel();
        navegacao.setOpaque(false);
        navegacao.setLayout(new BoxLayout(navegacao, BoxLayout.X_AXIS));

        JLabel marca = new JLabel("GIF");
        marca.setForeground(Color.WHITE);
        marca.setFont(new Font("Segoe UI", Font.BOLD, 24));

        navegacao.add(marca);
        navegacao.add(Box.createHorizontalStrut(28));
        navegacao.add(criarLinkTopo("Loja", true, () -> vitrinePanel.getParent().revalidate()));
        navegacao.add(Box.createHorizontalStrut(24));
        navegacao.add(criarLinkTopo("Biblioteca", false, this::abrirBiblioteca));
        navegacao.add(Box.createHorizontalStrut(24));
        navegacao.add(criarLinkTopo("Conta", false, this::abrirConta));

        JPanel acoes = new JPanel();
        acoes.setOpaque(false);
        acoes.setLayout(new BoxLayout(acoes, BoxLayout.X_AXIS));

        JTextField pesquisa = new JTextField("  Buscar jogos...");
        pesquisa.setPreferredSize(new Dimension(250, 36));
        pesquisa.setMaximumSize(new Dimension(250, 36));
        pesquisa.setBackground(new Color(25, 43, 60));
        pesquisa.setForeground(TEXTO_SUAVE);
        pesquisa.setCaretColor(Color.WHITE);
        pesquisa.setBorder(BorderFactory.createLineBorder(BORDA));
        pesquisa.addActionListener(e -> pesquisarJogo(pesquisa.getText().trim()));

        btnCarrinho = criarBotaoEscuro("Carrinho");
        btnCarrinho.addActionListener(e -> abrirCarrinho());
        atualizarBotaoCarrinho();

        JButton btnAdicionar = criarBotaoEscuro("Adicionar jogo");
        btnAdicionar.addActionListener(e -> abrirCadastroJogo());

        acoes.add(pesquisa);
        acoes.add(Box.createHorizontalStrut(14));
        acoes.add(btnCarrinho);
        acoes.add(Box.createHorizontalStrut(10));
        acoes.add(btnAdicionar);

        barra.add(navegacao, BorderLayout.WEST);
        barra.add(acoes, BorderLayout.EAST);
        return barra;
    }

    private JLabel criarLinkTopo(String texto, boolean ativo, Runnable acao) {
        JLabel link = new JLabel(texto);
        link.setForeground(ativo ? Color.WHITE : TEXTO_SUAVE);
        link.setFont(new Font("Segoe UI", ativo ? Font.BOLD : Font.PLAIN, 14));
        link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        link.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                acao.run();
            }
        });
        return link;
    }

    private JPanel criarDestaque() {
        Jogo principal = jogosDaLoja.get(0);
        JPanel painel = new JPanel(new BorderLayout(18, 0));
        painel.setBackground(PAINEL);
        painel.setBorder(BorderFactory.createLineBorder(BORDA));
        painel.setMaximumSize(new Dimension(860, 380));
        painel.setAlignmentX(CENTER_ALIGNMENT);

        ImagePanel hero = new ImagePanel(principal.getImagem(), true);
        hero.setLayout(null);
        hero.setPreferredSize(new Dimension(560, 380));

        JLabel titulo = new JLabel("<html>" + principal.getTitulo().replaceFirst(" ", "<br>") + "</html>");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 42));
        titulo.setBounds(32, 178, 470, 110);

        JLabel categoria = criarEtiqueta(principal.getSubtitulo());
        categoria.setBounds(32, 290, 128, 25);

        JButton preco = criarBotaoPreco(formatarPreco(principal));
        preco.setBounds(32, 330, 118, 42);
        preco.addActionListener(e -> abrirJogo(principal));

        JButton comprar = criarBotaoClaro("Ver detalhes");
        comprar.setBounds(166, 330, 162, 42);
        comprar.addActionListener(e -> abrirJogo(principal));

        hero.add(titulo);
        hero.add(categoria);
        hero.add(preco);
        hero.add(comprar);
        hero.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        hero.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirJogo(principal);
            }
        });

        JPanel lista = new JPanel(new GridLayout(3, 1, 0, 10));
        lista.setOpaque(false);
        lista.setBorder(BorderFactory.createEmptyBorder(16, 0, 16, 16));
        for (int i = 0; i < Math.min(3, jogosDaLoja.size()); i++) {
            lista.add(criarMiniCard(jogosDaLoja.get(i), i == 0));
        }

        painel.add(hero, BorderLayout.CENTER);
        painel.add(lista, BorderLayout.EAST);
        return painel;
    }

    private JPanel criarMiniCard(Jogo jogo, boolean ativo) {
        ImagePanel card = new ImagePanel(jogo.getImagem(), true);
        card.setPreferredSize(new Dimension(240, 100));
        card.setBorder(BorderFactory.createLineBorder(ativo ? VERDE : PAINEL, ativo ? 2 : 1));
        card.setLayout(new BorderLayout());
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel titulo = new JLabel(jogo.getTitulo());
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        titulo.setBorder(BorderFactory.createEmptyBorder(62, 12, 8, 12));
        card.add(titulo, BorderLayout.SOUTH);
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirJogo(jogo);
            }
        });
        return card;
    }

    private JPanel criarOfertas() {
        JPanel secao = new JPanel();
        secao.setOpaque(false);
        secao.setLayout(new BoxLayout(secao, BoxLayout.Y_AXIS));
        secao.setMaximumSize(new Dimension(860, 980));
        secao.setAlignmentX(CENTER_ALIGNMENT);

        JPanel tituloLinha = new JPanel();
        tituloLinha.setOpaque(false);
        tituloLinha.setLayout(new BoxLayout(tituloLinha, BoxLayout.X_AXIS));
        tituloLinha.setAlignmentX(CENTER_ALIGNMENT);
        tituloLinha.setMaximumSize(new Dimension(860, 36));

        JLabel titulo = new JLabel("Ofertas Especiais");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JLabel tempo = new JLabel("  (Tempo Limitado)");
        tempo.setForeground(TEXTO_SUAVE);
        tempo.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        tituloLinha.add(titulo);
        tituloLinha.add(tempo);
        tituloLinha.add(Box.createHorizontalGlue());

        JPanel grid = new JPanel(new GridLayout(0, 2, 24, 24));
        grid.setOpaque(false);
        grid.setAlignmentX(CENTER_ALIGNMENT);
        grid.setMaximumSize(new Dimension(860, 980));

        for (int i = 1; i < jogosDaLoja.size(); i++) {
            grid.add(criarCardOferta(jogosDaLoja.get(i), i % 2 == 0 ? "-25%" : "-50%"));
        }

        secao.add(tituloLinha);
        secao.add(Box.createVerticalStrut(18));
        secao.add(grid);
        return secao;
    }

    private JPanel criarCardOferta(Jogo jogo, String desconto) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(PAINEL);
        card.setBorder(BorderFactory.createLineBorder(BORDA));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        ImagePanel imagem = new ImagePanel(jogo.getImagem(), false);
        imagem.setPreferredSize(new Dimension(390, 250));
        imagem.setLayout(null);

        JLabel selo = new JLabel(desconto, SwingConstants.CENTER);
        selo.setOpaque(true);
        selo.setBackground(new Color(207, 47, 51));
        selo.setForeground(Color.WHITE);
        selo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        selo.setBounds(14, 14, 58, 28);
        imagem.add(selo);

        JPanel info = new JPanel();
        info.setBackground(PAINEL);
        info.setBorder(BorderFactory.createEmptyBorder(14, 16, 16, 16));
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel(jogo.getTitulo());
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 17));

        JLabel preco = new JLabel(formatarPreco(jogo));
        preco.setForeground(VERDE);
        preco.setFont(new Font("Segoe UI", Font.BOLD, 16));

        info.add(titulo);
        info.add(Box.createVerticalStrut(8));
        info.add(preco);

        card.add(imagem, BorderLayout.CENTER);
        card.add(info, BorderLayout.SOUTH);
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirJogo(jogo);
            }
        });
        return card;
    }

    private JLabel criarEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(estaVazio(texto) ? "Aventura" : texto, SwingConstants.CENTER);
        etiqueta.setOpaque(true);
        etiqueta.setBackground(new Color(52, 82, 108));
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return etiqueta;
    }

    private JButton criarBotaoPreco(String texto) {
        JButton botao = new JButton(texto);
        botao.setBackground(VERDE);
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return botao;
    }

    private JButton criarBotaoClaro(String texto) {
        JButton botao = new JButton(texto);
        botao.setBackground(Color.WHITE);
        botao.setForeground(new Color(20, 24, 30));
        botao.setFont(new Font("Segoe UI", Font.BOLD, 13));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return botao;
    }

    private JButton criarBotaoEscuro(String texto) {
        JButton botao = new JButton(texto);
        botao.setBackground(new Color(31, 47, 64));
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 12));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDA),
                BorderFactory.createEmptyBorder(8, 14, 8, 14)
        ));
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return botao;
    }

    private void atualizarBotaoCarrinho() {
        int quantidade = 0;
        if (usuarioLogado != null) {
            try {
                List<Jogo> jogos = new CarrinhoService().BuscarJogosCarrinho(usuarioLogado);
                quantidade = jogos == null ? 0 : jogos.size();
            } catch (Exception ex) {
                quantidade = 0;
            }
        }
        btnCarrinho.setText(quantidade > 0 ? "Carrinho (" + quantidade + ")" : "Carrinho");
    }

    private String formatarPreco(Jogo jogo) {
        BigDecimal preco = jogo.getPrecoPromocao() != null ? jogo.getPrecoPromocao() : jogo.getPrecoPadrao();
        if (preco == null) {
            preco = BigDecimal.ZERO;
        }
        return "R$ " + preco.setScale(2, RoundingMode.HALF_UP).toString().replace(".", ",");
    }

    private void abrirPrimeiroJogo() {
        if (!jogosDaLoja.isEmpty()) {
            abrirJogo(jogosDaLoja.get(0));
        }
    }

    private void abrirJogo(Jogo jogo) {
        if (usuarioLogado == null) {
            JOptionPane.showMessageDialog(this, "Faca login para acessar os jogos.");
            return;
        }
        dispose();
        new TelaJogos(usuarioLogado, completarJogo(jogo)).setVisible(true);
    }

    private void pesquisarJogo(String termo) {
        if (estaVazio(termo) || termo.equalsIgnoreCase("Buscar jogos...")) {
            return;
        }

        for (Jogo jogo : jogosDaLoja) {
            if (jogo.getTitulo().toLowerCase().contains(termo.toLowerCase())) {
                abrirJogo(jogo);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Nenhum jogo encontrado para: " + termo);
    }

    private void abrirCarrinho() {
        if (usuarioLogado == null) {
            JOptionPane.showMessageDialog(this, "Faca login para abrir o carrinho.");
            return;
        }

        CarrinhoService service = new CarrinhoService();
        List<Jogo> jogos = service.BuscarJogosCarrinho(usuarioLogado);
        carrinhoDoUsuario = service.BuscarCarrinho(usuarioLogado);

        dispose();
        new Carrinho(jogos, carrinhoDoUsuario).setVisible(true);
    }
    
    private void abrirBiblioteca() {
        if (usuarioLogado == null) {
            JOptionPane.showMessageDialog(this, "Faca login para abrir a biblioteca.");
            return;
        }
        
        dispose();
        new Biblioteca().setVisible(true);
    }
    
    private void abrirConta() {
        if (usuarioLogado == null) {
            JOptionPane.showMessageDialog(this, "Faca login para abrir a biblioteca.");
            return;
        }
        
        dispose();
        new Conta().setVisible(true);
    }
    private void abrirCadastroJogo() {
        String titulo = JOptionPane.showInputDialog(this, "Titulo");
        if (estaVazio(titulo)) {
            return;
        }

        String descricao = JOptionPane.showInputDialog(this, "Descricao");
        String lancamento = JOptionPane.showInputDialog(this, "Lancamento (AAAA-MM-DDTHH:MM)");
        String preco = JOptionPane.showInputDialog(this, "Preco");

        JFileChooser chooser = new JFileChooser();
        String caminhoImagem = "";
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            caminhoImagem = chooser.getSelectedFile().getAbsolutePath();
        }

        try {
            Jogo jogo = new Jogo();
            jogo.setTitulo(titulo);
            jogo.setDescricao(descricao);
            jogo.setSubtitulo("");
            jogo.setPrecoPadrao(new BigDecimal(preco));
            jogo.setPrecoPromocao(null);
            jogo.setEstaEmPromocao(false);
            jogo.setImagem(caminhoImagem);
            jogo.setDataLancamento(LocalDateTime.parse(lancamento));
            jogo.setIdDistribuidor(1);
            jogo.setIdCriador(usuarioLogado == null ? 1 : usuarioLogado.getId());

            boolean salvou = new JogoDAO().salvar(jogo, usuarioLogado);
            JOptionPane.showMessageDialog(this, salvou ? "Jogo cadastrado." : "Nao foi possivel cadastrar o jogo.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage());
        }
    }

    private String nomeUsuario() {
        if (usuarioLogado == null) {
            return "Usuario nao logado";
        }
        return "Conta: " + usuarioLogado.getNome();
    }

    private boolean estaVazio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Inicio().setVisible(true));
    }

    private static class ImagePanel extends JPanel {

        private final String caminho;
        private final boolean escurecer;

        ImagePanel(String caminho, boolean escurecer) {
            this.caminho = caminho;
            this.escurecer = escurecer;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            Image imagem = null;
            if (caminho != null && new File(caminho).exists()) {
                imagem = new ImageIcon(caminho).getImage();
            }

            if (imagem != null) {
                g2.drawImage(imagem, 0, 0, getWidth(), getHeight(), this);
            } else {
                g2.setColor(new Color(34, 48, 64));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }

            if (escurecer) {
                g2.setColor(new Color(0, 0, 0, 120));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }

            g2.dispose();
        }
    }
}
