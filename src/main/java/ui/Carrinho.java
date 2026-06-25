package ui;

import model.Jogo;
import model.Usuario;
import model.CarrinhoModel;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;

import service.CarrinhoService;
import util.Sessao;


public class Carrinho extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Carrinho.class.getName());

    private List<Jogo> itensCarrinho;
    private CarrinhoModel carrinho;
    Usuario usuariologado;
    public Carrinho(List<Jogo> itensCarrinho, CarrinhoModel carrinho) {
        initComponents();
        setLocationRelativeTo(null);
        Sessao sessao = new Sessao();
        
        usuariologado = sessao.getUsuarioLogado();

        this.itensCarrinho = itensCarrinho;
        this.carrinho = carrinho;
        
        tituloCarrinho.setText("Carrinho");
        
        if(this.itensCarrinho != null){
            atualizarCarrinho();
        }
        
        PersonalizarTela();
        pack();
        setLocationRelativeTo(null);
        
    }

    private void atualizarCarrinho() {

        jPanel1.removeAll();
        
        BigDecimal valortotal = BigDecimal.ZERO;
        
        for (Jogo jogo : itensCarrinho) {

            PainelJogoCarrinho painel = new PainelJogoCarrinho(
                    jogo,
                    carrinho,
                    jogoRemovido -> {
                        itensCarrinho.remove(jogoRemovido);
                        atualizarCarrinho();
                    }
                );

            painel.setAlignmentX(LEFT_ALIGNMENT);
            
            jPanel1.add(painel);
            
            jPanel1.add(Box.createVerticalStrut(12));
            
            //calcula o valor total do carrinho
            if(jogo.isEstaEmPromocao()){
                valortotal = valortotal.add(jogo.getPrecoPromocao());
            }
            else{
                valortotal = valortotal.add(jogo.getPrecoPadrao());
            }
        }

        valorTotalCarrinho.setText("Valor Total: R$"+ valortotal);
        
        jPanel1.revalidate();
        jPanel1.repaint();
    }
    
    public void PersonalizarTela(){
        Color fundo = new Color(18, 24, 34);
        Color superficie = new Color(21, 31, 44);
        Color borda = new Color(48, 74, 95);
        Color textoSecundario = new Color(150, 160, 180);
        Color verde = new Color(50, 166, 70);

        getContentPane().setBackground(fundo);
        setMinimumSize(new Dimension(820, 560));
        setPreferredSize(new Dimension(960, 640));

        jPanel2.setBackground(fundo);
        jPanel1.setBackground(superficie);
        jPanel1.setBorder(BorderFactory.createEmptyBorder(18, 24, 18, 24));

        Border linhaDiscreta = BorderFactory.createLineBorder(borda, 1);
        panelCarrinho.setBorder(linhaDiscreta);
        panelCarrinho.setBackground(superficie);
        panelCarrinho.getViewport().setBackground(superficie);
        panelCarrinho.setViewportBorder(null);
        panelCarrinho.getVerticalScrollBar().setUnitIncrement(16);
        personalizarBarraRolagem(panelCarrinho.getVerticalScrollBar(), fundo, borda);
        
        tituloCarrinho.setFont(
            new Font("Segoe UI", Font.BOLD, 28)
        );
        
        tituloCarrinho.setForeground(
            new Color(255, 255, 255)
        );
        
        valorTotalCarrinho.setFont(new Font("Segoe UI", Font.BOLD, 20));
        valorTotalCarrinho.setForeground(verde);

        personalizarBotao(pagarCarrinho, verde, Color.WHITE, new Dimension(184, 42));
        
        btnTelaInicio.setText("Voltar");
        personalizarBotao(btnTelaInicio, new Color(32, 48, 64), textoSecundario, new Dimension(96, 34));
        
       
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

    private void personalizarBarraRolagem(JScrollBar barra, Color trilho, Color polegar) {
        barra.setBackground(trilho);
        barra.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = polegar;
                this.trackColor = trilho;
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return criarBotaoVazio();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return criarBotaoVazio();
            }

            private JButton criarBotaoVazio() {
                JButton botao = new JButton();
                botao.setPreferredSize(new Dimension(0, 0));
                botao.setMinimumSize(new Dimension(0, 0));
                botao.setMaximumSize(new Dimension(0, 0));
                return botao;
            }
        });
    }
    /** 
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCarrinho = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        tituloCarrinho = new javax.swing.JLabel();
        btnTelaInicio = new javax.swing.JButton();
        valorTotalCarrinho = new javax.swing.JLabel();
        pagarCarrinho = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(18, 24, 34));

        panelCarrinho.setBackground(new java.awt.Color(21, 31, 44));

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));
        panelCarrinho.setViewportView(jPanel1);

        tituloCarrinho.setText("jLabel1");

        btnTelaInicio.setText("voltar");
        btnTelaInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTelaInicioMouseClicked(evt);
            }
        });

        valorTotalCarrinho.setText("jLabel1");

        pagarCarrinho.setText("Finalizar Compra");
        pagarCarrinho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pagarCarrinhoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnTelaInicio)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valorTotalCarrinho)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tituloCarrinho)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 438, Short.MAX_VALUE)
                                .addComponent(pagarCarrinho)))
                        .addGap(24, 24, 24))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnTelaInicio)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(tituloCarrinho)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(pagarCarrinho)
                        .addGap(14, 14, 14)))
                .addComponent(valorTotalCarrinho)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCarrinho))
                .addGap(56, 56, 56))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(panelCarrinho, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTelaInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTelaInicioMouseClicked
        this.dispose();
        
        //abre a tela de login
        new Inicio().setVisible(true);
    }//GEN-LAST:event_btnTelaInicioMouseClicked

    private void pagarCarrinhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pagarCarrinhoMouseClicked
        
        //Paga o carrinho
        CarrinhoService service = new CarrinhoService();
        service.ComprarCarrinho(carrinho, usuariologado, itensCarrinho);
        
        this.dispose();
        //abre a tela de login
        new Biblioteca().setVisible(true);
    }


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Carrinho(new ArrayList<>(), new CarrinhoModel()).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTelaInicio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton pagarCarrinho;
    private javax.swing.JScrollPane panelCarrinho;
    private javax.swing.JLabel tituloCarrinho;
    private javax.swing.JLabel valorTotalCarrinho;
    // End of variables declaration//GEN-END:variables
}
