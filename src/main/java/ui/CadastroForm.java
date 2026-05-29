package ui;

import model.Usuario;
import service.UsuarioService;

import java.text.SimpleDateFormat;

import javax.print.DocFlavor.STRING;
import javax.swing.JFrame;

public class CadastroForm extends Jframe {

    public CadastroForm() {
        initComponents();
    }
    
    private void btnCadastrarMouseClicked(java.awt.event.MouseEvent evt) {

        try {

            String nome = txtNome.getText();
            String senha = new String(txtSenha.getPassword());
            String apelido = txtApelido.getText();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            java.util.Date data = sdf.parse(txtDataNascimento.getText());

            Usuario usuario = new Usuario(
                nome, 
                senha,
                apelido,
                data
            );

            UsuarioService service = new UsuarioService();

            boolean resultado = service.cadastrarUsuario(usuario);

            if(resultado) {
                iblMensagem.setText("Usuário cadastrado com sucesso!");
            }
            else {
                iblMensagem.setText("Erro ao cadastrar usuário!");
            }
        } catch (Exception e) {
            iblMensagem.setText("Data Inválida!");
        }
    }
}
