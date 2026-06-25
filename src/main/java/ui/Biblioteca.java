package ui;

import service.*;
import model.*;
import dao.JogoDAO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.*;

public class Biblioteca extends JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Biblioteca.class.getName());
    private static final Color FUNDO = new Color(22, 26, 33);
    private static final Color TOPO = new Color(24, 28, 36);
    private static final Color PAINEL = new Color(27, 44, 60);
    private static final Color BORDA = new Color(44, 82, 112);
    private static final Color VERDE = new Color(59, 185, 86);
    private static final Color TEXTO_SUAVE = new Color(164, 174, 186);
    private Usuario usuarioLogado;
    private JPanel painelListaJogos;
    private JLabel lblTituloJogo;
    private JLabel lblDescricao;
    private JLabel lblHorasJogadas;
    private ImagePanel painelImagemJogo;
    private JButton btnJogar;
    private JPanel painelDetalhes;

    public Biblioteca(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        
        configurarJanela();
        construirTela();
        carregarJogos();
    }
    
    private void configurarJanela() {
        setTitle("GIF - Biblioteca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(960, 720));
        setSize(1220, 780);
        setLocationRelativeTo(null);
    }
    
    private void construirTela() {
        JPanel raiz = new JPanel(new BorderLayout());
        raiz.setBackground(FUNDO);

        raiz.add(criarBarraSuperior(), BorderLayout.NORTH);
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(FUNDO);
        
        painelCentral.add(criarPainelEsquerdo(), BorderLayout.WEST);
        painelCentral.add(criarPainelDireito(), BorderLayout.CENTER);
        raiz.add(painelCentral, BorderLayout.CENTER);
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
        navegacao.add(criarLinkTopo("Loja", false, () -> abrirLoja()));
        navegacao.add(Box.createHorizontalStrut(24));
        navegacao.add(criarLinkTopo("Biblioteca", true, () -> {}));
        navegacao.add(Box.createHorizontalStrut(24));
        navegacao.add(criarLinkTopo("Conta", false, () -> JOptionPane.showMessageDialog(this, "Conta: " + (usuarioLogado != null ? usuarioLogado.getNome() : "Deslogado"))));

        barra.add(navegacao, BorderLayout.WEST);
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

    private JScrollPane criarPainelEsquerdo() {
        painelListaJogos = new JPanel();
        painelListaJogos.setLayout(new BoxLayout(painelListaJogos, BoxLayout.Y_AXIS));
        painelListaJogos.setBackground(TOPO);
        painelListaJogos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollEsquerdo = new JScrollPane(painelListaJogos);
        scrollEsquerdo.setPreferredSize(new Dimension(250, 0));
        scrollEsquerdo.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, BORDA));
        scrollEsquerdo.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollEsquerdo.getVerticalScrollBar().setUnitIncrement(16);
        
        return scrollEsquerdo;
    }

    private JPanel criarPainelDireito() {
        painelDetalhes = new JPanel();
        painelDetalhes.setLayout(new BoxLayout(painelDetalhes, BoxLayout.Y_AXIS));
        painelDetalhes.setBackground(FUNDO);
        painelDetalhes.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        
        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setOpaque(false);
        cabecalho.setMaximumSize(new Dimension(1000, 60));
        
        lblTituloJogo = new JLabel("Selecione um jogo");
        lblTituloJogo.setForeground(Color.WHITE);
        lblTituloJogo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        
        btnJogar = new JButton("JOGAR");
        btnJogar.setBackground(VERDE);
        btnJogar.setForeground(Color.WHITE);
        btnJogar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnJogar.setFocusPainted(false);
        btnJogar.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        btnJogar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnJogar.setVisible(false); 
        btnJogar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Iniciando jogo..."));
        
        cabecalho.add(lblTituloJogo, BorderLayout.WEST);
        cabecalho.add(btnJogar, BorderLayout.EAST);
        
        painelImagemJogo = new ImagePanel(null, false);
        painelImagemJogo.setPreferredSize(new Dimension(800, 350));
        painelImagemJogo.setMaximumSize(new Dimension(1000, 400));
        painelImagemJogo.setBorder(BorderFactory.createLineBorder(BORDA));
        
        JPanel painelInfo = new JPanel();
        painelInfo.setLayout(new BoxLayout(painelInfo, BoxLayout.Y_AXIS));
        painelInfo.setOpaque(false);
        painelInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        lblHorasJogadas = new JLabel("Horas Jogadas: 0,0");
        lblHorasJogadas.setForeground(TEXTO_SUAVE);
        lblHorasJogadas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JLabel tituloDesc = new JLabel("DESCRIÇÃO");
        tituloDesc.setForeground(Color.WHITE);
        tituloDesc.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        lblDescricao = new JLabel("<html>Nenhuma descrição disponível.</html>");
        lblDescricao.setForeground(TEXTO_SUAVE);
        lblDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDescricao.setMaximumSize(new Dimension(800, 200));

        painelInfo.add(Box.createVerticalStrut(15));
        painelInfo.add(lblHorasJogadas);
        painelInfo.add(Box.createVerticalStrut(20));
        painelInfo.add(tituloDesc);
        painelInfo.add(Box.createVerticalStrut(10));
        painelInfo.add(lblDescricao);

        painelDetalhes.add(cabecalho);
        painelDetalhes.add(Box.createVerticalStrut(20));
        painelDetalhes.add(painelImagemJogo);
        painelDetalhes.add(painelInfo);
        
        painelDetalhes.setVisible(false); 

        return painelDetalhes;
    }

    private void carregarJogos() {
        painelListaJogos.removeAll(); 

        JogoDAO jogoDAO = new JogoDAO();
        List<Jogo> jogos = jogoDAO.listarJogosDoUsuario(usuarioLogado.getId());

        
        if (jogos == null || jogos.isEmpty()) {
            jogos = criarJogosDeExemplo();
        }

        for (Jogo jogo : jogos) {
            JButton btnJogo = criarBotaoJogoLista(jogo.getTitulo());
            
            
            btnJogo.addActionListener(e -> exibirJogoPorObjeto(jogo));
            
            painelListaJogos.add(btnJogo);
            painelListaJogos.add(Box.createVerticalStrut(5));
        }

        painelListaJogos.revalidate();
        painelListaJogos.repaint();
    }

    
    private List<Jogo> criarJogosDeExemplo() {
        List<Jogo> lista = new java.util.ArrayList<>();
        
        lista.add(criarJogoMock("Stardew Valley", "Monte sua fazenda ai vagabundo.", "stardewValley.jpg"));
        lista.add(criarJogoMock("Inside", "afundo.", "inside.jpg"));
        lista.add(criarJogoMock("DeadCells", "Fé.", "DeadCells.jpg"));
        lista.add(criarJogoMock("Celeste", "É isso ai mano.", "celeste.jpg"));
        lista.add(criarJogoMock("Hollow Knight: Silksong", "Um anao foda.", "silksong.jpeg"));
        lista.add(criarJogoMock("Cuphead", "Uma maratona rápida, bonita e impiedosa contra atividades acumuladas.", "cuphead.jpg"));
        
        return lista;
    }

    private Jogo criarJogoMock(String titulo, String descricao, String nomeImagem) {
        Jogo jogo = new Jogo();
        jogo.setTitulo(titulo);
        jogo.setDescricao(descricao);
        jogo.setImagem(new java.io.File("imagens/jogos/" + nomeImagem).getAbsolutePath());
        return jogo;
    }

    private void exibirJogoPorObjeto(Jogo jogo) {
        if (jogo != null) {
            painelDetalhes.setVisible(true);
            btnJogar.setVisible(true);
            lblTituloJogo.setText(jogo.getTitulo());
      
            String descricao = jogo.getDescricao() != null && !jogo.getDescricao().isEmpty() 
                    ? jogo.getDescricao() : "Sem descrição.";
            lblDescricao.setText("<html><p style='width: 600px;'>" + descricao + "</p></html>");
            
            lblHorasJogadas.setText("Horas Jogadas: 12,4h"); // Simulação de horas jogadas
            painelImagemJogo.setCaminho(jogo.getImagem());
        }
    }
    
    
    
    private JButton criarBotaoJogoLista(String texto) {
        JButton botao = new JButton(texto);
        botao.setBackground(TOPO);
        botao.setForeground(TEXTO_SUAVE);
        botao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        botao.setHorizontalAlignment(SwingConstants.LEFT);
        botao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        botao.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                botao.setBackground(PAINEL);
                botao.setForeground(Color.WHITE);
            }
            public void mouseExited(MouseEvent evt) {
                botao.setBackground(TOPO);
                botao.setForeground(TEXTO_SUAVE);
            }
        });
        return botao;
    }

    private void exibirJogo(int idJogo) {
        JogoDAO jogoDAO = new JogoDAO();
        Jogo jogo = jogoDAO.buscarPorId(idJogo);

        if (jogo != null) {
            painelDetalhes.setVisible(true);
            btnJogar.setVisible(true);
            
            lblTituloJogo.setText(jogo.getTitulo());
            
            String descricao = jogo.getDescricao() != null && !jogo.getDescricao().isEmpty() 
                    ? jogo.getDescricao() : "Sem descrição.";
            lblDescricao.setText("<html><p style='width: 600px;'>" + descricao + "</p></html>");
            
            lblHorasJogadas.setText("Horas Jogadas: 0,0");
            
            painelImagemJogo.setCaminho(jogo.getImagem());
        }
    }
    
    private void abrirLoja() {
        dispose();
        new Inicio().setVisible(true); 
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> {
            Usuario usuarioTeste = new Usuario();
            usuarioTeste.setNome("Jogador 1");
            new Biblioteca(usuarioTeste).setVisible(true);
        });
    }

    private static class ImagePanel extends JPanel {
        private String caminho;
        private final boolean escurecer;

        ImagePanel(String caminho, boolean escurecer) {
            this.caminho = caminho;
            this.escurecer = escurecer;
            setOpaque(false);
        }

        public void setCaminho(String novoCaminho) {
            this.caminho = novoCaminho;
            this.repaint();
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
                g2.setColor(new Color(164, 174, 186));
                g2.drawString("Imagem indisponível", getWidth()/2 - 60, getHeight()/2);
            }
            if (escurecer) {
                g2.setColor(new Color(0, 0, 0, 120));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
            g2.dispose();
        }
    }
}