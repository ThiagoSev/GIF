package service;

import dao.*;
import model.*;


public class UsuarioService {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario LogarSistema(String nomeUsuario, String senhaUsuario){

    if ((nomeUsuario == null || nomeUsuario.isEmpty()) ||
        (senhaUsuario == null || senhaUsuario.isEmpty())) {
        
        return null;
    }

    return usuarioDAO.BuscarUsuario(
            new Usuario(nomeUsuario, senhaUsuario));
}
}
