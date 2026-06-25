package ui;

import model.Jogo;
import model.CarrinhoModel;
import java.math.BigDecimal;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import util.*;
import service.*;


public class PainelJogoCarrinho extends javax.swing.JPanel {

    private Jogo jogo;   
    CarrinhoModel carrinho = new CarrinhoModel();
    private OnRemoverListener listener;

    
    public PainelJogoCarrinho(Jogo jogo, CarrinhoModel carrinho, OnRemoverListener listener) {
        initComponents();
        
        this.jogo = jogo;        
        this.carrinho = carrinho;
        this.listener = listener;
        
        nomeJogo.setText(jogo.getTitulo());
        BigDecimal preco = jogo.isEstaEmPromocao() ? jogo.getPrecoPromocao() : jogo.getPrecoPadrao();
        precoJogo.setText("R$ " + preco);
        
        lancadoEm.setText("Lançado em: "+ jogo.getDataLancamento());
        
        ImageIcon icon = new ImageIcon(
            jogo.getImagem()
        );
        Image img = icon.getImage();
        Image imgRedimensionada = img.getScaledInstance(
                180,
                104,
                Image.SCALE_SMOOTH
        );
        
        imagemJogo.setIcon(
            new ImageIcon(imgRedimensionada)
        );
        
        PersonalizarPainel( img);
    }
    
    public void PersonalizarPainel(Image img){
        Color fundoCard = new Color(24, 34, 48);
        Color bordaCard = new Color(48, 74, 95);
        Color textoSecundario = new Color(150, 160, 180);
        Color verde = new Color(50, 166, 70);

        setBackground(fundoCard);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 132));
        setMinimumSize(new Dimension(520, 132));
        setBorder(
            javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(bordaCard, 1),
                javax.swing.BorderFactory.createEmptyBorder(12, 14, 12, 14)
            )
        );

        imagemJogo.setPreferredSize(new Dimension(180, 104));
        imagemJogo.setMinimumSize(new Dimension(180, 104));

        nomeJogo.setForeground(Color.WHITE);
        nomeJogo.setFont(
            new Font("Segoe UI", Font.BOLD, 20)
        );
        precoJogo.setForeground(
            verde
        );

        precoJogo.setFont(
            new Font("Segoe UI", Font.BOLD, 20)
        );
        
        lancadoEm.setForeground(
            textoSecundario
        );

        lancadoEm.setFont(
            new Font("Segoe UI", Font.PLAIN, 13)
        );
        
        btnRemoverJogo.setText("Remover");

        btnRemoverJogo.setBackground(
            new Color(32, 48, 64)
        );

        btnRemoverJogo.setForeground(textoSecundario);
        btnRemoverJogo.setFont(new Font("Segoe UI", Font.BOLD, 12));

        btnRemoverJogo.setFocusPainted(false);

        btnRemoverJogo.setBorderPainted(false);
        btnRemoverJogo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRemoverJogo.setMargin(new Insets(6, 14, 6, 14));
        btnRemoverJogo.setPreferredSize(new Dimension(96, 30));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imagemJogo = new javax.swing.JLabel();
        nomeJogo = new javax.swing.JLabel();
        precoJogo = new javax.swing.JLabel();
        btnRemoverJogo = new javax.swing.JButton();
        lancadoEm = new javax.swing.JLabel();

        imagemJogo.setMinimumSize(new java.awt.Dimension(180, 104));
        imagemJogo.setPreferredSize(new java.awt.Dimension(180, 104));

        nomeJogo.setText("nomeJogo");
        nomeJogo.setToolTipText("");

        precoJogo.setText("Preço");

        btnRemoverJogo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnRemoverJogo.setText("Remover");
        btnRemoverJogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRemoverJogoMouseClicked(evt);
            }
        });

        lancadoEm.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lancadoEm.setForeground(new java.awt.Color(153, 153, 153));
        lancadoEm.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imagemJogo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomeJogo)
                    .addComponent(lancadoEm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(precoJogo)
                    .addComponent(btnRemoverJogo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imagemJogo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeJogo)
                    .addComponent(precoJogo))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lancadoEm)
                    .addComponent(btnRemoverJogo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRemoverJogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoverJogoMouseClicked
        CarrinhoService service = new CarrinhoService();
        
        var resultado = service.RemoverJogoCarrinho(carrinho, jogo);
        
        if(resultado){
            listener.onRemover(jogo);
        }
        
    }//GEN-LAST:event_btnRemoverJogoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRemoverJogo;
    private javax.swing.JLabel imagemJogo;
    private javax.swing.JLabel lancadoEm;
    private javax.swing.JLabel nomeJogo;
    private javax.swing.JLabel precoJogo;
    // End of variables declaration//GEN-END:variables
}
