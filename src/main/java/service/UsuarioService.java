package service;

import dao.*;
import model.*;


public class UsuarioService {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public boolean LogarSistema(String nomeUsuario, String senhaUsuario){
        if((nomeUsuario == null || nomeUsuario == "") || (senhaUsuario == null || senhaUsuario == "")){
            return false;
        }
        
        return usuarioDAO.BuscarUsuario(new Usuario(nomeUsuario, senhaUsuario));
    }

    public boolean CadastrarUsuario(Usuario usuario) {
        return dao.Cadastrar(usuario);
    }
}
