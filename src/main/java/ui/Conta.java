/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import model.*;
import util.Sessao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Conta extends javax.swing.JFrame {

    private Usuario usuarioLogado;
    
    // Definição da paleta de cores unificada (Padrão TelaJogos)
    private final Color fundo = new Color(18, 24, 34);
    private final Color superficie = new Color(21, 31, 44);
    private final Color card = new Color(24, 34, 48);
    private final Color borda = new Color(48, 74, 95);
    private final Color textoSecundario = new Color(150, 160, 180);
    private final Color verde = new Color(50, 166, 70);
    private final Color branco = new Color(200, 200, 200);

    public Conta() {
        Sessao sessao = new Sessao();
        this.usuarioLogado = sessao.getUsuarioLogado();

        configurarJanela();
        criarInterface();

        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("GIF - Conta");
        setSize(1200, 750);
        setMinimumSize(new Dimension(980, 620));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(fundo);
    }

    private void criarInterface() {
        JPanel principal = new JPanel(new BorderLayout());
        principal.setBackground(fundo);

        // -----------------------------------------------------------------
        JPanel topo = new JPanel(new BorderLayout());
        topo.setBackground(fundo);
        topo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borda));
        topo.setPreferredSize(new Dimension(1200, 68));

        // Container para alinhar itens à esquerda (Logo + Botões)
        JPanel esquerdaTopo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        esquerdaTopo.setOpaque(false);
        esquerdaTopo.setBorder(new EmptyBorder(16, 56, 16, 0));

        JLabel logo = new JLabel("GIF");
        logo.setForeground(branco);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        logo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        esquerdaTopo.add(logo);

        JPanel menu = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        menu.setOpaque(false);
        menu.setBorder(new EmptyBorder(2, 25, 0, 0));

        JButton btnLoja = criarBotaoMenu("Loja");
        JButton btnBiblioteca = criarBotaoMenu("Biblioteca");
        JButton btnConta = criarBotaoMenu("Conta");
        btnConta.setForeground(branco); // Destaque na aba atual

        btnLoja.addActionListener(e -> {
            dispose();
            new Inicio().setVisible(true);
        });

        btnBiblioteca.addActionListener(e -> {
            dispose();
            new Biblioteca().setVisible(true);
        });

        menu.add(btnLoja);
        menu.add(btnBiblioteca);
        menu.add(btnConta);
        esquerdaTopo.add(menu);

        // Container para alinhar itens à direita (Barra de pesquisa)
        JPanel direitaTopo = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        direitaTopo.setOpaque(false);
        direitaTopo.setBorder(new EmptyBorder(16, 0, 16, 56));

        topo.add(esquerdaTopo, BorderLayout.WEST);
        topo.add(direitaTopo, BorderLayout.EAST);

        // -----------------------------------------------------------------
        // CONTEÚDO CENTRAL
        // -----------------------------------------------------------------
        JPanel conteudo = new JPanel(new BorderLayout(0, 24));
        conteudo.setOpaque(false);
        conteudo.setBorder(new EmptyBorder(28, 56, 32, 56));

        JLabel titulo = new JLabel("Detalhes da Conta");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        conteudo.add(titulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout(28, 0));
        centro.setOpaque(false);

        // PAINEL ESQUERDO (Perfil e Histórico - Estilo jPanel2)
        JPanel esquerda = new JPanel();
        esquerda.setBackground(superficie);
        esquerda.setLayout(new BoxLayout(esquerda, BoxLayout.Y_AXIS));
        esquerda.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borda, 1),
                BorderFactory.createEmptyBorder(24, 28, 24, 28)
        ));

        // Bloco de Perfil
        JPanel perfil = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        perfil.setOpaque(false);

        JLabel avatar = new JLabel("👤");
        avatar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60));
        avatar.setForeground(Color.WHITE);

        JPanel dadosUsuario = new JPanel();
        dadosUsuario.setOpaque(false);
        dadosUsuario.setLayout(new BoxLayout(dadosUsuario, BoxLayout.Y_AXIS));
        dadosUsuario.setBorder(new EmptyBorder(0, 20, 0, 0));

        JLabel nome = new JLabel(usuarioLogado.getNome());
        nome.setForeground(Color.WHITE);
        nome.setFont(new Font("Segoe UI", Font.BOLD, 26));

        String emailGerado = usuarioLogado.getNome() != null 
                ? usuarioLogado.getNome().toLowerCase().replace(" ", "") + "@if.edu.br" 
                : "usuario@if.edu.br";
        JLabel email = new JLabel(emailGerado);
        email.setForeground(textoSecundario);
        email.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel nivel = new JLabel("Nível 12");
        nivel.setForeground(verde);
        nivel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        dadosUsuario.add(nome);
        dadosUsuario.add(Box.createVerticalStrut(4));
        dadosUsuario.add(email);
        dadosUsuario.add(Box.createVerticalStrut(8));
        dadosUsuario.add(nivel);

        perfil.add(avatar);
        perfil.add(dadosUsuario);
        esquerda.add(perfil);

        esquerda.add(Box.createVerticalStrut(24));
        JSeparator linha = new JSeparator();
        linha.setForeground(borda);
        linha.setBackground(borda);
        esquerda.add(linha);
        esquerda.add(Box.createVerticalStrut(20));

        // Histórico de compras
        JLabel historicoTitulo = new JLabel("Histórico de Compras Recente");
        historicoTitulo.setForeground(Color.WHITE);
        historicoTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        esquerda.add(historicoTitulo);
        esquerda.add(Box.createVerticalStrut(16));

        esquerda.add(criarCompra("Campus Simulator 2024", "12 de Out, 2023", "R$ 49,90"));
        esquerda.add(Box.createVerticalStrut(12));
        esquerda.add(criarCompra("Cálculo IV: O Despertar", "05 de Set, 2023", "R$ 59,90"));

        // PAINEL DIREITO (Carteira)
        JPanel direita = new JPanel();
        direita.setOpaque(false);
        direita.setLayout(new BorderLayout());
        direita.setPreferredSize(new Dimension(320, 0));

        JPanel carteira = new JPanel();
        carteira.setBackground(superficie);
        carteira.setLayout(new BoxLayout(carteira, BoxLayout.Y_AXIS));
        carteira.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borda, 1),
                BorderFactory.createEmptyBorder(24, 24, 24, 24)
        ));

        JLabel carteiraTitulo = new JLabel("Carteira GIF");
        carteiraTitulo.setForeground(Color.WHITE);
        carteiraTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        carteiraTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel saldo = new JLabel("R$ 15,00");
        saldo.setForeground(verde);
        saldo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        saldo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton adicionar = new JButton("Adicionar Fundos");
        personalizarBotao(adicionar, verde, Color.WHITE, new Dimension(200, 42));
        adicionar.setAlignmentX(Component.LEFT_ALIGNMENT);

        carteira.add(carteiraTitulo);
        carteira.add(Box.createVerticalStrut(16));
        carteira.add(saldo);
        carteira.add(Box.createVerticalStrut(24));
        carteira.add(adicionar);

        direita.add(carteira, BorderLayout.NORTH);

        centro.add(esquerda, BorderLayout.CENTER);
        centro.add(direita, BorderLayout.EAST);
        conteudo.add(centro, BorderLayout.CENTER);

        principal.add(topo, BorderLayout.NORTH);
        principal.add(conteudo, BorderLayout.CENTER);

        setContentPane(principal);
    }

    // -----------------------------------------------------------------
    // MÉTODOS AUXILIARES DE ESTILIZAÇÃO (Padrão de UI da TelaJogos)
    // -----------------------------------------------------------------
    private void personalizarBotao(JButton botao, Color fundo, Color texto, Dimension tamanho) {
        botao.setBackground(fundo);
        botao.setForeground(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 13));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setContentAreaFilled(true);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.setMargin(new Insets(8, 18, 8, 18));
        botao.setPreferredSize(tamanho);
        botao.setMinimumSize(tamanho);
        botao.setMaximumSize(tamanho);
    }

    private JButton criarBotaoMenu(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JPanel criarCompra(String jogo, String data, String preco) {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(card);
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borda, 1),
                BorderFactory.createEmptyBorder(14, 16, 14, 16)
        ));
        painel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

        JPanel info = new JPanel();
        info.setOpaque(false);
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));

        JLabel nomeJogo = new JLabel(jogo);
        nomeJogo.setForeground(Color.WHITE);
        nomeJogo.setFont(new Font("Segoe UI", Font.BOLD, 15));

        JLabel dataCompra = new JLabel(data);
        dataCompra.setForeground(textoSecundario);
        dataCompra.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        info.add(nomeJogo);
        info.add(Box.createVerticalStrut(2));
        info.add(dataCompra);

        JLabel valor = new JLabel(preco);
        valor.setForeground(Color.WHITE);
        valor.setFont(new Font("Segoe UI", Font.BOLD, 16));

        painel.add(info, BorderLayout.WEST);
        painel.add(valor, BorderLayout.EAST);

        return painel;
    }

    // SÓ PARA TESTAR
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Conta();
        });
    }
}
