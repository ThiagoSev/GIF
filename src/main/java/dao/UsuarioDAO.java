package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;
import util.Conexao;

public class UsuarioDAO {
    public boolean BuscarUsuario(Usuario usuario){
            String sql = "SELECT * FROM usuario WHERE nome = '" + usuario.getNome() + "' AND senha = '" + usuario.getSenha()+"'";
        
        try (Connection conn = Conexao.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
                        
            return rs.next();
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
