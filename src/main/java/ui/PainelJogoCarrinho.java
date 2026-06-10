package ui;

import model.Jogo;
import model.CarrinhoModel;

import util.*;
import service.*;


public class PainelJogoCarrinho extends javax.swing.JPanel {

    Jogo jogo = new Jogo();   
    CarrinhoModel carrinho = new CarrinhoModel();
    private OnRemoverListener listener;

    
    public PainelJogoCarrinho(Jogo jogo, CarrinhoModel carrinho, OnRemoverListener listener) {
        initComponents();
        
        this.jogo = jogo;        
        this.carrinho = carrinho;
        this.listener = listener;
        
        nomeJogo.setText(jogo.getTitulo());
        precoJogo.setText("R$ " + jogo.getPrecoPadrao());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imagemJogo = new javax.swing.JLabel();
        nomeJogo = new javax.swing.JLabel();
        precoJogo = new javax.swing.JLabel();
        btnRemoverJogo = new javax.swing.JButton();

        imagemJogo.setText("imagme");

        nomeJogo.setText("nomeJogo");
        nomeJogo.setToolTipText("");

        precoJogo.setText("Preço");

        btnRemoverJogo.setText("Remover");
        btnRemoverJogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRemoverJogoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(imagemJogo)
                        .addGap(29, 29, 29)
                        .addComponent(nomeJogo)
                        .addGap(40, 40, 40)
                        .addComponent(precoJogo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(btnRemoverJogo)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imagemJogo)
                    .addComponent(nomeJogo)
                    .addComponent(precoJogo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRemoverJogo)
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRemoverJogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoverJogoMouseClicked
        CarrinhoService service = new CarrinhoService();
        
        service.RemoverJogoCarrinho(carrinho, jogo);
        
        listener.onRemover(jogo);
    }//GEN-LAST:event_btnRemoverJogoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRemoverJogo;
    private javax.swing.JLabel imagemJogo;
    private javax.swing.JLabel nomeJogo;
    private javax.swing.JLabel precoJogo;
    // End of variables declaration//GEN-END:variables
}
