package ui;

import model.Jogo;
import model.CarrinhoModel;
import java.math.BigDecimal;

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
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imagemJogo = new javax.swing.JLabel();
        nomeJogo = new javax.swing.JLabel();
        precoJogo = new javax.swing.JLabel();
        btnRemoverJogo = new javax.swing.JButton();
        lancadoEm = new javax.swing.JLabel();

        imagemJogo.setText("imagme");

        nomeJogo.setText("nomeJogo");
        nomeJogo.setToolTipText("");

        precoJogo.setText("Preço");

        btnRemoverJogo.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        btnRemoverJogo.setText("Remover");
        btnRemoverJogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRemoverJogoMouseClicked(evt);
            }
        });

        lancadoEm.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lancadoEm.setForeground(new java.awt.Color(153, 153, 153));
        lancadoEm.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagemJogo)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomeJogo)
                    .addComponent(lancadoEm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRemoverJogo)
                    .addComponent(precoJogo))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imagemJogo)
                    .addComponent(nomeJogo)
                    .addComponent(precoJogo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lancadoEm)
                    .addComponent(btnRemoverJogo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
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
