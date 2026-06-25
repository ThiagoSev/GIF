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
        setLocationRelativeTo(null);
        
        this.usuarioLogado = usuarioLogado;
        this.jogo = jogo;
        personalizarTela();
        exibirJogo(jogo);
    }
    
   private void personalizarTela() {

    // Fundo dos painéis
    jPanel2.setBackground(new java.awt.Color(32, 34, 37));
    jPanel4.setBackground(new java.awt.Color(32, 34, 37));

    // Título do jogo
    lblTituloJogo.setFont(
            new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 24)
    );
    lblTituloJogo.setForeground(java.awt.Color.WHITE);

    // Descrição
    txtDescricao.setBackground(
            new java.awt.Color(47, 49, 54)
    );
    txtDescricao.setForeground(java.awt.Color.WHITE);

    // Preço
    lblPreco.setFont(
            new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 22)
    );
    lblPreco.setForeground(
            new java.awt.Color(76, 175, 80)
    );

    // Data de lançamento
    lbLancamento.setForeground(java.awt.Color.WHITE);
    lblData.setForeground(
            new java.awt.Color(180, 180, 180)
    );

    // Botão comprar
    btnComprar.setBackground(
            new java.awt.Color(76, 175, 80)
    );
    btnComprar.setForeground(java.awt.Color.WHITE);

    // Botões superiores
    btnUsuario.setBackground(
            new java.awt.Color(54, 57, 63)
    );
    btnUsuario.setForeground(java.awt.Color.WHITE);

    btnRemover.setBackground(
            new java.awt.Color(54, 57, 63)
    );
    btnRemover.setForeground(java.awt.Color.WHITE);

    // Pesquisa
    texfPesquisa.setBackground(
            new java.awt.Color(47, 49, 54)
    );
    texfPesquisa.setForeground(java.awt.Color.WHITE);

    // Imagem
    lblImagem.setBorder(
            javax.swing.BorderFactory.createLineBorder(
                    new java.awt.Color(80, 80, 80),
                    2
            )
    );
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
                        .addContainerGap(14, Short.MAX_VALUE)
                        .addComponent(lblImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnComprar))
                .addGap(43, 43, 43))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTitulo.setText("Titulo");

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
                .addGap(29, 29, 29)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRemover)
                .addGap(18, 18, 18)
                .addComponent(texfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUsuario)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo)
                    .addComponent(texfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuario)
                    .addComponent(btnRemover))
                .addGap(14, 14, 14))
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
