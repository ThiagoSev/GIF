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


/**
 *
 * @author User
 */
public class TelaJogos extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaJogos.class.getName());
    private Usuario usuarioLogado;
    /**
     * Creates new form TelaJogos
     */
    public TelaJogos(Usuario usuarioLogado) {
        initComponents();
        
        this.usuarioLogado = usuarioLogado;

    btnAdicionar.setVisible(usuarioLogado.isAdministrador());
    btnRemover.setVisible(usuarioLogado.isAdministrador());
    
 }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnAdicionar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        btnAdicionar.setText("Adicionar jogo");
        btnAdicionar.addActionListener(this::btnAdicionarActionPerformed);

        btnRemover.setText("Remover Jogo");
        btnRemover.addActionListener(this::btnRemoverActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(btnAdicionar)
                        .addGap(109, 109, 109)
                        .addComponent(btnRemover)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar)
                    .addComponent(btnRemover))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
try {

        String titulo = JOptionPane.showInputDialog(this,
                "Título do jogo:");

        String subtitulo = JOptionPane.showInputDialog(this,
                "Subtítulo:");

        String descricao = JOptionPane.showInputDialog(this,
                "Descrição:");

        String preco = JOptionPane.showInputDialog(this,
                "Preço:");

        Jogo jogo = new Jogo();

        jogo.setTitulo(titulo);
        jogo.setSubtitulo(subtitulo);
        jogo.setDescricao(descricao);
        jogo.setPrecoPadrao(new BigDecimal(preco));

        jogo.setPrecoPromocao(null);
        jogo.setEstaEmPromocao(false);

        jogo.setDataLancamento(LocalDateTime.now());

        jogo.setIdDistribuidor(1);
        jogo.setIdCriador(1);

        JogoDAO dao = new JogoDAO();
        boolean result = dao.salvar(jogo, usuarioLogado);
        if(result)
            JOptionPane.showMessageDialog(this,
                "Jogo cadastrado com sucesso!");
        else
            JOptionPane.showMessageDialog(this,
                "Erro ao cadastrar jogo");


    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao cadastrar: " + e.getMessage());
    }
    }//GEN-LAST:event_btnAdicionarActionPerformed

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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      Usuario admin = new Usuario(
        1,
        "admin",
        "123",
        true
    );

    java.awt.EventQueue.invokeLater(() ->
        new TelaJogos(admin).setVisible(true)
    );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
