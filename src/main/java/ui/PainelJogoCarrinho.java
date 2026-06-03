package ui;

import model.Jogo;

public class PainelJogoCarrinho extends javax.swing.JPanel {

    public PainelJogoCarrinho(Jogo jogo) {
        initComponents();
        
        nomeJogo.setText(jogo.getDescricao());
        precoJogo.setText("R$ " + jogo.getPrecoPadrao());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imagemJogo = new javax.swing.JLabel();
        nomeJogo = new javax.swing.JLabel();
        precoJogo = new javax.swing.JLabel();
        BtnRemoverJogo = new javax.swing.JButton();

        imagemJogo.setText("imagme");

        nomeJogo.setText("nomeJogo");
        nomeJogo.setToolTipText("");

        precoJogo.setText("Preço");

        BtnRemoverJogo.setText("jButton1");

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
                        .addComponent(BtnRemoverJogo)))
                .addContainerGap(40, Short.MAX_VALUE))
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
                .addComponent(BtnRemoverJogo)
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnRemoverJogo;
    private javax.swing.JLabel imagemJogo;
    private javax.swing.JLabel nomeJogo;
    private javax.swing.JLabel precoJogo;
    // End of variables declaration//GEN-END:variables
}
