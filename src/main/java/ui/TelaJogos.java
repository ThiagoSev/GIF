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
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import javax.swing.BorderFactory;



/**
 *
 * @author User
 */
public class TelaJogos extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaJogos.class.getName());
    private static final Color FUNDO = new Color(22, 26, 33);
    private static final Color TOPO = new Color(24, 28, 36);
    private static final Color PAINEL = new Color(27, 44, 60);
    private static final Color BORDA = new Color(44, 82, 112);
    private static final Color VERDE = new Color(59, 185, 86);
    private static final Color TEXTO_SUAVE = new Color(164, 174, 186);
    private Usuario usuarioLogado;
    private Jogo jogo;
    private List<Jogo> jogosDaLoja;

    /**
     * Creates new form TelaJogos
     */
    public TelaJogos(Usuario usuarioLogado, Jogo jogo) {
        initComponents();
        lblImagem.setPreferredSize(
        new Dimension(800, 400));
        setLocationRelativeTo(null);
        texfPesquisa.addActionListener(e -> pesquisarJogo(texfPesquisa.getText().trim()));
        
        this.usuarioLogado = usuarioLogado;
        this.jogo = jogo;
        personalizarTela();
        exibirJogo(jogo);
        carregarJogos();
    }
    
  private void personalizarTela() {

    getContentPane().setBackground(FUNDO);

    jPanel2.setBackground(FUNDO);
    jPanel4.setBackground(TOPO);

    jPanel4.setBorder(BorderFactory.createMatteBorder(
            0, 0, 1, 0, BORDA));

    btnInicion.setText("GIF");
    btnInicion.setForeground(Color.WHITE);
    btnInicion.setBackground(TOPO);
    btnInicion.setFont(new Font("Segoe UI", Font.BOLD, 24));
    btnInicion.setFocusPainted(false);
    btnInicion.setBorderPainted(false);
    btnInicion.setContentAreaFilled(true);
    btnInicion.setOpaque(true);

    lblTituloJogo.setForeground(Color.WHITE);
    lblTituloJogo.setFont(new Font("Segoe UI", Font.BOLD, 32));

    txtDescricao.setLineWrap(true);
    txtDescricao.setWrapStyleWord(true);
    txtDescricao.setEditable(false);
    txtDescricao.setBackground(PAINEL);
    txtDescricao.setForeground(Color.WHITE);
    txtDescricao.setCaretColor(Color.WHITE); // cursor branco
    txtDescricao.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

    lblPreco.setForeground(VERDE);
    lblPreco.setFont(new Font("Segoe UI", Font.BOLD, 28));

    lbLancamento.setForeground(Color.WHITE);
    lblData.setForeground(TEXTO_SUAVE);

    btnComprar.setBackground(VERDE);
    btnComprar.setForeground(Color.WHITE);
    btnComprar.setFocusPainted(false);

    btnUsuario.setBackground(TOPO);
    btnUsuario.setForeground(Color.WHITE);
    btnUsuario.setFocusPainted(false);
    btnUsuario.setBorderPainted(false);
    btnUsuario.setContentAreaFilled(true);
    btnUsuario.setOpaque(true);

    btnRemover.setBackground(PAINEL);
    btnRemover.setForeground(Color.WHITE);
    btnRemover.setFocusPainted(false);
    btnRemover.setBorderPainted(false);
    btnRemover.setContentAreaFilled(true);
    btnRemover.setOpaque(true);
    btnRemover.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    
    btnLoja.setBackground(TOPO);
    btnLoja.setForeground(Color.WHITE);
    btnLoja.setFocusPainted(false);
    btnLoja.setBorderPainted(false);
    btnLoja.setContentAreaFilled(true);
    btnLoja.setOpaque(true);
    
    btnBiblioteca.setBackground(TOPO);
    btnBiblioteca.setForeground(Color.WHITE);
    btnBiblioteca.setFocusPainted(false);
    btnBiblioteca.setBorderPainted(false);
    btnBiblioteca.setContentAreaFilled(true);
    btnBiblioteca.setOpaque(true);
    
    btnCarrinho.setBackground(PAINEL);
    btnCarrinho.setForeground(Color.WHITE);
    btnCarrinho.setFocusPainted(false);
    btnCarrinho.setBorderPainted(false);
    btnCarrinho.setContentAreaFilled(true);
    btnCarrinho.setOpaque(true);
    btnCarrinho.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    

    texfPesquisa.setBackground(PAINEL);
    texfPesquisa.setForeground(Color.WHITE);
    texfPesquisa.setCaretColor(Color.WHITE); 
    texfPesquisa.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

    lblImagem.setBorder(
            BorderFactory.createLineBorder(BORDA, 2)
    );
}

    
    private void exibirJogo(Jogo jogo) {
        
    lblTituloJogo.setText(jogo.getTitulo());

   txtDescricao.setText(
    jogo.getDescricao() != null
        ? jogo.getDescricao()
        : "Sem descrição."
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
    private boolean estaVazio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
    
    private String caminhoImagem(String nomeImagem) {
        return new File("imagens/jogos/" + nomeImagem).getAbsolutePath();
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
    
      private void abrirJogo(Jogo jogo) {
        if (usuarioLogado == null) {
            JOptionPane.showMessageDialog(this, "Faca login para acessar os jogos.");
            return;
        }
        dispose();
        new TelaJogos(usuarioLogado, completarJogo(jogo)).setVisible(true);
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
        texfPesquisa = new javax.swing.JTextField();
        btnUsuario = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnInicion = new javax.swing.JButton();
        btnLoja = new javax.swing.JButton();
        btnBiblioteca = new javax.swing.JButton();
        btnCarrinho = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(23, 26, 33));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblImagem.setToolTipText("");
        lblImagem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
                .addGap(16, 16, 16)
                .addComponent(lblImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbLancamento)
                        .addGap(26, 26, 26)
                        .addComponent(lblData))
                    .addComponent(lblTituloJogo, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(lblPreco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnComprar)
                .addGap(64, 64, 64))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lblTituloJogo)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbLancamento)
                            .addComponent(lblData))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(11, Short.MAX_VALUE)
                        .addComponent(lblImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnComprar))
                .addGap(43, 43, 43))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        texfPesquisa.setText("Pesquisa");
        texfPesquisa.addActionListener(this::texfPesquisaActionPerformed);

        btnUsuario.setText("Conta");

        btnRemover.setText("Excluir");
        btnRemover.addActionListener(this::btnRemoverActionPerformed);

        btnInicion.setText("GIF");
        btnInicion.addActionListener(this::btnInicionActionPerformed);

        btnLoja.setText("Loja");
        btnLoja.addActionListener(this::btnLojaActionPerformed);

        btnBiblioteca.setText("Biblioteca");
        btnBiblioteca.addActionListener(this::btnBibliotecaActionPerformed);

        btnCarrinho.setText("Carrinho");
        btnCarrinho.addActionListener(this::btnCarrinhoActionPerformed);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnInicion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLoja)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBiblioteca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRemover)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(texfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCarrinho)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuario)
                    .addComponent(btnInicion)
                    .addComponent(btnLoja)
                    .addComponent(btnBiblioteca)
                    .addComponent(btnCarrinho, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void texfPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texfPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texfPesquisaActionPerformed
                                         

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

    private void btnInicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicionActionPerformed
  dispose();
        new Inicio().setVisible(true);
    }//GEN-LAST:event_btnInicionActionPerformed

    private void btnLojaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLojaActionPerformed
    dispose();
        new Inicio().setVisible(true);
    }//GEN-LAST:event_btnLojaActionPerformed

    private void btnBibliotecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBibliotecaActionPerformed
        dispose();
        new Biblioteca(usuarioLogado).setVisible(true);
    }//GEN-LAST:event_btnBibliotecaActionPerformed

    private void btnCarrinhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarrinhoActionPerformed
     CarrinhoService service = new CarrinhoService();
     
        List<Jogo> jogos =
                service.BuscarJogosCarrinho(usuarioLogado);

        CarrinhoModel carrinho =
                service.BuscarCarrinho(usuarioLogado);

        this.dispose();

        new Carrinho(
                jogos,
                carrinho
        ).setVisible(true);
    }//GEN-LAST:event_btnCarrinhoActionPerformed

    /**
     * @param args the command line arguments
     */

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBiblioteca;
    private javax.swing.JButton btnCarrinho;
    private javax.swing.JButton btnComprar;
    private javax.swing.JButton btnInicion;
    private javax.swing.JButton btnLoja;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnUsuario;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbLancamento;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JLabel lblPreco;
    private javax.swing.JLabel lblTituloJogo;
    private javax.swing.JTextField texfPesquisa;
    private javax.swing.JTextArea txtDescricao;
    // End of variables declaration//GEN-END:variables
}
