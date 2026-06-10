package util;
import model.Usuario;

public class UsuarioLogadoSingleton {
    private Usuario usuarioLogado;
    
    public Usuario getUsuarioLogado(Usuario usuario){
        return usuarioLogado;
    }
    
    //preenchido no login
    public void setUsuarioLogado(Usuario usuario){
        if(usuarioLogado == null)
            usuarioLogado = usuario;
    }
    
}
