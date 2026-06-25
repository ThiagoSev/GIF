/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;
import service.*;
import model.*;
import dao.JogoDAO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class TelaJogos extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaJogos.class.getName());
    private Usuario usuarioLogado;
    private Jogo jogo;

    /**
     * Creates new form TelaJogos
     */
    public TelaJogos(Usuario usuarioLogado, Jogo jogo) {
        initComponents();
        
        this.usuarioLogado = usuarioLogado;
        this.jogo = jogo;
        personalizarTela();
        exibirJogo(jogo);
        pack();
        setLocationRelativeTo(null);
    }
    
   private void personalizarTela() {

    Color fundo = new Color(18, 24, 34);
    Color superficie = new Color(21, 31, 44);
    Color card = new Color(24, 34, 48);
    Color borda = new Color(48, 74, 95);
    Color textoSecundario = new Color(150, 160, 180);
    Color verde = new Color(50, 166, 70);

    getContentPane().setBackground(fundo);
    setMinimumSize(new Dimension(980, 620));
    setPreferredSize(new Dimension(1080, 660));
    setTitle("GIF - Jogo");

    jPanel2.setBackground(superficie);
    jPanel2.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borda, 1),
            BorderFactory.createEmptyBorder(24, 28, 24, 28)
    ));

    jPanel4.setBackground(fundo);
    jPanel4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borda));

    lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
    lblTitulo.setForeground(verde);
    lblTitulo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    lblTitulo.setToolTipText("Voltar ao inicio");

    lblTituloJogo.setFont(new Font("Segoe UI", Font.BOLD, 32));
    lblTituloJogo.setForeground(Color.WHITE);

    personalizarAreaTexto(txtDescricao, card, borda, textoSecundario);
    jScrollPane1.setBorder(BorderFactory.createLineBorder(borda, 1));
    jScrollPane1.getViewport().setBackground(card);

    lblPreco.setFont(new Font("Segoe UI", Font.BOLD, 26));
    lblPreco.setForeground(verde);

    lbLancamento.setFont(new Font("Segoe UI", Font.BOLD, 13));
    lbLancamento.setForeground(Color.WHITE);
    lblData.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    lblData.setForeground(textoSecundario);

    personalizarBotao(btnComprar, verde, Color.WHITE, new Dimension(170, 42));
    personalizarBotao(btnUsuario, new Color(32, 48, 64), textoSecundario, new Dimension(96, 34));
    personalizarBotao(btnRemover, new Color(32, 48, 64), textoSecundario, new Dimension(96, 34));

    personalizarCampoPesquisa(texfPesquisa, superficie, borda, textoSecundario);

    lblImagem.setBorder(BorderFactory.createLineBorder(borda, 1));
    lblImagem.setOpaque(true);
    lblImagem.setBackground(card);
    }

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
    }

    private void personalizarCampoPesquisa(JTextField campo, Color fundo, Color borda, Color texto) {
        campo.setBackground(fundo);
        campo.setForeground(texto);
        campo.setCaretColor(Color.WHITE);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borda, 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        campo.setPreferredSize(new Dimension(220, 36));
    }

    private void personalizarAreaTexto(JTextArea area, Color fundo, Color borda, Color texto) {
        area.setBackground(fundo);
        area.setForeground(texto);
        area.setCaretColor(Color.WHITE);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));
    }

    
    private void exibirJogo(Jogo jogo) {
        
    lblTitulo.setText("GIF");
    lblTituloJogo.setText(jogo.getTitulo());

    txtDescricao.setText(
            jogo.getDescricao()
    );
    
    DateTimeFormatter formato =
        DateTimeFormatter.ofPattern("dd/MM/yyyy");

    lblData.setText(
            jogo.getDataLancamento().format(formato)
    );

    lblPreco.setText(
            "R$ " + jogo.getPrecoPadrao()
    );
    ImageIcon icon = new ImageIcon(
            jogo.getImagem()
    );

    Image img = icon.getImage();
    
    Image imgRedimensionada = img.getScaledInstance(
            lblImagem.getWidth(),
            lblImagem.getHeight(),
            Image.SCALE_SMOOTH
    );

    lblImagem.setIcon(
            new ImageIcon(imgRedimensionada)
    );
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblImagem = new javax.swing.JLabel();
        lblTituloJogo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        lbLancamento = new javax.swing.JLabel();
        lblPreco = new javax.swing.JLabel();
        btnComprar = new javax.swing.JButton();
        lblData = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        texfPesquisa = new javax.swing.JTextField();
        btnUsuario = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(18, 24, 34));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(48, 74, 95)));

        lblImagem.setToolTipText("");
        lblImagem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(48, 74, 95)));

        lblTituloJogo.setText("Titulo do jogo");

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        txtDescricao.setText("Descriçao");
        jScrollPane1.setViewportView(txtDescricao);

        lbLancamento.setText("Lançamento");

        lblPreco.setText("Preço");

        btnComprar.setText("Comprar");
        btnComprar.addActionListener(this::btnComprarActionPerformed);

        lblData.setText("data");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbLancamento)
                        .addGap(18, 18, 18)
                        .addComponent(lblData))
                    .addComponent(lblTituloJogo, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblPreco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnComprar)
                .addGap(4, 4, 4))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTituloJogo)
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbLancamento)
                            .addComponent(lblData))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                        .addGap(0, 0, 0)))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(48, 74, 95)));

        lblTitulo.setText("Titulo");
        lblTitulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTituloMouseClicked(evt);
            }
        });

        texfPesquisa.setText("Pesquisa");
        texfPesquisa.addActionListener(this::texfPesquisaActionPerformed);

        btnUsuario.setText("Usuario");

        btnRemover.setText("Excluir");
        btnRemover.addActionListener(this::btnRemoverActionPerformed);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRemover)
                .addGap(18, 18, 18)
                .addComponent(texfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUsuario)
                .addGap(56, 56, 56))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo)
                    .addComponent(texfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void texfPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texfPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texfPesquisaActionPerformed

    private void lblTituloMouseClicked(java.awt.event.MouseEvent evt) {
        this.dispose();
        new Inicio().setVisible(true);
    }
                                         

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        try {

            String idTexto = JOptionPane.showInputDialog(this,
                    "ID do jogo que deseja remover:");

            int id = Integer.parseInt(idTexto);

            JogoDAO dao = new JogoDAO();
            dao.remover(id, usuarioLogado);


        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Erro ao remover: " + e.getMessage());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarActionPerformed
    
       CarrinhoService service = new CarrinhoService();

            service.AdicionarJogoCarrinho(
            usuarioLogado,
            jogo
    );


        List<Jogo> jogos =
                service.BuscarJogosCarrinho(usuarioLogado);

        CarrinhoModel carrinho =
                service.BuscarCarrinho(usuarioLogado);

        this.dispose();

        new Carrinho(
                jogos,
                carrinho
        ).setVisible(true);
    }//GEN-LAST:event_btnComprarActionPerformed

    /**
     * @param args the command line arguments
     */

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComprar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnUsuario;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbLancamento;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JLabel lblPreco;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTituloJogo;
    private javax.swing.JTextField texfPesquisa;
    private javax.swing.JTextArea txtDescricao;
    // End of variables declaration//GEN-END:variables
}
